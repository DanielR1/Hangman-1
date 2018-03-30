

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman {
	JFrame frame;
	JLabel label;
	JPanel panel;
	
void setup() {
	frame = new JFrame();
	panel = new JPanel();
	frame.add(panel);
	frame.setVisible(true);
	frame.pack();
	
}

public static void main(String[] args) {
	Hangman h = new Hangman();
	h.setup();
	String rounds = JOptionPane.showInputDialog("How many rounds would you like to play?");
	int r = Integer.parseInt(rounds);
	for (int i = 0; i < r; i++) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
		
		int random = new Random().nextInt(2999);
		for (int j = 0; j < random; j++) {
		br.readLine();
		}
		
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
