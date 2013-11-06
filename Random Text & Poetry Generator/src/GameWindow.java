import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class GameWindow extends JFrame {
	private JButton topButton, bottomButton;
	private GridLayout layout;
	
	private Controller controller;
	
	public GameWindow(Controller c) {
		super("Which is the AI's poety???");
		
		layout = new GridLayout(1, 3);
		topButton = new JButton("Top");
		topButton.addActionListener(c);
		bottomButton = new JButton("Bottom");
		bottomButton.addActionListener(c);
		layout.addLayoutComponent("Top", topButton);
		layout.addLayoutComponent("Bottom", bottomButton);
		
		setLayout(layout);
		setSize(500, 300);
		setResizable(false);
		setVisible(true);
	}
}
