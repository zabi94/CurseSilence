package zabi.minecraft.cursesilence;

import net.minecraftforge.common.config.Config;

@Config(modid = CurseSilence.MOD_ID)
public class ModConfig {
	
	@Config.LangKey("curses.config.mode.name")
	@Config.Comment("Reduce will replace the curse with a generic \"Cursed\", Remove will remove it completely, Shift only shows \"Cursed\" when shifting")
	public static Mode mode = Mode.Reduce;
	
	@Config.LangKey("curses.config.show_books.name")
	@Config.Comment("When this is true enchanted books won't be affected")
	public static boolean show_books = true;
	
	public static enum Mode {
		Reduce, Remove, Shift
	}

}
