package tests;

import static org.junit.jupiter.api.Assertions.*;

import models.Heading;
import models.MissionControl;
import models.Plateau;
import models.Position;
import models.Program;
import models.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MissionControlTest {

  private Plateau plateau;
  private MissionControl missionControl;

  @BeforeEach
  void setUp() {
    this.plateau = new Plateau("5 5");
    this.missionControl = new MissionControl(this.plateau);
  }

  @Test
  void testAddTwoRoversInvalid() {
    Rover roverOne = new Rover(new Position(1, 1), Heading.NORTH);
    Rover roverTwo = new Rover(new Position(1, 1), Heading.NORTH);

    this.missionControl.addRover(new Program(" "), roverOne);
    assertFalse(this.missionControl.addRover(new Program(" "), roverTwo));
  }

  @Test
  void testAddTwoRoversValid() {
    Rover roverOne = new Rover(new Position(1, 1), Heading.NORTH);
    Rover roverTwo = new Rover(new Position(1, 3), Heading.NORTH);

    this.missionControl.addRover(new Program(" "), roverOne);
    assertTrue(this.missionControl.addRover(new Program(" "), roverTwo));
  }

  @Test
  void testPlacementIsIllegalBecauseOutOfBoundsInitially() {
    Rover roverOne = new Rover(new Position(-1, 0), Heading.NORTH);
    assertFalse(this.missionControl.addRover(new Program(" "), roverOne));
  }

  @Test
  void testMoveIsIllegalBecauseIsPlacedOutOfBounds() {
    Rover roverOne = new Rover(new Position(0, 0), Heading.NORTH);
    Position newPosition = new Position(10, 10);

    assertFalse(this.missionControl.isMoveLegal(roverOne, newPosition));
  }

  @Test
  void testMoveIsLegal() {
    Rover roverOne = new Rover(new Position(0, 0), Heading.NORTH);
    Position newPosition = new Position(1, 1);

    assertTrue(this.missionControl.isMoveLegal(roverOne, newPosition));
  }

  @Test
  void testMoveIsIllegalBecauseCollision() {
    Rover roverOne = new Rover(new Position(0, 0), Heading.NORTH);
    Rover roverTwo = new Rover(new Position(1, 1), Heading.EAST);

    this.missionControl.addRover(new Program(" "), roverOne);
    this.missionControl.addRover(new Program(" "), roverTwo);

    Position newPosition = new Position(1, 1);

    assertFalse(this.missionControl.isMoveLegal(roverOne, newPosition));
  }
}
