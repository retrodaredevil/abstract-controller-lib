package me.retrodaredevil.controller.options;

/**
 * Represents a part of {@link RadioOptionOptionValue}. {@link #getRadioOptionName()} represents the name of this value.
 * The name is usually displayed to the user and is the only thing that distinguishes {@link RadioOption}s from one another.
 */
public interface RadioOption {
	String getRadioOptionName();
}
