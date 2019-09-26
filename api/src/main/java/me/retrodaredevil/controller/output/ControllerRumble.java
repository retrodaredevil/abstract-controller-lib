package me.retrodaredevil.controller.output;

import me.retrodaredevil.controller.ControllerPart;

public interface ControllerRumble extends ControllerPart {
	/**
	 * Rumbles forever until stopped by: calling this again with intensity = 0 or by calling another method
	 * with intensity = 0
	 * <p>
	 * It is recommended to use {@link #rumbleTimeout(long, double)} or {@link #rumbleTimeout(long, double, double)} because
	 * they provide the same features except if they stopped getting called continuously they will reset the rumble
	 * @param intensity The rumble intensity. If 0 stops rumble
	 */
	void rumbleForever(double intensity);
	/**
	 * Rumbles forever until stopped by: calling this again with amount = 0 or by calling another method
	 * <p>
	 * It is recommended to use {@link #rumbleTimeout(long, double)} or {@link #rumbleTimeout(long, double, double)} because
	 * they provide the same features except if they stopped getting called continuously they will reset the rumble
	 * @param leftIntensity The left rumble intensity range [0, 1]
	 * @param rightIntensity The left rumble intensity range [0, 1]
	 */
	void rumbleForever(double leftIntensity, double rightIntensity);

	/**
	 * Sets the rumble but will stop the rumble after millisTimeout milliseconds
	 * @param millisTimeout Amount of time for rumble to last (milliseconds)
	 * @param leftIntensity Left rumble intensity range: [0, 1]
	 * @param rightIntensity Right rumble intensity range: [0, 1]
	 */
	void rumbleTimeout(long millisTimeout, double leftIntensity, double rightIntensity);

	/**
	 * Sets the rumble but will stop the rumble after millisTimeout milliseconds
	 * @param millisTimeout Amount of time for rumble to last (milliseconds)
	 * @param intensity Rumble intensity range: [0, 1]
	 */
	void rumbleTimeout(long millisTimeout, double intensity);

	/**
	 * Same as {@link #rumbleTimeout(long, double, double)} except this should never be called
	 * continuously.
	 * <p>
	 * Depending on the implementation, this may less resource intensive or may provide more accurate rumble
	 */
	void rumbleTime(long millis, double leftIntensity, double rightIntensity);
	/**
	 * Same as {@link #rumbleTimeout(long, double)} except this should never be called
	 * continuously.
	 * <p>
	 * Depending on the implementation, this may less resource intensive or may provide more accurate rumble
	 */
	void rumbleTime(long millis, double intensity);

	/** @return true if analog rumble is supported at all, false if the intensity will only be interpreted as on or off*/
	boolean isAnalogRumbleSupported();
	/** This will be different than {@link #isAnalogRumbleSupported()} when isAnalogRumbleSupported() == true and
	 * this is false. When that is the case, it means that this still supports analog calls, but may implement
	 * it using something like PWM (Pulse Width Modulation) or something else
	 * @return true if analog rumble is supported, false if the rumble/vibrator can only be on or off */
	boolean isAnalogRumbleNativelySupported();
	/** @return true if the controller allows you to control the left and right individually, false
	 * 			if it doesn't allow that or if there is only one rumble. */
	boolean isLeftAndRightSupported();
}
