package me.retrodaredevil.controller.options;

import java.util.List;

/**
 * Represents an object that is able to be directly configured.
 * <p>
 * Reference OptionValue when you want to reference a mutable value, reference a {@link ConfigurableControllerPart}
 * when you want to reference a controller that is configurable
 * <p>
 * It is also recommended to override all the default methods if reasonable to do so
 */
public interface OptionValue {
	/** @return true if this uses an analog value, false otherwise */
	boolean isOptionAnalog();

	/** @return the minimum valid option value (inclusive) */
	double getMinOptionValue();
	/** @return the maximum valid option value (inclusive) */
	double getMaxOptionValue();

	/**
	 * If {@link #isOptionAnalog()} == true, passing a value that's not a whole number may be rounded
	 * or in worse case, floored. Sometimes it won't even round or floor it - depends on implementation.
	 * If you want to guarantee that the value is a whole number, use {@link #setOptionValue(int)}
	 *
	 * @param value The option value to set it to
	 * @throws IllegalArgumentException if value is out of range. This is not required to be thrown
	 */
	void setOptionValue(double value);

	/**
	 * Should do the same thing as {@link #setOptionValue(double)} except calling this guarantees an integer must be used
	 * <p>
	 * Using this does not necessarily mean that this is analog or digital. This method is just a simple
	 * overload to be used just as frequently as setOptionValue
	 * <p>
	 * By default, this just calls {@link #setOptionValue(double)}
	 * @param value The option value to set it to
	 * @throws IllegalArgumentException if value is out of range. This is not required to be thrown
	 */
	default void setOptionValue(int value){ setOptionValue((double) value); }
	/** @return the current option value */
	double getOptionValue();

	/** @return the default option value */
	double getDefaultOptionValue();
	/** Sets the option value to the default option value. */
	void setToDefaultOptionValue();

	/**
	 * NOTE: If this isn't empty, you can assume that the min option value is 0 and the
	 * max option value is the size of this list - 1. You should still check {@link #isOptionValueRadio()} though.
	 * <p>
	 * NOTE: Will NEVER return null. The reason for not returning null is to distinguish
	 * from a non radio option and a radio option since all radio options must have at least one option.
	 * <p>
	 * NOTE: You should not mutate the returned list, as that might cause bugs or immutability is
	 * enforced (may throw errors if you try to add to it)
	 * @return an empty list if this option value is not a radio option otherwise, will return a list of strings representing
	 * 			each radio option.
	 */
	List<String> getRadioOptions();

	/**
	 * If this is true, then {@link #isOptionAnalog()} should be false and {@link #isOptionValueBoolean()} should
	 * also be false and {@link #getMinOptionValue()} == 0
	 * @return true if this option value is radio, false otherwise
	 */
	default boolean isOptionValueRadio(){
		return getRadioOptions().isEmpty();
	}
//	default String getRadioOptionValue(){ I decided not to include this in the implementation as it doesn't add much
//		if(!isOptionValueRadio()){
//			throw new UnsupportedOperationException("Cannot get radio option if this OptionValue is not a radio option");
//		}
//		double value = getOptionValue();
//		int index = (int) value;
//
//		List<String> options = getRadioOptions();
//		if(index < 0 || index >= options.size()){
//			throw new IndexOutOfBoundsException("index: " + index + " (casted from double value: " + value + ") " +
//					"is out of bounds for options of size: " + options.size()
//					+ "\nThe class implementing radio options must have implemented it wrong.");
//		}
//		return options.get(index);
//	}

	/** @return true if the value represented by this is a boolean value (range: [0, 1] and not analog and not radio) */
	default boolean isOptionValueBoolean(){
		return !isOptionValueRadio() && getMinOptionValue() == 0 && getMaxOptionValue() == 1 && !isOptionAnalog();
	}

	/**
	 * Can only be called if {@link #isOptionValueBoolean()}
	 * @return The boolean option value. If {@link #getOptionValue()} == 1, this will return true
	 * @throws UnsupportedOperationException if {@link #isOptionValueBoolean()} == false
	 */
	default boolean getBooleanOptionValue(){
		if(!isOptionValueBoolean()){
			throw new UnsupportedOperationException("Cannot get a boolean value if the option value is not boolean");
		}
		return getOptionValue() == 1;
	}

	/**
	 * Can only be called if {@link #isOptionValueBoolean()}
	 * @param b The boolean value to set
	 * @throws UnsupportedOperationException if {@link #isOptionValueBoolean()} == false
	 */
	default void setBooleanOptionValue(boolean b){
		if(!isOptionValueBoolean()){
			throw new UnsupportedOperationException("Cannot set a boolean value if the option value is not boolean");
		}
		setOptionValue(b ? 1 : 0);
	}
}
