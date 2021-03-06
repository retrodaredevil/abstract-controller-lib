package me.retrodaredevil.controller;

public class MutableControlConfig implements ControlConfig{
	public double buttonDownDeadzone = .5;
	public double analogDeadzone = .005;
	public double fullAnalogDeadzone = .005;
	public double digitalDeadzone = .005;
	public double fullDigitalDeadzone = digitalDeadzone;

	public double rangeOverDeadzone = 0;

	public boolean cacheAngleAndMagnitudeInUpdate = false;
	public boolean useAbstractedIsDownIfPossible = true;

	public double switchToSquareInputThreshold = 1.01;

	@Override
	public double getButtonDownThreshold() { return buttonDownDeadzone; }
	@Override
	public double getAnalogDeadzone() { return analogDeadzone; }
	@Override
	public double getFullAnalogDeadzone() { return fullAnalogDeadzone; }
	@Override
	public double getDigitalDeadzone() { return digitalDeadzone; }
	@Override
	public double getFullDigitalDeadzone() { return fullDigitalDeadzone; }
	@Override
	public double getChangeInPositionDeadzone() { return rangeOverDeadzone; }

	@Override
	public boolean isCacheAngleAndMagnitudeInUpdate() { return cacheAngleAndMagnitudeInUpdate; }
	@Override
	public boolean isUseAbstractedIsDownIfPossible() { return useAbstractedIsDownIfPossible; }
	@Override
	public double getSwitchToSquareInputThreshold() { return switchToSquareInputThreshold; }
}
