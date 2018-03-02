package zabi.minecraft.cursesilence;

import net.minecraftforge.common.config.Config;

@Config(modid = CurseSilence.MOD_ID)
public class ModConfig {
	
	@Config.LangKey("curses.config.remove_completely")
	@Config.Comment("When this is true, the tooltip will completely disappear. When this is false, a general \"Cursed\" tooltip will appear")
	public static boolean remove_completely = false;
	
	@Config.LangKey("curses.config.show_books")
	@Config.Comment("When this is true, the tooltip won't be removed from enchanted books")
	public static boolean show_books = true;
	
	@Config.LangKey("curses.config.require_shift")
	@Config.Comment("When this is true, the tooltip won't be shown unless shift is pressed")
	public static boolean require_shift = false;

}
