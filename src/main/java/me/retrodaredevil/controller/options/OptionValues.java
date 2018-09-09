package me.retrodaredevil.controller.options;

import java.util.Collections;
import java.util.List;

public final class OptionValues {
	private OptionValues(){}

	public static OptionValue createBooleanOptionValue(boolean isTrueByDefault){
		return new SimpleOption(false, 0, 1, isTrueByDefault ? 1 : 0);
	}
	public static OptionValue createAnalogRangedOptionValue(double min, double max, double defaultValue){
		return new SimpleOption(true, min, max, defaultValue);
	}
	public static OptionValue createDigitalRangedOptionValue(int min, int max, int defaultValue){
		return new SimpleOption(false, min, max, defaultValue);
	}

	public static OptionValue createImmutableBooleanOptionValue(boolean value){
		return new ImmutableSimpleOption(false, 0, 1, value ? 1 : 0);
	}
	public static OptionValue createImmutableAnalogRangedOptionValue(double min, double max, double value){
		return new ImmutableSimpleOption(true, min, max, value);
	}
	public static OptionValue createImmutableAnalogRangedOptionValue(double value){
		return createImmutableAnalogRangedOptionValue(value, value, value);
	}
	public static OptionValue createImmutableDigitalRangedOptionValue(int min, int max, int value){
		return new ImmutableSimpleOption(false, min, max, value);
	}
	public static OptionValue createImmutableDigitalRangedOptionValue(int value){
		return createImmutableDigitalRangedOptionValue(value, value, value);
	}

	public static OptionValue createRadioOptionValue(List<String> options, int defaultOption){
		return new SimpleRadioOption(options, defaultOption);
	}

	private static final class SimpleOption implements OptionValue {
		private final boolean isAnalog;
		private final double min;
		private final double max;
		private final double defaultOption;

		private double value;

		SimpleOption(boolean analog, double min, double max, double defaultOption){
			this.isAnalog = analog;
			this.min = min;
			this.max = max;
			this.defaultOption = defaultOption;

			setOptionValue(defaultOption); // checks for out of range
		}


		@Override
		public boolean isOptionAnalog() { return isAnalog; }

		@Override
		public double getMinOptionValue() { return min; }

		@Override
		public double getMaxOptionValue() { return max; }

		@Override
		public double getDefaultOptionValue() { return defaultOption; }

		@Override
		public void setToDefaultOptionValue() {
			value = defaultOption;
		}

		@Override
		public void setOptionValue(final double value) {
			double newValue = value;
			if(!isAnalog){
				newValue = Math.round(value);
			}
			if(newValue < min || newValue > max){
				throw new IllegalArgumentException("value: " + newValue + " is out of range: [" + min + ", " + max + "].");
			}

			this.value = newValue;
		}

		@Override
		public double getOptionValue() { return value; }

		@Override
		public List<String> getRadioOptions() { return Collections.emptyList(); }
		@Override
		public boolean isOptionValueRadio() { return false; }
	}
	private static final class ImmutableSimpleOption implements OptionValue {
		private final boolean analog;
		private final double min, max, value;
		ImmutableSimpleOption(boolean analog, double min, double max, double value){
			this.analog = analog;
			this.min = min;
			this.max = max;
			this.value = value;
			if(value < min || value > max){
				throw new IllegalArgumentException("value: " + value + " is out of range: [" + min + ", " + max + "]");
			}
		}

		@Override
		public boolean isOptionAnalog() { return analog; }

		@Override
		public double getMinOptionValue() { return min; }

		@Override
		public double getMaxOptionValue() { return max; }

		@Override
		public double getDefaultOptionValue() { return value; }

		@Override
		public double getOptionValue() { return value; }

		@Override
		public List<String> getRadioOptions() { return Collections.emptyList(); }
		@Override
		public boolean isOptionValueRadio() { return false; }

		@Override
		public void setOptionValue(double value) {
			if(value == this.value){ // they can set the default value if they want
				return;
			}
			throw new UnsupportedOperationException("Cannot change value of an " + getClass().getSimpleName());
		}

		@Override
		public void setToDefaultOptionValue() {} // we shouldn't need to change the value
	}
	private static final class SimpleRadioOption implements OptionValue {
		private final List<String> options; // an unmodifiable list
		private final int defaultOptionIndex;
		private int optionIndex;

		SimpleRadioOption(List<String> optionsList, int defaultOptionIndex){
			this.options = Collections.unmodifiableList(optionsList);
			this.defaultOptionIndex = defaultOptionIndex;
			if(defaultOptionIndex < 0 || defaultOptionIndex >= options.size()){
				throw new IllegalArgumentException("Cannot create a " + getClass().getSimpleName() + " with an out of bounds default option");
			}
			setToDefaultOptionValue();
		}

		@Override
		public boolean isOptionAnalog() {
			return false;
		}

		@Override
		public double getMinOptionValue() {
			return 0;
		}

		@Override
		public double getMaxOptionValue() {
			return options.size() - 1;
		}

		@Override
		public void setOptionValue(double value) {
			setOptionValue((int) value);
		}

		@Override
		public void setOptionValue(int index) {
			if(index < 0 || index >= options.size()){
				throw new IllegalArgumentException("index: " + index + " is out of range for options of size: " + options.size());
			}
			this.optionIndex = index;
		}

		@Override
		public double getOptionValue() {
			return optionIndex;
		}

		@Override
		public double getDefaultOptionValue() {
			return defaultOptionIndex;
		}

		@Override
		public void setToDefaultOptionValue() {
			this.optionIndex = defaultOptionIndex;
		}

		@Override
		public List<String> getRadioOptions() {
			return options;
		}

		@Override
		public boolean isOptionValueRadio() {
			return true;
		}

		@Override
		public boolean isOptionValueBoolean() {
			return false;
		}
	}
}
