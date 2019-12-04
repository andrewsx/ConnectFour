package edu.nyu.pqs.assignment4.view;

import java.awt.Color;

/**
 * This interface is the View and will render the Swing UI and interact with the model as appropriate.
 * @author Andrew
 *
 */

public interface IObserver {
	/**
	 * To present the player with the 6x7 grid. 
	 */
	public void gameStarted();
	/**
	 * To inform the UI what the player selected for row and column position.
	 * @param row row index
	 * @param col column index
	 * @param c   Player color
	 */
	public void rowColSelected(int row, int col, Color c);
	/**
	 * Prepares the UI for game end.
	 */
	public void gameEnded();
	/**
	 * Prepares the UI for a game reset. 
	 */
	public void gameReset();
	/**
	 * Renders the UI label to declare the winning player. 
	 * @param c
	 */
	public void declareWinner(Color c);
	/**
	 * Renders the UI label showing what the PlayerTypes are as in Computer or Human.
	 * @param s
	 */
	public void playersSelected(String s);
	/**
	 * Renders the UI showing which player's turn it is. 
	 * @param c
	 */
	public void playerChangeStatus(Color c);
}
