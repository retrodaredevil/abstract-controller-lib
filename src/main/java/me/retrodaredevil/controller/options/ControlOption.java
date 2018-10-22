package me.retrodaredevil.controller.options;


import java.util.Objects;

public class ControlOption {
	private final String label;
	private final String description;
	private final String category;
	private final OptionValue optionValue;

	/**
	 *
	 * @param label The label for the option
	 * @param description The description of the option to be changed
	 * @param category The category of this ControlOption usually something like: "something.something"
	 * @param optionValue The OptionValue that can be mutated by calling its set methods
	 */
	public ControlOption(String label, String description, String category, OptionValue optionValue){
		this.label = Objects.requireNonNull(label);
		this.description = Objects.requireNonNull(description);
		this.category = Objects.requireNonNull(category);
		this.optionValue = Objects.requireNonNull(optionValue);
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

	public OptionValue getOptionValue(){
		return optionValue;
	}

	/**
	 * This originally was meant to be used as a key for saving and retreiving values, however it is
	 * recommended to use {@link #getCategory()} for this purpose
	 * @deprecated You should not use this as a key. Use {@link #getCategory()} instead
	 * @return By default returns category + "." + label
	 */
	@Deprecated
	public String getKey(){
		return category + "." + label;
	}
}

