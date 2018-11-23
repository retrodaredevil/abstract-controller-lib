package me.retrodaredevil.controller.options;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * A simple object for keeping track of {@link ControlOption}s and {@link ConfigurableObject}s
 * <p>
 * This is the recommended way for keeping track of {@link ControlOption}s because it allows
 * {@link ConfigurableObject#getControlOptions()} to return different values while keeping
 * values returned somewhere else consistent.
 */
public class OptionTracker implements ConfigurableObject{
	private final Collection<ConfigurableObject> parts = new LinkedHashSet<>();
	private final Collection<ControlOption> options = new LinkedHashSet<>();
	private final boolean controllerOptionsFirst;

	public OptionTracker(boolean controllerOptionsFirst){
		this.controllerOptionsFirst = controllerOptionsFirst;
	}
	public OptionTracker(){
		this(false);
	}

	public boolean add(ConfigurableObject configurableObject){
		return parts.add(configurableObject);
	}
	public boolean add(ControlOption controlOption){
		return options.add(controlOption);
	}
	public boolean remove(ConfigurableObject configurableObject){
		return parts.remove(configurableObject);
	}
	public boolean remove(ControlOption controlOption){
		return options.remove(controlOption);
	}

	public void clear(){
		parts.clear();
		options.clear();
	}


	/**
	 * @deprecated Use {@link #getControlOptions()}
	 * @return
	 */
	@Deprecated
	public Collection<? extends ControlOption> getOptions(){
		return getControlOptions();
	}

	@Override
	public Collection<? extends ControlOption> getControlOptions() {
		final Collection<ControlOption> r;
		if(controllerOptionsFirst){
			r = new ArrayList<>();
		} else {
			r = new ArrayList<>(options);
		}
		for(ConfigurableObject configurableObject : parts){
			r.addAll(configurableObject.getControlOptions());
		}
		if(controllerOptionsFirst){
			r.addAll(options);
		}
		return r;

	}
}
