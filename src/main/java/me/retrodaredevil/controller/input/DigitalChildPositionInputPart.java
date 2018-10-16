package me.retrodaredevil.controller.input;

public class DigitalChildPositionInputPart extends ChildPositionInputPart {
	public DigitalChildPositionInputPart(InputPart inputPart, DigitalGetter getter) {
		super(inputPart, AxisType.DIGITAL, (childInputPart) -> getter.isDown(childInputPart) ? 1 : 0);
	}
	public interface DigitalGetter{
		boolean isDown(InputPart inputPart);
	}
}
