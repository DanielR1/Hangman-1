
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	JFrame frame;
	JLabel label;
	JPanel panel;
	JLabel l;
	JLabel used;
	JLabel space;

	ArrayList<JLabel> c;
	boolean enable = false;
	String rounds = JOptionPane.showInputDialog("How many rounds would you like to play?");
	int r = Integer.parseInt(rounds);
	ArrayList<String> words = random();
	int lives;
	int wordchar;
	int rn = 1;
	String ul = "Used letters: ";
	String ulr = "";
	String rightletters = "";

	void setup() {
		frame = new JFrame();
		panel = new JPanel();
		l = new JLabel();
		used = new JLabel();
		space = new JLabel();
		panel.add(l);
		panel.add(used);

		l.setText("Lives: 10         ");
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		// frame.setSize(500,500);
		frame.addKeyListener(this);
		enable = true;

	}

	public ArrayList<String> random() {

		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < r; i++) {
			try {
				BufferedReader br = new BufferedReader(new FileReader("src/dictionary2.txt"));

				int random = new Random().nextInt(2999);
				for (int j = 0; j < random; j++) {
					br.readLine();
				}
				String w = br.readLine();
				boolean in = false;
				for (int j = 0; j < words.size(); j++) {
					if (words.get(j).equals(w)) {
						in = true;
						j = words.size();
					}
				}
				if (in == false) {
					words.add(w);
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return words;
	}

	public static void main(String[] args) {

		Hangman h = new Hangman();
		h.setup();
		h.createWord();
		for (int i = 0; i < h.r; i++) {
//System.out.println("run");
//System.out.println(h.words.get(h.rn-1));
			if (h.rn == h.r) {
				int x = 0;
				if (h.wordchar == 0) {
					JOptionPane.showMessageDialog(null, "You win! Out of rounds, goodbye!");
					System.exit(0);
					x = 1;
				}
				if (h.lives == 0) {
					JOptionPane.showMessageDialog(null,
							"You lose! The word was " + h.words.get(h.rn - 1) + ". You're out of rounds!");
					System.exit(0);
					x = 1;
				}
				if (x == 0) {
					i--;
				}
			} else {
				 System.out.println(h.wordchar);
				int x = 0;
				if (h.wordchar == 0) {
					System.out.println("win");
					JOptionPane.showMessageDialog(null, "You win! Let's play again!");
					h.rn++;
					h.panel.removeAll();
					h.l.setText("Lives: 10         ");
					h.used.setText("Used letters:");
					h.panel.add(h.l);
					h.createWord();
					h.panel.add(h.used);
					h.ul = "Used letters: ";
					h.ulr = "";
					h.rightletters = "";
					x = 11;
					h.wordchar = h.words.get(h.rn - 1).length();
				}
				if (h.lives == 0) {
//System.out.println("lose");
					JOptionPane.showMessageDialog(null,
							"You lose! The word was " + h.words.get(h.rn - 1) + ". Let's try again.");
					h.rn++;
					h.panel.removeAll();
					h.l.setText("Lives: 10         ");
					h.used.setText("Used letters:");
					h.panel.add(h.l);
					h.createWord();
					h.panel.add(h.used);
					h.wordchar = h.words.get(h.rn - 1).length();
					h.ul = "Used letters: ";
					h.ulr = "";
					h.rightletters = "";
					x = 11;
					h.wordchar = h.words.get(h.rn - 1).length();
				}
				if (x != 11) {

					i--;

				}
			}
		}
	}

	public void createWord() {

		c = new ArrayList<JLabel>();

		for (int j = 0; j < words.get(rn - 1).length(); j++) {
			c.add(new JLabel());
			c.get(j).setText("_");
			panel.add(c.get(j));
			c.get(j).setVisible(true);
			frame.pack();

		}
		panel.add(used);
		used.setText("Used letters:");
		frame.pack();
		lives = 10;
		wordchar = words.get(rn - 1).length();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println(wordchar);
		boolean death = true;
		if (enable == true) {
			if (rightletters.contains("" + e.getKeyChar())) {

			} else {

				for (int i = 0; i < words.get(rn - 1).length(); i++) {
					if (e.getKeyChar() == words.get(rn - 1).charAt(i)) {
						wordchar--;
						death = false;
						c.get(i).setText("" + e.getKeyChar());
						String temp = "" + e.getKeyChar() + ", ";
						rightletters += temp;

					}

				}

			}
			if (death) {

				if (ulr.contains("" + e.getKeyChar()) == false && rightletters.contains("" + e.getKeyChar()) == false) {

					lives--;
					l.setText("Lives: " + lives + "         ");
					String temp = "" + e.getKeyChar() + ", ";
					ul += temp;
					ulr += temp;
					used.setText(ul);
					frame.pack();

				}

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
