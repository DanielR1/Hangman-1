
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
	ArrayList<JLabel> c;
	boolean enable = false;
	String rounds = JOptionPane.showInputDialog("How many rounds would you like to play?");
	int r = Integer.parseInt(rounds);
	ArrayList<String> words = random();
	int lives;
	int wordchar;
	

	void setup() {
		frame = new JFrame();
		panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
		enable=true;

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

	}

	public void createWord() {
		
		for (int i = 0; i < r; i++) {
			c = new ArrayList<JLabel>();

			for (int j = 0; j < words.get(i).length(); j++) {
				c.add(new JLabel());
				c.get(j).setText("_");
				panel.add(c.get(j));
				c.get(j).setVisible(true);
				frame.pack();

			}
			lives=10;
			wordchar=c.size();

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	boolean death = true;
		if(enable==true) {
			for (int i = 0; i < words.get(r).length(); i++) {
				if(e.getKeyChar()==words.get(r).charAt(i)) {
					wordchar--;
					death=false;
				}
				
			}
			if (death) {
				lives--;
			}
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
