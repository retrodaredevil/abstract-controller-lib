package me.retrodaredevil.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class PartUpdater {
	private final Set<ControllerPart> parts = new LinkedHashSet<>();
	private final Set<ControllerPart> unmodifiableParts = Collections.unmodifiableSet(parts);
	
	
	public boolean addPart(ControllerPart part){
		return parts.add(part);
	}
	public boolean removePart(ControllerPart part){
		return parts.remove(part);
	}
	public final Collection<ControllerPart> getParts(){
		return unmodifiableParts;
	}
	
	public void updateParts(ControlConfig config){
		requireNonNull(config);
		for(ControllerPart part : parts){
			part.update(config);
		}
	}
	public boolean isAllPartsConnected(){
		for(ControllerPart part : parts){
			if(!part.isConnected()){
				return false;
			}
		}
		return true;
	}
	public boolean isAnyPartsConnected(){
		for(ControllerPart part : parts){
			if(part.isConnected()){
				return true;
			}
		}
		return false;
	}
	
	// region Convenience Methods
	public void addPartAssertNotPresent(ControllerPart part){
		boolean notPresent = addPart(part);
		if(!notPresent){
			throw new AssertionError("ControllerPart: " + part + " is already present in parts! this: " + this);
		}
	}
	public void addParts(ControllerPart... parts){
		for(ControllerPart part : parts){
			addPart(part);
		}
	}
	public void addParts(Collection<? extends ControllerPart> parts){
		for(ControllerPart part : parts){
			addPart(part);
		}
	}
	public void addPartsAssertNonePresent(ControllerPart... parts){
		for(ControllerPart part : parts){
			addPartAssertNotPresent(part);
		}
	}
	public void addPartsAssertNonePresent(Collection<? extends ControllerPart> parts){
		for(ControllerPart part : parts){
			addPartAssertNotPresent(part);
		}
	}
	// endregion
}
