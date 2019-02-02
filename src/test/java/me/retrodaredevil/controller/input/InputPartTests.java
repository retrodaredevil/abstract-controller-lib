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
	void testDummyInputPart(){
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
	void testDummyInputPartPresses(){
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
	@Test
	void testLowestPosition(){
		final DummyInputPart dummy1 = new DummyInputPart(.75, false);
		final DummyInputPart dummy2 = new DummyInputPart(-1, true);
		final InputPart inputPart = new LowestPositionInputPart(dummy1, dummy2);

		assertTrue(inputPart.getAxisType().isFull());
		assertTrue(inputPart.getAxisType().isAnalog());
		assertFalse(inputPart.getAxisType().isRangeOver());
		assertTrue(inputPart.getAxisType().isShouldUseDelta());

		inputPart.update(config);
		assertTrue(inputPart.isDown());
		assertFalse(inputPart.isDeadzone());
		assertEquals(.75, inputPart.getPosition());
		assertTrue(inputPart.isPressed());

		dummy2.setPosition(0);
		inputPart.update(config);
		assertFalse(inputPart.isDown());
		assertEquals(0, inputPart.getPosition());
		assertTrue(inputPart.isReleased());
	}
	@Test
	void testDigitalChildInputPartPressed(){
		final DummyInputPart dummy = new DummyInputPart(0, false);
		final InputPart inputPart = new DigitalChildPositionInputPart(dummy, InputPart::isPressed);

		inputPart.update(config);
		assertFalse(inputPart.isDown());
		assertFalse(inputPart.isPressed());
		assertFalse(inputPart.isReleased());

		dummy.setPosition(1);
		inputPart.update(config);
		assertTrue(inputPart.isDown());
		assertTrue(inputPart.isPressed());
		assertFalse(inputPart.isReleased());

		inputPart.update(config);
		assertFalse(inputPart.isDown());
		assertFalse(inputPart.isPressed());
		assertTrue(inputPart.isReleased());
		
		// digital child should try to add dummy as child
		assertThrows(AssertionError.class, () -> new DigitalChildPositionInputPart(dummy, InputPart::isPressed));
	}
	
	@Test
	void testDigitalAnalogInputPart(){
		final MutableControlConfig config = new MutableControlConfig();
		config.useAbstractedIsDownIfPossible = true;
		
		final DummyInputPart digital = new DummyInputPart(0, false);
		final DummyInputPart analog = new DummyInputPart(0.0, false);
		final InputPart inputPart = new DigitalAnalogInputPart(digital, analog);
		
		inputPart.update(config);
		assertTrue(inputPart.isDeadzone());
		assertFalse(inputPart.isDown());
		assertFalse(inputPart.isPressed());
		assertFalse(inputPart.isReleased());
        assertEquals(0, inputPart.getDigitalPosition());
        assertEquals(0.0, inputPart.getPosition());
        
        digital.setPosition(1);
        inputPart.update(config);
        assertTrue(digital.isDown());
        assertFalse(analog.isDown());
        assertTrue(inputPart.isDown());
        
        // digital still has a position of 1
        analog.setPosition(.5);
        inputPart.update(config);
        assertTrue(digital.isDown());
        assertTrue(analog.isDown());
        assertEquals(.5, inputPart.getPosition());
        assertTrue(analog.isPressed());
        assertFalse(digital.isPressed());
        assertFalse(inputPart.isPressed());
	}
	
	@Test
	void testMultiplierInputPart(){
		final DummyInputPart full = new DummyInputPart(1.0, true);
		final DummyInputPart multiplier = new DummyInputPart(0.0, false);
		
		final InputPart inputPart = new MultiplierInputPart(true, full, multiplier);
		
		inputPart.update(config);
		assertTrue(inputPart.isDeadzone());
		assertFalse(inputPart.isDown());
		assertFalse(inputPart.isPressed());
		assertFalse(inputPart.isReleased());
		assertEquals(0, inputPart.getPosition());
		
		multiplier.setPosition(1);
		
		inputPart.update(config);
		assertFalse(inputPart.isDeadzone());
		assertTrue(inputPart.isDown());
		assertTrue(inputPart.isPressed());
		assertFalse(inputPart.isReleased());
		assertEquals(1, inputPart.getPosition());
		
		
		multiplier.setPosition(.5);
		inputPart.update(config);
		assertTrue(inputPart.isDown());
		assertEquals(.5, inputPart.getPosition());
		
		full.setPosition(-1.0);
		inputPart.update(config);
		assertTrue(inputPart.isDown());
		assertEquals(-.5, inputPart.getPosition());
	}
}
