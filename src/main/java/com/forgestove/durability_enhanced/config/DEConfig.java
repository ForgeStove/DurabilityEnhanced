package com.forgestove.durability_enhanced.config;
import com.forgestove.durability_enhanced.util.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
public class DEConfig {
	static DEConfigData config;
	public static void register(ModContainer container) {
		SafeRun.run(() -> config = AutoConfig.register(DEConfigData.class, Toml4jConfigSerializer::new).getConfig());
		container.registerExtensionPoint(
			IConfigScreenFactory.class,
			(modContainer, screen) -> AutoConfig.getConfigScreen(DEConfigData.class, screen).get()
		);
	}
	public static DEConfigData get() {
		if (config != null) return config;
		return SafeCall.call(() -> config = AutoConfig.register(DEConfigData.class, Toml4jConfigSerializer::new).getConfig());
	}
}
