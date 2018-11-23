package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;

public interface StandardControllerInputCreator {
	InputPart createStart(ControllerPartCreator controller);
	InputPart createSelect(ControllerPartCreator controller);

	/** Creates the Y button or Triangle on playstation*/
	InputPart createYButton(ControllerPartCreator controller);
	/** Creates the A button or Cross on playstation*/
	InputPart createAButton(ControllerPartCreator controller);
	/** Creates the X button or Square on playstation*/
	InputPart createXButton(ControllerPartCreator controller);
	/** Creates the B button or Circle on playstation*/
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

	/**
	 * Should return true for nintendo controllers.
	 * @return true if the physical locations are swapped, false if they aren't, null if it could be either way
	 */
	Boolean getPhysicalLocationsSwapped();
	/**
	 * NOTE: This should almost always return null or false because the create*Button() methods say you
	 * should be returning the correct button names. If you need to swap physical locations, create the correct
	 * inputs corresponding to the buttons and use {@link #getPhysicalLocationsSwapped()}
	 * @return true if the button names (A/B and X/Y) are swapped, false if they aren't, null if it could be either way
	 */
	Boolean getButtonNamesSwapped();
}
