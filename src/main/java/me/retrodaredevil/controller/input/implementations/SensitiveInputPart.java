package me.retrodaredevil.controller.input.implementations;

import java.util.Objects;

import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.options.OptionValue;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Adjusts the position using the passed OptionValues. Methods such as {@link #isDown()}, {@link #isJustPressed()},
 * {@link #isJustReleased()}, and {@link #isDeadzone()} are the same as the passed input part
 * <p>
 * Even though this uses on another InputPart, it does not matter if this is updated before
 * that InputPart because we don't rely on that InputPart in our update()
 */
public class SensitiveInputPart extends SimpleInputPart {

	/** The input part where we get our input from before modifying it*/
	private final InputPart inputPart;
	/** The sensitivity option */
	private final OptionValue sensitivityMultiplier;
	/** The boolean invert option or null*/
	private final OptionValue invertOption;

	/**
	 * @param inputPart The input part to adjust the sensitivity of. If this doesn't already have a parent, it will be added as a child to this instance
	 * @param sensitivityMultiplier The sensitivity OptionValue that the position from inputPart will be multiplied by
	 * @param invertOption null or a boolean OptionValue representing if the position should be multiplied by -1
	 */
	public SensitiveInputPart(InputPart inputPart, OptionValue sensitivityMultiplier, OptionValue invertOption){
		super(autoAxisTypeHelper(inputPart, sensitivityMultiplier, invertOption != null));
		this.inputPart = Objects.requireNonNull(inputPart);
		this.sensitivityMultiplier = Objects.requireNonNull(sensitivityMultiplier);
		this.invertOption = invertOption;
		if(invertOption != null && !invertOption.isOptionValueBoolean()){
			throw new IllegalArgumentException("invertOption must be a boolean option value");
		}
		partUpdater.addPartAssertNotPresent(inputPart);
	}
	public SensitiveInputPart(InputPart inputPart, OptionValue sensitivityMultiplier){
		this(inputPart, sensitivityMultiplier, null);
	}
	private static AxisType autoAxisTypeHelper(InputPart inputPart, OptionValue sensitivityMultiplier, boolean canInvert){
		AxisType type = inputPart.getAxisType();
		return new AxisType(
				type.isFull() || canInvert || sensitivityMultiplier.getMinOptionValue() < 0,
				type.isAnalog() || sensitivityMultiplier.isOptionAnalog(),
				type.isRangeOver() || max(abs(sensitivityMultiplier.getMinOptionValue()), abs(sensitivityMultiplier.getMaxOptionValue())) > 1,
				type.isShouldUseDelta()
		);
	}

	@Override
	public double getPosition() {
		double r = inputPart.getPosition();
		r *= sensitivityMultiplier.getOptionValue();
		if(invertOption != null && invertOption.getBooleanOptionValue()){
			r *= -1;
		}
		return r;
	}

	@Override
	public int getDigitalPosition() {
		int r = inputPart.getDigitalPosition();
		r *= sensitivityMultiplier.getOptionValue();
		if(invertOption != null && invertOption.getBooleanOptionValue()){
			r *= -1;
		}
		return r;
	}

	@Override
	public boolean isDown() {
		return inputPart.isDown();
	}

	@Override
	public boolean isJustPressed() {
		return inputPart.isJustPressed();
	}

	@Override
	public boolean isJustReleased() {
		return inputPart.isJustReleased();
	}

	@Override
	public boolean isDeadzone() {
		return inputPart.isDeadzone();
	}

	@Override
	public boolean isConnected() {
		return inputPart.isConnected();
	}
}
