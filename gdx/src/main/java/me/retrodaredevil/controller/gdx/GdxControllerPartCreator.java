package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;
import me.retrodaredevil.controller.implementations.ControllerPartCreator;
import me.retrodaredevil.controller.input.*;
import me.retrodaredevil.controller.input.implementations.DigitalAnalogInputPart;
import me.retrodaredevil.controller.input.implementations.TwoAxisJoystickPart;
import me.retrodaredevil.controller.output.ControllerRumble;
import me.retrodaredevil.controller.output.DisconnectedRumble;

public class GdxControllerPartCreator implements ControllerPartCreator {
	private final ControllerProvider provider;

	public GdxControllerPartCreator(Controller controller){
		this(ControllerProviders.wrap(controller));
	}
	public GdxControllerPartCreator(ControllerProvider provider){
		this.provider = provider;
	}

	@Override
	public InputPart createDigital(int code) {
        return new ControllerInputPart(provider, AxisType.DIGITAL, code);
	}

	@Override
	public JoystickPart createPOV(int povNumber, int xAxis, int yAxis) {
        return createPOV(povNumber);
	}

	@Override
	public JoystickPart createPOV(int povNumber) {
        return new ControllerPovJoystick(provider, povNumber);
	}

	@Override
	public JoystickPart createPOV(int xAxis, int yAxis) {
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
				new ControllerInputPart(provider, AxisType.ANALOG, analogCode)
		);
	}

	@Override
	public ControllerRumble createRumble() {
        return DisconnectedRumble.getInstance();
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
