package me.retrodaredevil.controller.input.implementations;

import me.retrodaredevil.controller.input.InputPart;

import java.util.List;

public class MultiplierInputPart extends LowestPositionInputPart {
	
	
	public MultiplierInputPart(boolean ignoreIfDisconnected, List<InputPart> inputParts, boolean updateParts) {
		super(ignoreIfDisconnected, inputParts, updateParts);
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
