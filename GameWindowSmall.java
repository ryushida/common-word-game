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
 * The Small Game window for the guessing game
 */

public class GameWindowSmall extends GameWindow  {
	
	//The labels in buttons used in the frame
	private JButton firstWordB, secondWordB;
	
	//Button handlers:
	private FirstWordHandler fwHandler;
	private SecondWordHandler swHandler;

	public GameWindowSmall(int numberOfRounds) throws FileNotFoundException {
		super(numberOfRounds);
		
		/*
		 * First Word
		 */
		firstWordB = new JButton(words[random[0]]); //button
		fwHandler = new FirstWordHandler(); //handler
		firstWordB.addActionListener(fwHandler); //adds action listener
		
		/*
		 * Second Word
		 */
		secondWordB = new JButton(words[random[1]]); //button
		swHandler = new SecondWordHandler(); //handler
		secondWordB.addActionListener(swHandler); //adds action listener
		
		Container pane = getContentPane();
		
		//vertical, horizontal
		pane.setLayout(new GridLayout(2, 2));
		
		
		pane.add(firstWordB);
		pane.add(secondWordB);
		pane.add(roundsLabel);
		pane.add(pointsLabel);

	}

	/**
	 * method that shows new words on the board
	 */
	private void showNewWords() {
		//1st word
		random[0] = (int) (Math.random() * 1000);
		firstWordB.setText(words[random[0]]);
		
		//2nd word
		random[1] = (int) (Math.random() * 1000);
		while (random[0] == random[1]) {
			random[1] = (int) (Math.random() * 1000);
		}
		secondWordB.setText(words[random[1]]);
	}
	
	/**
	 * Abstract class for the handlers of the word choices
	 *
	 */
	private abstract class wordHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(moreCommon()) {
				//add up the points and update on screen
				points++;
				pointsLabel.setText("Points: " + String.valueOf(points));
			}
			updateResults();
			showNewWords();
			
			//add up the rounds and update on screen
			round++;
			roundsLabel.setText("Current Round: " + round);
			
			//if the specified number of rounds are completed
			if (round > GameWindowSmall.numberOfRounds) {
				dispose(); //closes current game window
				GameOverWindow gow = new GameOverWindow(points, numberOfRounds, results);
			}
				
		}
		//abstract method for seeing whether the selected word is more common than the others
		public abstract Boolean moreCommon();
		//abstract method for updating the results string
		public abstract void updateResults();
	}
	
	/**
	 * Event Handler that handles the first word choice
	 *
	 */
	private class FirstWordHandler extends wordHandler {
		public Boolean moreCommon() {
			//if the first word is more common
			if(random[0] < random[1])
				//return true
				return true;
			
			//else
			return false;
		}

		public void updateResults() {
			if(random[0] < random[1]) {
				results += "[" + words[random[0]] + "] " + words[random[1]] + "\n";
			} else {
				results += words[random[0]] + " [" + words[random[1]] + "] -X " + "\n";
			}
		}
	}
	
	/**
	 * Event Handler that handles the second word choice
	 *
	 */
	private class SecondWordHandler extends wordHandler {
		public Boolean moreCommon() {
			if(random[1] < random[0])
				//return true
				return true;
			
			//else
			return false;
		}
		
		public void updateResults() {
			if(random[0] < random[1]) {
				results += "[" + words[random[0]] + "] " + words[random[1]] + " -X\n";
			} else {
				results += words[random[0]] + " [" + words[random[1]] + "]\n";
			}
		}
	}

	@Override
	protected void initalizeRandom() {
		random = new int[2];
	}
}
