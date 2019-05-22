package me.retrodaredevil.controller.input;

public final class InputPartUtils {
	private InputPartUtils(){ throw new UnsupportedOperationException(); }
	public static AxisType autoAxisTypeHelper(Iterable<? extends InputPart> parts){
		boolean anyFull = false;
		boolean anyAnalog = false;
		for(InputPart part : parts){
			AxisType type = part.getAxisType();
			
			anyFull = anyFull || type.isFull();
			anyAnalog = anyAnalog || type.isAnalog();
		}
		return new AxisType(anyFull, anyAnalog, false, true);
	}
}
