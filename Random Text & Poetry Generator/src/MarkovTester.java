import java.io.*;

import javax.swing.JOptionPane;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream inOne=new BufferedInputStream(new FileInputStream(new File("arrays/Eliot.txt")));
		InputStream inTwo=new BufferedInputStream(new FileInputStream(new File("arrays/Eliot.txt")));
		//System.out.println(in);
		TextParser parser=new TextParser(inOne, inTwo, false);
		TextGenerator generator=new TextGenerator(parser);
		JOptionPane.showMessageDialog(null, "The result is: \n"+generator.generateText(15));
		//String result = generator.generateText(30);
		//JOptionPane.showMessageDialog(null, "The result is: \n"+result);
		//generator.readAloud(result);
		//File file=new File("arrays/Whitman.txt");
		//file.mkdir();
		//file.createNewFile();
		//parser.writeArray(file);
	}
}
