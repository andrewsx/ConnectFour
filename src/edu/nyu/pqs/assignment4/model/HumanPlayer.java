package edu.nyu.pqs.assignment4.model;

import java.awt.Color;
/**
 * This class is the Human implementation of Player interface.
 * @author Andrew
 *
 */
public class HumanPlayer implements IPlayer {
	private Color c;
	private PlayerType type;
	
	public HumanPlayer(Color c) {
		this.c = c;
		this.type = PlayerType.HUMAN;
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public Color getColor() {
		return c;
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public PlayerType getPlayerType() {
		return type;
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Human Player Attributes type=" + type + ", color=" + c;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof HumanPlayer)) {
			return false;
		}
		HumanPlayer other = (HumanPlayer) obj;
		if (c != other.c) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
}
