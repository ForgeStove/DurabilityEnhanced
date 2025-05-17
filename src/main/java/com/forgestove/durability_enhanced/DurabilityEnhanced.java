package com.forgestove.durability_enhanced;
import com.forgestove.durability_enhanced.config.DEConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
@Mod(DurabilityEnhanced.ID)
public class DurabilityEnhanced {
	public static final String ID = "durability_enhanced";
	public DurabilityEnhanced(ModContainer container) {
		DEConfig.register(container);
	}
}
