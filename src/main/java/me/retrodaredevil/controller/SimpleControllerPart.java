package me.retrodaredevil.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A simple abstract base class for ControllerParts that handles the parent, children and the config
 */
public abstract class SimpleControllerPart implements ControllerPart{
	private static boolean debugChangeInParent = false;

	private ControllerPart parent = null;
	private final Set<ControllerPart> children = new HashSet<>();

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
	public ControllerPart getParent() {
		return parent;
	}

	@Override
	public void setParent(final ControllerPart newParent) {
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
	public Collection<ControllerPart> getChildren() {
		return children;
	}

	@Override
	public void addChild(ControllerPart part) {
		Objects.requireNonNull(part);

		children.add(part);
		part.setParent(this);
	}

	@Override
	public boolean removeChild(ControllerPart part) {
		Objects.requireNonNull(part);

		if(part.getParent() == this) {
			part.setParent(null);
		}
		children.remove(part);
		return true;
	}

	@Override
	public void update(ControlConfig config) {
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
	/**
	 * NOTE: The ControllerParts that will be added as children may not necessarily get
	 * updated in the same order you expect. Because of this, it is expected that the implementation
	 * handles ControllerParts that the children rely upon before the children are updated (possibly in
	 * onSecondUpdate() or before)
	 *
	 * @param parts The parts to change each element's parent to this
	 * @param changeParent true if you want to allow the parent of parts that already have a parent to be changed
	 * @param canAlreadyHaveParents Should be true if you expect one or more elements to already have a parent. If false,
	 *                                 it will throw an AssertionError if a part already has a parent. If false,
	 *                                 changeParent's value will do nothing.
	 * @throws AssertionError if canAlreadyHaveParents == false and one of the parts in the
	 *                        parts Iterable has a parent that isn't null, this will be thrown
	 * @throws IllegalArgumentException only thrown when changeParent == true and canAlreadyHaveParents == false
	 */
	public void addChildren(Iterable<? extends ControllerPart> parts,
	                        boolean changeParent, boolean canAlreadyHaveParents){
		if(changeParent && !canAlreadyHaveParents){
			throw new IllegalArgumentException("If changeParent == true, canAlreadyHaveParents cannot be false");
		}
		for(ControllerPart part : parts){
			boolean hasParent = part.getParent() != null;
			if(!canAlreadyHaveParents && hasParent){
				throw new AssertionError("A part already has a parent");
			}
			if(changeParent || !hasParent) {
				part.setParent(this);
//				this.addChild(part);
			}
		}
	}

	/**
	 * @see #addChildren(Iterable, boolean, boolean)
	 */
	public void addChildren(boolean changeParent, boolean canAlreadyHaveParents, ControllerPart... parts){
		addChildren(Arrays.asList(parts), changeParent, canAlreadyHaveParents);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{parent: " + getParent() + "}";
	}
}
