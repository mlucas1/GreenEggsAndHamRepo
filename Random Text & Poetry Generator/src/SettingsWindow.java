import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextField gameLines;
	private Controller controller;

	public SettingsWindow(Controller c) {
		super("Settings");
		controller=c;
		setLayout(new GridLayout(5, 2));
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
				}
				if(num<=0) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a positive number for the number of lines.");
					numLines.setText("");
					return;
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
					if(maxLineLength.getText().equals("?"))
						num=-1;
					else {
						JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number for the maximum line length.");
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
				if(num<0) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a positive number for the minimum line length.");
					minLineLength.setText("");
					return;
				}
				try {
					if(num>Integer.parseInt(maxLineLength.getText()))
						JOptionPane.showMessageDialog(SettingsWindow.this, "The minimum line length must be less than\nthe maximum line length.");
				}catch(NumberFormatException e) {
					if(num>controller.getMaxLineLength())
						JOptionPane.showMessageDialog(SettingsWindow.this, "The minimum line length must be less than\nthe maximum line length.");
				}
				//System.out.println("Min is "+num);
				controller.setMinLineLength(num);

			}

		});
		add(minLineLength);
		add(new JLabel("Lines for game: ", SwingConstants.RIGHT));
		gameLines=new JTextField();
		gameLines.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int num;
				try {
					num=Integer.parseInt(gameLines.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a number for the number of lines for the game.");
					gameLines.setText("");
					return;
				}
				if(num<=0||num>10) {
					JOptionPane.showMessageDialog(SettingsWindow.this, "Please input a positive number less than 10 for the number of lines.");
					gameLines.setText("");
					return;
				}
				controller.setGameLines(num);
				//System.out.println("num lines is "+num);
			}

		});
		add(gameLines);
		JButton clear=new JButton("Restore defaults");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				numLines.setText("20");
				numLines.postActionEvent();
				maxLineLength.setText("?");
				maxLineLength.postActionEvent();
				minLineLength.setText("0");
				minLineLength.postActionEvent();
				gameLines.setText("2");
				gameLines.postActionEvent();
			}

		});
		add(clear);
		JButton confirm=new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(numLines.getText()!=null&&!numLines.getText().equals("")
						&&maxLineLength.getText()!=null&&!maxLineLength.getText().equals("")
						&&minLineLength.getText()!=null&&!minLineLength.getText().equals("")
						&&gameLines.getText()!=null&&!gameLines.getText().equals("")) {
					numLines.postActionEvent();
					maxLineLength.postActionEvent();
					minLineLength.postActionEvent();
					gameLines.postActionEvent();
				}
				else {
					JOptionPane.showMessageDialog(SettingsWindow.this, "You may not leave any fields blank.");
					return;
				}
				dispose();
			}

		});
		add(confirm);
		numLines.setText(""+controller.getNumLines());
		if(controller.getMaxLineLength()==-1)
			maxLineLength.setText("?");
		else
			maxLineLength.setText(""+controller.getMaxLineLength());
		minLineLength.setText(""+controller.getMinLineLength());
		gameLines.setText(""+controller.getGameLines());
		setSize(325, 210);
		setResizable(false);
		setVisible(true);
	}
	
}
