package edu.nyu.pqs.assignment4.model;


import java.awt.Color;
import edu.nyu.pqs.assignment4.view.IObserver;


/**
 * This interface serves as the foundation for the Connect Four game where one 
 * Connect Four object will be instantiated in the Singleton Pattern. It is the Model
 * in the MVC pattern and it will be communicating with the Views (Observers) and
 * holds the logic for notifying observers and game logic.
 * 
 * 
 * @author Andrew
 *
 */
public interface IConnectFour {
	/**
	 * This adds an observer to a list for accountability at time of observer object's instantiation 
	 * @param observer
	 * @return true if addition was successful
	 */
	public boolean addObserver(IObserver observer);
	/**
	 * This removes an observer from a list for accountability at end of game
	 * @param observer
	 * @return true if removal was successful
	 */
	public boolean removeObserver(IObserver observer);
	
	/**
	 * Notifies all observers that the game has started
	 */
	public void notifyStartGame();
	/**
	 * Notifies all observers that the game will be restarting
	 */
	public void notifyResetGame();
	/**
	 * 
	 * Notifies all observers that the game has ended 
	 * 
	 */
	public void notifyEndGame();
	/**
	 * Notifies all observers that a player has won
	 * @param c color of Player
	 */
	public void notifyPlayerWon(Color c);
	/**
	 * Notifies all observers that a column/row was selected and sends the coordinates for placement on UI grid.
	 * @param row row index
	 * @param col col index 
	 */
	public void notifyColumnRowSelected(int row, int col);
	/**
	 * Notifies all observers that a player switch occurred
	 */
	public void notifySwitchPlayer();
	/**
	 * Notifies all observers that two players have been selected
	 * @param s string denoting the PlayerTypes (Computer or Humans)
	 */
	public void notifyPlayersSelected(String s);
	/**
	 * Sets the player types using a Factory Pattern to create appropriate Player objects at runtime.
	 * @param one
	 * @param two
	 * @param s string denoting the PlayerTypes (Computer or Humans)
	 */
	public void setPlayers(PlayerType one, PlayerType two, String s);
	/**
	 * 
	 * Switches the turn between two players. 
	 * 
	 */
	public void switchPlayer();
	/**
	 * Inspects a column for an empty row cell for chip placement
	 * @param column index
	 * @return true is there is an empty spot
	 */
	public boolean testColumnForEmptyCell(int column);
	/**
	 * Checks for a win
	 * @param row index
	 * @param col index
	 * @return true if the winning conditions are satisfied, otherwise false
	 */
	public boolean checkIfWin(int row, int col, Color c);
	
	
}
