package me.retrodaredevil.controller.input.implementations;

import me.retrodaredevil.controller.*;
import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;

/**
 * A simple InputPart that is normally used with triggers that also have a corresponding button representing
 * if they are being pressed down or not.
 */
public class DigitalAnalogInputPart extends SimpleControllerPart implements InputPart {

	private final InputPart digital;
	private final InputPart analog;

	/**
	 *
	 * @param digital The digital InputPart. Its parent will be set to this if it does not already have one
	 * @param analog The analog InputPart. Its parent will be set to this if it does not already have one
	 */
	public DigitalAnalogInputPart(InputPart digital, InputPart analog, boolean updateParts){
		this.digital = digital;
		this.analog = analog;
		final AxisType digitalType = digital.getAxisType(), analogType = analog.getAxisType();
		if(digitalType.isFull() != analogType.isFull()){
			throw new IllegalArgumentException("Both digital and analog must have full range if either has full range!");
		}
		if(updateParts) {
			partUpdater.addPartsAssertNonePresent(digital, analog);
		}
	}
	public DigitalAnalogInputPart(InputPart digital, InputPart analog){
		this(digital, analog, true);
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
		final boolean analogConnected = analog.isConnected(), digitalConnected = digital.isConnected();
		if(!analogConnected && !digitalConnected){ // neither connected
			return true;
		} else if (analogConnected && digitalConnected){ // both connected
			return analog.isDeadzone() && digital.isDeadzone();
		} else if(analogConnected){ // only analog connected
			return analog.isDeadzone();
		}
		return digital.isDeadzone(); // only digital connected
	}

	@Override
	public double getPosition() {
		if(analog.isConnected()){
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
	public boolean isJustPressed() {
		if(config.isUseAbstractedIsDownIfPossible() && digital.isConnected() && !digital.isDeadzone()){
			return digital.isJustPressed();
		}
		return analog.isJustPressed();
	}

	@Override
	public boolean isJustReleased() {
		if(config.isUseAbstractedIsDownIfPossible() && digital.isConnected()){
			return digital.isJustReleased();
		}
		return analog.isJustReleased();
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
