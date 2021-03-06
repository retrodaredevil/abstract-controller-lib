package me.retrodaredevil.controller.input;

import me.retrodaredevil.controller.ControlConfig;

/**
 * Represents input that can be digital, analog and can have full range, not full range, or range over
 */
public interface InputPart extends DeadzoneablePart {
	/**
	 * Even if called multiple times, the value of this should not be different
	 * @return returns an {@link AxisType} object with info on what getPosition() is allowed to return
	 */
	AxisType getAxisType();

	/**
	 * @return true if the value returned by {@link #getPosition()} is close enough to 0 to be ignored.
	 * @throws me.retrodaredevil.controller.ControllerPartNotUpdatedException Thrown if this part isn't
	 * 			updated. This is not required to be thrown
	 */
	@Override
	boolean isDeadzone();

	/**
	 * Depending on what {@link #getAxisType()} returns, the returned value may have a smaller range.
	 * <br/>
	 * NOTE: The deadzone is not applied to this. If you want to check if this is within the deadzone,
	 * use {@link #isDeadzone()}
	 * @return The value of this axis/button. Range changes based on getAxisType() but will never be outside [-1, 1]
	 * @throws me.retrodaredevil.controller.ControllerPartNotUpdatedException Thrown if this part isn't
	 * 			updated. This is not required to be thrown
	 */
	double getPosition();
	
	/**
	 * Can be used for all AxisTypes.
	 * <p>
     * If this axis type is "full" then this will return true when the absolute values moves enough
     * in either direction
	 * @return true if this input part is currently down
	 * @throws me.retrodaredevil.controller.ControllerPartNotUpdatedException Thrown if this part isn't
	 * 			updated. This is not required to be thrown
	 */
	boolean isDown();

	/**
	 * Is this InputPart "just" pressed
	 * @return returns true the first frame the key is pressed down.
	 * @throws me.retrodaredevil.controller.ControllerPartNotUpdatedException Thrown if this part isn't
	 * 			updated. This is not required to be thrown
	 */
	boolean isJustPressed();

	/**
	 *
	 * @return true the first frame the button wasn't down
	 * @throws me.retrodaredevil.controller.ControllerPartNotUpdatedException Thrown if this part isn't
	 * 			updated. This is not required to be thrown
	 */
	boolean isJustReleased();


	/**
	 * NOTE: This will only ever return 1, 0, and -1
	 * <p>
	 * If {@link #isDown()} == true, then this should return 1 or -1
	 * @return returns {@link #getPosition()} unless {@link #getAxisType()}.{@link AxisType#isAnalog() isAnalog()} == true
	 * 			or {@link #getAxisType()}.{@link AxisType#isRangeOver() isRangeOver()} == true, it will round it using the
	 *          {@link ControlConfig#getButtonDownThreshold()}
	 * @throws me.retrodaredevil.controller.ControllerPartNotUpdatedException Thrown if this part isn't
	 * 			updated. This is not required to be thrown
	 */
	int getDigitalPosition();
	
}
