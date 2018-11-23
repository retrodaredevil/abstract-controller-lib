package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

public interface ExtremeFlightJoystickInputCreator {
	// NOTE: Comments represent the indexes of the buttons and axi. (Starting from 0)

	InputPart createGridUpperLeft(ControllerPartCreator controller); // 6
	InputPart createGridUpperRight(ControllerPartCreator controller); // 7
	InputPart createGridMiddleLeft(ControllerPartCreator controller); // 8
	InputPart createGridMiddleRight(ControllerPartCreator controller); // 9
	InputPart createGridLowerLeft(ControllerPartCreator controller); // 10
	InputPart createGridLowerRight(ControllerPartCreator controller); // 11

	InputPart createTrigger(ControllerPartCreator controller); // 0
	InputPart createThumbButton(ControllerPartCreator controller); // 1
	InputPart createThumbLeftUpper(ControllerPartCreator controller); // 4
	InputPart createThumbLeftLower(ControllerPartCreator controller); // 2
	InputPart createThumbRightUpper(ControllerPartCreator controller); // 5
	InputPart createThumbRightLower(ControllerPartCreator controller); // 3

	JoystickPart createMainJoystick(ControllerPartCreator controller); // 0, 1
	InputPart createTwist(ControllerPartCreator controller); // 2
	InputPart createSlider(ControllerPartCreator controller); // 3

	JoystickPart createDPad(ControllerPartCreator controller); // pov 0 | 4, 5


}
