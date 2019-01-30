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
	
	// region default methods
	/** @return null if {@link #isDeadzone()} is true otherwise {@link #getAngle()}*/
	default Double getAngleOrNull(){
		return isDeadzone() ? null : getAngle();
	}
	/** @return null if {@link #isDeadzone()} is true otherwise {@link #getAngleRadians()}*/
	default Double getAngleRadiansOrNull(){
		return isDeadzone() ? null : getAngleRadians();
	}
	/** @return def if {@link #isDeadzone()} is true otherwise {@link #getAngle()}*/
	default double getAngleOrDefault(double def){
		return isDeadzone() ? def : getAngle();
	}
	/** @return def if {@link #isDeadzone()} is true otherwise {@link #getAngleRadians()}*/
	default double getAngleRadiansOrDefault(double def){
		return isDeadzone() ? def : getAngleRadians();
	}
	// endregion
}
