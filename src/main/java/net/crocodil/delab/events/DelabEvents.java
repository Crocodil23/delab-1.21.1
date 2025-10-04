package net.crocodil.delab.events;


import io.netty.util.internal.ThreadLocalRandom;
import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.enchants.DelabEnchantmentHelper;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.crocodil.delab.entity.DelabEntityTypes;
import net.crocodil.delab.entity.Mudaur;
import net.crocodil.delab.items.DelabArmorMaterials;
import net.crocodil.delab.items.DelabItems;
import net.crocodil.delab.items.Hammers.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.effects.AllOf;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.Set;



@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DelabEvents {

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    private static boolean isDamageBlocked(DamageContainer container) {
        return (container.getOriginalDamage() <= container.getShieldDamage());
    }
    private static float getNewRealDamage(DamageContainer container, float addDmg) {
        float origDmg = container.getOriginalDamage();
        float newDmg = container.getNewDamage();
        return container.getNewDamage() + addDmg * (newDmg / origDmg);
    }

    @SubscribeEvent
    public static void DoubleStrikeEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            if(!isDamageBlocked(event.getContainer())) {
                Entity direct = event.getSource().getDirectEntity();
                if ((direct instanceof LivingEntity living)) {
                    ItemStack main = living.getMainHandItem();
                    ItemStack off = living.getOffhandItem();
                    TagKey<Item> daggerTag = DelabTags.Items.DAGGER_ENCHANTABLE;
                    if (main.is(daggerTag) && off.is(daggerTag)) {
                        int lvlMain = DelabEnchantmentHelper.getEnchantmentLvl(living.level(),
                                DelabEnchantments.DOUBLE_STRIKE,
                                main);
                        int lvlOff = DelabEnchantmentHelper.getEnchantmentLvl(living.level(),
                                DelabEnchantments.DOUBLE_STRIKE,
                                off);
                        int lvl = lvlMain + lvlOff;
                        if (lvl >= 0) {
                            float addDmg = 0.5F * lvl;
                            event.setNewDamage(getNewRealDamage(event.getContainer(), addDmg));
                        }
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
            if(!isDamageBlocked(event.getContainer())) {
                if ((direct instanceof LivingEntity living)) {
                    ItemStack DaggerMain = living.getMainHandItem();
                    ItemStack DaggerOff = living.getOffhandItem();
                    boolean IsCanWork = false;
                    if (DaggerOff.is(DelabTags.Items.DAGGER_ENCHANTABLE)) {
                        if (DaggerMain.is(DelabTags.Items.DAGGER_ENCHANTABLE))
                            IsCanWork = true;
                    } else if (DaggerMain.is(DelabTags.Items.DAGGER_ENCHANTABLE))
                        IsCanWork = true;
                    int MainLvl = DelabEnchantmentHelper.getEnchantmentLvl(living.level(),
                            DelabEnchantments.SHADOW_STRIKE, DaggerMain);
                    int OffLvl = DelabEnchantmentHelper.getEnchantmentLvl(living.level(),
                            DelabEnchantments.SHADOW_STRIKE, DaggerOff);
                    int SSLvl = Math.max(MainLvl, OffLvl);
                    if (IsCanWork && SSLvl > 0 && living.hasEffect(DelabMobEffects.SHADOW_STRIKE)) {
                        float DmgMultiplier = 1 + SSLvl * 0.5F;
                        event.setNewDamage(event.getNewDamage() * DmgMultiplier);
                    }
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
            if(!isDamageBlocked(event.getContainer())) {
                Entity direct = event.getSource().getDirectEntity();
                if ((direct instanceof LivingEntity living)) {
                    ItemStack spear = living.getMainHandItem();
                    TagKey<Item> spearTag = DelabTags.Items.SPEAR_ENCHANTABLE;
                    if (spear.is(spearTag) && living.isPassenger()) {
                        int CSlvl = DelabEnchantmentHelper.getEnchantmentLvl(living.level(),
                                DelabEnchantments.CAVALRY_STRIKE,
                                spear);
                        if (CSlvl >= 0) {
                            float addDmg = 1.5F * CSlvl;
                            event.setNewDamage(getNewRealDamage(event.getContainer(), addDmg));
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void FrozenAxeEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            if (!isDamageBlocked(event.getContainer())) {
                Entity direct = event.getSource().getDirectEntity();
                if ((direct instanceof LivingEntity living)) {
                    boolean canAdd = true;
                    if(living.getMainHandItem().is(DelabItems.FROZEN_AXE))
                    {
                        if(direct instanceof Player player)
                            if(player.getAttackStrengthScale(0.5F) < 1)
                                canAdd = false;
                    }
                    else
                        canAdd = false;
                    if(canAdd)
                        DelabMobEffects.addMagicalFrostMobeEffect(event.getEntity(), living, 160);
                }
                if(event.getEntity().getTicksFrozen() >= 140)
                    event.setNewDamage(getNewRealDamage(event.getContainer(), 2));
            }
        }
    }

    @SubscribeEvent
    public static void MudDamageBonusEvent(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            if(!isDamageBlocked(event.getContainer())) {
                Entity direct = event.getSource().getDirectEntity();
                LivingEntity living = event.getEntity();
                if ((direct instanceof LivingEntity direct_living)
                        && living.hasEffect(DelabMobEffects.IN_MUD)) {
                    ItemStack hammer = direct_living.getMainHandItem();
                    float addDmg = 0;
                    if (hammer.is(DelabItems.ABOMINATION_HAMMER))
                        addDmg +=2;
                    else if(direct_living instanceof Mudaur)
                        addDmg += 1;
                    event.setNewDamage(getNewRealDamage(event.getContainer(), addDmg));
                }
            }

        }
    }
    @SubscribeEvent
    public static void ArmorDamageBonusEffect(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            if(!isDamageBlocked(event.getContainer())) {
                Entity direct = event.getSource().getDirectEntity();
                if ((direct instanceof LivingEntity direct_living)) {
                    ItemStack weapom = direct_living.getMainHandItem();
                    float addDmg = 0F;
                    if (weapom.is(ItemTags.AXES) &&
                            DelabArmorMaterials.isFullSetOff(DelabArmorMaterials.FROZEN, direct_living)) {
                        addDmg += 0.5F;
                    }
                    if (weapom.is(DelabTags.Items.HAMMER_ENCHANTABLE) &&
                            DelabArmorMaterials.isFullSetOff(DelabArmorMaterials.ABOMINATION, direct_living)) {
                        addDmg += 0.5F;
                    }
                    event.setNewDamage(getNewRealDamage(event.getContainer(), addDmg));
                }
            }
        }
    }
    @SubscribeEvent
    public static void AbominationBowDamageBonus(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide) {
            Entity direct = event.getSource().getDirectEntity();
            LivingEntity living = event.getEntity();
            if ((direct instanceof AbstractArrow arrow)) {
                if(arrow.getOwner() instanceof LivingEntity owner)
                {
                    ItemStack bow = owner.getMainHandItem();
                    float addDmg = 0.0F;
                    if (bow.is(DelabItems.ABOMINATION_BOW) && living.hasEffect(DelabMobEffects.IN_MUD))
                        addDmg += 2.0F;

                    else if(bow.is(DelabItems.FROZEN_BOW) && living.getTicksFrozen() >= 140) {
                        addDmg += 2;
                    }

                    if(addDmg > 0)
                        event.setNewDamage(getNewRealDamage(event.getContainer(), addDmg));
                    System.out.println(event.getOriginalDamage());
                    System.out.println(event.getNewDamage());
                }

            }
        }
    }
    @SubscribeEvent
    public static void mudaurSpawn(EntityJoinLevelEvent event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return;

        var entity = event.getEntity();

        if (!(entity instanceof Zombie)) return;

        if (entity.getType() == DelabEntityTypes.MUDAUR.get()) return;
        if (!(level instanceof ServerLevel serverLevel)) return;

        BlockPos pos = entity.blockPosition();
        if (!level.getBiome(pos).is(Tags.Biomes.IS_SWAMP)) return;
        if (entity.tickCount > 0) return;

        if (serverLevel.getRandom().nextFloat() < 0.8f) {
            event.setCanceled(true);

            Mob mudaur = DelabEntityTypes.MUDAUR.get().create(serverLevel);

            if (mudaur == null) return;

            mudaur.moveTo(entity.getX(), entity.getY(), entity.getZ(),
                    entity.getYRot(), entity.getXRot());

            mudaur.finalizeSpawn(serverLevel,
                    serverLevel.getCurrentDifficultyAt(mudaur.blockPosition()),
                    MobSpawnType.NATURAL,
                    null
            );

            serverLevel.addFreshEntity(mudaur);
        }
    }

}
