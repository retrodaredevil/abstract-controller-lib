package me.retrodaredevil.controller.options;

import me.retrodaredevil.controller.ControllerPart;

/**
 * Represents a part of a controller with an adjustable sensitivity with a known range (max and min)
 */
public interface SensitivityControllerPart extends ControllerPart {
	double getMaxSensitivity();
	double getMinSensitivity();

	double getSensitivity();

	/**
	 * NOTE: In some implementations, a sensitivity of 0 may be in range, but may still be invalid
	 * @param sensitivity The sensitivity
	 * @throws IllegalArgumentException if sensitivity is out of range. Not required to be thrown if out of range
	 */
	void setSensitivity(double sensitivity);

	double getDefaultSensitivity();

	void setToDefaultSensitivity();
}
