package sqr;
import javax.swing.*;

public class Game {
	public Game(int width, int height, String title) {
		JFrame frame = new JFrame(title);      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frame.setSize(width, height);  

        frame.setVisible(true);
	}
}