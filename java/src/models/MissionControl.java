package models;

import java.util.ArrayList;

/**
 * A MissionControl class which maintains a list of rovers and their associated programs.
 * Can run each rover sequentially. Each MissionControl also has a plateau, which it uses
 * to decide which moves are legal for each rover, so they do not go out of bounds
 * and do not collide with another rover.
 *
 * @author Ben
 *
 */
public class MissionControl {

  /**
   * Each mission control has a plateau and a list of rovers, each associated
   * with a program.
   */
  private Plateau plateau;
  private ArrayList<RoverProgramPair> roversWithPrograms;

  public MissionControl(Plateau plateau) {
    this.plateau = plateau;
    this.roversWithPrograms = new ArrayList<RoverProgramPair>();
  }

  /**
   * Registers a rover to the current mission, by associating it with a program
   * and adding it to the list of rovers.
   *
   * @param program
   * @param rover
   * @return true - if rover and program successfully added to mission, false, if rover
   * cannot be placed on plateau because there is already one there.
   */
  public boolean addRover(Program program, Rover rover) {
    // Can't put a rover outside the plateau, or in a position
    // where another rover already is.
    if (!this.isMoveLegal(rover, rover.getPosition())) {
      return false;
    }

    // The add method on ArrayList returns a boolean on successful add.
    return this.roversWithPrograms.add(new RoverProgramPair(program, rover));
  }

  /**
   * Public method to run the mission by moving the rovers and then
   * reporting their positions in the format the spec wants.
   */
  public void go() {
    this.moveRoversSequentially();
    this.reportPositions();
  }

  /**
   * Determines whether a rover can move to a position by considering
   *
   * 1) Whether there is already a rover in the space of another rover,
   * 2) Whether the rover wants to go out of bounds.
   *
   * @param rover the rover that we want to move.
   * @param newPosition the position we want to move it to.
   * @return whether the rover can actually be moved to the new position.
   */
  public boolean isMoveLegal(Rover rover, Position newPosition) {
    boolean doesNotCollide = roversWithPrograms
      .stream()
      .map(RoverProgramPair::getRover)
      .noneMatch(r -> {
        boolean collides =
          !r.equals(rover) && r.getPosition().equals(newPosition);

        return collides;
      });
    // technically the the first check on the left hand side isn't needed
    // Ah, no, it is. Sometimes the program checks non-movements by default. TODO optimisation?

    // Only return true if rover would not collide and would remain in bounds.
    return doesNotCollide && this.plateau.isInBounds(newPosition);
  }

  /**
   * Iterates over the list of rovers and executes their program.
   */
  private void moveRoversSequentially() {
    /**
     * Sometimes if a rover fails, want to know which one exactly has failed.
     */
    int roverCounter = 0;

    for (RoverProgramPair roverProgramPair : this.roversWithPrograms) {
      roverCounter++;

      Program program = roverProgramPair.getProgram();
      Rover rover = roverProgramPair.getRover();

      // Iterate over the instructions of the program.
      while (program.hasNext()) {
        // Figure out the next position of the rover, based on the current (next)
        // instruction in its program.
        Instruction nextInstruction = program.next();
        Position nextPotentialPosition = rover.turnOrCalculateNextPosition(
          nextInstruction
        );

        // If the move is legal, then mission control can instruct
        // the rover to move to its position
        if (this.isMoveLegal(rover, nextPotentialPosition)) {
          rover.moveToPosition(nextPotentialPosition);
        } else {
          // Otherwise we can log that the rover wants to make an illegal
          // move,
          System.err.println(
            "Rover " +
            roverCounter +
            " made an illegal move, stopping at " +
            rover.getPosition() +
            " ..."
          );

          // and then stop iterating over its program.
          break;
        }
      }
    }
  }

  /**
   * Iterates over the rovers and uses their (implict) .toString() method
   * to report the position and heading of the rover.
   */
  private void reportPositions() {
    for (RoverProgramPair roverProgramPair : this.roversWithPrograms) {
      System.out.println(roverProgramPair.getRover());
    }
  }
}
