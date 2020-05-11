// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import fox.spiteful.avaritia.crafting.CompressorManager;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.ISidedInventory;

public class TileEntityCompressor extends TileLudicrous implements ISidedInventory
{
    private ItemStack input;
    private ItemStack processing;
    private ItemStack output;
    private int facing;
    private int progress;
    private int target;
    private String ingredient;
    private int packetCount;
    private boolean packet;
    private static final int[] top;
    private static final int[] sides;
    
    public TileEntityCompressor() {
        this.facing = 2;
        this.progress = 0;
        this.target = 0;
    }
    
    public void func_145845_h() {
        if (this.packetCount > 0) {
            --this.packetCount;
        }
        if (this.input != null && (this.processing == null || this.progress < this.target) && CompressorManager.getOutput(this.input) != null && (this.output == null || CompressorManager.getOutput(this.input).func_77969_a(this.output))) {
            if (this.processing == null) {
                this.processing = CompressorManager.getOutput(this.input);
                this.target = CompressorManager.getCost(this.input);
                this.ingredient = CompressorManager.getName(this.input);
            }
            if (CompressorManager.getOutput(this.input).func_77969_a(this.processing)) {
                final int needed = this.target - this.progress;
                if (needed >= this.input.field_77994_a) {
                    this.progress += this.input.field_77994_a;
                    this.input = null;
                }
                else {
                    this.progress = this.target;
                    final ItemStack input = this.input;
                    input.field_77994_a -= needed;
                }
            }
            this.func_70296_d();
            this.packet = true;
        }
        if (this.progress >= this.target && this.processing != null && (this.output == null || (this.output.func_77969_a(this.processing) && this.output.field_77994_a + this.processing.field_77994_a <= this.output.func_77976_d()))) {
            if (this.output == null) {
                this.output = this.processing.func_77946_l();
            }
            else if (this.output.func_77969_a(this.processing)) {
                final ItemStack output = this.output;
                output.field_77994_a += this.processing.field_77994_a;
            }
            this.progress -= this.target;
            if (this.progress == 0) {
                this.processing = null;
                this.ingredient = null;
            }
            this.func_70296_d();
            this.packet = true;
        }
        if (this.packet && this.packetCount <= 0) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            this.packetCount = 10;
            this.packet = false;
        }
    }
    
    public int getFacing() {
        return this.facing;
    }
    
    public void setFacing(final int dir) {
        this.facing = dir;
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public int getTarget() {
        return this.target;
    }
    
    public String getIngredient() {
        return this.ingredient;
    }
    
    @Override
    public void readCustomNBT(final NBTTagCompound tag) {
        this.input = ItemStack.func_77949_a(tag.func_74775_l("Input"));
        this.processing = ItemStack.func_77949_a(tag.func_74775_l("Processing"));
        this.output = ItemStack.func_77949_a(tag.func_74775_l("Output"));
        if (this.processing != null) {
            this.target = CompressorManager.getPrice(this.processing);
            if (this.target != 0) {
                this.progress = tag.func_74762_e("Progress");
                if (tag.func_74764_b("Ingredient")) {
                    this.ingredient = tag.func_74779_i("Ingredient");
                }
            }
            else {
                this.processing = null;
            }
        }
        else {
            this.progress = 0;
            this.target = 0;
            this.ingredient = null;
        }
        this.facing = tag.func_74765_d("Facing");
    }
    
    @Override
    public void writeCustomNBT(final NBTTagCompound tag) {
        tag.func_74777_a("Facing", (short)this.facing);
        if (this.input != null) {
            final NBTTagCompound produce = new NBTTagCompound();
            this.input.func_77955_b(produce);
            tag.func_74782_a("Input", (NBTBase)produce);
        }
        else {
            tag.func_82580_o("Input");
        }
        if (this.processing != null) {
            final NBTTagCompound produce = new NBTTagCompound();
            this.processing.func_77955_b(produce);
            tag.func_74782_a("Processing", (NBTBase)produce);
            tag.func_74768_a("Progress", this.progress);
            if (this.ingredient != null) {
                tag.func_74778_a("Ingredient", this.ingredient);
            }
            else {
                tag.func_82580_o("Ingredient");
            }
        }
        else {
            tag.func_82580_o("Processing");
            tag.func_82580_o("Progress");
            tag.func_82580_o("Target");
            tag.func_82580_o("Ingredient");
        }
        if (this.output != null) {
            final NBTTagCompound produce = new NBTTagCompound();
            this.output.func_77955_b(produce);
            tag.func_74782_a("Output", (NBTBase)produce);
        }
        else {
            tag.func_82580_o("Output");
        }
    }
    
    public int func_70302_i_() {
        return 2;
    }
    
    public ItemStack func_70301_a(final int slot) {
        if (slot == 0) {
            return this.input;
        }
        return this.output;
    }
    
    public ItemStack func_70298_a(final int slot, final int decrement) {
        if (slot == 0) {
            if (this.input == null) {
                return null;
            }
            if (decrement < this.input.field_77994_a) {
                final ItemStack take = this.input.func_77979_a(decrement);
                if (this.input.field_77994_a <= 0) {
                    this.input = null;
                }
                return take;
            }
            final ItemStack take = this.input;
            this.input = null;
            return take;
        }
        else {
            if (slot != 1) {
                return null;
            }
            if (this.output == null) {
                return null;
            }
            if (decrement < this.output.field_77994_a) {
                final ItemStack take = this.output.func_77979_a(decrement);
                if (this.output.field_77994_a <= 0) {
                    this.output = null;
                }
                return take;
            }
            final ItemStack take = this.output;
            this.output = null;
            return take;
        }
    }
    
    public void func_70295_k_() {
    }
    
    public void func_70305_f() {
    }
    
    public boolean func_70300_a(final EntityPlayer player) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player.func_70092_e(this.field_145851_c + 0.5, this.field_145848_d + 0.5, this.field_145849_e + 0.5) <= 64.0;
    }
    
    public boolean func_94041_b(final int slot, final ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (slot == 0) {
            if (this.processing == null) {
                return true;
            }
            if (CompressorManager.getOutput(stack) == null) {
                return false;
            }
            if (CompressorManager.getOutput(stack).func_77969_a(this.processing)) {
                return true;
            }
        }
        return false;
    }
    
    public int func_70297_j_() {
        return 64;
    }
    
    public void func_70299_a(final int slot, final ItemStack stack) {
        if (slot == 0) {
            this.input = stack;
        }
        else if (slot == 1) {
            this.output = stack;
        }
    }
    
    public ItemStack func_70304_b(final int slot) {
        return null;
    }
    
    public String func_145825_b() {
        return "container.neutronium_compressor";
    }
    
    public boolean func_145818_k_() {
        return false;
    }
    
    public int[] func_94128_d(final int side) {
        if (side == 1) {
            return TileEntityCompressor.top;
        }
        return TileEntityCompressor.sides;
    }
    
    public boolean func_102007_a(final int slot, final ItemStack stack, final int side) {
        return this.func_94041_b(slot, stack);
    }
    
    public boolean func_102008_b(final int slot, final ItemStack stack, final int side) {
        return slot == 1 && side != 1;
    }
    
    static {
        top = new int[] { 0 };
        sides = new int[] { 1 };
    }
}
