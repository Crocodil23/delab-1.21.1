package net.crocodil.delab.events;


import io.netty.util.internal.ThreadLocalRandom;
import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.enchants.DelabEnchantmentHelper;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.crocodil.delab.items.DelabArmorMaterials;
import net.crocodil.delab.items.DelabItems;
import net.crocodil.delab.items.Hammers.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DelabEvents {

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void DoubleStrikeEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();
            if ((direct instanceof Player player)) {
                ItemStack main = player.getMainHandItem();
                ItemStack off = player.getOffhandItem();
                TagKey<Item> daggerTag = DelabTags.Items.DAGGER_ENCHANTABLE;
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
                if(DaggerOff.is(DelabTags.Items.DAGGER_ENCHANTABLE)) {
                    if (DaggerMain.is(DelabTags.Items.DAGGER_ENCHANTABLE))
                        IsCanWork = true;
                }
                else if (DaggerMain.is(DelabTags.Items.DAGGER_ENCHANTABLE))
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
                if(DaggerOff.is(DelabTags.Items.DAGGER_ENCHANTABLE)) {
                    if (DaggerMain.is(DelabTags.Items.DAGGER_ENCHANTABLE))
                        IsCanWork = true;
                }
                else if (DaggerMain.is(DelabTags.Items.DAGGER_ENCHANTABLE))
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
    @SubscribeEvent
    public static void DestroyerEvent(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        int lvl = DelabEnchantmentHelper.getEnchantmentLvl(player.level(), DelabEnchantments.DESTROYER, mainHandItem);
        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer
        && lvl > 0) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed( initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
    @SubscribeEvent
    public static void CavalryStrikeEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();
            if ((direct instanceof Player player)) {
                ItemStack spear = player.getMainHandItem();
                TagKey<Item> spearTag = DelabTags.Items.SPEAR_ENCHANTABLE;
                if (spear.is(spearTag) && player.isPassenger()) {
                    int CSlvl = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                            DelabEnchantments.CAVALRY_STRIKE,
                            spear);
                    if (CSlvl >= 0) {
                        float OrigDmg = event.getOriginalDamage();
                        float NewDmg = OrigDmg + 1.5F * CSlvl;
                        event.setNewDamage(NewDmg);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void HammerMudBonusEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();
            Entity entity = event.getEntity();
            if ((direct instanceof Player player && entity instanceof LivingEntity living)) {
                ItemStack hammer = player.getMainHandItem();
                if (hammer.is(DelabItems.ABOMINATION_HAMMER) && living.hasEffect(DelabMobEffects.IN_MUD)) {
                    float OrigDmg = event.getOriginalDamage();
                    float NewDmg = OrigDmg + 2;
                    event.setNewDamage(NewDmg);
                }
            }
        }
    }
    @SubscribeEvent
    public static void ArmorDamageBonusEffect(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();
            Entity entity = event.getEntity();
            if ((direct instanceof Player player && entity instanceof LivingEntity)) {
                ItemStack hammer = player.getMainHandItem();
                if (hammer.is(DelabTags.Items.HAMMER_ENCHANTABLE) &&
                        DelabArmorMaterials.isFullSetOff(DelabArmorMaterials.ABOMINATION, player)) {
                    float OrigDmg = event.getOriginalDamage();
                    float NewDmg = OrigDmg + 0.5F;
                    event.setNewDamage(NewDmg);
                }
            }
        }
    }

}
