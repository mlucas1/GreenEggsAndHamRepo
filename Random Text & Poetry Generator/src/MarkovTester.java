import java.awt.Dimension;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		File file=new File("TwelfthNight.txt");
		InputStream inOne=new BufferedInputStream(new FileInputStream(file));
		InputStream inTwo=new BufferedInputStream(new FileInputStream(file));
		InputStream inThree=new BufferedInputStream(new FileInputStream(file));
		TextParser parser=new TextParser(file, null, inOne, inTwo, inThree, true);
		parser.writeArray(new File("arrays/Melville.txt"));
		/**TextGenerator generator=new TextGenerator(parser);
		String result = generator.generateText();*/

		TextGenerator generator=new TextGenerator(parser);
		String result = generator.generateText();
		
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
