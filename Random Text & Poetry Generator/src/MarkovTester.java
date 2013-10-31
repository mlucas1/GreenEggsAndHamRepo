import java.awt.Dimension;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream inOne=new BufferedInputStream(new FileInputStream(new File("Eliot.txt")));
		InputStream inTwo=new BufferedInputStream(new FileInputStream(new File("Eliot.txt")));
		InputStream inThree=new BufferedInputStream(new FileInputStream(new File("Eliot.txt")));
		TextParser parser=new TextParser(inOne, inTwo, inThree, true);
		TextGenerator generator=new TextGenerator(parser);
		String result = generator.generateText();
		
		/*
		String[] game = generator.generateGame();
		String result = game[0] + "\n" + game[1];
		*/
		
		JTextArea msg = new JTextArea(result);
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(msg);
		scrollPane.setPreferredSize(new Dimension(800,500)); 

		//generator.readAloud(result);
		System.out.println("Total Words: " + parser.numWords);
		System.out.println("Unique Words: " + parser.numUniqueTokens);
		System.out.println(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory());
		
		JOptionPane.showMessageDialog(null, scrollPane);
		
	}
}
