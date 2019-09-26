package me.retrodaredevil.controller.input;

import me.retrodaredevil.controller.SimpleControllerPart;
import me.retrodaredevil.controller.input.implementations.AutoCachingInputPart;

/**
 * This class can be used to wrap an InputPart or JoystickPart from another source to make sure
 * it always reflects that other source (such as a getter method for an InputPart in something like
 * a {@link me.retrodaredevil.controller.types.StandardControllerInput})
 */
public final class References {
	private References(){}

	public static InputPart create(InputPartGetter getter){
		return new InputPartReference(getter);
	}
	public static JoystickPart create(JoystickPartGetter getter){
		return new JoystickPartReference(getter);
	}

	/**
	 * Creates an InputPart that must be updated before each call
	 * @param axisType The axis type
	 * @param getter The getter for the position that must comply with axis type. (Usually a lambda)
	 * @return The created InputPart
	 */
	public static InputPart create(AxisType axisType, PositionGetter getter){
		return new AutoCachingInputPart(axisType) {
			@Override
			protected double calculatePosition() {
				return getter.getPosition();
			}

			@Override
			public boolean isConnected() {
				return true;
			}
		};
	}

	public interface InputPartGetter {
		InputPart getInputPart();
	}
	public interface JoystickPartGetter {
		JoystickPart getJoystickPart();
	}
	public interface PositionGetter {
		double getPosition();
	}
	private static class JoystickPartReference extends SimpleControllerPart implements JoystickPart {

		private final JoystickPartGetter getter;

		public JoystickPartReference(JoystickPartGetter getter){
			this.getter = getter;
		}

		private JoystickPart getJoystickPart(){
			return getter.getJoystickPart();
		}

		@Override
		public InputPart getXAxis() {
			return getJoystickPart().getXAxis();
		}

		@Override
		public InputPart getYAxis() {
			return getJoystickPart().getYAxis();
		}

		@Override
		public JoystickType getJoystickType() {
			return getJoystickPart().getJoystickType();
		}

		@Override
		public double getMagnitude() {
			return getJoystickPart().getMagnitude();
		}

		@Override
		public double getCorrectMagnitude() {
			return getJoystickPart().getCorrectMagnitude();
		}

		@Override
		public double getX() {
			return getJoystickPart().getX();
		}

		@Override
		public double getY() {
			return getJoystickPart().getY();
		}

		@Override
		public boolean isXDeadzone() {
			return getJoystickPart().isXDeadzone();
		}

		@Override
		public boolean isYDeadzone() {
			return getJoystickPart().isYDeadzone();
		}

		@Override
		public double getAngle() {
			return getJoystickPart().getAngle();
		}

		@Override
		public double getAngleRadians() {
			return getJoystickPart().getAngleRadians();
		}

		@Override
		public boolean isDeadzone() {
			return getJoystickPart().isDeadzone();
		}

		@Override
		public boolean isConnected() {
			return getJoystickPart().isConnected();
		}
		
		// region default methods
		@Override
		public double getCorrectX() {
			return getJoystickPart().getCorrectX();
		}
		@Override
		public double getCorrectY() {
			return getJoystickPart().getCorrectY();
		}
		
		// endregion
	}

	private static class InputPartReference extends SimpleControllerPart implements InputPart {
		private final InputPartGetter getter;
		public InputPartReference(InputPartGetter getter){
			this.getter = getter;
		}
		private InputPart getInputPart(){
			return getter.getInputPart();
		}
		@Override
		public double getPosition() {
			return getInputPart().getPosition();
		}

		@Override
		public boolean isDown() {
			return getInputPart().isDown();
		}

		@Override
		public boolean isJustPressed() {
			return getInputPart().isJustPressed();
		}

		@Override
		public boolean isJustReleased() {
			return getInputPart().isJustReleased();
		}

		@Override
		public int getDigitalPosition() {
			return getInputPart().getDigitalPosition();
		}

		@Override
		public boolean isConnected() {
			return getInputPart().isConnected();
		}

		@Override
		public AxisType getAxisType() {
			return getInputPart().getAxisType();
		}

		@Override
		public boolean isDeadzone() {
			return getInputPart().isDeadzone();
		}
		
	}
}
