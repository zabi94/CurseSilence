package zabi.minecraft.cursesilence;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name=CurseSilence.MOD_NAME, modid = CurseSilence.MOD_ID, version=CurseSilence.MOD_VERSION, clientSideOnly=true, acceptedMinecraftVersions=CurseSilence.MC_VERSIONS)
public class CurseSilence {
	
	public static final String MOD_ID = "cursesilence";
	public static final String MOD_NAME = "Curse Silence";
	public static final String MOD_VERSION = "1.1";
	public static final String MC_VERSIONS = "[1.11,1.13)";
	
	
	public static final ArrayList<Enchantment> curses = new ArrayList<Enchantment>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new LanguageRefresher());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		Enchantment.REGISTRY.forEach(e -> {
			if (e.isCurse()) {
				curses.add(e);
			}
		});
	}
	
}
