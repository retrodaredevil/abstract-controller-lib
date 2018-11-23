package me.retrodaredevil.controller.implementations.mappings;

import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.implementations.StandardControllerInputCreator;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

/**
 * PS4 Mappings for a {@link me.retrodaredevil.controller.types.StandardControllerInput}
 */
public class PS4StandardControllerInputCreator implements StandardControllerInputCreator {
	// useful image: https://delightlylinux.files.wordpress.com/2016/02/ds4buttons.jpg


	@Override public InputPart createStart(ControllerPartCreator controller) {
		return controller.createDigital(9);
	}
	@Override public InputPart createSelect(ControllerPartCreator controller) {
		return controller.createDigital(8);
	}

	@Override public InputPart createYButton(ControllerPartCreator controller) {
		return controller.createDigital(3);
	}
	@Override public InputPart createAButton(ControllerPartCreator controller) {
		return controller.createDigital(1);
	}
	@Override public InputPart createXButton(ControllerPartCreator controller) {
		return controller.createDigital(0);
	}
	@Override public InputPart createBButton(ControllerPartCreator controller) {
		return controller.createDigital(2);
	}

	@Override public InputPart createLeftBumper(ControllerPartCreator controller) {
		return controller.createDigital(4);
	}
	@Override public InputPart createRightBumper(ControllerPartCreator controller) {
		return controller.createDigital(5);
	}
	@Override public InputPart createLeftTrigger(ControllerPartCreator controller) {
		return controller.createTrigger(6, 3);
	}
	@Override public InputPart createRightTrigger(ControllerPartCreator controller) {
		return controller.createTrigger(7, 4);
	}

	@Override public InputPart createLeftStick(ControllerPartCreator controller) {
		return controller.createDigital(10);
	}
	@Override public InputPart createRightStick(ControllerPartCreator controller) {
		return controller.createDigital(11);
	}

	@Override public JoystickPart createDPad(ControllerPartCreator controller) {
		return controller.createPOV(0);
	}
	@Override public JoystickPart createLeftJoy(ControllerPartCreator controller) {
		return controller.createJoystick(0, 1);
	}
	@Override public JoystickPart createRightJoy(ControllerPartCreator controller) {
		return controller.createJoystick(2, 5);
	}

	@Override public Boolean getPhysicalLocationsSwapped() {
        return false;
	}
	@Override public Boolean getButtonNamesSwapped() {
        return false;
	}
}
