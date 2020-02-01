package me.retrodaredevil.controller.input.implementations;

import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.InputPartUtils;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

/**
 * This class can be used to make an input require multiple buttons to be pressed at once
 */
public class LowestPositionInputPart extends SimpleInputPart {

	protected final List<InputPart> inputParts;
	protected final boolean ignoreIfDisconnected;

	/**
	 * NOTE: If two or more {@link InputPart}s in the list are tied for the lowest position, the one that comes first in
	 * the list will take priority
	 * @param ignoreIfDisconnected true if you want something that is disconnected to be ignored. (Recommended to be true)
	 * @param inputParts The list of InputParts. Each that does not have a parent will have its parent set to this
	 */
	public LowestPositionInputPart(boolean ignoreIfDisconnected, List<InputPart> inputParts, boolean updateParts) {
		super(InputPartUtils.autoAxisTypeHelper(inputParts));
		this.inputParts = inputParts;
		this.ignoreIfDisconnected = ignoreIfDisconnected;
		if(updateParts) {
			partUpdater.addPartsAssertNonePresent(inputParts);
		}
	}
	/**
	 * NOTE: If two or more {@link InputPart}s in the list are tied for the lowest position, the one that comes first in
	 * the list will take priority
	 * @param ignoreIfDisconnected true if you want something that is disconnected to be ignored. (Recommended to be true)
	 * @param inputParts The InputParts
	 */
	@Deprecated
	public LowestPositionInputPart(boolean ignoreIfDisconnected, InputPart... inputParts){
		this(ignoreIfDisconnected, Arrays.asList(inputParts), true);
	}
	/**
	 * Calls {@link #LowestPositionInputPart(boolean, InputPart...)} with ignoreIfDisconnected = false
	 * @param inputParts The InputParts
	 */
	@Deprecated
	public LowestPositionInputPart(InputPart... inputParts){
		this(false, inputParts);
	}

	@Override
	public double getPosition() {
		Double lowest = null;
		for(InputPart part : inputParts){
			if(ignoreIfDisconnected && !part.isConnected()){
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
			if(!part.isConnected()){
				if(ignoreIfDisconnected){
					continue;
				} else {
					return false;
				}
			}
			if(!part.isDown()){
				return false;
			}
		}
		return !inputParts.isEmpty();
	}

	@Override
	public boolean isJustPressed() {
		boolean pressed = false;
		for(InputPart part : inputParts){
			if(!part.isConnected()){
				if(ignoreIfDisconnected){
					continue;
				} else {
					return false;
				}
			}
			if(!part.isDown()){
				return false;
			}
			pressed = pressed || part.isJustPressed();
		}
		return pressed;
	}

	@Override
	public boolean isJustReleased() {
		boolean anyReleased = false;
		int down = 0;

		for(InputPart part : inputParts){
			if(part.isJustReleased()){
				anyReleased = true;
			} else if(part.isDown()){
				down++;
			}
		}
		return anyReleased && (down == inputParts.size() - 1);
	}

	@Override
	public boolean isConnected() {
		if(ignoreIfDisconnected){
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
