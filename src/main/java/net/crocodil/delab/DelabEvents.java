package net.crocodil.delab;


import io.netty.util.internal.ThreadLocalRandom;
import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.enchants.DelabEnchantmentHelper;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DelabEvents {

    @SubscribeEvent
    public static void DoubleStrikeEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();
            if ((direct instanceof Player player)) {
                ItemStack main = player.getMainHandItem();
                ItemStack off = player.getOffhandItem();
                TagKey<Item> daggerTag = DelabTags.Items.DAGGERS_ENCHANTABLE;
                if (main.is(daggerTag) && off.is(daggerTag)) {
                    int lvlMain = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                            DelabEnchantments.DOUBLE_STRIKE,
                            main);
                    int lvlOff = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                            DelabEnchantments.DOUBLE_STRIKE,
                            off);
                    int lvl = lvlMain + lvlOff;
                    if (lvl >= 0) {
                        float OrigDmg = event.getOriginalDamage();
                        float NewDmg = OrigDmg + 0.5F * lvl;
                        event.setNewDamage(NewDmg);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void HeadHunterEvent(LivingDropsEvent event)
    {
        if (!event.getEntity().level().isClientSide)
        {
            if ((event.getSource().getEntity() instanceof Player player)) {
                ItemStack DaggerMain = player.getMainHandItem();
                ItemStack DaggerOff = player.getOffhandItem();
                boolean IsCanWork = false;
                if(DaggerOff.is(DelabTags.Items.DAGGERS_ENCHANTABLE)) {
                    if (DaggerMain.is(DelabTags.Items.DAGGERS_ENCHANTABLE))
                        IsCanWork = true;
                }
                else if (DaggerMain.is(DelabTags.Items.DAGGERS_ENCHANTABLE))
                    IsCanWork = true;

                if(IsCanWork)
                {
                    int MainLvl = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                            DelabEnchantments.HEAD_HUNTER, DaggerMain);
                    int OffLvl = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                            DelabEnchantments.HEAD_HUNTER, DaggerOff);
                    int HHLvl = Math.max(MainLvl, OffLvl);
                    int percent = Math.min(100, HHLvl * 5);
                    event.getDrops().removeIf(ItemEntity -> ItemEntity.getItem().is(Items.WITHER_SKELETON_SKULL));
                    if((ThreadLocalRandom.current().nextInt(100) < percent))
                    {
                        ItemStack head = DelabEnchantmentHelper.getHeadForEntity(event.getEntity());
                        if(!head.isEmpty())
                        {
                            event.getDrops().add(new ItemEntity(
                                    event.getEntity().level(),
                                    event.getEntity().getX(),
                                    event.getEntity().getY(),
                                    event.getEntity().getZ(),
                                    head
                            ));
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void ShadowStrikeDamageEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();
            if ((direct instanceof Player player)) {
                ItemStack DaggerMain = player.getMainHandItem();
                ItemStack DaggerOff = player.getOffhandItem();
                boolean IsCanWork = false;
                if(DaggerOff.is(DelabTags.Items.DAGGERS_ENCHANTABLE)) {
                    if (DaggerMain.is(DelabTags.Items.DAGGERS_ENCHANTABLE))
                        IsCanWork = true;
                }
                else if (DaggerMain.is(DelabTags.Items.DAGGERS_ENCHANTABLE))
                    IsCanWork = true;
                int MainLvl = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                        DelabEnchantments.SHADOW_STRIKE, DaggerMain);
                int OffLvl = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                        DelabEnchantments.SHADOW_STRIKE, DaggerOff);
                int SSLvl = Math.max(MainLvl, OffLvl);
                if(IsCanWork && SSLvl > 0 && player.hasEffect(DelabMobEffects.SHADOW_STRIKE))
                {
                    float DmgMultiplier = 1 + SSLvl * 0.5F;
                    float NewDmg = event.getOriginalDamage() * DmgMultiplier;
                    event.setNewDamage(NewDmg);
                }
            }
        }
    }
    @SubscribeEvent
    public static void ShadowStrikeDRemoveAfterDamage(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();

            if (direct != null)
            {
                if(direct instanceof LivingEntity livingEntity)
                    livingEntity.removeEffect(DelabMobEffects.SHADOW_STRIKE);
            }
            LivingEntity target = event.getEntity();
            target.removeEffect(DelabMobEffects.SHADOW_STRIKE);
        }
    }

}