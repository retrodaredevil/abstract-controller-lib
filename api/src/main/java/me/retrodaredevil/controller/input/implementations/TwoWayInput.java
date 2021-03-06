package me.retrodaredevil.controller.input.implementations;

import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;

/**
 * Represents a "full" InputPart so that you are able to use two other InputParts where one is
 * positive and the other is negative. (Can be useful to create an Axis for x and y using A and D,
 * then W and S or for other d-pad like buttons)
 */
public class TwoWayInput extends SimpleInputPart {
	private final InputPart part1;
	private final InputPart part2;

	/**
	 * the getPosition() will work like part1.getPosition() - part2.getPosition()
	 * <br/><br/>
	 * For each part1 and part2, their parent will only be set to this if they don't already have a parent. (Uses the addChildren() method)
	 * @param part1 The InputPart that will be the positive value
	 * @param part2 The InputPart that will be the negative value
	 */
	public TwoWayInput(InputPart part1, InputPart part2, boolean updateParts){
		super(new AxisType(true,
				part1.getAxisType().isAnalog() || part2.getAxisType().isAnalog(),
				part1.getAxisType().isRangeOver() || part2.getAxisType().isRangeOver(),
				part1.getAxisType().isShouldUseDelta() && part2.getAxisType().isShouldUseDelta()));
		AxisType type1 = part1.getAxisType();
		AxisType type2 = part2.getAxisType();
		if(type1.isFull() || type2.isFull()){
			throw new IllegalArgumentException("Neither part1 or part2's AxisType can be 'full'");
		}
		if(type1.isShouldUseDelta() != type2.isShouldUseDelta()){
			System.err.println("part1 and part2's isShouldUseDelta() are different! part1: " + part1 + " part2: " + part2);
		}
		this.part1 = part1;
		this.part2 = part2;
		if(updateParts) {
			partUpdater.addPartsAssertNonePresent(part1, part2);
		}
	}
	public InputPart getPart1(){
		return part1;
	}
	public InputPart getPart2(){
		return part2;
	}

	@Override
	public boolean isConnected() {
		return part1.isConnected() && part2.isConnected();
	}

	@Override
	public double getPosition() {
		return part1.getPosition() - part2.getPosition();
	}

	@Override
	public boolean isDown() {
		return part1.isDown() != part2.isDown();
	}

	@Override
	public boolean isJustPressed() {
		return (part1.isJustPressed() && !part2.isDown()) || (part2.isJustPressed() && !part1.isDown())
				|| (part1.isDown() && part2.isJustReleased()) || (part2.isDown() && part1.isJustReleased());
	}

	@Override
	public boolean isJustReleased() {
		if(part1.isJustReleased() || part2.isJustReleased()){
			return part1.isDown() == part2.isDown();
		}
		return false;
	}
}
