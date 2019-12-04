package edu.nyu.pqs.assignment4.model;

import java.awt.Color;

/**
 * This class represents the board upon where the game data will be stored and contains logic
 * for determining a winning board state. 
 * 
 */
import java.util.Arrays;
public class Board {
	
	
	private final int row = 6;
	private final int col = 7;
	private Color[][] board = new Color[row][col];
	
	
	/**
	 * Returns the row count
	 * @return
	 */
	protected int getRow() {
		return row;
	}
	/**
	 * Returns the column count
	 * @return
	 */
	protected int getCol() {
		return col;
	}
	
	/**
	 * Searches the board for a horizontal win (4 in a row).
	 * @param row row index
	 * @param col column index
	 * @param c Player color
	 * @return count of cells containing the player's color
	 */
	protected int checkWinHorizontal(int row, int col, Color c) {
		int count = 0;
		int colNumLeft = col - 1;
		int colNumRight = col + 1;
		while (colNumLeft >= 0 && board[row][colNumLeft] == c) {
			count++;
			colNumLeft--;
		}
		while (colNumRight < this.col && board[row][colNumRight] == c) {
			count++;
			colNumRight++;
		}
		return count;
	}
	
	/**
	 * Searches the board for a vertical win (4 in a column).
	 * @param row row index
	 * @param col column index
	 * @param c Player color
	 * @return count of cells containing the player's color
	 */
	protected int checkWinVertical(int row, int col, Color c) {
		int count = 0;
		int rowNum = row + 1;
		while (rowNum < this.row) {
			if (getColor(rowNum,col) == c) {
				count++;
				rowNum++;
			} else {
				break;
			}
			
		}
		return count;
	}
	/**
	 * Searches the board for a diagonal win (4 in a row diagonally).
	 * @param row row index
	 * @param col column index
	 * @param c Player color
	 * @return count of cells containing the player's color
	 */
	protected int checkWinDiagonalUpperLeftToBottomRight(int row, int col, Color c) {
		int count = 0;
		int rowNum = row - 1;
		int colNum = col - 1;
		while (rowNum >= 0 && colNum >= 0 && board[rowNum][colNum] == c) {
			count++;
			rowNum--;
			colNum--;
		}
		
		rowNum = row + 1;
		colNum = col + 1;
		
		while (rowNum < this.row && colNum < this.col && board[rowNum][colNum] == c) {
			count++;
			rowNum++;
			colNum++;
		}
		return count;
		
	}
	/**
	 * Searches the board for a diagonal win in the other direction.
	 * @param row row index
	 * @param col column index
	 * @param c Player color
	 * @return count of cells containing the player's color
	 */
	protected int checkWinDiagonalBottomLeftToUpperRight(int row, int col, Color c) {
		int count = 0;
		int rowNum = row + 1;
		int colNum = col - 1;
		while (rowNum < this.row && colNum >= 0 && board[rowNum][colNum] == c) {
			count++;
			rowNum++;
			colNum--;
		}
		
		rowNum = row - 1;
		colNum = col + 1;
		
		while (rowNum >= 0 && colNum < this.col && board[rowNum][colNum] == c) {
			count++;
			rowNum--;
			colNum++;
		}
		return count;
	}
	
	/**
	 * Checks board if it is full (stalemate)
	 * @return true if board is full
	 */
	
	protected boolean checkBoardFull() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Sets the color of a particular cell to the player's color
	 * @param row row index
	 * @param col column index
	 * @param c player's color
	 */
	protected void setColor(int row, int col, Color c) {
		if (c == null) {
			throw new NullPointerException("Color cannot be null");
		} 
		if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
			throw new IllegalArgumentException("Row or column beyond grid size.");
		} 
		board[row][col] = c;
		
	}
	/**
	 * Gets the color of a particular cell 
	 * @param row row index
	 * @param col column index
	 * @return Color of the Grid Location
	 */
	protected Color getColor(int row, int col) {
		if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
			throw new IllegalArgumentException("Row or column beyond grid size.");
		} 
		return board[row][col];
	}
	/**
	 * Checks to see if there is any empty row spot for designated column.
	 * @param row row index
	 * @param col column index
	 * @return row index containing empty cell
	 */
	protected int getEmptyRowCellForColumn(int col) {
		if (col < 0 || col >= this.col) {
			throw new IllegalArgumentException("Row or column beyond grid size.");
		}
		int rowNum = row - 1;
		while (rowNum >= 0) {
			if (board[rowNum][col] != null) {
				rowNum--;
			} else {
				return rowNum;
			}
		}
		return -1;
	}
	/**
	 * Resets the grid to a new one with spaces containing null
	 */
	protected void resetGrid() {
		board = new Color[row][col];
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + Arrays.hashCode(board);
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Board)) {
			return false;
		}
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board)) {
			return false;
		}
		if (col != other.col) {
			return false;
		}
		if (row != other.row) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public String toString() {
		return "State of board  row=" + row + ", column=" + col;
	}

}
