import java.io.*;

import javax.swing.JOptionPane;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream inOne=new BufferedInputStream(new FileInputStream(new File("seuss.txt")));
		InputStream inTwo=new BufferedInputStream(new FileInputStream(new File("seuss.txt")));
		//System.out.println(in);
		TextParser parser=new TextParser(inOne, inTwo, true);
		TextGenerator generator=new TextGenerator(parser);
		JOptionPane.showMessageDialog(null, "The result is: \n"+generator.generateText(25));
		/*File file=new File("arrays/Eliot.txt");
		//file.mkdir();
		file.createNewFile();
		parser.writeArray(file);*/
	}
}
