package me.retrodaredevil.controller.implementations;

import me.retrodaredevil.controller.SimpleControllerInput;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;
import me.retrodaredevil.controller.types.LogitechAttack3JoystickControllerInput;
import me.retrodaredevil.controller.types.RumbleCapableController;

public class BaseLogitechAttack3JoystickControllerInput extends SimpleControllerInput implements LogitechAttack3JoystickControllerInput, RumbleCapableController {


	private final ControllerPartCreator controller;

	private final InputPart trigger;
	private final InputPart leftUpper, leftLower, centerLeft, centerRight, rightUpper, rightLower;
	private final InputPart thumbUpper, thumbLower, thumbLeft, thumbRight;
	private final InputPart slider;
	private final JoystickPart joystick;
	private final ControllerRumble rumble;

	public BaseLogitechAttack3JoystickControllerInput(LogitechAttack3JoystickInputCreator creator, ControllerPartCreator controller){
		this.controller = controller;

		trigger = creator.createTrigger(controller);
		thumbLower = creator.createThumbLower(controller);
		thumbUpper = creator.createThumbUpper(controller);
		thumbLeft = creator.createThumbLeft(controller);
		thumbRight = creator.createThumbRight(controller);

		leftUpper = creator.createLeftUpper(controller);
		leftLower = creator.createLeftLower(controller);

		centerLeft = creator.createCenterLeft(controller);
		centerRight = creator.createCenterRight(controller);

		rightLower = creator.createRightLower(controller);
		rightUpper = creator.createRightUpper(controller);

		joystick = creator.createJoystick(controller);
		slider = creator.createSlider(controller);
		rumble = controller.createRumble();
		
		partUpdater.addPartsAssertNonePresent(
				trigger, thumbLower, thumbUpper, thumbLeft, thumbRight,
				leftUpper, leftLower, centerLeft, centerRight, rightLower, rightUpper, joystick, slider, rumble
		);
	}

	@Override
	public ControllerRumble getRumble() {
		return rumble;
	}

	@Override
	public boolean isConnected() {
		return controller.isConnected();
	}

	@Override
	public InputPart getLeftUpper() {
		return leftUpper;
	}

	@Override
	public InputPart getLeftLower() {
		return leftLower;
	}

	@Override
	public InputPart getCenterLeft() {
		return centerLeft;
	}

	@Override
	public InputPart getCenterRight() {
		return centerRight;
	}

	@Override
	public InputPart getRightUpper() {
		return rightUpper;
	}

	@Override
	public InputPart getRightLower() {
		return rightLower;
	}

	@Override
	public InputPart getThumbUpper() {
		return thumbUpper;
	}

	@Override
	public InputPart getThumbLower() {
		return thumbLower;
	}

	@Override
	public InputPart getThumbLeft() {
		return thumbLeft;
	}

	@Override
	public InputPart getThumbRight() {
		return thumbRight;
	}

	@Override
	public JoystickPart getMainJoystick() {
		return joystick;
	}

	@Override
	public InputPart getTrigger() {
		return trigger;
	}

	@Override
	public InputPart getSlider() {
		return slider;
	}
}
