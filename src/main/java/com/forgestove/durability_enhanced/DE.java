package com.forgestove.durability_enhanced;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.fml.common.Mod;
@Mod(DE.ID)
public class DE {
	public static final String ID = "durability_enhanced";
	public static final DEConfig CONFIG = AutoConfig.register(DEConfig.class, Toml4jConfigSerializer::new).getConfig();
	public DE() {
		DEConfig.register();
	}
}
