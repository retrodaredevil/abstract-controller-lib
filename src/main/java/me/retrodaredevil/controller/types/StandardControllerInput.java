package me.retrodaredevil.controller.types;

import me.retrodaredevil.controller.types.subtypes.ClickDualJoystickControllerInput;

/**
 * This class can be used to store and access the standard layout on most modern controllers. Note that when
 * implementing, you should NEVER return null
 * <p>
 * None of the values returned defined in this interface should be null, however some may not be
 * "connected"
 */
public interface StandardControllerInput extends ClickDualJoystickControllerInput, PS1ControllerInput {

}
