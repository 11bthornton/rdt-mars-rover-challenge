package models;

/**
 * Essentially this is a tuple class. But just
 * more semantically readable. This allows the 
 * MissionControl class to associate a rover with a program without
 * the use of a hashmap, which might change the order in which rovers
 * are run.
 */
public class RoverProgramPair {
	
	private Program program;
	private Rover rover;
	
	public RoverProgramPair(Program program, Rover rover) {
		this.program = program;
		this.rover = rover;
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	public Rover getRover() {
		return this.rover;
	}
}
