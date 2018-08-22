package me.retrodaredevil.controller.options;

import me.retrodaredevil.controller.ControllerPart;

/**
 * Similar to {@link SensitivityControllerPart} except used for thresholds
 */
public interface ThresholdControllerPart extends ControllerPart {
	double getMaxThreshold();
	double getMinThreshold();

	double getThreshold();

	/**
	 * NOTE: In some implementations, a threshold of 0 may be in range, but may still be invalid
	 * @param threshold The threshold
	 * @throws IllegalArgumentException if sensitivity is out of range. Not required to be thrown if out of range
	 */
	void setThreshold(double threshold);

	double getDefaultThreshold();

	void setToDefaultThreshold();
}
