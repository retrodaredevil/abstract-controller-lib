package me.retrodaredevil.controller.types;

import me.retrodaredevil.controller.types.subtypes.SingleJoystickControllerInput;
import me.retrodaredevil.controller.types.subtypes.SliderControllerInput;

/**
 * Represents a single joystick controller with a slider axis
 */
public interface BasicFlightJoystickControllerInput extends SingleJoystickControllerInput, SliderControllerInput {
}
