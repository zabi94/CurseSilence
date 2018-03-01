package zabi.minecraft.cursesilence;

import java.util.ArrayList;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zabi.minecraft.cursesilence.proxy.CommonProxy;

@Mod(name=CurseSilence.MOD_NAME, modid = CurseSilence.MOD_ID, version=CurseSilence.MOD_VERSION)
public class CurseSilence {
	
	public static final String MOD_ID = "cursesilence";
	public static final String MOD_NAME = "Curse Silence";
	public static final String MOD_VERSION = "${version}";
	
	@SidedProxy(modId = MOD_ID, clientSide="zabi.minecraft.cursesilence.proxy.ClientProxy", serverSide="zabi.minecraft.cursesilence.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final ArrayList<Enchantment> curses = new ArrayList<Enchantment>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		proxy.registerListeners();
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
