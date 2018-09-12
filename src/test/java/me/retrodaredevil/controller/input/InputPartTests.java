package me.retrodaredevil.controller.input;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import me.retrodaredevil.controller.ControlConfig;
import me.retrodaredevil.controller.MutableControlConfig;
import me.retrodaredevil.controller.options.OptionValue;
import me.retrodaredevil.controller.options.OptionValues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class InputPartTests {
	private final ControlConfig config = new MutableControlConfig(); // contains reasonable defaults

	private InputPartTests(){}

	@Test
	void testConstantInputParts(){
		final InputPart part = new DummyInputPart(1, false);
		final AxisType type = part.getAxisType();
		assertFalse(type.isFull());
		assertFalse(type.isAnalog());
		assertFalse(type.isRangeOver());
		assertTrue(type.isShouldUseDelta());

		part.update(config);
		assertEquals(1, part.getPosition());
		assertTrue(part.isDown());
		assertFalse(part.isDeadzone());

		assertThrows(IllegalArgumentException.class, () -> new DummyInputPart(-1, false));
		assertThrows(IllegalArgumentException.class, () -> new DummyInputPart(-2, false, false));
		assertThrows(IllegalArgumentException.class, () -> new DummyInputPart(2, false, false));
		assertThrows(IllegalArgumentException.class, () -> new DummyInputPart(new AxisType(false, false), .5));
	}

	@Test
	void testDummyInputPart(){
		DummyInputPart dummy = new DummyInputPart(-.51, true);

		dummy.update(config);
		assertTrue(dummy.isDown());
		assertTrue(dummy.isPressed());
		assertFalse(dummy.isDeadzone());

		dummy.update(config);
		assertTrue(dummy.isDown());
		assertFalse(dummy.isPressed());

	}

	@Test
	void testHighestPositionInputPart(){
		DummyInputPart dummy = new DummyInputPart(-.51, true);
		InputPart inputPart = new HighestPositionInputPart(
				Arrays.asList(dummy,  new DummyInputPart(.125, true)),
				true);
		inputPart.update(config);
		// HighestPositionInputParts tests
		assertEquals(-.51, inputPart.getPosition());
		assertTrue(inputPart.isDown());
		assertFalse(inputPart.isDeadzone());



		// pressed tests
		assertTrue(inputPart.isPressed());
		inputPart.update(config);
		assertFalse(inputPart.isPressed());

		// released tests
		dummy.setPosition(-.4);
		inputPart.update(config);
		assertTrue(inputPart.isReleased());
		assertEquals(-.4, inputPart.getPosition());
		assertEquals(-.4, dummy.getPosition());

		// make sure works for other InputParts in HighestPositionInputPart
		dummy.setPosition(0);
		inputPart.update(config);
		assertEquals(.125, inputPart.getPosition());


	}
	@Test
	void testSensitiveInputPart(){
		final InputPart dummy = new DummyInputPart(1.5, true);
		final OptionValue multiplier = OptionValues.createAnalogRangedOptionValue(0, 2, 1);
		final OptionValue invert = OptionValues.createBooleanOptionValue(false);
		final InputPart inputPart = new SensitiveInputPart(dummy, multiplier, invert);

		inputPart.update(config);
		assertEquals(1.5, inputPart.getPosition());
		assertTrue(inputPart.getAxisType().isFull());
		assertTrue(inputPart.getAxisType().isAnalog());
		assertTrue(inputPart.getAxisType().isRangeOver());
		assertTrue(inputPart.getAxisType().isShouldUseDelta());

		multiplier.setOptionValue(2);
		invert.setBooleanOptionValue(true);
		inputPart.update(config);
		assertEquals(-3, inputPart.getPosition());

	}
}
