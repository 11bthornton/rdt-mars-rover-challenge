package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Heading;

class HeadingTest {

	@Test
	void testParser() {
		assertEquals(Heading.NORTH, Heading.fromCharacter('N'));
		assertEquals(Heading.EAST, Heading.fromCharacter('E'));
		assertEquals(Heading.SOUTH, Heading.fromCharacter('S'));
		assertEquals(Heading.WEST, Heading.fromCharacter('W'));
		
		assertThrows(IllegalArgumentException.class, () -> Heading.fromCharacter('k'));
	}
	
	@Test
	void testRotateLeft() {
		assertEquals(Heading.WEST, Heading.NORTH.rotateLeft());
		assertEquals(Heading.NORTH, Heading.EAST.rotateLeft());
		assertEquals(Heading.EAST, Heading.SOUTH.rotateLeft());
		assertEquals(Heading.SOUTH, Heading.WEST.rotateLeft());
	}
	
	@Test
	void testRotateRight() {
		assertEquals(Heading.EAST, Heading.NORTH.rotateRight());
		assertEquals(Heading.SOUTH, Heading.EAST.rotateRight());
		assertEquals(Heading.WEST, Heading.SOUTH.rotateRight());
		assertEquals(Heading.NORTH, Heading.WEST.rotateRight());
	}
	
	@Test
	void testToString() {
		assertEquals("N", Heading.NORTH.toString());
		assertEquals("E", Heading.EAST.toString());
		assertEquals("S", Heading.SOUTH.toString());
		assertEquals("W", Heading.WEST.toString());
	}
}
