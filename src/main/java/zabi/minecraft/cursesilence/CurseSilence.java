package zabi.minecraft.cursesilence;

import java.util.List;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CurseSilence implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ConfigHandler.loadConfig();
	}

	@Environment(EnvType.CLIENT)
	public static void appendEnchantmentsAndCurses(List<Text> tooltip, ListTag enchantments) {
		for(int i = 0; i < enchantments.size(); ++i) {
			CompoundTag compoundTag = enchantments.getCompound(i);
			Registry.ENCHANTMENT.getOrEmpty(Identifier.tryParse(compoundTag.getString("id"))).ifPresent((e) -> {
				tooltip.add(e.getName(compoundTag.getInt("lvl")));
			});
		}

	}

}
