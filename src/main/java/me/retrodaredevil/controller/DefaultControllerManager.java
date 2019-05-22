package me.retrodaredevil.controller;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.SortedSet;

/**
 * A simple concrete controller manager that updates controllers in the order they were added.
 */
public class DefaultControllerManager implements ControllerManager {

	private final ControlConfig config;
	private final Collection<ControllerInput> controllers = new LinkedHashSet<>();

	public DefaultControllerManager(){
		config = new MutableControlConfig();
	}

	public DefaultControllerManager(ControlConfig config){
		this.config = config;
	}


	@Override
	public boolean addController(ControllerInput controller){
		return this.controllers.add(controller);
	}

	@Override
	public boolean removeController(ControllerInput controller) {
		return controllers.remove(controller);
	}

	@Override
	public void update(){
		for(ControllerInput controller : controllers){
			try {
				controller.update(config);
			} catch(Exception ex){
				System.err.println("Error while updating controller: " + controller + " which is of class: " + controller.getClass().getSimpleName());
				throw ex;
			}
		}
	}
}
