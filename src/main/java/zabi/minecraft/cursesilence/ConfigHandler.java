package zabi.minecraft.cursesilence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.TranslatableText;

public class ConfigHandler implements ModMenuApi {
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final File CONFIG_FILE = new File(new File("config"), "cursesilence.json");
	
	public static ConfigInstance CONFIG = new ConfigInstance();

	public static ConfigBuilder builder() {

		ConfigBuilder configBuilder = ConfigBuilder.create()
				.setTitle(new TranslatableText("cursesilence.mod_name"))
				.setEditable(true)
				.setSavingRunnable(() -> writeJson());

		ConfigCategory general = configBuilder.getOrCreateCategory(new TranslatableText("cursesilence.config.general"));

		general.addEntry(configBuilder.entryBuilder()
				.startEnumSelector(new TranslatableText("cursesilence.config.mode.name"), ConfigInstance.HideMode.class, CONFIG.mode)
				.setTooltip(
						new TranslatableText("cursesilence.config.mode.line1"),
						new TranslatableText("cursesilence.config.mode.line2"),
						new TranslatableText("cursesilence.config.mode.line3"),
						new TranslatableText("cursesilence.config.mode.line4")
				)
				.setSaveConsumer(newValue -> CONFIG.mode = newValue)
				.build()

				);

		return configBuilder;
	}


	private static void writeJson() {
		if (!CONFIG_FILE.exists()) {
			try {
				CONFIG_FILE.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (FileWriter fw = new FileWriter(CONFIG_FILE)) {
			fw.write(GSON.toJson(CONFIG));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadConfig() {
		if (!CONFIG_FILE.exists()) {
			writeJson();
		} else {
			try (FileReader fr = new FileReader(CONFIG_FILE)) {
				CONFIG = GSON.fromJson(fr, ConfigInstance.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public String getModId() {
		return "cursesilence";
	}

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			return builder().setParentScreen(parent).build();
		};
	}

}
