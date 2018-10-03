package me.retrodaredevil.controller.types;

import me.retrodaredevil.controller.output.ControllerRumble;

public interface RumbleCapableController {
	/**
	 * NOTE: Should not return null
	 * @return The ControllerRumble object that may or may not be connected
	 */
	ControllerRumble getRumble();
}
