package me.retrodaredevil.controller.options;

import me.retrodaredevil.controller.ControllerPart;

/**
 * This should not be referenced with instanceof. If you wish to use instanceof, reference
 * {@link ConfigurableObject} instead as it is something that is configurable that may not be a
 * controller part. If you need something that is a ControllerPart that is also a ConfigurableObject,
 * this should be referenced only if this type is needed for a parameter.
 * <p>
 * This should also be implemented instead of {@link ConfigurableObject} when you are also
 * implementing {@link ControllerPart} as well.
 */
public interface ConfigurableControllerPart extends ControllerPart, ConfigurableObject {
}
