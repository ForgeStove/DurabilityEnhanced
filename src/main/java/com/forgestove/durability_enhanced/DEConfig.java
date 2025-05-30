package com.forgestove.durability_enhanced;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
@Config(name = DE.ID)
public class DEConfig implements ConfigData {
	@Tooltip public boolean noRepairAccumulatePenalties = true;
}
