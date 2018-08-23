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

	/** @return the minimum valid option value (inclusive) */
	double getMinOptionValue();
	/** @return the maximum valid option value (inclusive) */
	double getMaxOptionValue();

	/**
	 * @param value The option value to set it to
	 */
	void setOptionValue(double value);
	/** @return the current option value */
	double getOptionValue();

	/** @return the default option value */
	double getDefaultOptionValue();
	/** Sets the option value to the default option value. */
	void setToDefaultOptionValue();

	/** @return true if the value represented by this is a boolean value (range: [0, 1] and not analog) */
	default boolean isOptionValueBoolean(){
		return getMinOptionValue() == 0 && getMaxOptionValue() == 1 && !isOptionAnalog();
	}

	/**
	 * Can only be called if {@link #isOptionValueBoolean()}
	 * @return The boolean option value. If {@link #getOptionValue()} == 1, this will return true
	 */
	default boolean getBooleanOptionValue(){
		if(!isOptionValueBoolean()){
			throw new IllegalStateException("Cannot get a boolean value if the option value is not boolean");
		}
		return getOptionValue() == 1;
	}
}
