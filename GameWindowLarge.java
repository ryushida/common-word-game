import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

/*
 * Ryuhei Shida
 * 5/28/17
 * Period 3
 * 
 * The Large Game window for the guessing game
 */

public class GameWindowLarge extends GameWindow {

	//The buttons used in the frame
	private JButton thirdWordB, fourthWordB, fifthWordB, sixthWordB;
	
	//Button handlers:
	private FirstWordHandler firstWHandler;
	private SecondWordHandler secondWHandler;
	private ThirdWordHandler thirdWHandler;
	private FourthWordHandler fourthWHandler;
	private FifthWordHandler fifthWHandler;
	private SixthWordHandler sixthWHandler;
	
	public GameWindowLarge(int numberOfRounds) throws FileNotFoundException {
		super(numberOfRounds);
		
		/*
		 * First Word
		 */
		firstWordB = new JButton(words[random[0]]); //button
		firstWHandler = new FirstWordHandler(); //handler
		firstWordB.addActionListener(firstWHandler); //adds action listener
		
		/*
		 * Second Word
		 */
		secondWordB = new JButton(words[random[1]]); //button
		secondWHandler = new SecondWordHandler(); //handler
		secondWordB.addActionListener(secondWHandler); //adds action listener
		
		/*
		 * Third Word
		 */
		thirdWordB = new JButton(words[random[2]]);
		thirdWHandler = new ThirdWordHandler();
		thirdWordB.addActionListener(thirdWHandler);
		
		/*
		 * Fourth Word
		 */
		fourthWordB = new JButton(words[random[3]]);
		fourthWHandler = new FourthWordHandler();
		fourthWordB.addActionListener(fourthWHandler);
		
		/*
		 * Fifth Word
		 */
		fifthWordB = new JButton(words[random[4]]);
		fifthWHandler = new FifthWordHandler();
		fifthWordB.addActionListener(fifthWHandler);
		
		/*
		 * sixth Word
		 */
		sixthWordB = new JButton(words[random[5]]);
		sixthWHandler = new SixthWordHandler();
		sixthWordB.addActionListener(sixthWHandler);

		
		Container pane = getContentPane();
		
		//vertical, horizontal
		pane.setLayout(new GridLayout(4, 2));
		
		
		pane.add(firstWordB);
		pane.add(secondWordB);
		pane.add(thirdWordB);
		pane.add(fourthWordB);
		pane.add(fifthWordB);
		pane.add(sixthWordB);
		pane.add(roundsLabel);
		pane.add(pointsLabel);

	}
	
	/**
	 * method that shows new words on the board
	 */
	private void showNewWords() {
		random[0] = (int) (Math.random() * 1000);
		firstWordB.setText(words[random[0]]);
		
		random[1] = (int) (Math.random() * 1000);
		while (random[1] == random[0]) {
			random[1] = (int) (Math.random() * 1000);
		}
		secondWordB.setText(words[random[1]]);
		
		random[2] = (int) (Math.random() * 1000);
		while (random[2] == random[0] || random[2] == random[1]) {
			random[2] = (int) (Math.random() * 1000);
		}
		thirdWordB.setText(words[random[2]]);
		
		random[3] = (int) (Math.random() * 1000);
		while (random[3] == random[0] || random[3] == random[1] || random[3] == random[2]) {
			random[3] = (int) (Math.random() * 1000);
		}
		fourthWordB.setText(words[random[3]]);
		
		random[4] = (int) (Math.random() * 1000);
		while (random[4] == random[0] || random[4] == random[1] || random[4] == random[2] || random[4] == random[3]) {
			random[4] = (int) (Math.random() * 1000);
		}
		fifthWordB.setText(words[random[4]]);

		random[5] = (int) (Math.random() * 1000);
		while (random[5] == random[0] || random[5] == random[1] || random[5] == random[2] || random[5] == random[3] || random[5] == random[4]) {
			random[5] = (int) (Math.random() * 1000);
		}
		sixthWordB.setText(words[random[5]]);
	}
	
	/**
	 * Abstract class for the handlers of the word choices
	 *
	 */
	private abstract class wordHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(moreCommon()) {
				points++;
				pointsLabel.setText("Points: " + String.valueOf(points));
			}
			updateResults();
			showNewWords();
			round++;
			roundsLabel.setText("Current Round: " + round);
			
