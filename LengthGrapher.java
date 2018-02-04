import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.geom.*;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * Ryuhei Shida
 * 5/28/17
 * Period 3
 * 
 * Graphs the lengths of the words
 */
public class LengthGrapher extends JFrame {

	// specifies the width and height of the frame
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 300;

	public LengthGrapher() throws FileNotFoundException {

		setTitle("lengths of words");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	/**
	 * Draws the graph with the values, labels, and lines
	 */
	public void paint(Graphics g) {
		Words wh = null;
		try {
			wh = new Words();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		int[] lengths = null;

		try {
			lengths = wh.getLengths();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Graphics2D g2 = (Graphics2D) g;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		//adds y axis labels
		JButton[] buttons = new JButton[14];
		for (int i = 0; i <= 13; i++) {
			buttons[i] = new JButton(String.valueOf(i));
			buttons[i].setBounds(20, 260 - i * 20, 20, 20);
			buttons[i].setBorder(null);
			buttons[i].addActionListener(new yLabelHandler(i));
			panel.add(buttons[i]);
			add(panel);
		}
		super.paint(g2);

		for (int i = 1; i <= 13; i++) {
			// y axis labels
//			g.drawString(String.valueOf(i), 35, 300 - i * 20);

			// horizontal lines
			Line2D line = new Line2D.Float(50, 280 - i * 20, 1050, 280 - i * 20);
			g2.draw(line);
		}

		// vertical bars
		for (int i = 50; i < 1050; i = i + 1) {
			Line2D line = new Line2D.Float(i, 300, i, 300 - lengths[i - 50] * 20);
			g2.draw(line);
		}
	}
	
	/**
	 * Button handler that handles the y axis label buttons
	 *
	 */
	private class yLabelHandler implements ActionListener {
		
		private int num;
		
		/**
		 * sets the num variable to the value of the y axis label
		 * @param i number from the y axis label
		 */
		public yLabelHandler(int i) {
			this.num = i;
		}

		public void actionPerformed(ActionEvent e) {
			for (int i = num; i <= num; i++) {
				try {
					//show the frame below the graph
					JFrame frame = new JFrame();
					frame.setLocation(0, 300);
					
					//get outputString with ", " to separate the words
					Words w = new Words();
					String outputString = w.getWordsWithLength(i, ", ");
					
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
					
					frame.setTitle(i + "");
					frame.setSize(WIDTH, HEIGHT);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		
	}
}