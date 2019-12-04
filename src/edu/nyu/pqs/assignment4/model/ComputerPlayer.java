package edu.nyu.pqs.assignment4.model;

import java.awt.Color;
/**
 * This is the Computer implementation of Player interface.
 * @author Andrew
 *
 */
public class ComputerPlayer implements IPlayer {
	private Color c;
	private PlayerType type;
	
	public ComputerPlayer(Color c) {
		this.c = c;
		this.type = PlayerType.COMPUTER;
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
		return "Computer Player Attributes type=" + type + ", color=" + c;
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
		if (!(obj instanceof ComputerPlayer)) {
			return false;
		}
		ComputerPlayer other = (ComputerPlayer) obj;
		if (c != other.c) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}


}
