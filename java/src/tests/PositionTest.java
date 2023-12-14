package tests;

import static org.junit.jupiter.api.Assertions.*;

import models.Position;
import org.junit.jupiter.api.Test;

class PositionTest {

  @Test
  void testConstructor() {
    Position position = new Position(5, 3);

    assertEquals(5, position.getXPosition());
    assertEquals(3, position.getYPosition());
  }

  @Test
  void testNorthStep() {
    Position position = (new Position(5, 3)).northStep();

    assertEquals(5, position.getXPosition());
    assertEquals(4, position.getYPosition());
  }

  @Test
  void testEastStep() {
    Position position = (new Position(5, 3)).eastStep();

    assertEquals(6, position.getXPosition());
    assertEquals(3, position.getYPosition());
  }

  @Test
  void testSouthStep() {
    Position position = (new Position(5, 3)).southStep();

    assertEquals(5, position.getXPosition());
    assertEquals(2, position.getYPosition());
  }

  @Test
  void testWestStep() {
    Position position = (new Position(5, 3)).westStep();

    assertEquals(4, position.getXPosition());
    assertEquals(3, position.getYPosition());
  }

  @Test
  void testEqualityReference() {
    Position pos1 = new Position(1, 1);

    assertEquals(pos1, pos1);
  }

  @Test
  void testEqualitySeparateObjects() {
    Position pos1 = new Position(1, 1);
    Position pos2 = new Position(1, 1);

    assertEquals(pos1, pos2);
  }

  @Test
  void testInequality() {
    Position pos1 = new Position(1, 1);
    Position pos2 = new Position(1, 3);

    assertNotEquals(pos1, pos2);
  }

  @Test
  void testInequalitySwaps() {
    Position pos1 = new Position(2, 1);
    Position pos2 = new Position(1, 2);

    assertNotEquals(pos1, pos2);
  }

  @Test
  void testToString() {
    Position pos1 = new Position(1, 5);

    assertEquals("1 5", pos1.toString());
  }
}
