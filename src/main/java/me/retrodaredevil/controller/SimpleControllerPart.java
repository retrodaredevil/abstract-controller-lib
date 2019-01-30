package me.retrodaredevil.controller;

import java.util.*;

/**
 * A simple abstract base class for ControllerParts that handles the parent, children and the config
 */
public abstract class SimpleControllerPart implements ControllerPart{
	private static boolean debugChangeInParent = false;

	private ControllerPart parent = null;
	private final Set<ControllerPart> children = new HashSet<>();
	private final Set<ControllerPart> unmodifiableChildren = Collections.unmodifiableSet(children);

	/** Can be accessed after or during the first call to update. */
	protected ControlConfig config;

	/**
	 * @param debugChangeInParent true if a message should be logged to the console whenever
	 *                               a parent is changed (only debugs when previous parent != null), false for no debug
	 */
	public static void setDebugChangeInParent(boolean debugChangeInParent){
		SimpleControllerPart.debugChangeInParent = debugChangeInParent;
	}
	public static boolean isDebuggingChangeInParent(){
		return debugChangeInParent;
	}

	@Override
	public final ControllerPart getParent() {
		return parent;
	}

	@Override
	public final void setParent(final ControllerPart newParent) {
		if(newParent == this){
			throw new IllegalArgumentException("Cannot set parent to this");
		}
		if(this.parent == newParent){ // don't do anything if it's the same
			return;
		}
		final ControllerPart oldParent = this.parent;
		this.parent = newParent;
		if(oldParent != null) {
			if(isDebuggingChangeInParent()){
				System.out.println(getClass().getSimpleName()
						+ " (or " + this + ") is changing its parent from old parent: " + oldParent
						+ " to new parent: " + newParent);
			}
			oldParent.removeChild(this);
		}
		if(newParent != null){
			newParent.addChild(this);
		}
	}

	@Override
	public final Collection<ControllerPart> getChildren() {
        return unmodifiableChildren;
	}

	@Override
	public final void addChild(ControllerPart part) {
		Objects.requireNonNull(part);
		if(part == this){
			throw new IllegalArgumentException("Cannot add 'this' as a child!");
		}

		children.add(part);
		part.setParent(this);
	}

	@Override
	public final boolean removeChild(ControllerPart part) {
		Objects.requireNonNull(part);

		if(part.getParent() == this) {
			part.setParent(null);
		}
		return children.remove(part);
	}

	@Override
	public final void update(ControlConfig config) {
		this.config = config;
		onUpdate();
		onSecondUpdate();
		updateChildren();
		onAfterChildrenUpdate();
		onLateUpdate();
	}
	protected void updateChildren(){
		for(ControllerPart part : getChildren()){
			part.update(config);
		}
	}

	/**
	 * Should be overridden to update data that doesn't rely on another ControllerPart
	 */
	protected void onUpdate(){}

	/**
	 * Should be overridden to update data that relies on a subclass (abstract) implementation that is updated
	 * in onUpdate()
	 */
	protected void onSecondUpdate(){}

	/**
	 * Should be overridden to update data that relies on children.
	 */
	protected void onAfterChildrenUpdate(){}

	/**
	 * Should be overridden to act on updated data. This is called after all children are fully updated
	 * and everything in this instance should also be updated.
	 */
	protected void onLateUpdate(){}

	protected boolean areAllChildrenConnected(){
		for(ControllerPart part : getChildren()){
			if(!part.isConnected()){
				return false;
			}
		}
		return true;
	}
	protected boolean areAnyChildrenConnected(){
		for(ControllerPart part : getChildren()){
			if(part.isConnected()){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{parent: " + getParent() + "}";
	}
}
