package me.retrodaredevil.controller.input.implementations;

import me.retrodaredevil.controller.input.InputPart;

import java.util.Arrays;
import java.util.List;

public class MultiplierInputPart extends LowestPositionInputPart {
	
	
	public MultiplierInputPart(boolean allowNotConnected, List<InputPart> inputParts) {
		super(allowNotConnected, inputParts);
	}
	public MultiplierInputPart(boolean allowNotConnected, InputPart... inputParts){
		this(allowNotConnected, Arrays.asList(inputParts));
	}
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
        	if(part.isConnected()) {
				r *= part.getPosition();
			}
		}
        return r;
	}
	
}
