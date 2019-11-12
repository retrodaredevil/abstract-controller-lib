package me.retrodaredevil.controller.implementations.mappings;

import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.implementations.ExtremeFlightJoystickInputCreator;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

public class DefaultExtremeFlightJoystickInputCreator implements ExtremeFlightJoystickInputCreator {
	@Override
	public InputPart createGridUpperLeft(ControllerPartCreator controller) {
		return controller.createDigital(6);
	}

	@Override
	public InputPart createGridUpperRight(ControllerPartCreator controller) {
		return controller.createDigital(7);
	}

	@Override
	public InputPart createGridMiddleLeft(ControllerPartCreator controller) {
		return controller.createDigital(8);
	}

	@Override
	public InputPart createGridMiddleRight(ControllerPartCreator controller) {
		return controller.createDigital(9);
	}

	@Override
	public InputPart createGridLowerLeft(ControllerPartCreator controller) {
		return controller.createDigital(10);
	}

	@Override
	public InputPart createGridLowerRight(ControllerPartCreator controller) {
		return controller.createDigital(11);
	}

	@Override
	public InputPart createTrigger(ControllerPartCreator controller) {
		return controller.createDigital(0);
	}

	@Override
	public InputPart createThumbButton(ControllerPartCreator controller) {
		return controller.createDigital(1);
	}

	@Override
	public InputPart createThumbLeftUpper(ControllerPartCreator controller) {
		return controller.createDigital(4);
	}

	@Override
	public InputPart createThumbLeftLower(ControllerPartCreator controller) {
		return controller.createDigital(2);
	}

	@Override
	public InputPart createThumbRightUpper(ControllerPartCreator controller) {
		return controller.createDigital(5);
	}

	@Override
	public InputPart createThumbRightLower(ControllerPartCreator controller) {
		return controller.createDigital(3);
	}

	@Override
	public JoystickPart createMainJoystick(ControllerPartCreator controller) {
		return controller.createJoystick(0, 1);
	}

	@Override
	public InputPart createTwist(ControllerPartCreator controller) {
		return controller.createFullAnalog(2, false);
	}

	@Override
	public InputPart createSlider(ControllerPartCreator controller) {
		return controller.createFullAnalog(3, true);
	}

	@Override
	public JoystickPart createDPad(ControllerPartCreator controller) {
		return controller.createPov(0, 4, 5);
	}
}
