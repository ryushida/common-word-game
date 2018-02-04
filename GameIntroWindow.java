import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
 * Ryuhei Shida
 * 5/27/17
 * Period 3
 * 
 * Intro window for the game with instructions and options
 */

public class GameIntroWindow extends JFrame {
	
	//specifies the width and height of the frame
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	
	//The labels, textfields, and buttons used in the frame
	private JLabel instructionsLabel, instructionsText, rounds, choicesLabel;
	private JTextField roundsTF;
	private JButton startGameB, endGameB;
	
	//button handlers
	private StartGameHandler startGameH;
	private EndGameHandler endGameH;
	
	//combo box
	private JComboBox<String> choicesComboBox;
	
	public GameIntroWindow() {
		
		setTitle("Common Word Guessing Game");
		
		/*
		 * Game Intro Text
		 */
		instructionsLabel = new JLabel("Instructions: ", SwingConstants.CENTER);
		instructionsText = new JLabel("Guess the most common word (specify # of rounds)", SwingConstants.LEFT);
		
		rounds = new JLabel("# of rounds: ", SwingConstants.RIGHT);
		/*
		 * TextField to enter the number of rounds (10 by default)
		 */
		roundsTF = new JTextField("10", 10);
	
		
		choicesLabel = new JLabel("Number of Choices in the Game: ", SwingConstants.RIGHT);
		
		/*
		 * comboBox
		 */
		String[] options = { "small (2)", "medium (4)", "large(6)" };
		choicesComboBox = new JComboBox<String>(options);
		choicesComboBox.setSelectedIndex(0);
				
		
		/*
		 * "Start Game" Button
		 */
		startGameB = new JButton("Start Game");
		startGameH = new StartGameHandler();
		startGameB.addActionListener(startGameH);
		
		
		/*
		 * "End Game" Button
		 */
		endGameB = new JButton("End Game");
		endGameH = new EndGameHandler();
		endGameB.addActionListener(endGameH);
		
		Container pane = getContentPane();
		
		pane.setLayout(new GridLayout(4, 2));
		
		//add to content pane
		pane.add(instructionsLabel);
		pane.add(instructionsText);
		pane.add(rounds);
		pane.add(roundsTF);
		pane.add(choicesLabel);
		pane.add(choicesComboBox);
		pane.add(startGameB);
		pane.add(endGameB);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}
	
	/**
	 * Event Handler that handles the "Start Game" button and starts a new game
	 */
	public class StartGameHandler implements ActionListener {
		
		public StartGameHandler() {
		}

		public void actionPerformed(ActionEvent e) {
			try {
				//guard against 0 and null and negative numbers
				String roundsInput = roundsTF.getText();
				if(!isInteger(roundsInput)) {
					JOptionPane.showMessageDialog(null, "Please input a positive integer");
				} else if (Integer.parseInt(roundsInput) <= 0) {
					JOptionPane.showMessageDialog(null, "Please input a positive integer");
				}
				else {
					dispose(); //close current window
					int rounds = Integer.valueOf(roundsTF.getText());
					if (choicesComboBox.getSelectedIndex() == 0) {
						GameWindowSmall gw = new GameWindowSmall(rounds);
//						SmallGameWindow gw = new SmallGameWindow();
					} else if (choicesComboBox.getSelectedIndex() == 1){
						GameWindowMedium gw = new GameWindowMedium(rounds);
					} else {
						GameWindowLarge lw = new GameWindowLarge(rounds);
					}
				}
				
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		/**
		 * returns whether the string is an integer
		 * @param lengthString the userInput
		 * @return true if Integer, false if not integer
		 */
		private boolean isInteger(String lengthString) {
			//if it is a positive integer
			if (lengthString.matches("^-?\\d+$")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Button Handler for the End Game Button
	 *
	 */
	private class EndGameHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose(); //closes current window to end game
		}
		
	}
	
}
