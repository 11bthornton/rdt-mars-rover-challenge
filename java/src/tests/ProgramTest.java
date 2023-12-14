package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Program;

class ProgramTest {

	@Test
	void testIllegalProgram() {
		assertThrows(IllegalArgumentException.class, () -> new Program("FFFLSLDLFLMMDMF"));
	}
	
	@Test
	void testIllegalBlankProgram() {
		assertDoesNotThrow(() -> new Program(" "));
	}
	
	@Test
	void testBlankProgram() {
		assertDoesNotThrow(() -> new Program(""));
	}
	
	@Test
	void testValidProgram() {
		assertDoesNotThrow(() -> new Program("MLRMLRMLLRRLRLMMMLR"));
	}
	
	@Test
	void testValidProgramIterator() {
		String programString = "MLRMLRMLLRRLRLMMMLR";
		Program program = new Program(programString);
		
		int counter = 0;
		
		while(program.hasNext()) {
			counter++;
			program.next();
		}
		
		assertEquals(programString.length(), counter);
	}

}
