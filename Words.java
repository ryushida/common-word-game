import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Ryuhei Shida
 * 5/29/17
 * Period 3
 * 
 * Gets the words from the input file and returns necessary words
 */
public class Words {
	
	private static String output = "";
	private String[] words = new String[1000];
	
	/**
	 * Creates a words object with the words in a String array
	 * @throws FileNotFoundException
	 */
	public Words() throws FileNoptFoundException {
		File inFile = new File("1000words.txt");
		
		Scanner in = new Scanner(inFile);
		
		//put all of the words from the file in the words Array
		for (int i = 0; i < 1000; i++) {
			words[i] = in.nextLine();
		}
		
		in.close();
	}
	
	/**
	 * Gets the words that start with specified letter
	 * @param firstLetter the letter that the words start with
	 * @return String with the words that start with firstLetter
	 * @throws FileNotFoundException
	 */
	public String getWordsStartingWith(String firstLetter) throws FileNotFoundException {
		//resets each time the getWord is called
		output = "";
		//gets words starting with a specific letter
		ArrayList<String> wordsStartingWith = new ArrayList<String>();;
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].substring(0, 1).equalsIgnoreCase(firstLetter)) {
				wordsStartingWith.add(words[i]);
			}
		}
		
		for (String s : wordsStartingWith)
			output += s + " ";
		
		//return the output, or (none) if there are not any words
		if (output.equals("")) {
			return "(none)";
		} else {
			return output;
		}
	}
	
	/**
	 * Gets the words that end with specified letter
	 * @param lastLetter the letter that the words end with
	 * @return String with the words that end with lastLetter
	 * @throws FileNotFoundException
	 */
	public String getWordsEndingWith(String lastLetter) throws FileNotFoundException {
		//resets each time the getWord is called
		output = "";
		//gets words starting with a specific letter
		ArrayList<String> wordsEndingWith = new ArrayList<String>();
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].substring(words[i].length()-1, words[i].length()).equalsIgnoreCase(lastLetter)) {
				wordsEndingWith.add(words[i]);
			}
		}
		
		for (String s : wordsEndingWith)
			output += s + " ";
		
		//return the output, or (none) if there are not any words
		if (output.equals("")) {
			return "(none)";
		} else {
			return output;
		}
	}
	
	/**
	 * Gets the words with a specified length
	 * @param length the length that the words have
	 * @return String with the words that have the length of length
	 * @throws FileNotFoundException
	 */
	public String getWordsWithLength(int length, String divider) throws FileNotFoundException {
		//resets each time the getWord is called
		output = "";
		//gets words with specific length
		ArrayList<String> wordsWithLength = new ArrayList<String>();
		
		//adds words that have the specified length to the arrayList
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() == length) {
				wordsWithLength.add(words[i]);
			}
		}
		
		//adds word and divider to output string
		for (int i = 0; i < wordsWithLength.size(); i++) {
			output += wordsWithLength.get(i);
			
			//adds divider only if it is not at the very end
			if (i != wordsWithLength.size()-1) {
				output += divider;
			}
		}
			
		if (output.equals("")) {
			return "(none)";
		} else {
			return output;
		}
	}
	
	/**
	 * Gets the length of all of the words
	 * @return int array with the lengths
	 * @throws FileNotFoundException
	 */
	public int[] getLengths() throws FileNotFoundException {
		
		int[] lengths = new int[words.length];
		
		for (int i = 0; i < words.length; i++) {
			lengths[i] = words[i].length();
		}
		
		return lengths;
	}
	
	
	/**
	 * Gets the String array with the words
	 * @return String array with the list of words
	 * @throws FileNotFoundException
	 */
	public String[] getWordsArray() throws FileNotFoundException {
		return this.words;
	}

	
	/**
	 * Gets the words with a specific letter
	 * @param letter letter to find words that include this
	 * @return String with list of words that include letter
	 */
	public String getWordsIncluding(String letter) {
		
		output = "";
		
		//gets words including a specific letter
		ArrayList<String> wordsIncluding = new ArrayList<String>();
		
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length()-1; j++) {
				if(words[i].substring(j, j+1).equalsIgnoreCase(letter)) {
					wordsIncluding.add(words[i]);
					continue;
				}
			}
		}
		
		for (String s : wordsIncluding)
			output += s + " ";
		
		//return the output, or (none) if there are not any words
		if (output.equals("")) {
			return "(none)";
		} else {
			return output;
		}
	}

	public String getPalindromes() {
		
		output = "";
		
		ArrayList<String> palindromes = new ArrayList<String>();
		
		for (int i = 0; i < words.length; i++) {
			if(words[i].toLowerCase().equals(reverseString(words[i]))) {
				palindromes.add(words[i]);
			}
		}
		
		for (String s : palindromes)
			output += s + " ";
		
		return output;
	}

	/**
	 * Returns the reverse of a string given the length and input
	 * @param input the string to find the reverse of
	 * @param length the length of the given string
	 * @return the reversed string
	 */
	public String reverseString(String input) {
		String reverse = "";
		int length = input.length();
		//reads the string backwards
		for(int i = length; i > 0; i--) {
			reverse += input.substring(i-1, i).toLowerCase();
		}
		
		return reverse;
	}
	
}
