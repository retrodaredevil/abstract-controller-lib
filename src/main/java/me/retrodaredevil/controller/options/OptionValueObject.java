package me.retrodaredevil.controller.options;

/**
 * Represents an object that is able to be directly configured.
 * <p>
 * Instead of referencing this, it is recommended to reference a {@link ConfigurableControllerPart}
 * because it is possible that concrete implementations of this class implement {@link ConfigurableControllerPart}
 * as well.
 */
public interface OptionValueObject {
	/** @return true if this uses an analog value, false otherwise */
	boolean isOptionAnalog();

	double getMinOptionValue();
	double getMaxOptionValue();

	void setOptionValue(double value);
	double getOptionValue();

	double getDefaultOptionValue();
	void setToDefaultOptionValue();
}
