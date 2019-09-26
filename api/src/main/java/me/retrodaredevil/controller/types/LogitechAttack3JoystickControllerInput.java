package me.retrodaredevil.controller.types;

import me.retrodaredevil.controller.input.InputPart;

public interface LogitechAttack3JoystickControllerInput extends BasicFlightJoystickControllerInput {
	InputPart getLeftUpper();
	InputPart getLeftLower();

	InputPart getCenterLeft();
	InputPart getCenterRight();

	InputPart getRightUpper();
	InputPart getRightLower();

	InputPart getThumbUpper();
	InputPart getThumbLower();
	InputPart getThumbLeft();
	InputPart getThumbRight();
}
