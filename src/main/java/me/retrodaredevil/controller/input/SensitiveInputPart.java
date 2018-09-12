package me.retrodaredevil.controller.input;

import java.util.Collections;
import java.util.Objects;

import me.retrodaredevil.controller.options.OptionValue;

public class SensitiveInputPart extends AutoCachingInputPart {

	private final InputPart inputPart;
	private final OptionValue sensitivityMultiplier;
	private final OptionValue invertOption; // nullable

	public SensitiveInputPart(InputPart inputPart, OptionValue sensitivityMultiplier, OptionValue invertOption){
		super(autoAxisTypeHelper(inputPart, sensitivityMultiplier, invertOption != null), true);
		this.inputPart = Objects.requireNonNull(inputPart);
		this.sensitivityMultiplier = Objects.requireNonNull(sensitivityMultiplier);
		this.invertOption = invertOption;
		if(invertOption != null && !invertOption.isOptionValueBoolean()){
			throw new IllegalArgumentException("invertOption must be a boolean option value");
		}
		addChildren(Collections.singleton(inputPart), true, true);
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
