package models;

/**
 * An enum for managing the Heading State and providing a clear,
 * semantically readable interface for switching between state.
 *
 * @author Ben
 *
 */
public enum Heading {
  /**
   * Self-explanatory.
   */
  NORTH,
  EAST,
  SOUTH,
  WEST;

  /**
   * Transforms a character into the relavant Heading Variant.
   *
   * @param headingCharacter
   * @return the Heading variant
   * @throws IllegalArgumentException - when character is not valid.
   */
  public static Heading fromCharacter(char headingCharacter) {
    switch (headingCharacter) {
      case 'N':
        return Heading.NORTH;
      case 'E':
        return Heading.EAST;
      case 'S':
        return Heading.SOUTH;
      case 'W':
        return Heading.WEST;
      default:
        throw new IllegalArgumentException(
          headingCharacter + " is not a valid heading!"
        );
    }
  }

  /**
   * Transforms a heading by applying a ninety-degree left rotation.
   *
   * @return the new Heading
   * @throws IllegalStateException - Categorically not possible but required for compilation.
   */
  public Heading rotateLeft() {
    switch (this) {
      case NORTH:
        return WEST;
      case EAST:
        return NORTH;
      case SOUTH:
        return EAST;
      case WEST:
        return SOUTH;
      default:
        throw new IllegalStateException("Unexpected value: " + this);
    }
  }

  /**
   * Transforms a heading by applying a ninety-degree right rotation.
   *
   * @return the new Heading
   * @throws IllegalStateException - Categorically not possible but required for compilation.
   */
  public Heading rotateRight() {
    switch (this) {
      case NORTH:
        return EAST;
      case EAST:
        return SOUTH;
      case SOUTH:
        return WEST;
      case WEST:
        return NORTH;
      default:
        throw new IllegalStateException("Unexpected value: " + this);
    }
  }

  /**
   * Transforms back into a string.
   *
   * @return - the string representation of this variant.
   */
  @Override
  public String toString() {
    switch (this) {
      case NORTH:
        return "N";
      case EAST:
        return "E";
      case SOUTH:
        return "S";
      case WEST:
        return "W";
      default:
        throw new IllegalStateException("Unexpected value: " + this);
    }
  }
}
