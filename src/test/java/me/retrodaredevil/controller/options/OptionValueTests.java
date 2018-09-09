package me.retrodaredevil.controller.options;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

final class OptionValueTests {
	private OptionValueTests(){}

	@Test
	void testBooleanOptionValue(){
		final OptionValue booleanOptionValue = OptionValues.createBooleanOptionValue(true);
		assertEquals(booleanOptionValue.getOptionValue(), booleanOptionValue.getDefaultOptionValue());
		assertEquals(1, booleanOptionValue.getOptionValue());
		assertTrue(booleanOptionValue.isOptionValueBoolean());
		assertTrue(booleanOptionValue.getBooleanOptionValue());

		assertThrows(IllegalArgumentException.class, () -> booleanOptionValue.setOptionValue(-.6));
		assertTrue(booleanOptionValue.getRadioOptions().isEmpty());
		assertFalse(booleanOptionValue.isOptionValueRadio());
	}

	@Test
	void testAnalogRangedOptionValue(){
		final OptionValue optionValue = OptionValues.createAnalogRangedOptionValue(-1, 1, 0);
		assertEquals(optionValue.getOptionValue(), optionValue.getDefaultOptionValue());
		assertEquals(0, optionValue.getOptionValue());
		assertEquals(-1, optionValue.getMinOptionValue());
		assertEquals(1, optionValue.getMaxOptionValue());

		assertFalse(optionValue.isOptionValueBoolean());
		assertThrows(UnsupportedOperationException.class, optionValue::getBooleanOptionValue);

		assertThrows(IllegalArgumentException.class, () -> optionValue.setOptionValue(-2));

		optionValue.setOptionValue(.5);
		assertEquals(.5, optionValue.getOptionValue());

		assertThrows(IllegalArgumentException.class, () -> OptionValues.createAnalogRangedOptionValue(-1, 1, -2));
		assertTrue(optionValue.getRadioOptions().isEmpty());
		assertFalse(optionValue.isOptionValueRadio());
	}

	@Test
	void testDigitalRangedOptionValue(){
		final OptionValue optionValue = OptionValues.createDigitalRangedOptionValue(-6, 7, 2);
		assertEquals(optionValue.getOptionValue(), optionValue.getDefaultOptionValue());
		assertEquals(2, optionValue.getOptionValue());
		assertEquals(-6, optionValue.getMinOptionValue());
		assertEquals(7, optionValue.getMaxOptionValue());

		assertFalse(optionValue.isOptionValueBoolean());
		assertThrows(UnsupportedOperationException.class, optionValue::getBooleanOptionValue);

		assertThrows(IllegalArgumentException.class, () -> optionValue.setOptionValue(-9));

		optionValue.setOptionValue(.9); // the value should be rounded
		assertEquals(1, optionValue.getOptionValue());

		assertThrows(IllegalArgumentException.class, () -> OptionValues.createDigitalRangedOptionValue(-1, 1, -2));
		assertTrue(optionValue.getRadioOptions().isEmpty());
		assertFalse(optionValue.isOptionValueRadio());
	}

	@Test
	void testRadioOptionValue(){
		final OptionValue optionValue = OptionValues.createRadioOptionValueWithStrings(Arrays.asList("option 1", "option 2", "option 3"), 1);
		assertEquals(optionValue.getOptionValue(), optionValue.getDefaultOptionValue());
		assertEquals(0, optionValue.getMinOptionValue());
		assertEquals(optionValue.getRadioOptions().size() - 1, optionValue.getMaxOptionValue());
		assertEquals("option 2", optionValue.getRadioOptions().get((int) optionValue.getOptionValue()).getRadioOptionName());
		optionValue.setOptionValue(2);
		assertEquals("option 3", optionValue.getRadioOptions().get((int) optionValue.getOptionValue()).getRadioOptionName());

		assertTrue(optionValue.isOptionValueRadio());
		assertThrows(IllegalArgumentException.class, () -> optionValue.setOptionValue(-1));
	}

	@Test
	void testImmutableOptionValues(){
		final OptionValue booleanOptionValue = OptionValues.createImmutableBooleanOptionValue(true);
		for(OptionValue optionValue : Arrays.asList(OptionValues.createImmutableAnalogRangedOptionValue(.5),
				OptionValues.createImmutableDigitalRangedOptionValue(2), booleanOptionValue)){
			optionValue.setToDefaultOptionValue();
			optionValue.setOptionValue(optionValue.getDefaultOptionValue());
			assertThrows(UnsupportedOperationException.class, () -> optionValue.setOptionValue(1234));
		}
		booleanOptionValue.getBooleanOptionValue();
		booleanOptionValue.setBooleanOptionValue(true);
		assertThrows(UnsupportedOperationException.class, () -> booleanOptionValue.setBooleanOptionValue(false));
		assertTrue(booleanOptionValue.isOptionValueBoolean());
		assertTrue(booleanOptionValue.getBooleanOptionValue());
	}
}
