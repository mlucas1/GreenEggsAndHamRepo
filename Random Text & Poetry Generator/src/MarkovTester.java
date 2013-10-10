import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		InputStream in=new FileInputStream("seuss.txt");
		System.out.println(in);
		TextParser parser=new TextParser(in, true);
		TextGenerator generator=new TextGenerator(parser);
		System.out.println("\nThe result is: \n"+generator.generateText(25));
		File file=new File("arrays/seuss.txt");
		//file.mkdir();
		file.createNewFile();
		parser.writeArray(file);
	}
}
