package me.retrodaredevil.controller.input;

import me.retrodaredevil.controller.ControllerPartNotUpdatedException;

/**
 * A digital button like InputPart where {@link #isPressed()} returns true when the passed InputPart
 * is released
 */
public class JustPressedInputPart extends SimpleInputPart{
	private final InputPart inputPart;
	private boolean released = false;
	private boolean wasReleased = false;

	/**
	 * If the passed InputPart doesn't have a parent, its parent will be set to this.
	 * @param inputPart The input part
	 */
	public JustPressedInputPart(InputPart inputPart){
		super(new AxisType(false, false));
		this.inputPart = inputPart;
		addChildren(false, true, inputPart);
	}

	@Override
	protected void onAfterChildrenUpdate() {
		super.onUpdate();
		wasReleased = released;
		try {
			released = inputPart.isReleased();
		} catch(ControllerPartNotUpdatedException ignored){
		}
	}

	@Override
	public double getPosition() {
		return isDown() ? 1 : 0;
	}

	@Override
	public boolean isDown() {
		return isPressed();
	}

	@Override
	public boolean isPressed() {
		return inputPart.isReleased();
	}

	@Override
	public boolean isReleased() {
		return wasReleased && !isPressed();
	}

	@Override
	public boolean isConnected() {
		return inputPart.isConnected();
	}
}
