package me.retrodaredevil.controller.options;

import java.util.List;

/**
 * This only point of this class is to add some extra type safety. This class does not define any
 * additional behaviour and therefor should not be used with instanceof and should not be passed
 * around with a wildcard as a generic as using {@link OptionValue} would be a better option.
 * <p>
 * Also, it is unlikely that implementors' versions of {@link #isOptionValueRadio()} will be false,
 * it is still valid for them to do so.
 */
public interface RadioOptionOptionValue<T extends RadioOption> extends OptionValue{

	@Override
	List<? extends T> getRadioOptions();
}
