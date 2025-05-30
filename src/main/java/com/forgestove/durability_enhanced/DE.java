package com.forgestove.durability_enhanced;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
@Mod(DE.ID)
public class DE {
	public static final String ID = "durability_enhanced";
	public static final DEConfig CONFIG = AutoConfig.register(DEConfig.class, Toml4jConfigSerializer::new).getConfig();
	public DE(ModContainer container) {
		container.registerExtensionPoint(
			IConfigScreenFactory.class,
			(modContainer, screen) -> AutoConfig.getConfigScreen(DEConfig.class, screen).get()
		);
	}
}
