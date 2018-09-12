package me.retrodaredevil.controller;

import org.junit.jupiter.api.Test;

import me.retrodaredevil.controller.input.DummyInputPart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ControllerTests {
	private ControllerTests(){}

	@Test
	void testChangeInParent(){
		SimpleControllerPart.setDebugChangeInParent(true);
		final ControllerPart parent = new DummyInputPart(0, false);
		final ControllerPart newParent = new DummyInputPart(0, false);
		final ControllerPart child = new DummyInputPart(0, false);

		assertNull(child.getParent());
		child.setParent(parent);
		assertEquals(parent, child.getParent());
		assertTrue(parent.getChildren().contains(child));
		child.setParent(newParent);
		assertEquals(newParent, child.getParent());
		assertTrue(newParent.getChildren().contains(child));

		final SimpleControllerPart thirdParent = new DummyInputPart(0, false);
		assertThrows(AssertionError.class, () -> thirdParent.addChildren(false, false, child));
		assertThrows(IllegalArgumentException.class, () -> thirdParent.addChildren(true, false, child));
		assertThrows(NullPointerException.class, () -> thirdParent.addChild(null));
		assertThrows(IllegalArgumentException.class, () -> thirdParent.addChild(thirdParent));

		thirdParent.addChildren(false, true, child);
		assertEquals(newParent, child.getParent());
		thirdParent.addChildren(true, true, child);
		assertEquals(thirdParent, child.getParent());
	}
}
