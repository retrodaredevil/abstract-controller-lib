package me.retrodaredevil.controller.options;


import java.util.Objects;

public class ControlOption {
	private final String label;
	private final String description;
	private final String category;
	private final OptionValueObject optionValue;

	/**
	 *
	 * @param label The label for the option
	 * @param description The description of the option to be changed
	 * @param category The category of this ControlOption usually something like: "something.something"
	 * @param optionValue The OptionValueObject that can be mutated by calling its set methods
	 */
	public ControlOption(String label, String description, String category, OptionValueObject optionValue){
		this.label = label;
		this.description = description;
		this.category = category;
		this.optionValue = optionValue;

		Objects.requireNonNull(label);
		Objects.requireNonNull(description);
		Objects.requireNonNull(category);
		Objects.requireNonNull(optionValue);
	}
	public String getLabel(){
		return label;
	}
	public String getDescription(){
		return description;
	}
	public String getCategory(){
		return category;
	}
	public OptionValueObject getOptionValueObject(){
		return optionValue;
	}
}

