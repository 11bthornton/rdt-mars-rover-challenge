package models;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a program, i.e. a sequence of instructions which
 * can be validated and then iterated over step by step.
 *
 * @author Ben
 *
 */
public class Program implements Iterator<Instruction> {

  /**
   * Define a ONE-TIME regex pattern statically so a new one is not compiled
   * on a per-instance basis.
   */
  private static Pattern PROGRAM_REGEX_PATTERN = Pattern.compile("^[LRM]*$");

  private String program;

  /**
   * Helper attribute for implementation of iterator over the program. In essence,
   * what instruction are we now at? Starts from zero.
   */
  private int currentInstructionPosition;

  /**
   * Validates a string version of the program using a regex.
   * @param program
   * @throws IllegalArgumentException when the program is not matched by the regex.
   */
  public Program(String program) {
    /**
     * Trim the program string and match it against the regex
     */
    Matcher matcher = Program.PROGRAM_REGEX_PATTERN.matcher(program.trim());

    /**
     * If it matches, then initialise instance variables or throw IllegalArgumentException
     */
    if (matcher.matches()) {
      this.program = program.trim();
      this.currentInstructionPosition = 0;
    } else {
      throw new IllegalArgumentException(
        "Invalid program. Only 'L', 'R', and 'M' are allowed."
      );
    }
  }

  /**
   * Methods that implement the Iterator<Instruction> interface.
   */
  @Override
  public boolean hasNext() {
    // Ensures we don't try get an Instruction that doesn't exist.
    return currentInstructionPosition < program.length();
  }

  /**
   * Gets the next instruction
   *
   * @throws NoSuchElementException when there are no more
   */
  @Override
  public Instruction next() {
    /**
     * Avoids out of bounds errors
     */
    if (!this.hasNext()) {
      throw new NoSuchElementException(
        "Program has ended. Invalid state reached"
      );
    }

    /**
     * Get the current instruction character using the current position, then advance the pointer index.
     */
    char nextInstruction = this.program.charAt(this.currentInstructionPosition);
    this.currentInstructionPosition++;

    /**
     * Return the Instruction - This WILL succeed because the regex has done the check.
     */
    return Instruction.fromCharacter(nextInstruction);
  }
}
