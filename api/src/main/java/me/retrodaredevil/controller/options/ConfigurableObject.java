package me.retrodaredevil.controller.options;

import java.util.Collection;

public interface ConfigurableObject {
	/**
	 * Sometimes the returned value will be a new instance each time this is called and other times, the returned
	 * value will be mutated and returned (don't rely on old returned values. Calling this will give you an updated version).
	 * @return A Collection of ControlOptions that should not be mutated. This is normally ordered
	 * 			when iterating over it.
	 */
	Collection<? extends ControlOption> getControlOptions();
}
