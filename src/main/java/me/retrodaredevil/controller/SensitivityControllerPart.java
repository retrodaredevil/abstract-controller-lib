package me.retrodaredevil.controller;

/**
 * Represents a part of a controller with an adjustable sensitivity with a known range (max and min)
 */
public interface SensitivityControllerPart extends ControllerPart {
	double getMaxSensitivity();
	double getMinSensitivity();

	double getSensitivity();
	void setSensitivity(double sensitivity);
}
