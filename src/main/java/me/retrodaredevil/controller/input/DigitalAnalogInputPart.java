package me.retrodaredevil.controller.input;

import me.retrodaredevil.controller.SimpleControllerPart;

/**
 * A simple InputPart that is normally used with triggers that also have a corresponding button representing
 * if they are being pressed down or not.
 */
public class DigitalAnalogInputPart extends SimpleControllerPart implements InputPart {

	private final InputPart digital;
	private final InputPart analog;

	public DigitalAnalogInputPart(InputPart digital, InputPart analog){
		this.digital = digital;
		this.analog = analog;
		final AxisType digitalType = digital.getAxisType(), analogType = analog.getAxisType();
		if(digitalType.isFull() != analogType.isFull()){
			throw new IllegalArgumentException("Both digital and analog must have full range if either has full range!");
		}
		addChildren(false, false, digital, analog);
	}

	@Override
	public AxisType getAxisType() {
		if(analog.isConnected()){
			return analog.getAxisType();
		}
        return digital.getAxisType();
	}

	@Override
	public boolean isDeadzone() {
        return analog.isDeadzone() || (!analog.isConnected() && digital.isDeadzone());
	}

	@Override
	public double getPosition() {
        if(analog.isConnected() && !analog.isDeadzone()){
        	return analog.getPosition();
		}
		return digital.getPosition();
	}

	@Override
	public boolean isDown() {
		if(config.isUseAbstractedIsDownIfPossible() && digital.isConnected() && !digital.isDeadzone()){
			return digital.isDown();
		}
        return analog.isDown();
	}

	@Override
	public boolean isPressed() {
		if(config.isUseAbstractedIsDownIfPossible() && digital.isConnected() && !digital.isDeadzone()){
			return digital.isPressed();
		}
        return analog.isPressed();
	}

	@Override
	public boolean isReleased() {
		if(config.isUseAbstractedIsDownIfPossible() && digital.isConnected()){
			return digital.isReleased();
		}
		return analog.isReleased();
	}

	@Override
	public int getDigitalPosition() {
		if(config.isUseAbstractedIsDownIfPossible() && digital.isConnected() && !digital.isDeadzone()){
			return digital.getDigitalPosition();
		}
		return analog.getDigitalPosition();
	}

	@Override
	public boolean isConnected() {
        return digital.isConnected() || analog.isConnected();
	}
}
