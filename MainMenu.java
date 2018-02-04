import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.FileNotFoundException;

/*
 * Ryuhei Shida
 * 5/6/17
 * Period 3
 * 
 * The Main Menu for the program
 * 
 */

public class MainMenu extends JFrame {
	
	//specifies the width and height of the frame
	private static final int WIDTH = 900;
	private static final int HEIGHT = 300;
	
	//the buttons used in the frame
	private JButton findWordsB, graphLengthsB, printWordsB, guessingGameB, exitB;
	
	//Button handlers:
	private FindWordsHandler fwHandler;
	private GraphLengthsHandler glHandler;
	private PrintWordsHandler pwHandler;
	private GuessingGameHandler ggHandler;
	private ExitButtonHandler ebHandler;
	
	public MainMenu()
	{
		
		/*
		 * Print the words
		 */
		printWordsB = new JButton("Print Words"); //button
		pwHandler = new PrintWordsHandler(); //handler
		printWordsB.addActionListener(pwHandler); //adds action listener
				
		/*
		 * Find Words
		 */
		findWordsB = new JButton("Find Words"); //button
		fwHandler = new FindWordsHandler(); //handler
		findWordsB.addActionListener(fwHandler); //adds action listener
		
		
		/*
		 * Graph lengths of words
		 */
		graphLengthsB = new JButton("Graph Lengths"); //button
		glHandler = new GraphLengthsHandler(); //handler
		graphLengthsB.addActionListener(glHandler); //adds action listener
		
		/*
		 * Guessing Game
		 */
		guessingGameB = new JButton("Guessing Game"); //button
		ggHandler = new GuessingGameHandler(); //handler
		guessingGameB.addActionListener(ggHandler); //adds action listener
		
		/*
		 * Exit
		 */
		exitB = new JButton("Exit"); //button
		ebHandler = new ExitButtonHandler(); //handler
		exitB.addActionListener(ebHandler); //adds action listener
		
		//sets title of Frame 
		setTitle("1000 Most Common Words");
		
		Container pane = getContentPane();
		
		//vertical, horizontal
		pane.setLayout(new GridLayout(5, 1));
		
		pane.add(printWordsB);
		pane.add(findWordsB);
		pane.add(graphLengthsB);
		pane.add(guessingGameB);
		pane.add(exitB);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Event Handler that handles the print words button
	 *
	 */
	private class PrintWordsHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			try {
				WordsPrinter wp = new WordsPrinter();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Event Handler that handles the find words button
	 *
	 */
	private class FindWordsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WordFinder wf = new WordFinder();
		}
	}
	
	/**
	 * Event Handler that handles the graph lengths button
	 */
	private class GraphLengthsHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				LengthGrapher lg = new LengthGrapher();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	
	/**
	 * Event Handler that handles the guessing game button
	 *
	 */
	private class GuessingGameHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			GameIntroWindow giw = new GameIntroWindow();
		}
		
	}
	
	/**
	 * Event Handler that handles the exit button
	 *
	 */
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		MainMenu wordObj = new MainMenu();
	}
}
