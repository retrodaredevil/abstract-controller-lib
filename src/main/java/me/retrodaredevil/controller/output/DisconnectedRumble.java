package me.retrodaredevil.controller.output;

import me.retrodaredevil.controller.ControlConfig;

/**
 * Instead of using null, an instance of this should be used in places where rumble isn't supported
 * but an instance is needed.
 *
 * NOTE that you do not need to update this. Updating it will do nothing
 */
public class DisconnectedRumble implements ControllerRumble {
	
	private DisconnectedRumble(){
	}
	
	private enum InstanceHolder {
		INSTANCE;
		private final ControllerRumble rumble;
		
		InstanceHolder() {
			rumble = new DisconnectedRumble();
		}
	}
	public static ControllerRumble getInstance(){
		return InstanceHolder.INSTANCE.rumble;
	}
	
	@Override
	public void update(ControlConfig config) {
	
	}
	
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
