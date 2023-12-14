package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Instruction;

class InstructionTest {

	@Test
	void testParser() {
		assertEquals(Instruction.MOVE_FORWARDS, Instruction.fromCharacter('M'));
		assertEquals(Instruction.ROTATE_LEFT_NINETY_DEGREES, Instruction.fromCharacter('L'));
		assertEquals(Instruction.ROTATE_RIGHT_NINETY_DEGREES, Instruction.fromCharacter('R'));
		
		assertThrows(IllegalArgumentException.class, () -> Instruction.fromCharacter('p'));
	}

}
