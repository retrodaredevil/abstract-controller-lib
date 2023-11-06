package me.retrodaredevil.controller.gdx;

import com.badlogic.gdx.controllers.Controller;
import me.retrodaredevil.controller.input.AxisType;
import me.retrodaredevil.controller.input.implementations.AutoCachingInputPart;

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

	@Override
	protected double calculatePosition() {
		Controller controller = provider.getController();
		if(controller == null || !isConnected()){
			return 0;
		}
		AxisType type = getAxisType();
		if(isAxis){
			final double valueRaw = controller.getAxis(this.code);
			final double value;
			if (type.isFull()) {
				value = valueRaw * (inverted ? -1 : 1);
			} else {
				if (valueRaw < 0) {
					// TODO think about whether or not we want this to be an exception. People could give us a wrong button mapping and then it would crash
					// TODO this happens with PS4 controllers, indicating its mapping is incorrect
					throw new AssertionError("value is negative when the type is not full! This is unexpected on gdx-controllers 2.x!");
				}
				value = inverted ? 1 - valueRaw : valueRaw;
			}
			// In the gdx module, we scale based on if it is full or not, but with gdx-controllers 2.x we don't have to
			return value;
		}
		boolean raw = controller.getButton(this.code);
		return (raw == !inverted) ? 1 : 0;
	}

	@Override
	public boolean isConnected() {
		return provider.isConnected() && code >= provider.getController().getMinButtonIndex() && code <= provider.getController().getMaxButtonIndex();
	}
	
}
