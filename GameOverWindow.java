import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
 * Ryuhei Shida
 * 5/29/17
 * Period 3
 * 
 * Game Over Window that shows score and option to start a new game
 */
public class GameOverWindow extends JFrame {
	
	//specifies the width and height of the frame
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	private JButton resultsB, newSmallGameB, newMediumGameB, newLargeGameB, endGameB;
	private JTextField roundsTF;
	private JLabel gameOverText, scoreText, rounds, percentageCorrect, placeHolder;
	
	//button handlers
	private ResultsHandler resultsH;
	private NewSmallGameHandler newSmallGameH;
	private NewMediumGameHandler newMediumGameH;
	private NewLargeGameHandler newLargeGameH;
	private EndGameHandler endGameH;
	
	private String sizeOfLastGame;
	private String results;
	
	public GameOverWindow(int points, int numberOfRounds, String results) {
		
		this.results = results;
		
		setTitle("GAME OVER");
		
		gameOverText = new JLabel("GAME OVER", SwingConstants.CENTER);
		gameOverText.setFont(new Font("Arial", Font.BOLD , 20));
		scoreText = new JLabel("Score: " + points + "/" + numberOfRounds, SwingConstants.CENTER);
		
		/*
		 * "Results" Button
		 */
		resultsB = new JButton("Results");
		resultsH = new ResultsHandler();
		resultsB.addActionListener(resultsH);
		
		/*
		 * "New Game (small)" Button
		 */
		newSmallGameB = new JButton("New Game (small)");
		newSmallGameH = new NewSmallGameHandler();
		newSmallGameB.addActionListener(newSmallGameH);
		
		/*
		 * "New Game (medium)" Button
		 */
		newMediumGameB = new JButton("New Game (medium)");
		newMediumGameH = new NewMediumGameHandler();
		newMediumGameB.addActionListener(newMediumGameH);
		
		/*
		 * "New Game (large)" Button
		 */
		newLargeGameB = new JButton("New Game (large)");
		newLargeGameH = new NewLargeGameHandler();
		newLargeGameB.addActionListener(newLargeGameH);
		
		rounds = new JLabel("# of rounds: ", SwingConstants.RIGHT);
		/*
		 * TextField to enter the number of rounds (10 by default)
		 */
		String numberRoundsString = Integer.toString(numberOfRounds);
		roundsTF = new JTextField(numberRoundsString, 10);

		/*
		 * Percentage Correct
		 */
		int percentage = (int) ((double) points / (double) numberOfRounds * 100);
		percentageCorrect = new JLabel("Percentage Correct: " + percentage + "%", SwingConstants.CENTER);
		
		placeHolder = new JLabel(""); 
		
		
		/*
		 * "End Game" Button
		 */
		endGameB = new JButton("End Game");
		endGameH = new EndGameHandler();
		endGameB.addActionListener(endGameH);
		
		Container pane = getContentPane(); 
		
		pane.setLayout(new GridLayout(5, 2));
		
		pane.add(gameOverText);
		pane.add(scoreText);
		pane.add(percentageCorrect);
		pane.add(resultsB);
		pane.add(newSmallGameB);
		pane.add(newMediumGameB);
		pane.add(newLargeGameB);
		pane.add(endGameB);
//		pane.add(placeHolder);
		pane.add(rounds);
		pane.add(roundsTF);

		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	public class ResultsHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//show the frame below the graph
			JFrame frame = new JFrame();
			frame.setLocation(0, 400);
			
			//get outputString with ", " to separate the words
//				Words w = new Words();
//				String outputString = w.getWordsWithLength(i, ", ");
			
			String outputString = results;
			
			//add output string to the text area
			JTextArea text = new JTextArea(outputString);
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			frame.add(text);
			
			//add scroll bars
			JScrollPane scroll = new JScrollPane (text, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			frame.add(scroll);
			
			//specifies the width and height of the frame
			final int WIDTH = 600;
			final int HEIGHT = 300;
			
			frame.setTitle("Results");
			frame.setSize(WIDTH, HEIGHT);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(HIDE_ON_CLOSE);

		}
	}
	
	public abstract class NewGameHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String roundsInput = roundsTF.getText();
			if(!isInteger(roundsInput)) {
				JOptionPane.showMessageDialog(null, "Please input a positive integer");
			} else if (Integer.parseInt(roundsInput) <= 0) {
				JOptionPane.showMessageDialog(null, "Please input a positive integer");
			} else {
				int rounds = Integer.valueOf(roundsInput);
				dispose(); //closes current window for new game
				try {
					createNewWindow(rounds);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
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
		
		public abstract void createNewWindow(int rounds) throws FileNotFoundException;
		
	}
	
	/**
	 * Event Handler that handles the "New Game (small)" button and starts a new game
	 * 
	 */
	public class NewSmallGameHandler extends NewGameHandler {
		public void createNewWindow(int rounds) throws FileNotFoundException {
			GameWindowSmall gw = new GameWindowSmall(rounds);
		}
	}
	
	/**
	 * Event Handler that handles the "New Game (medium)" button and starts a new game
	 * 
	 */
	public class NewMediumGameHandler extends NewGameHandler {
		public void createNewWindow(int rounds) throws FileNotFoundException {
			GameWindowMedium gw = new GameWindowMedium(rounds);
		}
	}
	
	public class NewLargeGameHandler extends NewGameHandler {
		public void createNewWindow(int rounds) throws FileNotFoundException {
			GameWindowLarge gw = new GameWindowLarge(rounds);
		}	
	}
	
	/**
	 * Event Handler that handles the "End Game" button and closes the game windows 
	 *
	 */
	private class EndGameHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose(); //closes current window to end game
		}
	}

}
