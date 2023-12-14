package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ApplicationLaunch;

/**
 * A class to test expected outputs of the *whole* program.
 * 
 * Had help from https://www.baeldung.com/java-testing-system-out-println
 * to capture System.out outputs. 
 * 
 * @author Ben
 */
class ApplicationLaunchTest {
	
	private final PrintStream sysOut = System.out;
	private final ByteArrayOutputStream captureStandard = new ByteArrayOutputStream();
	
	private final PrintStream sysErr = System.err;
	private final ByteArrayOutputStream captureError = new ByteArrayOutputStream(); 
	
	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(captureStandard));
		System.setErr(new PrintStream(captureError));
	}
	
	/**
	 * Tests the output of the test input provided by the specification.
	 */
	@Test
	void testTestProram() {
		assertDoesNotThrow(
			() -> ApplicationLaunch.main(new String[] {"./testprograms/exampleprogram.txt"})
		);
		
		String expectedOut = "1 2 N" + System.lineSeparator() + "5 1 E";
		
		assertEquals(expectedOut, captureStandard.toString().trim());
	}
	
	@Test
	void testTestProramInvalidPlacement() {
		assertDoesNotThrow(
			() -> ApplicationLaunch.main(new String[] {"./testprograms/testprograminvalidplacement.txt"})
		);
		
		String expectedOut = "1 2 N";
		
		assertEquals(expectedOut, captureStandard.toString().trim());
	}
	
	@Test
	void testTestProgramRoverWantsToLeavePlateauSoShouldBeOnEdge() {
		assertDoesNotThrow(
			() -> ApplicationLaunch.main(new String[] {"./testprograms/testprogramrovergoesoutofbounds.txt"})
		);
			
		String expectedOut = "0 5 N";
			
		assertEquals(expectedOut, captureStandard.toString().trim());
	}
	
	@Test
	void testTestProgramRoverCrashesIntoOther() {
		assertDoesNotThrow(
			() -> ApplicationLaunch.main(new String[] {"./testprograms/testprogramtworoverssecondonecrashes.txt"})
		);
			
		String expectedOut = "0 5 N" + System.lineSeparator() + "1 5 W";
			
		assertEquals(expectedOut, captureStandard.toString().trim());
	}
	
	@Test
	void testTestIllegalProgram() {
		
		ApplicationLaunch.main(new String[] {"./testprograms/illegalinstructiononerover.txt"});
			
			
		assertEquals("Invalid program. Only 'L', 'R', and 'M' are allowed.", captureError.toString().trim());
	}
	
	@Test
	void testTestIllegalProgramForOneRoverOnly() {
		
		ApplicationLaunch.main(new String[] {"./testprograms/illegalinstructiontworovers.txt"});
			
		assertEquals("Invalid program. Only 'L', 'R', and 'M' are allowed.", captureError.toString().trim());
		
		String expectedOut = "0 2 W";
		
		assertEquals(expectedOut, captureStandard.toString().trim());
	}
	
	@Test
	void testIllegalHeading() {
		
		ApplicationLaunch.main(new String[] {"./testprograms/illegalheading.txt"});
			
		assertEquals("T is not a valid heading!", captureError.toString().trim());
		
		String expectedOut = "0 5 W";
		
		assertEquals(expectedOut, captureStandard.toString().trim());
	}
	
	@Test
	void testIllegalNumberPosition() {
		
		ApplicationLaunch.main(new String[] {"./testprograms/illegalnumber.txt"});
			
		assertEquals("File contains invalid number for position coordinate", captureError.toString().trim());
		
	}
	
	@Test
	void testInvalidPlateau() {
		
		ApplicationLaunch.main(new String[] {"./testprograms/invalidplateau.txt"});
			
		assertTrue(captureError.toString().trim().contains("not running. Please fix input and try again"));
	}
	
	@Test
	void testProgramWithNoInputFile() {
		ApplicationLaunch.main(new String[] {});
		
//		assert(captureError.toString().trim().contains("You need to provide a file input!"));
	}
	
	@AfterEach
	public void tearDown() {
		System.setOut(sysOut);
		System.setErr(sysErr);
	}

}
