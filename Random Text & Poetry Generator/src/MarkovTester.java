import java.io.*;

import javax.swing.JOptionPane;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream inOne=new BufferedInputStream(new FileInputStream(new File("arrays/Eliot.txt")));
		InputStream inTwo=new BufferedInputStream(new FileInputStream(new File("arrays/Eliot.txt")));
		TextParser parser=new TextParser(inOne, inTwo, false);
		TextGenerator generator=new TextGenerator(parser);
		String result = generator.generateText(30);
		//generator.readAloud(result);
		System.out.println("Total Words: " + parser.numWords);
		System.out.println("Unique Words: " + parser.numUniqueTokens);
		JOptionPane.showMessageDialog(null, "The result is: \n"+result);
		
	}
}
