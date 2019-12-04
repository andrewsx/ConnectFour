package edu.nyu.pqs.assignment4.model;
/**
 * This class represents a Point, or coordinate on the grid. Each consists of a row and a column (x, y).
 * @author Andrew
 *
 */
public class Point {
	private int row;
	private int col;
	
	
	public Point(int r, int c) {
		this.row = r;
		this.col = c;
	}
	/**
	 * Returns the row index
	 * @return
	 */
	protected int getRow() {
		return row;
	}
	/**
	 * Returns the column index
	 * @return
	 */
	protected int getCol() {
		return col;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
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
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
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
	 */
	@Override
	public String toString() {
		return "Point Attributes: row=" + this.row + ", col=" + this.col;
	}
}
