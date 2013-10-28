import java.awt.Dimension;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream inOne=new BufferedInputStream(new FileInputStream(new File("Melville.txt")));
		InputStream inTwo=new BufferedInputStream(new FileInputStream(new File("Melville.txt")));
		TextParser parser=new TextParser(inOne, inTwo, true);
		TextGenerator generator=new TextGenerator(parser);
		String result = generator.generateText(2);
		
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
