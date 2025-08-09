package net.crocodil.delab;

import net.crocodil.delab.enchants.DelabEnchantmentHelper;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Map;

@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DelabEvents
{
    @SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide)
        {
            Entity direct = event.getSource().getDirectEntity();
            if ((direct instanceof Player player))
            {
                ItemStack main = player.getMainHandItem();
                ItemStack off  = player.getOffhandItem();
                TagKey<Item> daggerTag = DelabTags.Items.DAGGERS_ENCHANTABLE;
                if (main.is(daggerTag) && off.is(daggerTag))
                {
                    int lvlMain = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                            DelabEnchantments.DOUBLE_STRIKE,
                            main);
                    int lvlOff = DelabEnchantmentHelper.getEnchantmentLvl(player.level(),
                            DelabEnchantments.DOUBLE_STRIKE,
                            off);
                    int lvl = lvlMain + lvlOff;
                    if(lvl >= 0) {
                        float OrigDmg = event.getOriginalDamage();
                        float NewDmg = OrigDmg + 0.5F * lvl + 1;
                        event.setNewDamage(NewDmg);
                    }
                }
            }
        }
    }
}