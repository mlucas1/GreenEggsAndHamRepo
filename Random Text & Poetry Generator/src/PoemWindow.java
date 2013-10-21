import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class PoemWindow extends JFrame{

	private Controller control;
	private GridBagLayout manager;
	private GridBagConstraints constraints;
	private JButton refreshButton, presetButton, userButton, 
						readButton, storeButton, settingsButton;
	private JScrollPane poemPane;
	private JPanel poemPanel;
	private JTextPane poemText;
	
	public static void main(String[]args)
	{
		PoemWindow test = new PoemWindow(new Controller());
	}
	
	public PoemWindow(Controller parent) {
		super("Poetry");
		control = parent;
		manager = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		constraints.weightx = 0.4;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		refreshButton = new JButton("Refresh Poem");
		refreshButton.addActionListener(control);
		manager.setConstraints(refreshButton, constraints);
		this.add(refreshButton);
		
		constraints.gridy = 1;
		presetButton = new JButton("Read Aloud");
		presetButton.addActionListener(control);
		manager.setConstraints(presetButton, constraints);
		this.add(presetButton);
		
		constraints.gridy = 2;
		userButton = new JButton("Preset Styles");
		userButton.addActionListener(control);
		manager.setConstraints(userButton, constraints);
		this.add(userButton);
		
		constraints.gridy = 3;
		readButton = new JButton("User Text");
		readButton.addActionListener(control);
		manager.setConstraints(readButton, constraints);
		this.add(readButton);
		
		constraints.gridy = 4;
		storeButton = new JButton("Save Line");
		storeButton.addActionListener(control);
		manager.setConstraints(storeButton, constraints);
		this.add(storeButton);
		
		constraints.gridy = 5;
		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(control);
		manager.setConstraints(settingsButton, constraints);
		this.add(settingsButton);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 10;
		constraints.gridheight = 6;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		poemText = new JTextPane();
		poemPanel = new JPanel(new BorderLayout());
		poemPanel.add(poemText );
		poemPane = new JScrollPane(poemPanel);
		poemPane.setViewportView(poemText); 
		manager.setConstraints(poemPane, constraints);
		this.add(poemPane);
		
		setSize(700, 500);
		setLayout(manager);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setPoemText(String poem)
	{
		poemText.setText(poem);
	}
	
	public String getPoemText()
	{
		return poemText.getText();
	}

}
