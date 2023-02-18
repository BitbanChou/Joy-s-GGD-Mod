package net.joy187.joyggd.block.blockentity;

import net.joy187.joyggd.block.Stove2Block;
import net.joy187.joyggd.block.StoveBlock;
import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.BlockEntityInit;
import net.joy187.joyggd.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;


public class Stove2BlockEntity extends TileEntity implements IClearable, ITickableTileEntity
{
    private static final VoxelShape GRILLING_AREA = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 25.0F, 16.0F);
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    private final int[] cookingProgress = new int[1];
    private final int[] cookingTime = new int[1];

    public Stove2BlockEntity() {
        super(BlockEntityInit.STOVE2.get());
    }

    public void tick() {
        boolean flag = this.getBlockState().getValue(Stove2Block.LIT);
        boolean flag1 = this.level.isClientSide;
        if (flag1) {
            if (flag) {
                this.makeParticles();
            }

        } else {
            if (flag) {
                this.cook();
            } else {
                for(int i = 0; i < this.items.size(); ++i) {
                    if (this.cookingProgress[i] > 0) {
                        this.cookingProgress[i] = MathHelper.clamp(this.cookingProgress[i] - 2, 0, this.cookingTime[i]);
                    }
                }
            }

        }
    }

    private void cook() {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (!itemstack.isEmpty()) {
                int j = this.cookingProgress[i]++;
                if (this.cookingProgress[i] >= ModConfigs.Client.drawTimesTotal.get()) {
//                    IInventory iinventory = new Inventory(itemstack);
//                    ItemStack itemstack1 = this.level.getRecipeManager().getRecipeFor(IRecipeType.CAMPFIRE_COOKING, iinventory, this.level).map((p_213979_1_) -> {
//                        return p_213979_1_.assemble(iinventory);
//                    }).orElse(itemstack);

                    ItemStack resultStack = null;
                    Random ran = new Random();
                    int co=ran.nextInt(120);
                    if(co<5) resultStack= new ItemStack(ItemInit.CAR.get());
                    else if(co>=5 && co<10) resultStack= new ItemStack(ItemInit.MERMAID.get());
                    else if(co>=10 && co<=15) resultStack= new ItemStack(ItemInit.LANTERN.get());
                    else if(co>=20 && co<=25) resultStack= new ItemStack(ItemInit.DROOL.get());
                    else if(co>25 && co<=30) resultStack= new ItemStack(ItemInit.CHANDLIER.get());
                    else if(co>30 && co<35) resultStack= new ItemStack(ItemInit.MERMAIDB.get());
                    else if(co>=35 && co<=40) resultStack= new ItemStack(ItemInit.GUITARPANTS.get());
                    else if(co>=45 && co<=50) resultStack= new ItemStack(ItemInit.NV.get());
                    else if(co>=51 && co<53) resultStack= new ItemStack(Items.HEART_OF_THE_SEA);
                    else if(co>=53 && co<55) resultStack= new ItemStack(Items.NETHER_STAR);
                    else if(co>=55 && co<=60) resultStack= new ItemStack(ItemInit.PEACE.get());
                    else if(co>=61 && co<70) resultStack= new ItemStack(Items.DIAMOND);
                    else if(co>=70 && co<75) resultStack= new ItemStack(ItemInit.SPACESHIP.get());
                    else if(co>=75 && co<=85) resultStack= new ItemStack(Items.LAPIS_LAZULI);
                    else if(co>85 && co<90) resultStack= new ItemStack(ItemInit.GUITAR.get());
                    else if(co>=90 && co<95) resultStack= new ItemStack(Items.TOTEM_OF_UNDYING);
                    else if(co>=95 && co<100) resultStack= new ItemStack(ItemInit.MAID.get());
                    else if(co>=100 && co<105) resultStack= new ItemStack(ItemInit.DRESS.get());
                    else if(co>=105 && co<110) resultStack= new ItemStack(ItemInit.BOXHEAD.get());
                    else if(co>=110 && co<113) resultStack= new ItemStack(Items.NETHERITE_INGOT);
                    else if(co>=113) resultStack= new ItemStack(ItemInit.SLACK.get());
                    else resultStack= new ItemStack(Items.IRON_INGOT);

                    BlockPos blockpos = this.getBlockPos();
                    InventoryHelper.dropItemStack(this.level, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), resultStack);
                    this.items.set(i, ItemStack.EMPTY);
                    this.markUpdated();
                }
            }
        }

    }

    private void makeParticles() {
        World world = this.getLevel();
        if (world != null) {
            BlockPos blockpos = this.getBlockPos();
            Random random = world.random;
//            if (random.nextFloat() < 0.11F) {
//                for(int i = 0; i < random.nextInt(2) + 2; ++i) {
//                    StoveBlock.makeParticles(world, blockpos, this.getBlockState().getValue(StoveBlock.SIGNAL_FIRE), false);
//                }
//            }

            int l = this.getBlockState().getValue(Stove2Block.FACING).get2DDataValue();

            for(int j = 0; j < this.items.size(); ++j) {
                if (!this.items.get(j).isEmpty() && random.nextFloat() < 0.2F) {
                    Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
                    float f = 0.3125F;
                    double d0 = (double)blockpos.getX() + 0.5D - (double)((float)direction.getStepX() * 0.3125F) + (double)((float)direction.getClockWise().getStepX() * 0.3125F);
                    double d1 = (double)blockpos.getY() + 0.5D;
                    double d2 = (double)blockpos.getZ() + 0.5D - (double)((float)direction.getStepZ() * 0.3125F) + (double)((float)direction.getClockWise().getStepZ() * 0.3125F);

                    for(int k = 0; k < 4; ++k) {
                        world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 5.0E-4D, 0.0D);
                    }
                }
            }

        }
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
        this.items.clear();
        ItemStackHelper.loadAllItems(p_230337_2_, this.items);
        if (p_230337_2_.contains("CookingTimes", 11)) {
            int[] aint = p_230337_2_.getIntArray("CookingTimes");
            System.arraycopy(aint, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, aint.length));
        }

        if (p_230337_2_.contains("CookingTotalTimes", 11)) {
            int[] aint1 = p_230337_2_.getIntArray("CookingTotalTimes");
            System.arraycopy(aint1, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, aint1.length));
        }

    }

    public CompoundNBT save(CompoundNBT p_189515_1_) {
        this.saveMetadataAndItems(p_189515_1_);
        p_189515_1_.putIntArray("CookingTimes", this.cookingProgress);
        p_189515_1_.putIntArray("CookingTotalTimes", this.cookingTime);
        return p_189515_1_;
    }

    private CompoundNBT saveMetadataAndItems(CompoundNBT p_213983_1_) {
        super.save(p_213983_1_);
        ItemStackHelper.saveAllItems(p_213983_1_, this.items, true);
        return p_213983_1_;
    }

    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 13, this.getUpdateTag());
    }

    public CompoundNBT getUpdateTag() {
        return this.saveMetadataAndItems(new CompoundNBT());
    }

