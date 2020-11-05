package zabi.minecraft.cursesilence.mixin;

import java.util.List;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zabi.minecraft.cursesilence.ConfigHandler;
import zabi.minecraft.cursesilence.ConfigInstance;

@Mixin(ItemStack.class)
public class EnchantmentHider {

	@Inject(method = "appendEnchantments", at = @At(value = "HEAD"), cancellable = true)
	private static void applyCurseSilence(List<Text> tooltip, ListTag enchantments, CallbackInfo ci) {
		int removed = 0;
		for (Tag t: enchantments) {
			if (t instanceof CompoundTag) {
				Optional<Enchantment> ench = Registry.ENCHANTMENT.getOrEmpty(Identifier.tryParse(((CompoundTag) t).getString("id")));
				if (ench.isPresent()) {
					if (ench.get().isCursed()) {
						removed++;
					} else {
						tooltip.add(ench.get().getName(((CompoundTag) t).getInt("lvl")));
					}
				}
			}
		}

		if (removed > 0 && ConfigHandler.CONFIG.mode != ConfigInstance.HideMode.FULL) {
			int level = ConfigHandler.CONFIG.mode == ConfigInstance.HideMode.HINT ? 0: removed;
			tooltip.add(new TranslatableText("cursesilence.cursed", new TranslatableText("enchantment.level."+level)).formatted(Formatting.DARK_RED, Formatting.ITALIC));
		}

		ci.cancel();

	}

}
