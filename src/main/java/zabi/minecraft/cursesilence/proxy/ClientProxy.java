package zabi.minecraft.cursesilence.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import zabi.minecraft.cursesilence.LanguageRefresher;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerListeners() {
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new LanguageRefresher());
	}
}
