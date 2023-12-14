package models;

/**
 * Represents a Plateau of a fixed width and height.
 * Also allows for changing implied origin point.
 * @author Ben
 *
 */
public class Plateau {
	
	/**
	 * Just in case we want to manage and allow negative coordinates, or move the plateau more
	 * into the positive region. Defined in this class only. 
	 */
	private Integer impliedXOrigin;
	private Integer impliedYOrigin;

	/**
	 * Defined in the configuration file (the real bounds of the plateau)
	 */
	private Integer height;
	private Integer width;
	
	/**
	 * Constructor for the plateau. Typically this one isn't used
	 * as this class will parse the string directly. But useful for testing
	 *  
	 * @param width - width of the plateau (x coordinate bound) 
	 * @param height - height of the plateau (y coordinate bound)
	 */
	public Plateau(Integer width, Integer height) {
		
		// Must be positive.
		if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
		
		this.height = height;
		this.width  = width;
		
		// Set the origin point.
		this.impliedXOrigin = 0;
		this.impliedYOrigin = 0;
	}
	
	/**
	 * A similar constructor but in String form that splits and parses it from 
	 * the configuration program file directly.
	 * 
	 * @param textualForm - String in the form "X Y"
	 */
	public Plateau(String textualForm) {
        
		// First split the string after trimming to deal with trailing and leading
		// white-space.
		String[] parts = textualForm.trim().split(" ");
        
		// Must be exactly two numbers here. 
        if (parts.length != 2) {
            throw new IllegalArgumentException("Textual form must contain exactly two numbers separated by a space");
        }

        Integer width;
        Integer height;
        
        // Need to consider the scenario where these aren't numbers.
        try {
        	
            width = Integer.parseInt(parts[0]);
            height = Integer.parseInt(parts[1]);
        
        } catch (NumberFormatException e) {
        
        	throw new IllegalArgumentException("Invalid number format", e);
        }
        
        /**
         * Then like earlier, check the bounds and set the instance variables.
         * 
         * Negatives aren't allowed because they play havoc with the inBounds checks later.
         */
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }

        this.width = width;
        this.height = height;
        this.impliedXOrigin = 0;
        this.impliedYOrigin = 0;
    }
	
	/**
	 * Determines whether a given position is in bounds or not.
	 * 
	 * @param position - the position to determine whether it is in bounds of the plateau or not.
	 * @return
	 */
	public boolean isInBounds(Position position) {
		return 
			(
				position.getXPosition() >= this.impliedXOrigin && // Must be no less than 0 or no more than width
				position.getXPosition() <= this.width)
			&&
			(
				position.getYPosition() >= this.impliedYOrigin && // Must be no less than 0 or no more than height
				position.getYPosition() <= this.height
			);
	}
}
