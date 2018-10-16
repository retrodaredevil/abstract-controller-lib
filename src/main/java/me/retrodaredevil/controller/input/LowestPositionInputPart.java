package me.retrodaredevil.controller.input;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

/**
 * This class can be used to make an input require multiple buttons to be pressed at once
 */
public class LowestPositionInputPart extends SimpleInputPart {

	private final List<InputPart> inputParts;
	private final boolean allowNotConnected;

	/**
	 * @param allowNotConnected true if you want something that isn't connected to be treated like it isn't there
	 * @param inputParts The list of InputParts. Each that does not have a parent will have its parent set to this
	 */
	public LowestPositionInputPart(boolean allowNotConnected, List<InputPart> inputParts) {
		super(HighestPositionInputPart.autoAxisTypeHelper(inputParts));
		this.inputParts = inputParts;
		this.allowNotConnected = allowNotConnected;
		addChildren(inputParts, false, true);
	}
	/**
	 * @param allowNotConnected true if you want something that isn't connected to be treated like it isn't there
	 * @param inputParts The InputParts
	 */
	public LowestPositionInputPart(boolean allowNotConnected, InputPart... inputParts){
		this(allowNotConnected, Arrays.asList(inputParts));
	}
	/**
	 * Calls {@link #LowestPositionInputPart(boolean, InputPart...)} with allowNotConnected = false
	 * @param inputParts The InputParts
	 */
	public LowestPositionInputPart(InputPart... inputParts){
		this(false, inputParts);
	}

	@Override
	public double getPosition() {
		Double lowest = null;
		for(InputPart part : inputParts){
			if(allowNotConnected && !part.isConnected()){
				continue;
			}
			final double position = part.getPosition();
			if(lowest == null || abs(position) < abs(lowest)){
				lowest = position;
			}
		}
		return lowest == null ? 0 : lowest;
	}

	@Override
	public boolean isDown() {
		for(InputPart part : inputParts){
			if(!part.isDown()){
				return false;
			}
		}
		return !inputParts.isEmpty();
	}

	@Override
	public boolean isPressed() {
		boolean pressed = false;
		for(InputPart part : inputParts){
			if(!part.isDown()){
				return false;
			}
			pressed = pressed || part.isPressed();
		}
		return pressed;
	}

	@Override
	public boolean isReleased() {
		boolean anyReleased = false;
		int down = 0;

		for(InputPart part : inputParts){
			if(part.isReleased()){
				anyReleased = true;
			} else if(part.isDown()){
				down++;
			}
		}
		return anyReleased && (down == inputParts.size() - 1);
	}

	@Override
	public boolean isConnected() {
		if(allowNotConnected){
			for(InputPart part : inputParts){
				if(part.isConnected()){
					return true;
				}
			}
			return false;
		}
		for(InputPart part : inputParts){
			if(!part.isConnected()){
				return false;
			}
		}
		return !inputParts.isEmpty();
	}
}
