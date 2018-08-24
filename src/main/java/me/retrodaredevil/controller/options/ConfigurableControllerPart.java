package me.retrodaredevil.controller.options;

import java.util.Collection;

import me.retrodaredevil.controller.ControllerPart;

public interface ConfigurableControllerPart extends ControllerPart {
	/**
	 * Sometimes the returned value will be a new instance each time and other times, the returned
	 * value will be mutated and returned.
	 * <p>
	 * If this implements {@link OptionValueObject}, the returned list should contain this (if intended).
	 * @return A Collection of ControlOptions that should not be mutated. This is normally ordered
	 * 			when iterating over it.
	 */
	Collection<? extends ControlOption> getControlOptions();
}