			if (round > GameWindowMedium.numberOfRounds) {
				dispose(); //closes current game window
				GameOverWindow gow = new GameOverWindow(points, numberOfRounds, results);
			}
				
		}
		
		/**
		 * Checks if the word with index of random[index] is the more common than the other words
		 * @param wordRandomIndex index of the int array random
		 * @return true if most common, false if not
		 */
		protected Boolean mostCommon(int wordRandomIndex) {
			for (int i = wordRandomIndex + 1; i < random.length; i++) {
				if (random[i] < random[wordRandomIndex]) {
					return false;
				}
			}
			for (int i = 0; i < wordRandomIndex; i++) {
				if (random[i] < random[wordRandomIndex]) {
					return false;
				}
			}
			return true;

		}

		/**
		 * Adds all words with correct answer in brackets to the results string
		 * @param mostCommon the index of the most common word
		 */
		protected void addToResults(int mostCommon) {
			for (int i = 0; i < random.length; i++) {
				if (i == mostCommon) {
					results += "[" + words[random[i]] + "]";
				} else {
					results += words[random[i]];
				}
				if (i != random.length-1) {
					results += " ";
				}
			}
		}
		
		// abstract method for seeing whether the selected word is more common
		// than the others
		public abstract Boolean moreCommon();
		
		// abstract method for updating the results string
		public abstract void updateResults();
	}
	
	/**
	 * Event Handler that handles the first word choice
	 *
	 */
	private class FirstWordHandler extends wordHandler {
		public Boolean moreCommon() {
			//if the first word is more common than all of the others
			if(random[0] < random[1] && random[0] < random[2] && random[0] < random[3] 
					&& random[0] < random[4] && random[0] < random[5]) {
				//return true
				return true;
			}
				
			
			//else
			return false;
		}

		public void updateResults() {
			if (mostCommon(0)) {
				addToResults(0);
				results += "\n";
			} else if (mostCommon(1)) {
				addToResults(1);
				results += " -X\n";
			} else if (mostCommon(2)) {
				addToResults(2);
				results += " -X\n";
			} else if (mostCommon(3)){
				addToResults(3);
				results += " -X\n";
			} else if (mostCommon(4)) {
				addToResults(4);
				results += " -X\n";
			} else {
				addToResults(5);
				results += " -X\n";
			}
		}
	}
	
	/**
	 * Event Handler that handles the second word choice
	 *
	 */
	private class SecondWordHandler extends wordHandler {
		public Boolean moreCommon() {
			//if the second word is more common than all the others
			if(random[1] < random[0] && random[1] < random[2] && random[1] < random[3] 
					&& random[1] < random[4] && random[1] < random[5])				//return true
				return true;
			
			//else
			return false;
		}

		public void updateResults() {
			if (mostCommon(0)) {
				addToResults(0);
				results += " -X\n";
			} else if (mostCommon(1)) {
				addToResults(1);
				results += "\n";
			} else if (mostCommon(2)) {
				addToResults(2);
				results += " -X\n";
			} else if (mostCommon(3)){
				addToResults(3);
				results += " -X\n";
			} else if (mostCommon(4)) {
				addToResults(4);
				results += " -X\n";
			} else {
				addToResults(5);
				results += " -X\n";
			}
		}
	}
	
	/**
	 * Event Handler that handles the third word choice
	 *
	 */
	private class ThirdWordHandler extends wordHandler {
		public Boolean moreCommon() {
			//if the third word is more common than all the others
			if(random[2] < random[0] && random[2] < random[1] && random[2] < random[3] 
					&& random[2] < random[4] && random[2] < random[5])
				return true;
			
			//else
			return false;
		}

		public void updateResults() {
			if (mostCommon(0)) {
				addToResults(0);
				results += " -X\n";
			} else if (mostCommon(1)) {
				addToResults(1);
				results += " -X\n";
			} else if (mostCommon(2)) {
				addToResults(2);
				results += "\n";
			} else if (mostCommon(3)){
				addToResults(3);
				results += " -X\n";
			} else if (mostCommon(4)) {
				addToResults(4);
				results += " -X\n";
			} else {
				addToResults(5);
				results += " -X\n";
			}
		}
	}
	
	/**
	 * Event Handler that handles the fourth word choice
	 *
	 */
	private class FourthWordHandler extends wordHandler {
		public Boolean moreCommon() {
			//if the fourth word is more common than all the others
			if(random[3] < random[0] && random[3] < random[1] && random[3] < random[2] 
					&& random[3] < random[4] && random[3] < random[5])
				//return true
				return true;
			
			//else
			return false;
		}

		public void updateResults() {
			if (mostCommon(0)) {
				addToResults(0);
				results += " -X\n";
			} else if (mostCommon(1)) {
				addToResults(1);
				results += " -X\n";
			} else if (mostCommon(2)) {
				addToResults(2);
				results += " -X\n";
			} else if (mostCommon(3)){
				addToResults(3);
				results += "\n";
			} else if (mostCommon(4)) {
				addToResults(4);
				results += " -X\n";
			} else {
				addToResults(5);
				results += " -X\n";
			}
		}
	}
	
	/**
	 * Event Handler that handles the fifth word choice
	 *
	 */
	private class FifthWordHandler extends wordHandler {
		public Boolean moreCommon() {
			if(random[4] < random[0] && random[4] < random[1] && random[4] < random[2] 
					&& random[4] < random[3] && random[4] < random[5])
				return true;
			
			//else
			return false;
		}

		public void updateResults() {
			if (mostCommon(0)) {
				addToResults(0);
				results += " -X\n";
			} else if (mostCommon(1)) {
				addToResults(1);
				results += " -X\n";
			} else if (mostCommon(2)) {
				addToResults(2);
				results += " -X\n";
			} else if (mostCommon(3)){
				addToResults(3);
				results += " -X\n";
			} else if (mostCommon(4)) {
				addToResults(4);
				results += "\n";
			} else {
				addToResults(5);
				results += " -X\n";
			}
		}
		
	}
	
	/**
	 * Event Handler that handles the sixth word choice
	 *
	 */
	private class SixthWordHandler extends wordHandler {
		public Boolean moreCommon() {
			if(random[5] < random[0] && random[5] < random[1] && random[5] < random[2] 
					&& random[5] < random[3] && random[5] < random[4])
				return true;
			
			
			return false;
		}

		public void updateResults() {
			if (mostCommon(0)) {
				addToResults(0);
				results += " -X\n";
			} else if (mostCommon(1)) {
				addToResults(1);
				results += " -X\n";
			} else if (mostCommon(2)) {
				addToResults(2);
				results += " -X\n";
			} else if (mostCommon(3)){
				addToResults(3);
				results += " -X\n";
			} else if (mostCommon(4)) {
				addToResults(4);
				results += " -X\n";
			} else {
				addToResults(5);
				results += "\n";
			}
		}
		
	}

	protected void initalizeRandom() {
		random = new int[6];
	}

}
