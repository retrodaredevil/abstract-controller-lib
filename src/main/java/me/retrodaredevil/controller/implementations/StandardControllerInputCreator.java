package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

public interface StandardControllerInputCreator {
	InputPart createStart(ControllerPartCreator controller);
	InputPart createSelect(ControllerPartCreator controller);

	InputPart createYButton(ControllerPartCreator controller);
	InputPart createAButton(ControllerPartCreator controller);
	InputPart createXButton(ControllerPartCreator controller);
	InputPart createBButton(ControllerPartCreator controller);

	InputPart createLeftBumper(ControllerPartCreator controller);
	InputPart createRightBumper(ControllerPartCreator controller);
	InputPart createLeftTrigger(ControllerPartCreator controller);
	InputPart createRightTrigger(ControllerPartCreator controller);

	InputPart createLeftStick(ControllerPartCreator controller);
	InputPart createRightStick(ControllerPartCreator controller);

	JoystickPart createDPad(ControllerPartCreator controller);
	JoystickPart createLeftJoy(ControllerPartCreator controller);
	JoystickPart createRightJoy(ControllerPartCreator controller);
}
