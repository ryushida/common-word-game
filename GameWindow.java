import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/*
 * Ryuhei Shida
 * 5/28/17
 * Period 3
 * 
 * Abstract class for the small, medium, and large game windows
 */

public abstract class GameWindow extends JFrame {
	
	//specifies the width and height of the frame
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 700;

	//The labels and buttons used in the frame
	protected JLabel pointsLabel, roundsLabel;
	protected JButton firstWordB, secondWordB;

	//String array with the words from the input file
	protected String[] words;
	
	//variables for keeping track of points, number of rounds, and current round
	protected int points = 0;
	protected int round = 1;
	protected static int numberOfRounds = 10;
	
	//int array with random values for the selection of words
	protected int[] random;
	
	//string that holds results
	protected String results = "WrongAnswer [CorrectAnswer]\n-X means player was wrong\n\n";

	public GameWindow(int numberOfRounds) throws FileNotFoundException {
		GameWindow.numberOfRounds = numberOfRounds;
		
		Words w = new Words();
		words = w.getWordsArray();
		
		initalizeRandom();
		
		for (int i = 0; i < random.length; i++) {
			random[i] = (int) (Math.random() * 1000);
		}
		
		/*
		 * Generate new numbers if there is a duplicate
		 */
		for (int i = 1; i < random.length; i++) {
			while (random[i-1] == random[i]) {
				random[i] = (int) (Math.random() * 1000);
			}
		}
		
		/*
		 * Points
		 */
		pointsLabel = new JLabel("Points: 0", SwingConstants.CENTER);
		
		
		/*
		 * Rounds
		 */
		roundsLabel = new JLabel("Current Round: 1", SwingConstants.CENTER);

		
		
		setTitle("Guessing Game");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	protected abstract void initalizeRandom();
	

	
}
