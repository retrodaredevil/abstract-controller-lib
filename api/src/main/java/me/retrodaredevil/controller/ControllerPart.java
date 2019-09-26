package me.retrodaredevil.controller;

/**
 * Represents part of a controller such as a button or joystick or en entire controller itself
 * <p>
 */
public interface ControllerPart {

	/**
	 * After this method is called, whether it is for a superclass or subclass, calls to getXXXX should
	 * be the most current and accurate value.
	 * <p>
	 * If a superclass has defined abstract methods such as getPosition() or getX(), when the superclass's update is called,
	 * calling those methods must be up to date. This is one of the reasons that you might need to call
	 * super after performing important steps that affect the return value of the methods that the call to super's update might use.
	 * <p>
	 * After this is called, all the children should have also been updated. Note that children
	 * are not guaranteed to be updated in the same order each time.
	 */
	void update(ControlConfig config);



	/**
	 * If this part somehow represents a button or axis (like InputPart), then this should return true if the button
	 * exists on the controller, false otherwise.
	 *
	 * @return true if this ControllerPart will give accurate values and if it is connected.
	 */
	boolean isConnected();
	
}
