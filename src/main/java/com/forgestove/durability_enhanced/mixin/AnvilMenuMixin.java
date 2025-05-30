package com.forgestove.durability_enhanced.mixin;
import com.forgestove.durability_enhanced.DE;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {
	@Shadow private String itemName;
	@WrapOperation(
		method = "createResult",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AnvilMenu;calculateIncreasedRepairCost(I)I")
	)
	private int createResult(int oldRepairCost, Operation<Integer> original) {
		if (DE.CONFIG.noRepairAccumulatePenalties && (itemName == null || itemName.trim().isEmpty()))
			return oldRepairCost; // 启用并且修复物品，不累积惩罚
		return original.call(oldRepairCost); // 累计惩罚
	}
}