//    public Optional<CampfireCookingRecipe> getCookableRecipe(ItemStack p_213980_1_) {
//        return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.level.getRecipeManager().getRecipeFor(IRecipeType.CAMPFIRE_COOKING, new Inventory(p_213980_1_), this.level);
//    }

    public boolean addItem(ItemStack p_213984_1_, int p_213984_2_) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                this.cookingTime[i] = p_213984_2_;
                this.cookingProgress[i] = 0;
                this.items.set(i, p_213984_1_.split(1));
                this.markUpdated();
                return true;
            }
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void clearContent() {
        this.items.clear();
    }

    public void dowse() {
        if (this.level != null) {
            if (!this.level.isClientSide) {
                InventoryHelper.dropContents(this.level, this.getBlockPos(), this.getItems());
            }

            this.markUpdated();
        }

    }

    public int getNextEmptySlot() {
        for (int i = 0; i < this.items.size(); ++i) {
            ItemStack slotStack = this.items.get(i);
            if (slotStack.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public boolean isStoveBlockedAbove() {
        if (level != null) {
            BlockState above = level.getBlockState(worldPosition.above());
            return VoxelShapes.joinIsNotEmpty(GRILLING_AREA, above.getShape(level, worldPosition.above()), IBooleanFunction.AND);
        }
        return false;
    }

    public Vector2f getStoveItemOffset(int index) {
        final float X_OFFSET = 0.3F;
        final float Y_OFFSET = 0.2F;
        final Vector2f[] OFFSETS = {
                new Vector2f(X_OFFSET, Y_OFFSET),
                new Vector2f(0.0F, Y_OFFSET),
                new Vector2f(-X_OFFSET, Y_OFFSET),
                new Vector2f(X_OFFSET, -Y_OFFSET),
                new Vector2f(0.0F, -Y_OFFSET),
                new Vector2f(-X_OFFSET, -Y_OFFSET),
        };
        return OFFSETS[index];
    }
    
}