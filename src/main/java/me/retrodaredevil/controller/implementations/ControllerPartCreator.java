package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;

public interface ControllerPartCreator {
	/**
	 * Creates a digital button using the code
	 * @param code The code of the button. This can be 0 but cannot be less than 0
	 * @return The created InputPart
	 */
	InputPart createDigital(int code);

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

	JoystickPart createJoystick(int xAxis, int yAxis);

	/**
	 * Creates a full analog InputPart usually used for sliders
	 * @param axisCode
	 * @return
	 */
	InputPart createFullAnalog(int axisCode);

	/**
	 * Creates an analog InputPart usually used for triggers that you know must be analog
	 * @param axisCode
	 * @return
	 */
	InputPart createAnalog(int axisCode);

	InputPart createTrigger(int digitalCode, int analogCode);

	ControllerRumble createRumble();

	boolean isConnected();
	String getName();
}
