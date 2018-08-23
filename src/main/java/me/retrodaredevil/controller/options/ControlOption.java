package me.retrodaredevil.controller.options;


public class ControlOption {
	private final String label;
	private final String description;
	private final OptionControllerPart controllerPart;

	public ControlOption(String label, String description, OptionControllerPart controllerPart){
		this.label = label;
		this.description = description;
		this.controllerPart = controllerPart;
	}
	public String getLabel(){
		return label;
	}
	public String getDescription(){
		return description;
	}
	public OptionControllerPart getControllerPart(){
		return controllerPart;
	}
}

