import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class StringPromptScreen extends JFrame implements ActionListener{
	
	private Controller control;
	private GridBagLayout manager;
	private GridBagConstraints constraints;
	private JButton cancel, ok;
	private JScrollPane poemPane;
	private JPanel poemPanel;
	private JTextPane poemText;
	
	public StringPromptScreen(Controller c)
	{
		super("Enter your text: ");
		manager = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		constraints.weightx = 1.0;
		constraints.weighty = 10.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 5;
		constraints.gridwidth = 2;
		poemText = new JTextPane();
		poemPanel = new JPanel(new BorderLayout());
		poemPanel.add(poemText);
		poemPane = new JScrollPane(poemPanel);
		poemPane.setViewportView(poemText); 
		manager.setConstraints(poemPane, constraints);
		this.add(poemPane);
		validate();
		
		constraints.weighty = 1.0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		manager.setConstraints(cancel, constraints);
		this.add(cancel);
		validate();
		
		constraints.gridx = 1;
		ok = new JButton("OK");
		ok.addActionListener(this);
		manager.setConstraints(ok, constraints);
		this.add(ok);
		validate();
		
		control = c;
		
		setVisible(true);
		setSize(300, 200);
		setLayout(manager);
		validate();
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		String buttonText = ((JButton)(source)).getText();
		if (buttonText.equals("OK"))
		{
			control.userText = poemText.getText();
			control.actionPerformed(event);
		}
		this.dispose();
	}
}
