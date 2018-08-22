package me.retrodaredevil.controller.options;

import me.retrodaredevil.controller.ControllerPart;

public interface BooleanFlagControllerPart extends ControllerPart {
	void setBooleanFlag(boolean b);
	boolean getBooleanFlag();

	boolean getDefaultBooleanFlag();
	void setToDefaultBooleanFlag();
}
