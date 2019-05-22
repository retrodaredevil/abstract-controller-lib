package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.SimpleControllerInput;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;
import me.retrodaredevil.controller.types.ExtremeFlightJoystickControllerInput;
import me.retrodaredevil.controller.types.RumbleCapableController;

public class BaseExtremeFlightJoystickControllerInput extends SimpleControllerInput implements ExtremeFlightJoystickControllerInput, RumbleCapableController {
	private final ControllerPartCreator controller;
	private final InputPart gridUpperLeft, gridUpperRight, gridMiddleLeft, gridMiddleRight, gridLowerLeft, gridLowerRight;
	private final InputPart trigger, thumbButton, thumbLeftUpper, thumbLeftLower, thumbRightUpper, thumbRightLower;
	private final JoystickPart mainJoystick;
	private final JoystickPart dPad;
	private final InputPart twist;
	private final InputPart slider;
	private final ControllerRumble rumble;

	public BaseExtremeFlightJoystickControllerInput(ExtremeFlightJoystickInputCreator creator, ControllerPartCreator controller){
		this.controller = controller;

		gridUpperLeft = creator.createGridUpperLeft(controller);
		gridUpperRight = creator.createGridUpperRight(controller);
		gridMiddleLeft = creator.createGridMiddleLeft(controller);
		gridMiddleRight = creator.createGridMiddleRight(controller);
		gridLowerLeft = creator.createGridLowerLeft(controller);
		gridLowerRight = creator.createGridLowerRight(controller);

		trigger = creator.createTrigger(controller);
		thumbButton = creator.createThumbButton(controller);
		thumbLeftUpper = creator.createThumbLeftUpper(controller);
		thumbLeftLower = creator.createThumbLeftLower(controller);
		thumbRightUpper = creator.createThumbRightUpper(controller);
		thumbRightLower = creator.createThumbRightLower(controller);

		mainJoystick = creator.createMainJoystick(controller);
		dPad = creator.createDPad(controller);
		twist = creator.createTwist(controller);
		slider = creator.createSlider(controller);

		rumble = controller.createRumble();

		partUpdater.addPartsAssertNonePresent(
				gridUpperLeft, gridUpperRight,
				gridMiddleLeft, gridMiddleRight,
				gridLowerLeft, gridLowerRight,
				trigger, thumbButton,
				thumbLeftUpper, thumbLeftLower,
				thumbRightUpper, thumbRightLower,
				mainJoystick, dPad, twist, slider, rumble);
	}

	@Override
	public ControllerRumble getRumble() {
		return rumble;
	}

	// region grid buttons
	@Override
	public InputPart getGridUpperLeft() {
		return gridUpperLeft;
	}
	@Override
	public InputPart getGridUpperRight() {
		return gridUpperRight;
	}
	@Override
	public InputPart getGridMiddleLeft() {
		return gridMiddleLeft;
	}
	@Override
	public InputPart getGridMiddleRight() {
		return gridMiddleRight;
	}
	@Override
	public InputPart getGridLowerLeft() {
		return gridLowerLeft;
	}
	@Override
	public InputPart getGridLowerRight() {
		return gridLowerRight;
	}
	// endregion

	@Override
	public InputPart getTrigger() {
		return trigger;
	}

	// region thumb buttons
	@Override
	public InputPart getThumbButton() {
		return thumbButton;
	}
	@Override
	public InputPart getThumbLeftUpper() {
		return thumbLeftUpper;
	}
	@Override
	public InputPart getThumbLeftLower() {
		return thumbLeftLower;
	}
	@Override
	public InputPart getThumbRightUpper() {
		return thumbRightUpper;
	}
	@Override
	public InputPart getThumbRightLower() {
		return thumbRightLower;
	}
	//endregion

	@Override
	public JoystickPart getDPad() {
		return dPad;
	}

	@Override
	public InputPart getTwist() {
		return twist;
	}

	@Override
	public JoystickPart getMainJoystick() {
		return mainJoystick;
	}

	@Override
	public InputPart getSlider() {
		return slider;
	}

	@Override
	public boolean isConnected() {
		return controller.isConnected();
	}
}
