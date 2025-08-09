package net.crocodil.delab;


import io.netty.util.internal.ThreadLocalRandom;
import net.crocodil.delab.enchants.DelabEnchantmentHelper;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;






@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DelabEvents {


    @SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Pre event) {
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
    public static void onLivingDrops(LivingDropsEvent event)
    {
        if (!event.getEntity().level().isClientSide)
        {
            if ((event.getSource().getEntity() instanceof Player player))
            {
                ItemStack dagger = player.getMainHandItem();

                if(dagger.is(DelabTags.Items.DAGGERS_ENCHANTABLE))
                {
                    int hhlvl = DelabEnchantmentHelper.getEnchantmentLvl
                            (player.level(), DelabEnchantments.HEAD_HUNTER, dagger);
                    int percent = Math.min(100, hhlvl * 100);
                    if((ThreadLocalRandom.current().nextInt(100) < percent))
                    {
                        if(hhlvl > 0)
                        {
                            event.getDrops().removeIf(ItemEntity -> ItemEntity.getItem().is(Items.WITHER_SKELETON_SKULL));
                        }
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
}