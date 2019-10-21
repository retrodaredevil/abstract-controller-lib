package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;

import me.retrodaredevil.controller.input.implementations.AutoCachingInputPart;
import me.retrodaredevil.controller.input.AxisType;

public class ControllerInputPart extends AutoCachingInputPart {
	private final ControllerProvider provider;
	private final int code;
	private final boolean inverted;
	private final boolean isAxis;

	/**
	 *
	 * @param provider The {@link ControllerProvider} that provides the controller
	 * @param type The AxisType
	 * @param code The code for the button or axis. If this is < 0, then {@link #isConnected()} will return false.
	 *             However, this should not be used for a disconnected input part. Use {@link me.retrodaredevil.controller.input.implementations.DummyInputPart} for that.
	 * @param inverted true if this is inverted. Should be true for most y axises to when up, it is positive
	 * @param isAxis If true, the passed code is a code to be used with controller.getAxis(), otherwise, controller.getButton() will be used
	 */
	public ControllerInputPart(ControllerProvider provider, AxisType type, int code, boolean inverted, boolean isAxis){
		super(type);
		this.provider = provider;
		this.code = code;
		this.inverted = inverted;
		this.isAxis = isAxis;

		if(type.isRangeOver()){
			throw new UnsupportedOperationException("Controller Single Input does not support AxisType: " + type);
		}
	}
	public ControllerInputPart(ControllerProvider provider, AxisType type, int code, boolean inverted){
		this(provider, type, code, inverted,type.isAnalog() || type.isFull());
	}
	public ControllerInputPart(ControllerProvider provider, AxisType type, int code){
		this(provider, type, code, false);
	}

	public ControllerInputPart(Controller controller, AxisType type, int code, boolean inverted, boolean isAxis){
		this(ControllerProviders.wrap(controller), type, code, inverted, isAxis);
	}
	public ControllerInputPart(Controller controller, AxisType type, int code, boolean inverted){
		this(ControllerProviders.wrap(controller), type, code, inverted);
	}
	public ControllerInputPart(Controller controller, AxisType type, int code){
		this(ControllerProviders.wrap(controller), type, code);
	}
	@Override
	protected double calculatePosition() {
		Controller controller = provider.getController();
		if(controller == null){
			return 0;
		}
		AxisType type = getAxisType();
		if(isAxis){
			final double value = controller.getAxis(this.code) * (inverted ? -1 : 1);
			return type.isFull() ? value : ((value + 1.0) / 2.0);
		}
		return (controller.getButton(this.code) == !inverted) ? 1 : 0;
	}

	@Override
	public boolean isConnected() {
		return provider.isConnected() && code >= 0;
	}
	
}
