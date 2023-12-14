package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Heading;
import models.Instruction;
import models.Position;
import models.Rover;

class RoverTest {

	@Test
	void testToString() {
		Heading heading = Heading.NORTH;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		assertEquals("1 2 N", rover.toString());
	}
	
	@Test
	void testCalculatePositionRightTurn() {
		Heading heading = Heading.NORTH;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		Instruction instruction = Instruction.ROTATE_RIGHT_NINETY_DEGREES;
		
		Position newPosition = rover.turnOrCalculateNextPosition(instruction);
		
		assertEquals(newPosition, position);
	}
	
	@Test
	void testCalculatePositionLeftTurn() {
		Heading heading = Heading.NORTH;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		Instruction instruction = Instruction.ROTATE_RIGHT_NINETY_DEGREES;
		
		Position newPosition = rover.turnOrCalculateNextPosition(instruction);
		
		assertEquals(newPosition, position);
	}
	
	@Test
	void testCalculatePositionMoveNorthStep() {
		Heading heading = Heading.NORTH;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		Instruction instruction = Instruction.MOVE_FORWARDS;
		
		Position newPosition = rover.turnOrCalculateNextPosition(instruction);
		
		assertEquals(newPosition, position.northStep());
	}
	
	@Test
	void testCalculatePositionMoveEastStep() {
		Heading heading = Heading.EAST;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		Instruction instruction = Instruction.MOVE_FORWARDS;
		
		Position newPosition = rover.turnOrCalculateNextPosition(instruction);
		
		assertEquals(newPosition, position.eastStep());
	}
	
	@Test
	void testCalculatePositionMoveSouthStep() {
		Heading heading = Heading.SOUTH;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		Instruction instruction = Instruction.MOVE_FORWARDS;
		
		Position newPosition = rover.turnOrCalculateNextPosition(instruction);
		
		assertEquals(newPosition, position.southStep());
	}
	
	@Test
	void testCalculatePositionMoveWestStep() {
		Heading heading = Heading.WEST;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		Instruction instruction = Instruction.MOVE_FORWARDS;
		
		Position newPosition = rover.turnOrCalculateNextPosition(instruction);
		
		assertEquals(newPosition, position.westStep());
	}
	
	@Test
	void testMoveToPosition() {
		Heading heading = Heading.NORTH;
		Position position = new Position(1, 2);
		
		Rover rover = new Rover(position, heading);
		
		Position newPosition = new Position(501, 120);
		
		rover.moveToPosition(newPosition);
	
		assertEquals(rover.getPosition(), newPosition);
	}

}
