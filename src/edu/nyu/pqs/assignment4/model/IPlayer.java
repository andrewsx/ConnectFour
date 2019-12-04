package edu.nyu.pqs.assignment4.model;

import java.awt.Color;
/**
 * This interface is an abstraction of what a Player is. 
 * @author Andrew
 *
 */
public interface IPlayer {
	
	/**
	 * Returns the player's color
	 * @return player's color
	 */
	public Color getColor();
	/**
	 * Returns the player's Type (Computer, Human)
	 * @return player's Type (PlayerType.HUMAN, PlayerType.COMPUTER)
	 */
	public PlayerType getPlayerType();
	
	
}
