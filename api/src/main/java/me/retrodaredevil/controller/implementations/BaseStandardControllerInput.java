package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.SimpleControllerInput;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.options.OptionValue;
import me.retrodaredevil.controller.output.ControllerRumble;
import me.retrodaredevil.controller.types.RumbleCapableController;
import me.retrodaredevil.controller.types.StandardControllerInput;

public class BaseStandardControllerInput extends SimpleControllerInput implements StandardControllerInput, RumbleCapableController{

	private final ControllerPartCreator controller;
	private final OptionValue physicalLocationsSwapped;
	private final OptionValue buttonNamesCodeSwapped;

	private final InputPart start, select,
			yButton, aButton, xButton, bButton,
			leftBumper, rightBumper,
			leftTrigger, rightTrigger,
			leftStick, rightStick;

	private final JoystickPart dPad, leftJoy, rightJoy;

	private final ControllerRumble rumble;


	/**
	 *
	 * @param controller The object to help create parts of the controller
	 * @param physicalLocationsSwapped OptionValue representing if the face left and face down are swapped
	 *                                 or if the face right and face up are swapped. e.g: true for most nintendo controllers
	 * @param buttonNamesCodeSwapped OptionValue representing if the A/B buttons are swapped of the X/Y buttons are swapped.
	 *                               Normally false because most (nintendo) controllers only change the physical locations, not the codes.
	 */
	public BaseStandardControllerInput(StandardControllerInputCreator creator, ControllerPartCreator controller, OptionValue physicalLocationsSwapped, OptionValue buttonNamesCodeSwapped){
		this.controller = controller;
		this.physicalLocationsSwapped = physicalLocationsSwapped;
		this.buttonNamesCodeSwapped = buttonNamesCodeSwapped;
		if(!physicalLocationsSwapped.isOptionValueBoolean()){
			throw new IllegalArgumentException("physicalLocationsSwapped must be a boolean option value");
		}
		if(!buttonNamesCodeSwapped.isOptionValueBoolean()){
			throw new IllegalArgumentException("buttonNamesCodeSwapped must be a boolean option value");
		}

		start = creator.createStart(controller);
		select = creator.createSelect(controller);

		yButton = creator.createYButton(controller);
		aButton = creator.createAButton(controller);
		xButton = creator.createXButton(controller);
		bButton = creator.createBButton(controller);

		leftBumper = creator.createLeftBumper(controller);
		rightBumper = creator.createRightBumper(controller);

		leftTrigger = creator.createLeftTrigger(controller);
		rightTrigger = creator.createRightTrigger(controller);

		leftStick = creator.createLeftStick(controller);
		rightStick = creator.createRightStick(controller);

		dPad = creator.createDPad(controller);
		leftJoy = creator.createLeftJoy(controller);
		rightJoy = creator.createRightJoy(controller);

		rumble = controller.createRumble();


		// the axises already have parents and we don't want to change them
		partUpdater.addPartsAssertNonePresent(
				start, select, yButton, aButton, xButton, bButton,
				leftBumper, rightBumper, leftTrigger, rightTrigger,
				leftStick, rightStick,
				dPad, leftJoy, rightJoy, rumble
		);

	}

	@Override
	public JoystickPart getDPad() {
		return dPad;
	}

	@Override
	public JoystickPart getLeftJoy() {
		return leftJoy;
	}

	@Override
	public JoystickPart getRightJoy() {
		return rightJoy;
	}

	@Override
	public InputPart getLeftStick() {
		return leftStick;
	}

	@Override
	public InputPart getRightStick() {
		return rightStick;
	}

	@Override
	public InputPart getStart() {
		return start;
	}

	@Override
	public InputPart getSelect() {
		return select;
	}

	@Override
	public InputPart getFaceUp() {
		return physicalLocationsSwapped.getBooleanOptionValue() ? xButton : yButton;
	}
	@Override
	public InputPart getFaceDown() {
		return physicalLocationsSwapped.getBooleanOptionValue() ? bButton : aButton;
	}
	@Override
	public InputPart getFaceLeft() {
		return physicalLocationsSwapped.getBooleanOptionValue() ? yButton : xButton;
	}
	@Override
	public InputPart getFaceRight() {
		return physicalLocationsSwapped.getBooleanOptionValue() ? aButton : bButton;
	}

	@Override
	public InputPart getAButton() {
		return buttonNamesCodeSwapped.getBooleanOptionValue() ? bButton : aButton;
	}
	@Override
	public InputPart getBButton() {
		return buttonNamesCodeSwapped.getBooleanOptionValue() ? aButton : bButton;
	}
	@Override
	public InputPart getXButton() {
		return buttonNamesCodeSwapped.getBooleanOptionValue() ? yButton : xButton;
	}
	@Override
	public InputPart getYButton() {
		return buttonNamesCodeSwapped.getBooleanOptionValue() ? xButton : yButton;
	}

	@Override
	public InputPart getLeftBumper() {
		return leftBumper;
	}

	@Override
	public InputPart getRightBumper() {
		return rightBumper;
	}

	@Override
	public InputPart getLeftTrigger() {
		return leftTrigger;
	}

	@Override
	public InputPart getRightTrigger() {
		return rightTrigger;
	}

	@Override
	public boolean isConnected() {
		return controller.isConnected();
	}

	@Override
	public ControllerRumble getRumble() {
		return rumble;
	}
}
