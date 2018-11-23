package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

public interface LogitechAttack3JoystickInputCreator {
	InputPart createTrigger(ControllerPartCreator controller);
	InputPart createThumbLower(ControllerPartCreator controller);
	InputPart createThumbUpper(ControllerPartCreator controller);
	InputPart createThumbLeft(ControllerPartCreator controller);
	InputPart createThumbRight(ControllerPartCreator controller);

	InputPart createLeftUpper(ControllerPartCreator controller);
	InputPart createLeftLower(ControllerPartCreator controller);
	InputPart createCenterLeft(ControllerPartCreator controller);
	InputPart createCenterRight(ControllerPartCreator controller);
	InputPart createRightLower(ControllerPartCreator controller);
	InputPart createRightUpper(ControllerPartCreator controller);

	JoystickPart createJoystick(ControllerPartCreator controller);
	InputPart createSlider(ControllerPartCreator controller);
}
