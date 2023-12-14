package models;

import java.util.Objects;

/**
 * Represents a position in 2d coordinate space.
 * Provides methods to transform given instances into new instances.
 * 
 * Points are IMMUTABLE. 
 * 
 * @author Ben
 *
 */
public class Position {
	
	private Integer xPosition;
	private Integer yPosition;
	
	/**
	 * Constructor method.
	 * 
	 * @param xPosition
	 * @param yPosition
	 */
	public Position(Integer xPosition, Integer yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * Moves this position one step north (y positive direction)
	 * @return the new position
	 */
	public Position northStep() {
		return new Position(
			this.xPosition,
			this.yPosition + 1
		);
	}
	
	/**
	 * Moves this position one step east (x positive direction)
	 * @return the new position
	 */
	public Position eastStep() {
		return new Position(
			this.xPosition + 1,
			this.yPosition
		);
	}
	
	/**
	 * Moves this position one step south (y negative direction)
	 * @return the new position
	 */
	public Position southStep() {
		return new Position(
			this.xPosition,
			this.yPosition - 1
		);
	}
	
	/**
	 * Moves this position one step west (x negative direction)
	 * @return the new position
	 */
	public Position westStep() {
		return new Position(
			this.xPosition - 1,
			this.yPosition
		);
	}
	
	/**
	 * Some getter methods
	 * @return
	 */
	public Integer getXPosition() {
		return this.xPosition;
	}
	
	public Integer getYPosition() {
		return this.yPosition;
	}
	
	
	/**
	 * Equality override based on the xPosition and yPosition, not just the reference itself.
	 */
	@Override
    public boolean equals(Object o) {
        
		/**
		 * If object to compare has same reference as this,
		 * return true.
		 */
        if (this == o) {
        	return true;
        };
        
        /**
         * Need to check the type and whether it's null or not
         * Know that this instance cannot be null. Because we have "this". 
         */
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        
        /**
         * Then cast to THIS type
         */
        Position position = (Position) o;
        
        /**
         * And then equality based on whether the two integer positions match.
         */
        return Objects.equals(this.xPosition, position.xPosition) &&
               Objects.equals(this.yPosition, position.yPosition);
    }

    /**
     * Because have override equality method, need to make sure the hashcodes match up
     * too, although not strictly necessary for this program because this type is never hashed anyway.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
    
    /**
     * Spec wants output in this format: "X Y"
     */
    @Override
	public String toString() {
		return this.xPosition.toString() + " " + this.yPosition.toString();
	}
}
