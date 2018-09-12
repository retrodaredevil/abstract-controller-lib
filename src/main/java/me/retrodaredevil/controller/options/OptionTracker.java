package me.retrodaredevil.controller.options;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * A simple object for keeping track of {@link ControlOption}s and {@link ConfigurableControllerPart}s
 * <p>
 * This is the recommended way for keeping track of {@link ControlOption}s because it allows
 * {@link ConfigurableControllerPart#getControlOptions()} to return different values while keeping
 * values returned somewhere else consistent.
 */
public class OptionTracker {
	private final Collection<ConfigurableControllerPart> parts = new LinkedHashSet<>();
	private final Collection<ControlOption> options = new LinkedHashSet<>();
	private final boolean controllerOptionsFirst;

	public OptionTracker(boolean controllerOptionsFirst){
		this.controllerOptionsFirst = controllerOptionsFirst;
	}
	public OptionTracker(){
		this(false);
	}

	public boolean addController(ConfigurableControllerPart configurableControllerPart){
		return parts.add(configurableControllerPart);
	}
	public boolean removeController(ConfigurableControllerPart configurableControllerPart){
		return parts.remove(configurableControllerPart);
	}
	public boolean addControlOption(ControlOption controlOption){
		return options.add(controlOption);
	}
	public boolean removeControlOption(ControlOption controlOption){
		return options.remove(controlOption);
	}
	public void clear(){
		parts.clear();
		options.clear();
	}


	public Collection<? extends ControlOption> getOptions(){
		final Collection<ControlOption> r;
		if(controllerOptionsFirst){
			r = new ArrayList<>();
		} else {
			r = new ArrayList<>(options);
		}
		for(ConfigurableControllerPart part : parts){
			r.addAll(part.getControlOptions());
		}
		if(controllerOptionsFirst){
			r.addAll(options);
		}
		return r;
	}
}
