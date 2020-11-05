package zabi.minecraft.cursesilence.mixin;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import zabi.minecraft.cursesilence.CurseSilence;

@Mixin(EnchantedBookItem.class)
public abstract class EnchantedBookTooltip extends Item {

	public EnchantedBookTooltip(Settings settings) {
		super(settings);
	}

	@Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
		super.appendTooltip(stack, world, tooltip, context);
		CurseSilence.appendEnchantmentsAndCurses(tooltip, getEnchantmentTag(stack));
		ci.cancel();
	}

	@Shadow public static ListTag getEnchantmentTag(ItemStack stack) {return null;}
}
