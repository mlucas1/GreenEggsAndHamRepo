import java.io.*;

import javax.swing.JOptionPane;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream inOne=new BufferedInputStream(new FileInputStream(new File("Whitman.txt")));
		InputStream inTwo=new BufferedInputStream(new FileInputStream(new File("Whitman.txt")));
		TextParser parser=new TextParser(inOne, inTwo, true);
		TextGenerator generator=new TextGenerator(parser);
		String result = generator.generateText(50);
		//generator.readAloud(result);
		System.out.println("Total Words: " + parser.numWords);
		System.out.println("Unique Words: " + parser.numUniqueTokens);
		System.out.println(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory());
		JOptionPane.showMessageDialog(null, "The result is: \n"+result);
		
	}
}
