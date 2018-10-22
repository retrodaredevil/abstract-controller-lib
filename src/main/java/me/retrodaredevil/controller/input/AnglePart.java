package me.retrodaredevil.controller.input;

/**
 * Represents a ControllerPart where its input is an angle in degrees.
 * <br/>
 * Can have numerous uses such as a joystick, steering wheel, gyro, etc.
 */
public interface AnglePart extends DeadzoneablePart {

	/** @return angle of the AnglePart in degrees. */
	double getAngle();

	/** @return angle of the AnglePart in radians. */
	double getAngleRadians();

}
