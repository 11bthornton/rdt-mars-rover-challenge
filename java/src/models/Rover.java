package models;

/**
 * Class which represents a rover.
 * 
 * @author Ben
 *
 */
public class Rover {
	
	/**
	 * Rover has a heading and a position.
	 */
	private Heading heading;
	private Position position;
	
	/**
	 * Constructor methods that initialise instance variables.
	 * @param position
	 * @param heading
	 */
	public Rover(Position position, Heading heading) {
		this.heading = heading;
		this.position = position;
	}
	
	/**
	 * A rover can ALWAYS turn, but sometimes it cannot move. 
	 * 
	 * If the instruction is Turn, then simply change the heading and then return current
	 * position. MissionControl will decide that this move is legal.
	 * 
	 * If instruction is move forward, calculate what position WOULD be, then mission control 
	 * can take action.
	 * 
	 * @param instruction
	 * @return the would-be position, were this instruction to be followed.
	 */
	public Position turnOrCalculateNextPosition(Instruction instruction) {
		switch(instruction) {
			
			/**
			 * If move forwards, use current heading to find new position and return.
			 */
			case MOVE_FORWARDS:
				
				switch(this.heading) {
					case NORTH:
						return position.northStep();
					case EAST:
						return position.eastStep();
					case SOUTH:
						return position.southStep();
					case WEST:
						return position.westStep();
					default:
						throw new IllegalStateException("Illegal State Reached");
				}
				
			/**
			 * Otherwise rotate heading left or right depending on what the instruction
			 * is.
			 */
			case ROTATE_LEFT_NINETY_DEGREES:
				this.heading = this.heading.rotateLeft();
				return this.position;
				
			case ROTATE_RIGHT_NINETY_DEGREES:
				this.heading = this.heading.rotateRight();
				return this.position;
				
			default:
				throw new IllegalStateException("Illegal State Reached");
		}
	}
	
	/**
	 * This method is called if MissionControl deems it safe for this rover to move there.
	 * @param position
	 */
	public void moveToPosition(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Spec wants output in this format.
	 */
	@Override
	public String toString() {
		return this.position.toString() + " " + this.heading.toString();
	}
}
