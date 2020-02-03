package me.retrodaredevil.controller.implementations.mappings;

import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.implementations.LogitechAttack3JoystickInputCreator;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

public class DefaultLogitechAttack3JoystickInputCreator implements LogitechAttack3JoystickInputCreator {
	@Override
	public InputPart createTrigger(ControllerPartCreator controller) {
		return controller.createDigital(0);
	}

	@Override
	public InputPart createThumbLower(ControllerPartCreator controller) {
		return controller.createDigital(1);
	}

	@Override
	public InputPart createThumbUpper(ControllerPartCreator controller) {
		return controller.createDigital(2);
	}

	@Override
	public InputPart createThumbLeft(ControllerPartCreator controller) {
		return controller.createDigital(3);
	}

	@Override
	public InputPart createThumbRight(ControllerPartCreator controller) {
		return controller.createDigital(4);
	}

	@Override
	public InputPart createLeftUpper(ControllerPartCreator controller) {
		return controller.createDigital(5);
	}

	@Override
	public InputPart createLeftLower(ControllerPartCreator controller) {
		return controller.createDigital(6);
	}

	@Override
	public InputPart createCenterLeft(ControllerPartCreator controller) {
		return controller.createDigital(7);
	}

	@Override
	public InputPart createCenterRight(ControllerPartCreator controller) {
		return controller.createDigital(8);
	}

	@Override
	public InputPart createRightLower(ControllerPartCreator controller) {
		return controller.createDigital(9);
	}

	@Override
	public InputPart createRightUpper(ControllerPartCreator controller) {
		return controller.createDigital(10);
	}

	@Override
	public JoystickPart createJoystick(ControllerPartCreator controller) {
		return controller.createJoystick(0, 1);
	}

	@Override
	public InputPart createSlider(ControllerPartCreator controller) {
		return controller.createFullAnalog(2, true);
	}
}
