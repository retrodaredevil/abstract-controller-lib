package me.retrodaredevil.controller.input;

public class DummyInputPart extends AutoCachingInputPart {
	private double actualPosition;
	private boolean connected = true;

	public DummyInputPart(AxisType axisType, double position){
		super(check(axisType, position));
		this.actualPosition = position;
	}
	public DummyInputPart(double position, boolean full){
		this(position, full, Math.abs(position) > 1);
	}
	public DummyInputPart(double position, boolean full, boolean rangeOver){
		this(new AxisType(full, true, rangeOver, true), position);
	}
	public DummyInputPart(int position, boolean full){
		this(position, full, Math.abs(position) > 1);
	}
	public DummyInputPart(int position, boolean full, boolean rangeOver){
		this(new AxisType(full, false, rangeOver, true), position);
	}
	private static AxisType check(AxisType type, double position){

		if(!type.isRangeOver() && Math.abs(position) > 1){
			throw new IllegalArgumentException("Must be range over if abs(position) > 1");
		}
		if(!type.isFull() && position < 0){
			throw new IllegalArgumentException("Must be full if position < 1");
		}
		if(!type.isAnalog() && position != Math.floor(position)){
			throw new IllegalArgumentException("Must be analog if position is going to be a decimal");
		}
		return type;
	}

	public void setPosition(double position){
		this.actualPosition = position;
		check(getAxisType(), position);
	}
	public void setPosition(int position){
		setPosition((double) position);
	}
	public void setConnected(boolean connected){
		this.connected = connected;
	}

	@Override
	protected double calculatePosition() {
		return actualPosition;
	}

	@Override
	public double getPosition() {
		return actualPosition;
	}

	@Override
	public boolean isConnected() {
        return connected;
	}
}
