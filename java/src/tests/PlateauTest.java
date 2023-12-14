package tests;

import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Plateau;
import models.Position;

class PlateauTest {
	
	/**
	 * Definitely illegal arguments do not throw.
	 */
	@Test
	void assertInvalidArgumentsThrowsIllegalArgumentException() {
		assertThrows(
			IllegalArgumentException.class,
			() -> new Plateau(-1, -1)
		);
	}
	
	/**
	 * Boundary test for illegal arguments.
	 */
	@Test 
	void assertInvalidArgumentsOnBoundaryThrowsIllegalArugmentException() {
		assertThrows(
			IllegalArgumentException.class,
			() -> new Plateau(0, 0)
		);
	}
	
	/**
	 * Valid arguments do not throw an exception.
	 */
	@Test
	void assertValidArgumentsDoesNotThrow() {
		assertDoesNotThrow(
			() -> new Plateau(5, 5)
		);
	}
	
	@Test
	void assertPositionIsInBoundsForPlateau() {
		Plateau plateau = new Plateau(5, 5);
		
		assertTrue(plateau.isInBounds(new Position(3, 3)));
	}
	
	/**
	 * Boundary test.
	 */
	@Test
	void assertPositionIsInBoundsForPlateauBoundary() {
		Plateau plateau = new Plateau(5, 5);
		
		assertTrue(plateau.isInBounds(new Position(5, 5)));
	}
	
	/**
	 * Out of bounds test.
	 */
	@Test
	void assertPositionIsOutOfBoundsForPlateauBoundary() {
		Plateau plateau = new Plateau(5, 5);
		
		assertFalse(plateau.isInBounds(new Position(6, 5)));
		assertFalse(plateau.isInBounds(new Position(5, 6)));
		assertFalse(plateau.isInBounds(new Position(0, -1)));
		assertFalse(plateau.isInBounds(new Position(-1, 0)));
		
	}
	
	@Test
	void exampleTest() {
		Plateau plateau = new Plateau(4, 10);
		
		assertTrue(plateau.isInBounds(new Position(4, 10)));
	}

}
