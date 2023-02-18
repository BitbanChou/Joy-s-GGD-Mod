package net.joy187.joyggd.block;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.block.blockentity.StoveBlockEntity;
import net.joy187.joyggd.init.BlockEntityInit;
import net.joy187.joyggd.init.SoundInit;
import net.joy187.joyggd.util.ItemUtils;
import net.joy187.joyggd.util.MathUtils;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StoveBlock extends ContainerBlock
{
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private final boolean spawnParticles;
    private final int fireDamage;

    public StoveBlock(boolean p_i241174_1_, int p_i241174_2_, AbstractBlock.Properties p_i241174_3_) {
        super(p_i241174_3_);
        this.spawnParticles = p_i241174_1_;
        this.fireDamage = p_i241174_2_;
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.valueOf(true)).setValue(FACING, Direction.NORTH));
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult p_225533_6_) {
        TileEntity tileentity = level.getBlockEntity(pos);
        //System.out.println("使用");
        if (tileentity instanceof StoveBlockEntity) {
            StoveBlockEntity et = (StoveBlockEntity)tileentity;
            int stoveSlot = et.getNextEmptySlot();
            //System.out.println("stoveSlot:"+stoveSlot);
            if (stoveSlot < 0 || et.isStoveBlockedAbove()) {
                //System.out.println("0");
                return ActionResultType.PASS;
            }
//            CampfireTileEntity campfiretileentity = (CampfireTileEntity)tileentity;
            //ItemStack itemstack = player.getItemInHand(p_225533_5_);
            ItemStack heldStack = player.getItemInHand(hand);
            Item heldItem = heldStack.getItem();
            //Optional<CampfireCookingRecipe> optional = campfiretileentity.getCookableRecipe(itemstack);

            if (heldItem == Items.EMERALD) {
                //System.out.println("111");
                if (!level.isClientSide && et.addItem(player.abilities.instabuild ? heldStack.copy() : heldStack, stoveSlot)) {
                    //System.out.println("1");
                    return ActionResultType.SUCCESS;
                }
                level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundInit.DRAW.get(), SoundCategory.BLOCKS, 0.5F, 2.6F, false);
                return ActionResultType.CONSUME;
            }
        }
        //System.out.println("2");
        return ActionResultType.PASS;
    }

//    public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
//        if (!p_196262_4_.fireImmune() && p_196262_1_.getValue(LIT) && p_196262_4_ instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)p_196262_4_)) {
//            p_196262_4_.hurt(DamageSource.IN_FIRE, (float)this.fireDamage);
//        }
//
//        super.entityInside(p_196262_1_, p_196262_2_, p_196262_3_, p_196262_4_);
//    }

