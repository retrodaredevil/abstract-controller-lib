package me.retrodaredevil.controller.types;

import me.retrodaredevil.controller.output.ControllerRumble;

/**
 * NOTE, just because an object is an instance of a {@link RumbleCapableController}, doesn't mean
 * that it has rumble or is capable of it. You must check {@link ControllerRumble#isConnected()}
 */
public interface RumbleCapableController {
	/**
	 * NOTE: Should not return null
	 * @return The ControllerRumble object that may or may not be connected
	 */
	ControllerRumble getRumble();
}
