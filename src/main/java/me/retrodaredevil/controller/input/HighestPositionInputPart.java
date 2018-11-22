package me.retrodaredevil.controller.input;

import java.util.Arrays;
import java.util.List;

import me.retrodaredevil.controller.ControllerPart;

/**
 * This class can be used if you want to map two buttons to the same control.
 * <p>
 * Even though this class relies upon other InputParts, it does not matter if they were updated
 * before or after this is updated because we don't rely upon them in our update()
 */
public class HighestPositionInputPart extends SimpleInputPart {

	private final List<InputPart> parts;
	private final boolean allowMultiplePressesAndReleases;

	/**
	 *
	 * @param parts The controller parts to use. If a part's parent is null, it will automatically be handled by this, otherwise,
	 *              each part that already has a parent must update its position
	 */
	public HighestPositionInputPart(InputPart... parts){
		this(Arrays.asList(parts));
	}
	public HighestPositionInputPart(List<InputPart> parts){
		this(parts, false);
	}
	public HighestPositionInputPart(List<InputPart> parts, boolean allowMultiplePressesAndReleases){
		super(autoAxisTypeHelper(parts));
		this.parts = parts;
		this.allowMultiplePressesAndReleases = allowMultiplePressesAndReleases;
		addChildren(this.parts, false, true);
	}
	static AxisType autoAxisTypeHelper(Iterable<InputPart> parts){
		boolean anyFull = false;
		boolean anyAnalog = false;
		for(InputPart part : parts){
			AxisType type = part.getAxisType();

			anyFull = anyFull || type.isFull();
			anyAnalog = anyAnalog || type.isAnalog();
		}
		return new AxisType(anyFull, anyAnalog, false, true);
	}

	@Override
	public double getPosition(){
		double value = 0;
		for(InputPart part : parts){
			if(!part.isConnected()){
				continue;
			}
			double position = part.getPosition();
			if(Math.abs(position) > Math.abs(value)){
				value = position;
			} else if(position == -value){
				value = 0;
			}
		}
		return value;
	}

	@Override
	public boolean isDown() {
		for(InputPart part : parts){
			if(!part.isConnected()){
				continue;
			}
			if(part.isDown()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPressed() {
		boolean onePressed = false;
		for(InputPart part : parts){
			boolean pressed = part.isPressed();
			if(allowMultiplePressesAndReleases){
				if(pressed) return true;
			} else if(part.isDown() && !pressed){
				return false;
			} else if(!onePressed){
				onePressed = pressed;
			}
		}
		return onePressed;
	}

	@Override
	public boolean isReleased() {
		boolean oneReleased = false;
		for(InputPart part : parts){
			boolean released = part.isReleased();
			if(allowMultiplePressesAndReleases){
				if(released) return true;
			} else if(part.isDown()){
				return false;
			} else if(!oneReleased){
				oneReleased = released;
			}
		}
		return oneReleased;
	}

	@Override
	public boolean isConnected() {
		for(InputPart part : parts){
			if(part.isConnected()){
				return true;
			}
		}
		return false;
	}
}
