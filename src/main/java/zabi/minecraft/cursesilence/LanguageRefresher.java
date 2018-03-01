package zabi.minecraft.cursesilence;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LanguageRefresher implements IResourceManagerReloadListener {

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		TooltipRemover.currentTranslations.clear();
		CurseSilence.curses.parallelStream()
			.map(e -> I18n.format(e.getName()))
			.forEach(e->TooltipRemover.currentTranslations.add(e.toLowerCase().trim()));
	}

}
