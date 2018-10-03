package me.retrodaredevil.controller.output;

import me.retrodaredevil.controller.SimpleControllerPart;

/**
 * Instead of using null, an instance of this should be used in places where rumble isn't supported
 * but an instance is needed.
 */
public class DisconnectedRumble extends SimpleControllerPart implements ControllerRumble {
	@Override
	public void rumbleForever(double intensity) { throwException(); }
	@Override
	public void rumbleForever(double leftIntensity, double rightIntensity) { throwException(); }
	@Override
	public void rumbleTimeout(long millisTimeout, double leftIntensity, double rightIntensity) { throwException(); }
	@Override
	public void rumbleTimeout(long millisTimeout, double intensity) { throwException(); }

	@Override
	public void rumbleTime(long millis, double leftIntensity, double rightIntensity) { throwException(); }
	@Override
	public void rumbleTime(long millis, double intensity) { throwException(); }

	private void throwException(){
		throw new UnsupportedOperationException(getClass().getSimpleName() + " doesn't support rumble! It's a dummy!");
	}

	@Override
	public boolean isAnalogRumbleSupported() {
		return false;
	}

	@Override
	public boolean isAnalogRumbleNativelySupported() {
		return false;
	}

	@Override
	public boolean isLeftAndRightSupported() {
		return false;
	}

	@Override
	public boolean isConnected() {
		return false;
	}
}
