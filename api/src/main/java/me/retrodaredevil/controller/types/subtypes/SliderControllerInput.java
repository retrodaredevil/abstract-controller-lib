package me.retrodaredevil.controller.types.subtypes;

import me.retrodaredevil.controller.ControllerInput;
import me.retrodaredevil.controller.input.InputPart;

/**
 * A ControllerInput with a slider
 */
public interface SliderControllerInput extends ControllerInput {
	/**
     * The returned InputPart is allowed to have a full range [-1..1] but it may also be not a full range [0..1].
	 * <p>
     * It is recommended to use {@link me.retrodaredevil.controller.input.implementations.ScaledInputPart} to make sure this is in the correct range you desire.
	 * @return The slider on the controller which should be analog and can have full range or no full range
	 */
	InputPart getSlider();
}
