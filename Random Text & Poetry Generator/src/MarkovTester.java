import java.io.*;

import javax.swing.JOptionPane;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream in=new BufferedInputStream(new FileInputStream(new File("seuss.txt")));
		System.out.println(in);
		TextParser parser=new TextParser(in, true);
		TextGenerator generator=new TextGenerator(parser);
		JOptionPane.showMessageDialog(null, "The result is: \n"+generator.generateText(15));
		File file=new File("arrays/seuss.txt");
		//file.mkdir();
		file.createNewFile();
		parser.writeArray(file);
	}
}
