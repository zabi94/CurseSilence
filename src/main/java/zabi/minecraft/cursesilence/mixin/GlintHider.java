package zabi.minecraft.cursesilence.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import zabi.minecraft.cursesilence.ConfigHandler;
import zabi.minecraft.cursesilence.ConfigInstance;

@Mixin(Item.class)
public class GlintHider {
	
	@Inject(method = "hasGlint", at = @At(value = "HEAD"), cancellable = true)
	public void hasGlint(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (ConfigHandler.CONFIG.mode == ConfigInstance.HideMode.FULL) {
			cir.setReturnValue(EnchantmentHelper.get(stack).keySet().stream().anyMatch(e -> !e.isCursed()));
		}
	}

}
