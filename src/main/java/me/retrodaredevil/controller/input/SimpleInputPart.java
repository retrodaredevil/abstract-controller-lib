package me.retrodaredevil.controller.input;

import java.util.Objects;

import me.retrodaredevil.controller.ControlConfig;
import me.retrodaredevil.controller.SimpleControllerPart;

/**
 * An abstract base class for simple InputParts that handle deadzones, AxisType and digital position
 */
public abstract class SimpleInputPart extends SimpleControllerPart implements InputPart{
	private final AxisType type;

	public SimpleInputPart(AxisType type){
		this.type = Objects.requireNonNull(type);
	}

	@Override
	public AxisType getAxisType() {
		return type;
	}

	/**
	 * @return true if the current getValue() is in a deadzone or close enough to 0
	 */
	@Override
	public boolean isDeadzone() {
		return Math.abs(getPosition()) <= getDeadzone(getAxisType(), config);
	}

	/**
	 * Gets the deadzone based only on analog and full from type
	 * @param type The axis type
	 * @param config The config that stores the deadzone
	 * @return The deadzone
	 */
	public static double getDeadzone(AxisType type, ControlConfig config){
		if(type.isFull()){
			if(type.isAnalog()){
				return config.getFullAnalogDeadzone();
			}
			return config.getFullDigitalDeadzone();
		} else if(type.isAnalog()){ // !isFull()
			return config.getAnalogDeadzone();
		}
		return config.getDigitalDeadzone();
	}

	@Override
	public int getDigitalPosition() {
		double value = getPosition();
		if(!getAxisType().isFull()){
			if(value > config.getButtonDownDeadzone()){
				return 1;
			}
		} else {
			if (value > config.getButtonDownDeadzone()) {
				return 1;
			} else if (value < -config.getButtonDownDeadzone()) {
				return -1;
			}
		}

		return 0;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{AxisType:" + type + ",hashCode:" + Integer.toHexString(hashCode()) + "}";
	}
}
