import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class IntPromptScreen extends JFrame implements ActionListener{

	private Controller control;
	private GridBagLayout manager;
	private GridBagConstraints constraints;
	private JButton cancel, ok;
	private JScrollPane firstPane, lastPane;
	private JPanel firstPanel, lastPanel;
	private JTextField firstNum, lastNum;
	
	public IntPromptScreen(Controller c) {
		super("Enter the first and last line numbers: ");
		manager = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		firstNum = new JTextField();
		firstPanel = new JPanel(new BorderLayout());
		firstPanel.add(firstNum);
		firstPane = new JScrollPane(firstPanel);
		firstPane.setViewportView(firstNum); 
		manager.setConstraints(firstPane, constraints);
		this.add(firstPane);
		validate();
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		lastNum = new JTextField();
		lastPanel = new JPanel(new BorderLayout());
		lastPanel.add(lastNum);
		lastPane = new JScrollPane(lastPanel);
		lastPane.setViewportView(lastNum); 
		manager.setConstraints(lastPane, constraints);
		this.add(lastPane);
		validate();
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		manager.setConstraints(cancel, constraints);
		this.add(cancel);
		validate();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