//    public void onRemove(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
//        if (!p_196243_1_.is(p_196243_4_.getBlock())) {
//            TileEntity tileentity = p_196243_2_.getStoveBlockEntity(p_196243_3_);
//            if (tileentity instanceof CampfireTileEntity) {
//                InventoryHelper.dropContents(p_196243_2_, p_196243_3_, ((CampfireTileEntity)tileentity).getItems());
//            }
//
//            super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
//        }
//    }

    @Override
    public void onRemove(BlockState state, World level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof StoveBlockEntity) {
                //ItemUtils.dropItems(level, pos, ((StoveStoveBlockEntity) tileEntity).inventory);
                InventoryHelper.dropContents(level, pos, ((StoveBlockEntity)tileEntity).getItems());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(LIT, true);
    }

    private boolean isSmokeSource(BlockState p_220099_1_) {
        return p_220099_1_.is(Blocks.HAY_BLOCK);
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World p_180655_2_, BlockPos pos, Random rand) {
//        if (p_180655_1_.getValue(LIT)) {
//            if (p_180655_4_.nextInt(10) == 0) {
//                p_180655_2_.playLocalSound((double)p_180655_3_.getX() + 0.5D, (double)p_180655_3_.getY() + 0.5D, (double)p_180655_3_.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + p_180655_4_.nextFloat(), p_180655_4_.nextFloat() * 0.7F + 0.6F, false);
//            }
//
//            if (this.spawnParticles && p_180655_4_.nextInt(5) == 0) {
//                for(int i = 0; i < p_180655_4_.nextInt(1) + 1; ++i) {
//                    p_180655_2_.addParticle(ParticleTypes.LAVA, (double)p_180655_3_.getX() + 0.5D, (double)p_180655_3_.getY() + 0.5D, (double)p_180655_3_.getZ() + 0.5D, (double)(p_180655_4_.nextFloat() / 2.0F), 5.0E-5D, (double)(p_180655_4_.nextFloat() / 2.0F));
//                }
//            }
//
//        }
        if (stateIn.getValue(LIT)) {
            double x = (double) pos.getX() + 0.5D;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5D;
//            if (rand.nextInt(10) == 0) {
//                level.playLocalSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
//            }

            Direction direction = stateIn.getValue(BlockStateProperties.HORIZONTAL_FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double horizontalOffset = rand.nextDouble() * 0.6D - 0.3D;
            double xOffset = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : horizontalOffset;
            double yOffset = rand.nextDouble() * 6.0D / 16.0D;
            double zOffset = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : horizontalOffset;
//            level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
//            level.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
        }
    }

//    public static void dowse(IWorld p_235475_0_, BlockPos p_235475_1_, BlockState p_235475_2_) {
//        if (p_235475_0_.isClientSide()) {
//            for(int i = 0; i < 20; ++i) {
//                makeParticles((World)p_235475_0_, p_235475_1_, p_235475_2_.getValue(SIGNAL_FIRE), true);
//            }
//        }
//
//        TileEntity tileentity = p_235475_0_.getStoveBlockEntity(p_235475_1_);
//        if (tileentity instanceof CampfireTileEntity) {
//            ((CampfireTileEntity)tileentity).dowse();
//        }
//
//    }

//    public boolean placeLiquid(IWorld p_204509_1_, BlockPos p_204509_2_, BlockState p_204509_3_, FluidState p_204509_4_) {
//        if (!p_204509_3_.getValue(BlockStateProperties.WATERLOGGED) && p_204509_4_.getType() == Fluids.WATER) {
//            boolean flag = p_204509_3_.getValue(LIT);
//            if (flag) {
//                if (!p_204509_1_.isClientSide()) {
//                    p_204509_1_.playSound((PlayerEntity)null, p_204509_2_, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                }
//
//                dowse(p_204509_1_, p_204509_2_, p_204509_3_);
//            }
//
//            p_204509_1_.setBlock(p_204509_2_, p_204509_3_.setValue(WATERLOGGED, Boolean.valueOf(true)).setValue(LIT, Boolean.valueOf(false)), 3);
//            p_204509_1_.getLiquidTicks().scheduleTick(p_204509_2_, p_204509_4_.getType(), p_204509_4_.getType().getTickDelay(p_204509_1_));
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public void onProjectileHit(World p_220066_1_, BlockState p_220066_2_, BlockRayTraceResult p_220066_3_, ProjectileEntity p_220066_4_) {
//        if (!p_220066_1_.isClientSide && p_220066_4_.isOnFire()) {
//            Entity entity = p_220066_4_.getOwner();
//            boolean flag = entity == null || entity instanceof PlayerEntity || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(p_220066_1_, entity);
//            if (flag && !p_220066_2_.getValue(LIT) && !p_220066_2_.getValue(WATERLOGGED)) {
//                BlockPos blockpos = p_220066_3_.getBlockPos();
//                p_220066_1_.setBlock(blockpos, p_220066_2_.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
//            }
//        }
//
//    }

    public static void makeParticles(World p_220098_0_, BlockPos p_220098_1_, boolean p_220098_2_, boolean p_220098_3_) {
        Random random = p_220098_0_.getRandom();
        BasicParticleType basicparticletype = p_220098_2_ ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
        p_220098_0_.addAlwaysVisibleParticle(basicparticletype, true, (double)p_220098_1_.getX() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), (double)p_220098_1_.getY() + random.nextDouble() + random.nextDouble(), (double)p_220098_1_.getZ() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
        if (p_220098_3_) {
            p_220098_0_.addParticle(ParticleTypes.SMOKE, (double)p_220098_1_.getX() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), (double)p_220098_1_.getY() + 0.4D, (double)p_220098_1_.getZ() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
        }

    }

//    public static boolean isSmokeyPos(World p_235474_0_, BlockPos p_235474_1_) {
//        for(int i = 1; i <= 5; ++i) {
//            BlockPos blockpos = p_235474_1_.below(i);
//            BlockState blockstate = p_235474_0_.getBlockState(blockpos);
//            if (isLitCampfire(blockstate)) {
//                return true;
//            }
//
//            boolean flag = VoxelShapes.joinIsNotEmpty(VIRTUAL_FENCE_POST, blockstate.getCollisionShape(p_235474_0_, blockpos, ISelectionContext.empty()), IBooleanFunction.AND);//Forge fix: MC-201374
//            if (flag) {
//                BlockState blockstate1 = p_235474_0_.getBlockState(blockpos.below());
//                return isLitCampfire(blockstate1);
//            }
//        }
//
//        return false;
//    }

//    public static boolean isLitCampfire(BlockState p_226915_0_) {
//        return p_226915_0_.hasProperty(LIT) && p_226915_0_.is(BlockTags.CAMPFIRES) && p_226915_0_.getValue(LIT);
//    }

//    public FluidState getFluidState(BlockState p_204507_1_) {
//        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
//    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(LIT, FACING);
    }

    public TileEntity newStoveBlockEntity(IBlockReader p_196283_1_) {
        return new StoveBlockEntity();
    }

//    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
//        return false;
//    }

    //    public static boolean canLight(BlockState p_241470_0_) {
//        return p_241470_0_.is(BlockTags.CAMPFIRES, (p_241469_0_) -> {
//            return p_241469_0_.hasProperty(BlockStateProperties.WATERLOGGED) && p_241469_0_.hasProperty(BlockStateProperties.LIT);
//        }) && !p_241470_0_.getValue(BlockStateProperties.WATERLOGGED) && !p_241470_0_.getValue(BlockStateProperties.LIT);
//    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("tooltip.gashapon").withStyle(TextFormatting.GRAY));
        super.appendHoverText(stack, world, list, flag);
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new StoveBlockEntity();
    }
}