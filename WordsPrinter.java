import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * Ryuhei Shida
 * 5/27/17
 * Period 3
 * 
 * Prints the words from the input file in a scrollable window
 */

public class WordsPrinter extends JFrame{
	
	//specifies the width and height of the frame
	private static final int WIDTH = 300;
	private static final int HEIGHT = 700;
	
	public WordsPrinter() throws FileNotFoundException {
		
		//construct words object
		Words w = new Words();
		
		//gets the String array with 1000 words
		String[] words = w.getWordsArray();
		
		//adds each word to the output string along with
		//its corresponding number
		String outputString = "";
		for (int i = 0; i < words.length; i++) {
			outputString += i+1 + ": " + words[i] + "\n";
		}
		
		//add output string to the text area
		JTextArea text = new JTextArea(outputString);
		
		//add scroll bars
		JScrollPane scroll = new JScrollPane (text, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		setTitle("1000 words");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}
}
