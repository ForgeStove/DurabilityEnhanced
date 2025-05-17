package com.forgestove.durability_enhanced.config;
import com.forgestove.durability_enhanced.DurabilityEnhanced;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
@Config(name = DurabilityEnhanced.ID)
public class DEConfigData implements ConfigData {
	@Tooltip public boolean noRepairAccumulatePenalties = true;
}
