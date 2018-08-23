package me.retrodaredevil.controller.options;


public class ControlOption {
	private final String label;
	private final String description;
	private final OptionValueObject optionValue;

	public ControlOption(String label, String description, OptionValueObject optionValue){
		this.label = label;
		this.description = description;
		this.optionValue = optionValue;
	}
	public String getLabel(){
		return label;
	}
	public String getDescription(){
		return description;
	}
	public OptionValueObject getOptionValueObject(){
		return optionValue;
	}
}

