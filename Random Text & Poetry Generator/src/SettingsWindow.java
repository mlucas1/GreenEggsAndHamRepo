import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SettingsWindow extends JFrame {
	private JTextField numLines;
	private JTextField maxLineLength;
	private JTextField minLineLength;
	private Controller controller;

	public SettingsWindow(Controller c) {
		super("Settings");
		setLayout(new GridLayout(4, 2));
		add(new JLabel("Number of lines: ", SwingConstants.RIGHT));
		numLines=new JTextField();
		numLines.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num;
				try {
					num=Integer.parseInt(numLines.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number for the number of lines.");
					numLines.setText("");
					return;
<<<<<<< HEAD
=======
				}
				if(num<=0) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a positive number for the number of lines.");
					numLines.setText("");
					return;
>>>>>>> 10c9bd0032f8c2ccf19b4376ad8e71ba64c1b339
				}
				controller.setNumLines(num);
				//System.out.println("num lines is "+num);
			}

		});
		add(numLines);
		add(new JLabel("Maximum line length: ", SwingConstants.RIGHT));
		maxLineLength=new JTextField();
		maxLineLength.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num;
				try {
					num=Integer.parseInt(maxLineLength.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number for the maximum line length.");
					if(maxLineLength.getText().equals("?"))
						num=-1;
					else {
						maxLineLength.setText("");
						return;
					}
				}
				if(num<=0) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a positive number for the maximum line length.");
					maxLineLength.setText("");
					return;
				}
				controller.setMaxLineLength(num);
				//System.out.println("max is "+num);
			}

		});
		add(maxLineLength);
		add(new JLabel("Minimum line length: ", SwingConstants.RIGHT));
		minLineLength=new JTextField();
		minLineLength.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num;
				try {
					num=Integer.parseInt(minLineLength.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number for the minimum line length.");
					minLineLength.setText("");
					return;
				}
				if(num<=0) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a positive number for the minimum line length.");
					minLineLength.setText("");
					return;
				}
				//System.out.println("Min is "+num);
				controller.setMinLineLength(num);

			}

		});
		add(minLineLength);
		JButton clear=new JButton("Restore defaults");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				numLines.setText("20");
				numLines.postActionEvent();
				maxLineLength.setText("?");
				maxLineLength.postActionEvent();
				minLineLength.setText("0");
				minLineLength.postActionEvent();
			}

		});
		add(clear);
		JButton confirm=new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(numLines.getText()!=null&&!numLines.getText().equals("")) {
					numLines.postActionEvent();
				}
				if(maxLineLength.getText()!=null&&!maxLineLength.getText().equals("")) {
					maxLineLength.postActionEvent();
				}
				if(minLineLength.getText()!=null&&!minLineLength.getText().equals("")) {
					minLineLength.postActionEvent();
				}

			}

		});
		add(confirm);
		setSize(325, 175);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SettingsWindow(null);
	}
}
