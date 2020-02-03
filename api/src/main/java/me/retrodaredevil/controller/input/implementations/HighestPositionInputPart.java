package me.retrodaredevil.controller.input.implementations;

import java.util.Arrays;
import java.util.List;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.InputPartUtils;

/**
 * This class can be used if you want to map two buttons to the same control.
 * <p>
 * Even though this class relies upon other InputParts, it does not matter if they were updated
 * before or after this is updated because we don't rely upon them in our update()
 */
public class HighestPositionInputPart extends SimpleInputPart {

	private final List<InputPart> parts;
	private final boolean allowMultiplePressesAndReleases;

	public HighestPositionInputPart(List<InputPart> parts, boolean allowMultiplePressesAndReleases, boolean updateParts){
		super(InputPartUtils.autoAxisTypeHelper(parts));
		this.parts = parts;
		this.allowMultiplePressesAndReleases = allowMultiplePressesAndReleases;
		if(updateParts) {
			partUpdater.addPartsAssertNonePresent(parts);
		}
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
	public boolean isJustPressed() {
		boolean onePressed = false;
		for(InputPart part : parts){
			boolean pressed = part.isJustPressed();
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
	public boolean isJustReleased() {
		boolean oneReleased = false;
		for(InputPart part : parts){
			boolean released = part.isJustReleased();
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
