package zabi.minecraft.cursesilence;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zabi.minecraft.cursesilence.ModConfig.Mode;

@Mod.EventBusSubscriber
public class TooltipRemover {
	
	public static ArrayList<String> currentTranslations = new ArrayList<String>();
	
	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent evt) {
		if ((evt.getItemStack().getItem()!=Items.ENCHANTED_BOOK && evt.getItemStack().isItemEnchanted()) || (evt.getItemStack().getItem()==Items.ENCHANTED_BOOK && !ModConfig.show_books)) {
			if (currentTranslations.isEmpty()) {
				new LanguageRefresher().onResourceManagerReload(null);
			}
			ArrayList<String> removal = new ArrayList<String>();
			boolean found = evt.getToolTip().stream()
					.filter(s -> s.length()>2)
					.filter(s->isTranslation(s))
					.peek(s->{
						removal.add(s);
					})
					.findAny().isPresent();
			evt.getToolTip().removeAll(removal);
			if (ModConfig.mode != Mode.Remove && found && (ModConfig.mode != Mode.Shift || GuiScreen.isShiftKeyDown())) {
				evt.getToolTip().add("");
				evt.getToolTip().add(TextFormatting.RED.toString()+TextFormatting.ITALIC+I18n.format("curse.general_tooltip"));
			}
		}
	}
	
	@SubscribeEvent
	public static void onChanged(ConfigChangedEvent evt) {
		if (evt.getModID().equals(CurseSilence.MOD_ID)) {
			ConfigManager.sync(CurseSilence.MOD_ID, Type.INSTANCE);
		}
	}

	private static boolean isTranslation(String s) {
		return currentTranslations.stream().anyMatch(tr -> s.substring(2).toLowerCase().trim().endsWith(tr));
	}
}
