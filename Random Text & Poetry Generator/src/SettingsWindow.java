import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SettingsWindow extends JFrame {
	private JTextField numLines;
	private JTextField maxLineLength;
	private JTextField minLineLength;
	private Controller controller;

	public SettingsWindow(Controller c) {
		super("Settings");
		setLayout(new GridLayout(4, 2));
		add(new JLabel("Number of lines: "));
		numLines=new JTextField();
		numLines.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num;
				try {
					num=Integer.parseInt(numLines.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number.");
					numLines.setText("");
					return;
				}
				controller.setNumLines(num);

			}

		});
		add(numLines);
		add(new JLabel("Maximum line length: "));
		maxLineLength=new JTextField();
		maxLineLength.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num;
				try {
					num=Integer.parseInt(maxLineLength.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number.");
					maxLineLength.setText("");
					return;
				}
				controller.setMaxLineLength(num);

			}

		});
		add(maxLineLength);
		add(new JLabel("Minimum line length: "));
		minLineLength=new JTextField();
		minLineLength.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num;
				try {
					num=Integer.parseInt(minLineLength.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number.");
					minLineLength.setText("");
					return;
				}
				controller.setMinLineLength(num);

			}

		});
		add(minLineLength);
		JButton clear=new JButton("Restore defaults");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		add(clear);
		setSize(400, 200);
		setResizable(false);
		setVisible(true);
	}
}
