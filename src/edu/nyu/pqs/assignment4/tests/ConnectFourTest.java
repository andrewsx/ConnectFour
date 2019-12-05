package edu.nyu.pqs.assignment4.tests;

import java.awt.Color;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import edu.nyu.pqs.assignment4.model.Board;
import edu.nyu.pqs.assignment4.model.ConnectFour;
import edu.nyu.pqs.assignment4.model.IConnectFour;
import edu.nyu.pqs.assignment4.model.PlayerType;
import edu.nyu.pqs.assignment4.view.IObserver;
import edu.nyu.pqs.assignment4.view.Observer;

public class ConnectFourTest {

	@Test
	public void testAddingObserver() {
		IConnectFour model = ConnectFour.getInstance();
		IObserver obs1 = new Observer(model);
		Assertions.assertTrue(model.addObserver(obs1));
	}
	@Test
	public void testAddingNullObserver() {
		final IConnectFour model = ConnectFour.getInstance();
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				model.addObserver(null);
			}
		});
	}
	@Test
	public void testRemoveObserver() {
		IConnectFour model = ConnectFour.getInstance();
		IObserver obs1 = new Observer(model);
		model.addObserver(obs1);
		Assertions.assertTrue(model.removeObserver(obs1));
	}
	@Test
	public void testRemoveNullObserver() {
		final IConnectFour model = ConnectFour.getInstance();
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				model.removeObserver(null);
			}
		});
	}
	@Test
	public void testNotifyPlayerWonNull() {
		final IConnectFour model = ConnectFour.getInstance();
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				model.notifyPlayerWon(null);
			}
		});
	}
	@Test
	public void testNotifyPlayersSelectedNull() {
		final IConnectFour model = ConnectFour.getInstance();
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				model.notifyPlayersSelected(null);
			}
		});
	}
	@Test
	public void testSetPlayersNull() {
		final IConnectFour model = ConnectFour.getInstance();
		
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				model.setPlayers(null, PlayerType.HUMAN, "sfdsgfdg");
			}
		});
	}
	@Test
	public void testSetPlayersOtherNull() {
		final IConnectFour model = ConnectFour.getInstance();
		
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				model.setPlayers(PlayerType.HUMAN, null, "sfdsgfdg");
			}
		});
	}
	@Test
	public void testSetPlayersComputerCheck() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "sdfsfd");
		Assertions.assertTrue(model.getMode());
	}
	@Test
	public void testSwitchPlayer() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		model.notifyStartGame();
		model.switchPlayer();
		Assertions.assertEquals(PlayerType.COMPUTER, model.getPlayer().getPlayerType());
	}
	@Test
	public void testSwitchPlayerSwap() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		model.notifyStartGame();
		model.switchPlayer();
		model.switchPlayer();
		Assertions.assertEquals(PlayerType.HUMAN, model.getPlayer().getPlayerType());
	}
	@Test
	public void testColumnForEmptyCellFullColumnCheckWinNoAI() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.HUMAN, "Human vs. Human Mode Selected!");
		model.notifyStartGame();
		Board b = model.getBoard();
		for (int row = 5; row >= 3 ; row--) {  //sets all of column 0 to RED
			b.setColor(row, 0, Color.RED);
		}
		Assertions.assertTrue(model.testColumnForEmptyCell(0));
	}
	@Test
	public void testColumnForEmptyCellFullColumnCheckWinNoAISec() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.HUMAN, "Human vs. Human Mode Selected!");
		model.notifyStartGame();
		Board b = model.getBoard();
		
		b.setColor(5, 2, Color.RED);
		
		Assertions.assertTrue(model.testColumnForEmptyCell(0));
	}
	@Test
	public void testColumnForEmptyCellFullColumn() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		model.notifyStartGame();
		IObserver obs1 = new Observer(model);
		model.addObserver(obs1);
		Board b = model.getBoard();
		for (int i = 0; i < b.getRow(); i++) {  //sets all of column 0 to RED
			b.setColor(i, 0, Color.RED);
		}
		Assertions.assertFalse(model.testColumnForEmptyCell(0));
	}
	@Test
	public void testColumnForEmptyCellEmptyColumn() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		model.notifyStartGame();
		Board b = model.getBoard();
		for (int i = 0; i < b.getRow(); i++) {  //sets all of column 0 to RED
			b.setColor(i, 0, Color.RED);
		}
		Assertions.assertTrue(model.testColumnForEmptyCell(6));
	}
	@Test
	public void testCheckIfWin() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		model.notifyStartGame();
		IObserver obs1 = new Observer(model);
		model.addObserver(obs1);
		Board b = model.getBoard();
		for (int row = 5; row >= 3 ; row--) {  //sets all of column 0 to RED
			b.setColor(row, 0, Color.RED);
		}
		Assertions.assertTrue(model.checkIfWin(2,0));
	}

	@Test
	public void testComputerMakeMove() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		model.notifyStartGame();
		Board b = model.getBoard();
		for (int row = 5; row >= 3 ; row--) {  //sets all of column 0 to RED
			b.setColor(row, 0, Color.YELLOW);
		}
		model.switchPlayer();
		Assertions.assertTrue(model.checkIfWin(2,0));
	}
	@Test
	public void testColumnForEmptyCellFilledBoard() {
		final ConnectFour model = ConnectFour.getInstance();
		model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		model.notifyStartGame();
		Board b = model.getBoard();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				b.setColor(i, j, Color.RED);
			}
		}
		
		Assertions.assertTrue(model.testColumnForEmptyCell(6));
	}
}

//stalemate board
//b.setColor(0,0,Color.RED);
//b.setColor(0,3,Color.RED);
//b.setColor(0,6,Color.RED);
//b.setColor(1,0,Color.RED);
//b.setColor(1,1,Color.RED);
//b.setColor(1,2,Color.RED);
//b.setColor(1,4,Color.RED);
//b.setColor(1,5,Color.RED);
//b.setColor(2,3,Color.RED);
//b.setColor(3,0,Color.RED);
//b.setColor(3,1,Color.RED);
//b.setColor(3,3,Color.RED);
//b.setColor(3,4,Color.RED);
//b.setColor(3,5,Color.RED);
//b.setColor(4,3,Color.RED);
//b.setColor(4,6,Color.RED);
//b.setColor(5,1,Color.RED);
//b.setColor(5,2,Color.RED);
//b.setColor(5,4,Color.RED);
//b.setColor(5,5,Color.RED);
//b.setColor(5,6,Color.RED);
//b.setColor(0,1,Color.YELLOW);
