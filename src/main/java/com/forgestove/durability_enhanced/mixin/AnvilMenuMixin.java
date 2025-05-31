package com.forgestove.durability_enhanced.mixin;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {
	@WrapOperation(
		method = "createResult",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AnvilMenu;calculateIncreasedRepairCost(I)I")
	)
	public int calculateIncreasedRepairCost(
		int oldRepairCost,
		Operation<Integer> original,
		@Local(name = "itemstack") ItemStack itemstack,
		@Local(name = "itemstack1") ItemStack itemstack1,
		@Local(name = "itemstack2") ItemStack itemstack2
	) {
		if (itemstack1.isDamageableItem() && itemstack1.getItem().isValidRepairItem(itemstack, itemstack2)) return oldRepairCost;
		return original.call(oldRepairCost);
	}
}
