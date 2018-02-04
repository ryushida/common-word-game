import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * Ryuhei Shida
 * 5/24/17
 * Period 3
 * 
 * Finds words that meet specific requirements
 */

public class WordFinder extends JFrame {

	// specifies the width and height of the frame
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 600;

	// The labels, textfields, and buttons used in the frame
	private JLabel wordsStartingWith, wordsEndingWith, wordsWithLength, wordsIncluding;
	private JTextField wordsStartingWithTF, foundWordsStartingWithTF, wordsEndingWithTF, foundWordsEndingWithTF,
			wordsWithLengthTF, foundWordsWithLengthTF, wordsIncludingTF, foundWordsIncludingTF, foundPalindromesTF;
	private JButton findWordsStartingWithB, findWordsEndingWithB, findWordsWithLengthB, findWordsIncludingB,
			findPalindromesB, exitB;

	// Button handers:
	private FindWordsStartingWithHandler fwswHandler;
	private FindWordsEndingWithHandler fwewHandler;
	private FindWordsWithLengthHandler fwwlHandler;
	private FindWordsIncludingHandler fwiHandler;
	private FindPalindromesHandler fpHandler;
	private ExitButtonHandler ebHandler;

	public WordFinder() {
		/*
		 * Words Starting With
		 */
		wordsStartingWith = new JLabel("Words starting with:", SwingConstants.RIGHT); // label
		wordsStartingWithTF = new JTextField(10); // input
		findWordsStartingWithB = new JButton("Find"); // button
		fwswHandler = new FindWordsStartingWithHandler(); // handler
		findWordsStartingWithB.addActionListener(fwswHandler); // adds action
																// listener
		foundWordsStartingWithTF = new JTextField(10); // output

		/*
		 * Words Ending With
		 */
		wordsEndingWith = new JLabel("Words ending with:", SwingConstants.RIGHT); // label
		wordsEndingWithTF = new JTextField(10); // input
		findWordsEndingWithB = new JButton("Find"); // button
		fwewHandler = new FindWordsEndingWithHandler(); // handler
		findWordsEndingWithB.addActionListener(fwewHandler); // adds action
																// listener
		foundWordsEndingWithTF = new JTextField(10); // output

		/*
		 * Words with length of
		 */
		wordsWithLength = new JLabel("Words with length of:", SwingConstants.RIGHT);
		wordsWithLengthTF = new JTextField(10);
		findWordsWithLengthB = new JButton("Find");
		fwwlHandler = new FindWordsWithLengthHandler();
		findWordsWithLengthB.addActionListener(fwwlHandler);
		foundWordsWithLengthTF = new JTextField(10);

		/*
		 * Words including
		 */
		wordsIncluding = new JLabel("Words Including letter:", SwingConstants.RIGHT);
		wordsIncludingTF = new JTextField(10);
		findWordsIncludingB = new JButton("Find");
		fwiHandler = new FindWordsIncludingHandler();
		findWordsIncludingB.addActionListener(fwiHandler);
		foundWordsIncludingTF = new JTextField(10);

		/*
		 * Palindromes
		 */
		findPalindromesB = new JButton("Find Palindromes");
		fpHandler = new FindPalindromesHandler();
		findPalindromesB.addActionListener(fpHandler);
		foundPalindromesTF = new JTextField(10);

		/*
		 * Exit
		 */
		exitB = new JButton("Exit"); // button
		ebHandler = new ExitButtonHandler(); // handler
		exitB.addActionListener(ebHandler); // adds action listener

		Container pane = getContentPane();

		pane.setLayout(new GridLayout(10, 2));

		/*
		 * Words Starting With
		 */
		pane.add(wordsStartingWith); // label
		pane.add(wordsStartingWithTF); // input
		// prevents more than one character from being typed in the JTextField
		wordsStartingWithTF.setDocument(new JTextFieldLimit());
		pane.add(findWordsStartingWithB); // button
		pane.add(foundWordsStartingWithTF); // output

		/*
		 * Words Ending With
		 */
		pane.add(wordsEndingWith); // label
		pane.add(wordsEndingWithTF); // input
		// prevents more than one character from being typed in the JTextField
		wordsEndingWithTF.setDocument(new JTextFieldLimit());
		pane.add(findWordsEndingWithB); // button
		pane.add(foundWordsEndingWithTF); // output

		/*
		 * Words with length of
		 */
		pane.add(wordsWithLength);
		pane.add(wordsWithLengthTF);
		pane.add(findWordsWithLengthB);
		pane.add(foundWordsWithLengthTF);

		/*
		 * Words including letter
		 */
		pane.add(wordsIncluding);
		pane.add(wordsIncludingTF);
		// //prevents more than one character from being typed in the JTextField
		wordsIncludingTF.setDocument(new JTextFieldLimit());
		pane.add(findWordsIncludingB);
		pane.add(foundWordsIncludingTF);

		/*
		 * Palindromes
		 */
		pane.add(findPalindromesB);
		pane.add(foundPalindromesTF);

		pane.add(exitB);

		setTitle("Word Finder");

		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	/**
	 * Limits the the number of characters that can be typed in a JTextField
	 * 
	 */
	private class JTextFieldLimit extends PlainDocument {
		// the value of the limit
		private int limit = 1;

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length() <= limit)) {
				super.insertString(offset, str, attr);
			}
		}
	}

	/**
	 * Event Handler that handles the Find button for the words starting with a
	 * specific letter
	 *
	 */
	private class FindWordsStartingWithHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String firstLetter, output;

			firstLetter = String.valueOf(wordsStartingWithTF.getText());

			output = "";

			try {
				output = getWordsStartingWith(firstLetter);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			foundWordsStartingWithTF.setText("" + output);

		}

		/**
		 * Return string with words starting with certain letter
		 * 
		 * @param firstLetter
		 *            letter that words are starting with
		 * @return String with words that start with firstLetter
		 * @throws FileNotFoundException
		 */
		public String getWordsStartingWith(String firstLetter) throws FileNotFoundException {
			Words w = new Words();
			return w.getWordsStartingWith(firstLetter);
		}
	}

	/**
	 * Event Handler that handles the Find button for the words ending with a
	 * specific letter
	 *
	 */
	private class FindWordsEndingWithHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String lastLetter, output;

			lastLetter = String.valueOf(wordsEndingWithTF.getText());

			output = "";
			try {
				output = getWordsEndingWith(lastLetter);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			foundWordsEndingWithTF.setText("" + output);
		}

		/**
		 * Return string with words that end with a certain letter
		 * 
		 * @param lastLetter
		 *            letter that words end with
		 * @return string with words that end with lastLetter
		 * @throws FileNotFoundException
		 */
		public String getWordsEndingWith(String lastLetter) throws FileNotFoundException {
			Words w = new Words();
			return w.getWordsEndingWith(lastLetter);
		}
	}

	/**
	 * Event Handler that handles the Find button for the words with a specific
	 * length
	 */
	private class FindWordsWithLengthHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String output;

			String lengthString = wordsWithLengthTF.getText();

			// if the input is not an positive integer, print a message
			if (!isInteger(lengthString)) {
				JOptionPane.showMessageDialog(null, "Please input a positive integer");
			} else if (Integer.parseInt(lengthString) <= 0) {
				JOptionPane.showMessageDialog(null, "Please input a positive integer");
			} else {
				int length = Integer.parseInt(lengthString);

				output = "";
				try {
					output = getWordsWithLength(length, " ");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				foundWordsWithLengthTF.setText("" + output);
			}

		}

		/**
		 * returns whether the string is an integer
		 * 
		 * @param lengthString
		 *            the userInput
		 * @return true if Integer, false if not integer
		 */
		private boolean isInteger(String lengthString) {
			// if it is a positive integer
			if (lengthString.matches("^-?\\d+$")) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * returns string with words of certain length
		 * 
		 * @param length
		 *            the length to find words for
		 * @param string 
		 * @return String with words with length of length.
		 * @throws FileNotFoundException
		 */
		private String getWordsWithLength(int length, String dividor) throws FileNotFoundException {
			Words w = new Words();
			return w.getWordsWithLength(length, dividor);
		}
	}

	/**
	 * Event handler that handles the Find button for the words that include a
	 * specific letter
	 *
	 */
	private class FindWordsIncludingHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String letter, output;

			letter = String.valueOf(wordsIncludingTF.getText());

			output = "";
			try {
				output = getWordsIncluding(letter);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			foundWordsIncludingTF.setText("" + output);
		}

		/**
		 * Return String with words that include a certain letter
		 * 
		 * @param letter
		 *            letter to find words for
		 * @return String including the letter of letter.
		 * @throws FileNotFoundException
		 */
		private String getWordsIncluding(String letter) throws FileNotFoundException {
			Words w = new Words();
			return w.getWordsIncluding(letter);
		}
	}

	/**
	 * Event Handler that handles the find button for palindromes
	 *
	 */
	private class FindPalindromesHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String output = "";

			try {
				output = getPalindromes();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			foundPalindromesTF.setText("" + output);
		}

		/**
		 * Return string with palindromes
		 * 
		 * @return String with list of palindromes
		 * @throws FileNotFoundException
		 */
		private String getPalindromes() throws FileNotFoundException {
			Words w = new Words();
			return w.getPalindromes();
		}

	}

	/**
	 * Event Handler that handles the exit button
	 *
	 */
	public class ExitButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// System.exit(0);
			dispose();
		}
	}
}
