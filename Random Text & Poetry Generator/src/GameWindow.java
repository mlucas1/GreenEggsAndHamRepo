import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private JLabel leftText, rightText;
	private GameButton leftButton, rightButton;
	private JLabel correctScore, incorrectScore;
	private int numCorrect, numIncorrect;
	
	private Controller controller;
	
	public GameWindow(Controller c) {
		super("Guessing Game");
		setLayout(new GridLayout(4, 2));
		controller=c;
		numCorrect=0;
		numIncorrect=0;
		leftText=new JLabel("", SwingConstants.CENTER);
		add(leftText);
		rightText=new JLabel("", SwingConstants.CENTER);
		add(rightText);
		leftButton=new GameButton("It's this one!");
		add(leftButton);
		rightButton=new GameButton("No, it's this one!");
		add(rightButton);
		correctScore=new JLabel("Correct: "+numCorrect, SwingConstants.CENTER);
		add(correctScore);
		incorrectScore=new JLabel("Incorrect: "+numIncorrect, SwingConstants.CENTER);
		add(incorrectScore);
		setSize(500, 300);
		setResizable(false);
		setVisible(true);
	}
	
	public void prepareGame() {
		String[] game=controller.getGenerator().generateGame();
		boolean shouldSwitch=(int)(Math.random()*2)==1;
		if(shouldSwitch) {
			leftButton.setIsCorrect(false);
			rightButton.setIsCorrect(true);
			leftText.setText(game[0]);
			rightText.setText(game[1]);
		}
		else {
			leftButton.setIsCorrect(true);
			rightButton.setIsCorrect(false);
			leftText.setText(game[1]);
			rightText.setText(game[0]);
		}
	}
	
	private class GameButton extends JButton {
		private boolean isCorrect;
		
		public GameButton(String text) {
			super(text);
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String message;
					if(isCorrect) {
						numCorrect++;
						correctScore.setText("Correct: "+numCorrect);
						message="That's right!";
					}
					else {
						numIncorrect++;
						incorrectScore.setText("Incorrect: "+numIncorrect);
						message="Sorry, that's not right.";
					}
					message+="\nWould you like to play again?";
					repaint();
					if(JOptionPane.showConfirmDialog(GameWindow.this, message, "Play again?", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
						prepareGame();
					else
						dispose();
				}
				
			});
		}
		
		public void setIsCorrect(boolean c) {
			isCorrect=c;
		}
	}
}
