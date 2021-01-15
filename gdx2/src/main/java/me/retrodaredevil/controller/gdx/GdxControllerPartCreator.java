package me.retrodaredevil.controller.gdx;

import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.InputPart;
import me.retrodaredevil.controller.input.JoystickPart;
import me.retrodaredevil.controller.input.implementations.DigitalAnalogInputPart;
import me.retrodaredevil.controller.input.implementations.TwoAxisJoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;
import me.retrodaredevil.controller.output.DisconnectedRumble;

public class GdxControllerPartCreator implements ControllerPartCreator {
	private final ControllerProvider provider;
	private final boolean assumeTriggersAreDigitalUntilAnalogNonZero;

	public GdxControllerPartCreator(ControllerProvider provider, boolean assumeTriggersAreDigitalUntilAnalogNonZero){
		this.provider = provider;
		this.assumeTriggersAreDigitalUntilAnalogNonZero = assumeTriggersAreDigitalUntilAnalogNonZero;
	}
	public GdxControllerPartCreator(ControllerProvider provider){
		this(provider, false);
	}

	@Override
	public InputPart createDigital(int code) {
        return new ControllerInputPart(provider, AxisType.DIGITAL, code);
	}

	@Override
	public JoystickPart createPov(int povNumber, int xAxis, int yAxis) {
        return createPov(povNumber);
	}

	@Override
	public JoystickPart createPov(int povNumber) {
		return new DefaultControllerPovPart(provider);
	}

	@Override
	public JoystickPart createPov(int xAxis, int yAxis) {
        return createJoystick(xAxis, yAxis);
	}

	@Override
	public JoystickPart createJoystick(int xAxis, int yAxis) {
        return new TwoAxisJoystickPart(
        		new ControllerInputPart(provider, AxisType.FULL_ANALOG, xAxis),
				new ControllerInputPart(provider, AxisType.FULL_ANALOG, yAxis, true)
		);
	}

	@Override
	public InputPart createFullAnalog(int axisCode) {
		return new ControllerInputPart(provider, AxisType.FULL_ANALOG, axisCode);
	}

	@Override
	public InputPart createAnalog(int axisCode) {
		return new ControllerInputPart(provider, AxisType.ANALOG, axisCode);
	}

	@Override
	public InputPart createFullAnalog(int axisCode, boolean isVertical) {
        return new ControllerInputPart(provider, AxisType.FULL_ANALOG, axisCode, isVertical);
	}

	@Override
	public InputPart createAnalog(int axisCode, boolean isVertical) {
		return new ControllerInputPart(provider, AxisType.ANALOG, axisCode, isVertical);
	}

	@Override
	public InputPart createAnalogTrigger(int axisCode) {
		return new ControllerInputPart(provider, AxisType.ANALOG, axisCode);
	}

	@Override
	public InputPart createTrigger(int digitalCode, int analogCode) {
		if(analogCode < 0){
			return createDigital(digitalCode);
		}
        return new DigitalAnalogInputPart(
        		new ControllerInputPart(provider, AxisType.DIGITAL, digitalCode),
				new ControllerInputPart(provider, AxisType.ANALOG, analogCode, false, true, assumeTriggersAreDigitalUntilAnalogNonZero)
		);
	}

	@Override
	public ControllerRumble createRumble() {
        return DisconnectedRumble.getInstance(); // TODO
	}

	@Override
	public boolean isConnected() {
        return provider.isConnected();
	}

	@Override
	public String getName() {
        return provider.getName();
	}
}
