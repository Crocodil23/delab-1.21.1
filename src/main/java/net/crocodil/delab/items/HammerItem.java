package net.crocodil.delab.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends PickaxeItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
    private static final ResourceLocation BASE_ATTACK_KNOCKBACK_ID =
             ResourceLocation.parse("delab:base_attack_knockback");
    public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                                (double)(attackDamage + tier.getAttackDamageBonus()),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID,
                                (double)attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(BASE_ATTACK_KNOCKBACK_ID, 1,AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .build();
    }
    public static List<BlockPos> getBlocksToBeDestroyed(BlockPos pos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if(traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        if(traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for(int x = -1; x <= 1; x++) {
                for(int y = -1; y <= 1; y++) {
                    positions.add(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + y));
                }
            }
        }

        if(traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for(int x = -1; x <= 1; x++) {
                for(int y = -1; y <= 1; y++) {
                    positions.add(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ()));
                }
            }
        }

        if(traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for(int x = -1; x <= 1; x++) {
                for(int y = -1; y <= 1; y++) {
                    positions.add(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ() + x));
                }
            }
        }

        return positions;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float base = super.getDestroySpeed(stack, state);
        return base * 0.70F;
    }
}
