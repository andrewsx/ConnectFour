package edu.nyu.pqs.assignment4.tests;



import java.awt.Color;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import edu.nyu.pqs.assignment4.model.Board;


public class BoardTest {

	@Test
	public void testSetColorNull() {
		Assertions.assertThrows(NullPointerException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				Board b = new Board();
				b.setColor(2,4,null);
			}
		});
	}
	@Test
	public void testSetColorIllegal() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				Board b = new Board();
				b.setColor(-1,4,Color.RED);
			}
		});
	}
	@Test
	public void testGetColorIllegal() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				Board b = new Board();
				b.getColor(-1,4);
			}
		});
	}
	@Test
	public void testGetColorNoIssue() {
		
		Board b = new Board();
		b.setColor(5,0, Color.YELLOW);
		Assertions.assertEquals(Color.YELLOW,b.getColor(5,0));
	}
	@Test
	public void testCheckBoardFull() {
		Board b = new Board();
		for (int i = 0; i < b.getRow(); i++) {
			for (int j = 0; j < b.getCol(); j++) {
				b.setColor(i,j,Color.RED); 
			}
		}
		Assertions.assertTrue(b.checkBoardFull());
	}
	@Test
	public void testCheckBoardNotFull() {
		Board b = new Board();
		b.setColor(5,0, Color.YELLOW);
		Assertions.assertFalse(b.checkBoardFull());
	}
	@Test
	public void testResetGrid() {
		Board b = new Board();
		b.setColor(5,0, Color.YELLOW);
		b.resetGrid();
		Assertions.assertEquals(null,b.getColor(5,0));
	}
	@Test
	public void testgetEmptyRowCellForColumnIllegalArg() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				Board b = new Board();
				b.getEmptyRowCellForColumn(9);
			}
		});
	}
	@Test
	public void testgetEmptyRowCellForColumnFullColumn() {
		Board b = new Board();
		for (int row = 0; row < b.getRow(); row++) {
			b.setColor(row,0, Color.YELLOW);
		}
		
		Assertions.assertEquals(-1, b.getEmptyRowCellForColumn(0));
	}
	@Test
	public void testgetEmptyRowCellForColumnNoIssue() {
		Board b = new Board();
		b.setColor(5,0, Color.YELLOW);
		Assertions.assertEquals(4, b.getEmptyRowCellForColumn(0));
	}
	@Test
	public void testCheckWinHorizontal() {
		Board b = new Board();
		b.setColor(5,0, Color.YELLOW);
		b.setColor(5,2, Color.YELLOW);
		Assertions.assertEquals(2, b.checkWinHorizontal(5, 1, Color.YELLOW));
	}
	@Test
	public void testCheckWinVertical() {
		Board b = new Board();
		b.setColor(5,1, Color.YELLOW);
		b.setColor(4,1, Color.YELLOW);
		Assertions.assertEquals(2, b.checkWinVertical(3, 1, Color.YELLOW));
	}
	@Test
	public void testCheckWinDiagonalUpperLeftToBottomRight() {
		Board b = new Board();
		b.setColor(5,2, Color.YELLOW);
		b.setColor(3,0, Color.YELLOW);
		Assertions.assertEquals(2, b.checkWinDiagonalUpperLeftToBottomRight(4, 1, Color.YELLOW));
	}
	@Test
	public void testCheckDiagonalBottomLeftToUpperRight() {
		Board b = new Board();
		b.setColor(5,0, Color.YELLOW);
		b.setColor(3,2, Color.YELLOW);
		Assertions.assertEquals(2, b.checkWinDiagonalBottomLeftToUpperRight(4, 1, Color.YELLOW));
	}
	@Test
	public void testToString() {
		Board b = new Board();
		Assertions.assertEquals("State of board  row=6, column=7", b.toString());
	}
}

