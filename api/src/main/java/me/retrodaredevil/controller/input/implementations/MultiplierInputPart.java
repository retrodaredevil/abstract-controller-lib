package me.retrodaredevil.controller.input.implementations;

import me.retrodaredevil.controller.input.InputPart;

import java.util.Arrays;
import java.util.List;

public class MultiplierInputPart extends LowestPositionInputPart {
	
	
	public MultiplierInputPart(boolean ignoreIfDisconnected, List<InputPart> inputParts, boolean updateParts) {
		super(ignoreIfDisconnected, inputParts, updateParts);
	}
	@Deprecated
	public MultiplierInputPart(boolean ignoreIfDisconnected, InputPart... inputParts){
		super(ignoreIfDisconnected, inputParts);
	}
	@Deprecated
	public MultiplierInputPart(InputPart... inputParts){
		this(true, inputParts);
	}
	
	@Override
	public double getPosition() {
        double r = 1;
        for(InputPart part : inputParts){
        	if(part.isDeadzone()){
        		return 0;
			}
        	if(part.isConnected() || !ignoreIfDisconnected) {
				r *= part.getPosition();
			}
		}
        return r;
	}
	
}
