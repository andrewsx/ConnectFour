package edu.nyu.pqs.assignment4.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.assignment4.view.IObserver;
import edu.nyu.pqs.assignment4.model.Point;
import java.util.Random;


/**
 * This is the Model and will be communicating with the Views (Observers) and
 * holds the logic for notifying observers and game logic.
 * @author Andrew
 *
 */
public class ConnectFour implements IConnectFour {

	private static ConnectFour singleton = null;
	private boolean modeAI = false;
	private Board board;
	private List<IObserver> observers = new ArrayList<IObserver>();
	private List<Point> emptyCols = new ArrayList<Point>();
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer currentPlayer;
	private Random rand = new Random();
	
	private ConnectFour() {
		
	
	}
	
	public IPlayer getPlayer() {
		return currentPlayer;
	}
	
	public boolean getMode() {
		return modeAI;
	}
	public Board getBoard() {
		return board;
	}
	
	
	
	/**
	 * Returns instance of the singleton object 
	 * @return singleton
	 */
	public static ConnectFour getInstance() {
		if (singleton == null) {
			singleton = new ConnectFour();
		} 
		return singleton;
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public boolean addObserver(IObserver observer) {
		if (observer == null) {
			throw new IllegalArgumentException();
		}
		
		return observers.add(observer);
		
	}
	/**
	 * @inheritDoc 
	 */
	@Override
	public boolean removeObserver(IObserver observer) {
		if (observer == null) {
			throw new IllegalArgumentException();
		}
		return observers.remove(observer);
	}
	/**
	 * 
	 * @inheritDoc
	 * Instantiates a new board and sets the player to Player 1 initially.
	 */
	@Override
	public void notifyStartGame() {
		board = new Board();
		currentPlayer = player1;
		for (IObserver obs : observers) {
			obs.gameStarted();
		}
	}
	/**
	 * @inheritDoc
	 * Resets the board and sets player again to Player 1.
	 */
	@Override
	public void notifyResetGame() {
		board.resetGrid();
		currentPlayer = player1;
		for (IObserver obs: observers) {
			obs.gameReset();
		}
	}
	/**
	 * @inheritDoc
	 * 
	 */
	@Override
	public void notifyEndGame() {
		for (IObserver obs: observers) {
			obs.gameEnded();
		}
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public void notifyPlayerWon(Color c) {
		if (c == null)
			throw new IllegalArgumentException();
		for (IObserver obs: observers) {
			obs.declareWinner(c);
		}
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public void notifyPlayersSelected(String s) {
		if (s == null)
			throw new IllegalArgumentException();
		for (IObserver obs : observers) {
			obs.playersSelected(s);
		}
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public void notifyColumnRowSelected(int row, int col) {
		for (IObserver obs : observers) {
			obs.rowColSelected(row, col, currentPlayer.getColor());
		}
	}
	/**
	 * @inheritDoc
	 * Also sets the mode to AI if Player 2 is Computer and sends notification to Players
	 */
	@Override
	public void setPlayers(PlayerType a, PlayerType b, String s) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("PlayerType cannot be NULL");
		}
		if (b == PlayerType.COMPUTER) {
			modeAI = true;
		}
		player1 = PlayerFactory.createPlayerObject(a, Color.RED);
		player2 = PlayerFactory.createPlayerObject(b, Color.YELLOW);
		notifyPlayersSelected(s);
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public void notifySwitchPlayer() {
		for (IObserver obs : observers) {
			obs.playerChangeStatus(currentPlayer.getColor());
		}
	}
	/**
	 * @inheritDoc
	 * Sends a notification to all players after switch.
	 */
	@Override
	public void switchPlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
			
		}
		notifySwitchPlayer();
	}
	/**
	 * @inheritDoc
	 * Checks if the board is full, and if so, resets the board. 
	 * If there is an existing space for a row, colors the space, notifies players of column/row selection.
	 * Depending on whether the second player is human or computer, this method also checks if there is a winner
	 * after chip placement at that coordinate and notifies the players if there is a winner.
	 */
	@Override
	public boolean testColumnForEmptyCell(int column) {
		if (checkIfBoardFull()) {
			return true;
		}
		int row = board.getEmptyRowCellForColumn(column);
		
		if (row == -1) {
			return false;
		} else {
			board.setColor(row, column, currentPlayer.getColor());
			notifyColumnRowSelected(row, column);
			if (!checkIfWin(row, column, currentPlayer.getColor()) && !modeAI) {
				switchPlayer();  
			} else if (modeAI && !checkIfWin(row, column, currentPlayer.getColor()) && currentPlayer.getPlayerType() == PlayerType.HUMAN) {
				switchPlayer();
				computerMakeMove();
			} else {
				notifyPlayerWon(currentPlayer.getColor());
				
			}
			
			return true;
		}
	}
	/**
	 * A helper method for checking if the board is full
	 * @return true is full
	 */
	private boolean checkIfBoardFull() {
		if (board.checkBoardFull()) {
			currentPlayer = player1;
			notifyResetGame();
			return true;
		}
		return false;
	}
	/**
	 *  In Human vs. Computer mode, the computer first tries to find if there is a winning move
	 *  leading to a winning game. If there isn't any, then it randomly selects a column to insert if there is an open spot.
	 */
	private void computerMakeMove() {
		if (checkIfBoardFull()) {
			return;
		}
		int numCol = board.getCol();
		Color otherPlayerColor = currentPlayer.getColor() == Color.RED ? Color.YELLOW : Color.RED;
		
		//first check if opposing player is about to win. If so, stop them.
		for (int col = 0; col < numCol; col++) {
			int row = board.getEmptyRowCellForColumn(col);
			if (row != -1 && checkIfWin(row, col, otherPlayerColor)) {
				board.setColor(row, col, currentPlayer.getColor());
				notifyColumnRowSelected(row, col);
				switchPlayer();
				return;
			}
		}
		for (int col = 0; col < numCol; col++) {
			Color c = currentPlayer.getColor();
			int row = board.getEmptyRowCellForColumn(col);
			
			if (row != -1 && !checkIfWin(row, col, c)) {
				emptyCols.add(new Point(row, col));
			} else if (row != -1 && checkIfWin(row, col,c)) {
				board.setColor(row, col, c);
				notifyColumnRowSelected(row, col);
				notifyPlayerWon(c);
				return;
			}
		}
		Point coord = emptyCols.get(rand.nextInt(emptyCols.size()));
		board.setColor(coord.getRow(), coord.getCol(), currentPlayer.getColor());
		notifyColumnRowSelected(coord.getRow(), coord.getCol());
		emptyCols.clear();
		switchPlayer();
	}
	/**
	 * @inheritDoc
	 * 
	 */
	@Override
	public boolean checkIfWin(int row, int col, Color c) {
		int horizontalNum = board.checkWinHorizontal(row, col, c);
		int verticalNum = board.checkWinVertical(row, col, c);
		int diagonalUpperToBottomNum = board.checkWinDiagonalUpperLeftToBottomRight(row, col, c);
		int diagonalBottomToUpperNum = board.checkWinDiagonalBottomLeftToUpperRight(row, col, c);
		if (horizontalNum >= 3 || verticalNum >= 3 || diagonalUpperToBottomNum >= 3 || diagonalBottomToUpperNum >= 3) {
			return true;
		}
		return false;
	}
}


