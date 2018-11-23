package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;

/**
 * NOTE: Every code/axi code/pov number starts from 0. When implementing this class, you should make
 * sure that 0 is an acceptable value. If it isn't, you should probably add 1 to the passed value and
 * use that so classes such as {@link DefaultStandardControllerInputCreator} can be used
 */
public interface ControllerPartCreator {
	/**
	 * Creates a digital button using the code
	 * @param code The code of the button. This can be 0 but cannot be less than 0
	 * @return The created InputPart
	 */
	InputPart createDigital(int code);

	// region POVs
	/**
	 * Creates a POV joystick
	 * <p>
	 * Depending on the implementation, either dPadNumber should be used or xAxis and yAxis should be used.
	 * Some implementations are only able to use one or the other, so both must be accurate.
	 * <p>
	 * If you do not know the xAxis or yAxis for certain, use {@link #createPOV(int)}
	 * @param povNumber The number of the POV. (The most reliable)
	 * @param xAxis The code for the x axis of the POV. (May vary from controller to controller)
	 * @param yAxis The code for the y axis of the POV. (May vary from controller to controller)
	 * @return The created JoystickPart
	 */
	JoystickPart createPOV(int povNumber, int xAxis, int yAxis);
	/**
	 * Creates a POV joystick
	 * Some implementations are only able to use one or the other, so both must be accurate.
	 * @see #createPOV(int, int)
	 * @see #createPOV(int, int, int)
	 * @param povNumber The number of the POV.
	 * @return The created JoystickPart
	 */
	JoystickPart createPOV(int povNumber);
	/**
	 * Creates a POV joystick
	 * @see #createPOV(int)
	 * @see #createPOV(int, int, int)
	 * @param xAxis The code for the x axis of the POV.
	 * @param yAxis The code for the y axis of the POV.
	 * @return The created JoystickPart
	 */
	JoystickPart createPOV(int xAxis, int yAxis);
	//endregion

	JoystickPart createJoystick(int xAxis, int yAxis);

	// region analog
	/**
	 * Creates a full analog InputPart usually used for sliders
	 * <p>
	 * {@link #createFullAnalog(int, boolean)} should be used instead of this as it can make sure the input
	 * is/isn't inverted correct
	 * @see #createFullAnalog(int, boolean)
	 * @param axisCode The axis code
	 * @return The created analog InputPart
	 */
	InputPart createFullAnalog(int axisCode);

	/**
	 * Creates an analog InputPart usually used for triggers that you know must be analog.
	 * <p>
	 * {@link #createAnalog(int, boolean)} should be used instead of this as it can make sure the input
	 * is/isn't inverted correctly
	 * @see #createAnalog(int, boolean)
	 * @param axisCode The axis code
	 * @return The created analog InputPart
	 */
	InputPart createAnalog(int axisCode);

	/**
	 * Creates a full ranged analog InputPart
	 * @param axisCode The axis code
	 * @param isVertical true if the physical layout of the input is up and down
	 * @return The created analog InputPart
	 */
	InputPart createFullAnalog(int axisCode, boolean isVertical);

	/**
	 * Creates a non-full analog InputPart.
	 * <p>
	 * Can
	 * @param axisCode The axis code
	 * @param isVertical true if the physical layout of the input is up and down
	 * @return The created analog InputPart
	 */
	InputPart createAnalog(int axisCode, boolean isVertical);

	/**
	 * Creates an analog trigger using the given axis code.
	 * @param axisCode The axis code for the trigger
	 * @return The created trigger
	 */
	InputPart createAnalogTrigger(int axisCode);
	//endregion

	/**
	 * Used to create a trigger that may also have a button representing it being pressed down.
	 * <p>
	 * If analogCode is -1, the implementation will likely function the same as {@link #createDigital(int)}
	 * @param digitalCode The button code representing if this is being pressed down
	 * @param analogCode The axis code for the trigger or -1 to ignore analogCode
	 * @return The created trigger
	 */
	InputPart createTrigger(int digitalCode, int analogCode);

	ControllerRumble createRumble();

	boolean isConnected();
	String getName();
}
