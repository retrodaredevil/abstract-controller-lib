package me.retrodaredevil.controller.options;

public final class OptionValues {
	public static OptionValueObject createBooleanOptionValue(boolean isTrueByDefault){
		return new SimpleOption(false, 0, 1, isTrueByDefault ? 1 : 0);
	}
	public static OptionValueObject createAnalogRangedOptionValue(double min, double max, double defaultValue){
		return new SimpleOption(true, min, max, defaultValue);
	}
	public static OptionValueObject createDigitalRangedOptionValue(int min, int max, int defaultValue){
		return new SimpleOption(false, min, max, defaultValue);
	}

	private static class SimpleOption implements OptionValueObject {
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

			setOptionValue(defaultOption);
		}


		@Override
		public boolean isOptionAnalog() {
			return isAnalog;
		}

		@Override
		public double getMinOptionValue() {
			return min;
		}

		@Override
		public double getMaxOptionValue() {
			return max;
		}

		@Override
		public double getDefaultOptionValue() {
			return defaultOption;
		}

		@Override
		public void setToDefaultOptionValue() {
			value = defaultOption;
		}

		public double getDefaultOption() {
			return defaultOption;
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

			this.value = value;
		}

		@Override
		public double getOptionValue() {
			return value;
		}
	}
}
