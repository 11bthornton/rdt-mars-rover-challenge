package models;

/**
 * An enum for representing an instruction in a clear and semantically readable
 * manner.
 * @author Ben
 *
 */
public enum Instruction {
  /**
   * The enum variants
   */
  MOVE_FORWARDS,
  ROTATE_LEFT_NINETY_DEGREES,
  ROTATE_RIGHT_NINETY_DEGREES;

  /**
   * Transforms a character instances into the relevant enum variant.
   *
   * @param instructionCharacter
   * @return the relevant enum variant.
   * @throws IllegalArgumentException when the character is not M L or R. Returns with message.
   */
  public static Instruction fromCharacter(char instructionCharacter) {
    switch (instructionCharacter) {
      case 'M':
        return Instruction.MOVE_FORWARDS;
      case 'L':
        return Instruction.ROTATE_LEFT_NINETY_DEGREES;
      case 'R':
        return Instruction.ROTATE_RIGHT_NINETY_DEGREES;
      default:
        throw new IllegalArgumentException(
          instructionCharacter + " is not a valid instruction!"
        );
    }
  }
}
