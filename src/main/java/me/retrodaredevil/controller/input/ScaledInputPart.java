package me.retrodaredevil.controller.input;

import static java.lang.Math.*;

public class ScaledInputPart extends SimpleInputPart {
	private final InputPart inputPart;
	
	public ScaledInputPart(AxisType type, InputPart inputPart) {
		super(type);
		this.inputPart = inputPart;
		addChildren(false, true, inputPart);
	}
	
	@Override
	public double getPosition() {
		final AxisType neededType = getAxisType();
		final AxisType axisType = inputPart.getAxisType();
		final double position = getRoundedPosition();
		if(neededType.isFull() && !axisType.isFull()){ // scale [0..1] to [-1..1]
			if(position < 0){
				throw new IllegalStateException();
			}
			return position * 2.0 - 1.0;
		} else if(!neededType.isFull() && axisType.isFull()){ // scale [-1..1] to [0..1]
			return (position + 1.0) / 2.0;
		}
		return position;
	}
	private double getRoundedPosition(){
		final AxisType neededType = getAxisType();
		if(!neededType.isAnalog()){
			final double position = inputPart.getPosition();
			if(abs(position) >= config.getButtonDownThreshold()){
				if(neededType.isRangeOver()){
					return signum(position) * ceil(abs(position));
				}
				return signum(position);
			}
		}
		return inputPart.getPosition();
	}
	
	@Override
	public boolean isDown() {
		return getPosition() >= config.getButtonDownThreshold();
	}
	
	@Override
	public boolean isPressed() {
		return false;
	}
	
	@Override
	public boolean isReleased() {
		return false;
	}
	
	@Override
	public boolean isConnected() {
		return inputPart.isConnected();
	}
}
