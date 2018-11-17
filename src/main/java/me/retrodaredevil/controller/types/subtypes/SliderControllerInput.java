package me.retrodaredevil.controller.types.subtypes;

import me.retrodaredevil.controller.ControllerInput;
import me.retrodaredevil.controller.input.InputPart;

/**
 * A ControllerInput with a slider
 */
public interface SliderControllerInput extends ControllerInput {
	/**
     * The returned InputPart is allowed to have a full range [-1..1] but it may also be not a full range [0..1]
	 * @return The slider on the controller which should be analog and can have full range or no full range
	 */
	InputPart getSlider();
}
