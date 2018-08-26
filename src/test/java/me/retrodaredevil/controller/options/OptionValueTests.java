package me.retrodaredevil.controller.options;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class OptionValueTests {
	private OptionValueTests(){}

	@Test
	void testBooleanOptionValue(){
		OptionValueObject booleanOptionValue = OptionValues.createBooleanOptionValue(true);
		assertEquals(1, booleanOptionValue.getOptionValue());
		assertTrue(booleanOptionValue.isOptionValueBoolean());
		assertTrue(booleanOptionValue.getBooleanOptionValue());

		assertThrows(IllegalArgumentException.class, () -> booleanOptionValue.setOptionValue(-.6));
	}

	@Test
	void testAnalogRangedOptionValue(){
		OptionValueObject optionValue = OptionValues.createAnalogRangedOptionValue(-1, 1, 0);
		assertEquals(0, optionValue.getOptionValue());
		assertEquals(-1, optionValue.getMinOptionValue());
		assertEquals(1, optionValue.getMaxOptionValue());

		assertFalse(optionValue.isOptionValueBoolean());
		assertThrows(UnsupportedOperationException.class, optionValue::getBooleanOptionValue);

		assertThrows(IllegalArgumentException.class, () -> optionValue.setOptionValue(-2));

		optionValue.setOptionValue(.5);
		assertEquals(.5, optionValue.getOptionValue());

		assertThrows(IllegalArgumentException.class, () -> OptionValues.createAnalogRangedOptionValue(-1, 1, -2));
	}

	@Test
	void testDigitalRangedOptionValue(){
		OptionValueObject optionValue = OptionValues.createDigitalRangedOptionValue(-6, 7, 2);
		assertEquals(2, optionValue.getOptionValue());
		assertEquals(-6, optionValue.getMinOptionValue());
		assertEquals(7, optionValue.getMaxOptionValue());

		assertFalse(optionValue.isOptionValueBoolean());
		assertThrows(UnsupportedOperationException.class, optionValue::getBooleanOptionValue);

		assertThrows(IllegalArgumentException.class, () -> optionValue.setOptionValue(-9));

		optionValue.setOptionValue(.9);
		assertEquals(1, optionValue.getOptionValue());

		assertThrows(IllegalArgumentException.class, () -> OptionValues.createDigitalRangedOptionValue(-1, 1, -2));
	}
}
