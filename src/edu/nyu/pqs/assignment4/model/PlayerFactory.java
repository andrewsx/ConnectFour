package edu.nyu.pqs.assignment4.model;

import java.awt.Color;
/**
 * This is the Factory Design Pattern for instantiating different Player objects at runtime.
 * @author Andrew
 *
 */
public class PlayerFactory {
	
	/**
	 * Depending on the PlayerType, the appropriate Player object is instatiated and returned. 
	 * @param type PlayerType
	 * @param c Color (Red is Player 1, Yellow is Player 2)
	 * @return  Player object
	 */
	public static IPlayer createPlayerObject(PlayerType type, Color c) {
		if (type == null) {
			throw new IllegalArgumentException("PlayerType Can Not Be NULL");
		}
			
		if (c == null) {
			throw new IllegalArgumentException("Color Can Not Be NULL");
		}
			

		if (type == PlayerType.HUMAN) {
			return new HumanPlayer(c);
		} else {
			return new ComputerPlayer(c);
		}
	}
}
