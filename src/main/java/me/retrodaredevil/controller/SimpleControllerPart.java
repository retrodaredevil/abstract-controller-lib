package me.retrodaredevil.controller;

/**
 * A simple abstract base class for ControllerParts that handles updating other {@link ControllerPart}s and the config
 */
public abstract class SimpleControllerPart implements ControllerPart{

	protected final PartUpdater partUpdater = new PartUpdater();
	
	/** Can be accessed after or during the first call to update. */
	protected ControlConfig config;

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
		partUpdater.updateParts(config);
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


	@Override
	public String toString() {
		return getClass().getSimpleName() + "{hashCode: " + Integer.toHexString(hashCode()) + "}";
	}
}
