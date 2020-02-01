package me.retrodaredevil.controller.input.implementations;

import me.retrodaredevil.controller.SimpleControllerPart;
import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.input.JoystickType;

import java.util.Arrays;
import java.util.Collection;

/**
 * The recommended way to map a number of joysticks to a single {@link JoystickPart}
 */
public class MultiplexerJoystickPart extends SimpleControllerPart implements JoystickPart {
	private final Collection<? extends JoystickPart> joysticks;

	private final InputPart xAxis;
	private final InputPart yAxis;

	public MultiplexerJoystickPart(Collection<? extends JoystickPart> joysticks, boolean updateJoysticks) {
		this.joysticks = joysticks;
		if(updateJoysticks) {
			partUpdater.addPartsAssertNonePresent(joysticks);
		}

		final AxisType axisType = axisTypeHelper(joysticks);
		xAxis = new MultiplexerInputPart(axisType, false);
		yAxis = new MultiplexerInputPart(axisType, true);
		partUpdater.addPartsAssertNonePresent(xAxis, yAxis);
	}
	@Deprecated
	public MultiplexerJoystickPart(JoystickPart... joysticks){
		this(Arrays.asList(joysticks), true);
	}
	private static AxisType axisTypeHelper(Collection<? extends JoystickPart> joysticks){
		boolean analog = false;
		boolean rangeOver = false;
		boolean useDelta = true;
		for(JoystickPart joy : joysticks){
			JoystickType type = joy.getJoystickType();
			if(type.isAnalog()){
				analog = true;
			}
			if(type.isRangeOver()){
				rangeOver = true;
			}
			if(!type.isShouldUseDelta()){
				useDelta = false;
			}
		}
		return new AxisType(true, analog, rangeOver, useDelta);
	}

	private JoystickPart getCurrentJoystick(){
		JoystickPart record = null;
		double magnitude = 0;
		for(JoystickPart joy : joysticks){
			if(!joy.isDeadzone()){
				final double mag = joy.getMagnitude();
				if(mag > magnitude){
					record = joy;
					magnitude = mag;
				}
			}
		}
		return record;
	}

	@Override
	public InputPart getXAxis() {
		return xAxis;
	}

	@Override
	public InputPart getYAxis() {
		return yAxis;
	}

	@Override
	public JoystickType getJoystickType() {
		boolean analog = false;
		boolean rangeOver = false;
		boolean square = false;
		boolean useDelta = true;
		JoystickType cache = null;
		for(JoystickPart joy : joysticks){
			JoystickType type = joy.getJoystickType();
			if(type.isAnalog()){
				analog = true;
			}
			if(type.isRangeOver()){
				rangeOver = true;
			}
			if(type.isInputSquare()){
				square = true;
			}
			if(!type.isShouldUseDelta()){
				useDelta = false;
			}
			if(type.isAnalog() == analog && type.isRangeOver() == rangeOver
					&& type.isInputSquare() == square && type.isShouldUseDelta() == useDelta){
				cache = type;
			}
		}
		if(cache != null && cache.isAnalog() == analog && cache.isRangeOver() == rangeOver
				&& cache.isInputSquare() == square && cache.isShouldUseDelta() == useDelta){
			return cache; // return cache instead of creating new object if cache is correct
		}
		return new JoystickType(analog, rangeOver, square, useDelta);
	}

	@Override
	public double getMagnitude() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.getMagnitude();
		}
		return 0;
	}

	@Override
	public double getCorrectMagnitude() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.getCorrectMagnitude();
		}
		return 0;
	}

	@Override
	public double getX() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.getX();
		}
		return 0;
	}

	@Override
	public double getY() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.getY();
		}
		return 0;
	}

	@Override
	public boolean isXDeadzone() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.isXDeadzone();
		}
		return true;
	}

	@Override
	public boolean isYDeadzone() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.isYDeadzone();
		}
		return true;
	}

	@Override
	public double getAngle() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.getAngle();
		}
		return 0;
	}

	@Override
	public double getAngleRadians() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.getAngleRadians();
		}
		return 0;
	}

	@Override
	public boolean isDeadzone() {
		JoystickPart joy = getCurrentJoystick();
		if(joy != null){
			return joy.isDeadzone();
		}
		return true;
	}

	@Override
	public boolean isConnected() {
		for(JoystickPart joy : joysticks){
			if(joy.isConnected()){
				return true;
			}
		}
		return false;
	}
	private class MultiplexerInputPart extends SimpleInputPart {
		private final boolean yAxis;

		public MultiplexerInputPart(AxisType type, boolean yAxis) {
			super(type);
			this.yAxis = yAxis;
		}

		@Override
		public double getPosition() {
			if(yAxis){
				return getY();
			}
			return getX();
		}

		@Override
		public boolean isDown() {
			JoystickPart joy = getCurrentJoystick();
			if(joy != null){
				if(yAxis){
					return joy.getYAxis().isDown();
				}
				return joy.getXAxis().isDown();
			}
			return false;
		}

		@Override
		public boolean isJustPressed() {
			JoystickPart joy = getCurrentJoystick();
			if(joy != null){
				if(yAxis){
					return joy.getYAxis().isJustPressed();
				}
				return joy.getXAxis().isJustPressed();
			}
			return false;
		}

		@Override
		public boolean isJustReleased() {
			JoystickPart joy = getCurrentJoystick();
			if(joy != null){
				if(yAxis){
					return joy.getYAxis().isJustReleased();
				}
				return joy.getXAxis().isJustReleased();
			}
			return false;
		}

		@Override
		public boolean isConnected() {
			return MultiplexerJoystickPart.this.isConnected();
		}
	}
}
