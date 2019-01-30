package me.retrodaredevil.controller;

import java.util.Arrays;
import java.util.Collection;

/**
 * Represents part of a controller such as a button or joystick
 * <p>
 * When inheriting, it is recommended to use {@link SimpleControllerPart}. If you do not end up using
 * that, make sure you look at the source of that when implementing children and parents so your
 * code is compatible with them and doesn't result in a stack overflow
 */
public interface ControllerPart {
	/**
	 * If null: That means that this is being updated by something like a ControllerManager or that this
	 * ControllerPart is not being updated at all.
	 * @return The parent ControllerPart that calls this ControllerPart's update or null if there is none
	 */
	ControllerPart getParent();

	/**
	 * Normally, you shouldn't call this with a ControllerInput as parent because ControllerInput already handles this
	 * automatically
	 * <p>
	 * This is expected to call parent.addChild() only if old parent != parent
	 * <p>
	 * After this method is called, {@link #getParent()} should return parent
	 * @param parent The parent to set
	 * @throws IllegalArgumentException thrown if parent == this
	 */
	void setParent(ControllerPart parent);

	/**
	 * NOTE: The returned value should not be mutated
	 * <br/>
	 * NOTE: You can always assume that parent.getChildren().contains(part) == (part.getParent() == parent)
	 * UNLESS: you are in the middle of a addChild(), removeChild() or setParent() call (very unlikely)
	 * @return A Collection representing all the children
	 */
	Collection<ControllerPart> getChildren();

	/**
	 * When this method is called it is possible that part.getParent() == this but this.getChildren().contains(part) == false
	 * so this method should check to make sure getChildren() will contain part and should also call part.setParent(this)
	 * <p>
	 * This is expected to add part to collection of children if needed and should always call
	 * part.setParent() no matter what.
	 * @param part The part to make a child of this
	 */
	void addChild(ControllerPart part);

	/**
	 * Calling this method should set part's parent to null only if part.getParent() == this. Otherwise it's
	 * parent should not be changed.
	 * <p>
	 * Calling this method will also (obviously) remove part as a child so part will not be contained in getChildren()
	 * <p>
	 * While this method is being called, part#{@link #getParent()} may not be correct but after this
	 * is called it should be correct
	 * @param part The part to remove a child
	 * @return true if the part was removed
	 */
	boolean removeChild(ControllerPart part);


	/**
	 * After this method is called, whether it is for a superclass or subclass, calls to getXXXX should
	 * be the most current and accurate value.
	 * <p>
	 * If a superclass has defined abstract methods such as getPosition() or getX(), when the superclass's update is called,
	 * calling those methods must be up to date. This is one of the reasons that you might need to call
	 * super after performing important steps that affect the return value of the methods that the call to super's update might use.
	 * <p>
	 * After this is called, all the children should have also been updated. Note that children
	 * are not guaranteed to be updated in the same order each time.
	 */
	void update(ControlConfig config);



	/**
	 * If this part somehow represents a button or axis (like InputPart), then this should return true if the button
	 * exists on the controller, false otherwise.
	 *
	 * @return true if this ControllerPart will give accurate values and if it is connected.
	 */
	boolean isConnected();
	
	/**
	 * NOTE: Depending on the implementation of {@link ControllerPart}, children may not be added and may not be
	 * updated in the order you expect.
	 *
	 * @param parts The parts to change each element's parent to this
	 * @param changeParent true if you want to allow the parent of parts that already have a parent to be changed. If a part
	 *                     does not already have a parent, it will be set to this regardless of this value.
	 * @param canAlreadyHaveParents Should be true if you expect one or more elements to already have a parent. If false,
	 *                                 it will throw an AssertionError if a part already has a parent. If false,
	 *                                 changeParent's value will do nothing.
	 * @throws AssertionError if canAlreadyHaveParents == false and one of the parts in the
	 *                        parts Iterable has a parent that isn't null, this will be thrown
	 * @throws IllegalArgumentException only thrown when changeParent == true and canAlreadyHaveParents == false
	 */
	default void addChildren(Iterable<? extends ControllerPart> parts,
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
			}
		}
	}
	
	/**
	 * @see #addChildren(Iterable, boolean, boolean)
	 */
	default void addChildren(boolean changeParent, boolean canAlreadyHaveParents, ControllerPart... parts){
		addChildren(Arrays.asList(parts), changeParent, canAlreadyHaveParents);
	}
}
