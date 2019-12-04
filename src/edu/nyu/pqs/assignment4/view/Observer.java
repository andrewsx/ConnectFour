package edu.nyu.pqs.assignment4.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nyu.pqs.assignment4.model.IConnectFour;
import edu.nyu.pqs.assignment4.model.PlayerType;
/**
 * This class represents the View and the structure for presenting the UI to the user.
 * It communicates with the model upon interaction with the user, processing logic in the Model, 
 * and returning the data for presentation in the UI.
 * @author Andrew
 *
 */
public class Observer implements IObserver, ActionListener {
	private IConnectFour model;
	private final int row = 6;
	private final int col = 7;
	private boolean playersSelected = false;
	private List<JButton> listButtons = new ArrayList<JButton>();
	private JFrame frameAtStart = new JFrame();
	private JFrame frameForGrid = new JFrame();
	private JButton onePlayer = new JButton("Single Player Mode");
	private JButton twoPlayer = new JButton("Two Player Mode");
	private JButton start = new JButton("Start");
	private JButton exit = new JButton("Exit");
	private JButton reset = new JButton("Reset");
	private JLabel startLabel = new JLabel("Start Menu - Please Select Player Mode, Then Hit Start To Play!");
	private JLabel chooseDifferentColLabel = new JLabel("Column Full. Please pick a different one.");
	private JLabel curPlayerLabel = new JLabel("Player 1");
	private JLabel modeLabel = new JLabel();
	private JLabel winLabel = new JLabel("Game Over!");
	private JLabel coord = new JLabel();
	private JPanel displayWinner = new JPanel();
	private JPanel startPanel = new JPanel();
	private JPanel exitPanel = new JPanel();
	private JPanel grid = new JPanel();
	private JPanel[][] listPanels;
	/**
	 * Instantiates the observer and supplies it with the model and ensures that upon user clicking the close button,
	 * both windows will close. It also renders the opening game start window. 
	 * @param model
	 */
	public Observer(IConnectFour model) {
		this.model = model;
		model.addObserver(this);
		frameAtStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameForGrid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		openGameStartWindow();
	}
	/**    
	 *  Presents the player with the start window for player type selection.
	 */
	private void openGameStartWindow() {
		onePlayer.addActionListener(this);
		twoPlayer.addActionListener(this);
		start.addActionListener(this);
		exit.addActionListener(this);
		frameAtStart.add(startPanel);
		startLabel.setHorizontalAlignment(JLabel.CENTER);
		frameAtStart.add(startLabel, BorderLayout.NORTH);
		startLabel.setVisible(true);
		frameAtStart.add(modeLabel, BorderLayout.SOUTH);
		modeLabel.setVisible(false);
		modeLabel.setHorizontalAlignment(JLabel.CENTER);
		startPanel.setBackground(Color.white);
		startPanel.add(onePlayer);
		startPanel.add(twoPlayer);
		startPanel.add(start);
		startPanel.add(exit);
		frameAtStart.setSize(1000, 1000);
		frameAtStart.setLocationRelativeTo(null);
		frameAtStart.setVisible(true);
	}
	/**
	 *  Renders the grid and reset/exit buttons along with a row of buttons for column selection.
	 */
	private void initializeGrid() {
		listPanels = new JPanel[row][col];
		reset.addActionListener(this);
		exit.addActionListener(this); 
		frameAtStart.dispose();
		frameForGrid.add(grid);
		frameForGrid.setTitle("Connect Four");
		grid.setBackground(Color.GREEN);
		grid.setLayout(new GridLayout(row+1, col));
		
		for (int i = 0; i < col; i++) {
			JButton button = new JButton("" + (i + 1));
			button.addActionListener(this);
			grid.add(button);
			listButtons.add(button);
			
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				JPanel panel = new JPanel();
				panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				panel.setBackground(Color.WHITE);
				listPanels[i][j] = panel;
				grid.add(panel);
			}
		}
		
		exitPanel.add(reset);
		exitPanel.add(exit);
		exitPanel.add(displayWinner);
		exitPanel.add(chooseDifferentColLabel);
		exitPanel.add(coord);
		exitPanel.add(curPlayerLabel);
		coord.setVisible(false);
		curPlayerLabel.setVisible(true);
		chooseDifferentColLabel.setVisible(false);
		displayWinner.add(winLabel);
		winLabel.setVisible(false);
		displayWinner.setVisible(false);
		frameForGrid.add(BorderLayout.CENTER, grid);
		frameForGrid.add(BorderLayout.SOUTH, exitPanel);
		frameForGrid.setSize(2000, 2000);
		frameForGrid.setLocationRelativeTo(null);
		frameForGrid.setVisible(true);
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public void playersSelected(String s) {
		playersSelected = true;
		modeLabel.setText(s);
		modeLabel.setVisible(true);
	}
	
	/**
	 * @inheritDoc
	 */
	@Override
	public void gameStarted() {
		initializeGrid();
		
	}
	/**
	 * @inheritDoc
	 * Removes the observer
	 */
	@Override
	public void gameEnded() {
		model.removeObserver(this);
		System.exit(0);
		
	}
	/**
	 * @inheritDoc
	 * Removes all components inside panels and clears the buttons list
	 */
	@Override
	public void gameReset() {
		grid.removeAll();
		exitPanel.removeAll();
		frameForGrid.dispose();
		listButtons.clear();
		initializeGrid();
	}
	/**
	 * @inheritDoc
	 * Colors the appropriate row/col selection in the grid
	 */
	@Override
	public void rowColSelected(int row, int col, Color c) {
		coord.setText("The row selected is: " + (row+1) + "." + " The column selected is: " + (col+1) + ".");
		coord.setVisible(true);
		listPanels[row][col].setBackground(c);
	}
	/**
	 * @inheritDoc
	 * Also ensures that all buttons no longer respond after this point
	 */
	@Override
	public void declareWinner(Color c) {
		String temp = "";
		if (c == Color.RED) {
			temp = "1";
		} else {
			temp = "2";
		}
		
		winLabel.setText("Game Over! The Winner Is " + "Player " + temp + "!");
		displayWinner.setVisible(true);
		winLabel.setVisible(true);
		curPlayerLabel.setVisible(false);
		
		for (JButton button : listButtons) {
			button.removeActionListener(this);
		}
	}
	/**
	 * @inheritDoc
	 * 
	 */
	@Override 
	public void playerChangeStatus(Color c) {
		String temp = "";
		if (c == Color.RED) {
			temp = "1";
		} else {
			temp = "2";
		}
		curPlayerLabel.setText("Player " + temp + "'s Turn");
		
	}
	/**
	 * @inheritDoc
	 * Contains all the actions that occur upon user clicking of game buttons on UI.
	 * Upon the click of a column button, if there is no row spot open for that column, display a label informing user. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start && playersSelected) {
			model.notifyStartGame();
		}
		if (e.getSource() == exit) {
			model.notifyEndGame();
		}
		if (e.getSource() == reset) {
			model.notifyResetGame();
		}
		if (e.getSource() == onePlayer) {
			
			model.setPlayers(PlayerType.HUMAN, PlayerType.COMPUTER, "Human vs. Computer Mode Selected!");
		}
		if (e.getSource() == twoPlayer) {
			
			model.setPlayers(PlayerType.HUMAN, PlayerType.HUMAN, "Human vs. Human Mode Selected!");
		}
		for (int i = 0; i < listButtons.size(); i++) {
			if (e.getSource() == listButtons.get(i)) {
				boolean res = model.testColumnForEmptyCell(i);
				if (!res) {
					chooseDifferentColLabel.setVisible(true);
				}
				break;
			}
		}
	}
}
