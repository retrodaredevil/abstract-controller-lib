package me.retrodaredevil.controller.input;

import java.util.Objects;

import me.retrodaredevil.controller.options.OptionValue;

public class SensitiveInputPart extends AutoCachingInputPart {

	private final InputPart inputPart;
	private final OptionValue sensitivityMultiplier;
	private final OptionValue invertOption; // nullable

	/**
	 * @param inputPart The input part to adjust the sensitivity of. If this doesn't already have a parent, it will be added as a child to this instance
	 * @param sensitivityMultiplier The sensitivity OptionValue that the position from inputPart will be multiplied by
	 * @param invertOption null or a boolean OptionValue representing if the position should be multiplied by -1
	 */
	public SensitiveInputPart(InputPart inputPart, OptionValue sensitivityMultiplier, OptionValue invertOption){
		super(autoAxisTypeHelper(inputPart, sensitivityMultiplier, invertOption != null), true);
		this.inputPart = Objects.requireNonNull(inputPart);
		this.sensitivityMultiplier = Objects.requireNonNull(sensitivityMultiplier);
		this.invertOption = invertOption;
		if(invertOption != null && !invertOption.isOptionValueBoolean()){
			throw new IllegalArgumentException("invertOption must be a boolean option value");
		}
		addChildren(false, true, inputPart);
	}
	public SensitiveInputPart(InputPart inputPart, OptionValue sensitivityMultiplier){
		this(inputPart, sensitivityMultiplier, null);
	}
	private static AxisType autoAxisTypeHelper(InputPart inputPart, OptionValue sensitivityMultiplier, boolean canInvert){
		AxisType type = inputPart.getAxisType();
		return new AxisType(
				type.isFull() || canInvert,
				type.isAnalog() || sensitivityMultiplier.isOptionAnalog(),
				type.isRangeOver() || Math.abs(Math.max(sensitivityMultiplier.getMinOptionValue(), sensitivityMultiplier.getMaxOptionValue())) > 1,
				type.isShouldUseDelta()
				);
	}


	@Override
	public boolean isConnected() {
		return inputPart.isConnected();
	}

	@Override
	protected double calculatePosition() {
		double r = inputPart.getPosition();
		r *= sensitivityMultiplier.getOptionValue();
		if(invertOption != null && invertOption.getBooleanOptionValue()){
			r *= -1;
		}
		return r;
	}
}
