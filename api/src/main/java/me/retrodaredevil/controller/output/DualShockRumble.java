package me.retrodaredevil.controller.output;

import me.retrodaredevil.controller.SimpleControllerPart;

/**
 * A {@link ControllerRumble} where it supports ligth and heavy rumble. This assumes that the left rumble motor is
 * heavy and the right rumble motor is light
 */
public class DualShockRumble extends SimpleControllerPart implements ControllerRumble {
	public static final double DEFAULT_HEAVY_START = .5;
	public static final double DEFAULT_LIGHT_END = .6;
	
	private final double heavyStart;
	private final double lightEnd;
	private final ControllerRumble rumble;

	@Deprecated
	public DualShockRumble(ControllerRumble rumble) {
		this(rumble, DEFAULT_HEAVY_START, DEFAULT_LIGHT_END, true);
	}
	private DualShockRumble(ControllerRumble rumble, double heavyStart, double lightEnd, boolean updateRumble){
		this.rumble = rumble;
		this.heavyStart = heavyStart;
		this.lightEnd = lightEnd;
		if(updateRumble) {
			partUpdater.addPartsAssertNonePresent(rumble);
		}
	}
	
	@Override
	protected void onUpdate() {
		super.onUpdate();
		rumble.update(config);
	}
	
	@Override
	public void rumbleForever(double intensity) {
		rumble.rumbleForever(getHeavy(intensity), getLight(intensity));
	}
	@Override
	public void rumbleTime(long millis, double intensity) {
		rumble.rumbleTime(millis, getHeavy(intensity), getLight(intensity));
	}
	@Override
	public void rumbleTimeout(long millisTimeout, double intensity) {
		rumble.rumbleTime(millisTimeout, getHeavy(intensity), getLight(intensity));
	}
	private double getHeavy(double intensity){
		if(intensity > heavyStart){
			return (intensity - heavyStart) / (1 - heavyStart);
		}
		return 0;
	}
	private double getLight(double intensity){
		if(intensity > lightEnd){
			return 1;
		}
		return intensity / lightEnd;
	}
	
	@Override
	public void rumbleForever(double leftIntensity, double rightIntensity) {
		rumbleForever((leftIntensity + rightIntensity) / 2.0);
	}
	
	@Override
	public void rumbleTime(long millis, double leftIntensity, double rightIntensity) {
		rumbleTime(millis, (leftIntensity + rightIntensity) / 2.0);
	}
	
	@Override
	public void rumbleTimeout(long millisTimeout, double leftIntensity, double rightIntensity) {
		rumbleTimeout(millisTimeout, (leftIntensity + rightIntensity) / 2.0);
	}
	
	@Override
	public boolean isLeftAndRightSupported() {
		return false;
	}
	@Override
	public boolean isAnalogRumbleSupported() {
		return rumble.isAnalogRumbleSupported();
	}
	@Override
	public boolean isAnalogRumbleNativelySupported() {
		return rumble.isAnalogRumbleNativelySupported();
	}
	
	@Override
	public boolean isConnected() {
		return rumble.isConnected();
	}
}
