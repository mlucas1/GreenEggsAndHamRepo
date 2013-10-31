import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class GameWindow extends JFrame {
	private JTextField correctLine;
	private JButton correctButton;
	private JTextField computerLine;
	private JButton wrongButton;
	
	private Controller controller;
	
	public GameWindow(Controller c) {
		super("Guessing Game");
		
		
	}
}
