package me.retrodaredevil.controller.input;

import me.retrodaredevil.controller.ControllerPart;

public interface DeadzoneablePart extends ControllerPart {
	/**
	 * Depending on the implementation this may or may not return true. If it is a joystick, this
	 * will return true when centered. If this is an InputPart, it will return true when at 0.
	 * <p>
	 * The purpose of this method is not to allow a large deadzone, it is just meant to indicate when a joystick
	 * is centered or when something is truly at zero.
	 * @return true if the controller part's position is close enough to 0 to indicate that the reading
	 * 			may not be accurate.
	 */
	boolean isDeadzone();
}
