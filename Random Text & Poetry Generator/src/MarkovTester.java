import java.io.File;
import java.io.IOException;


public class MarkovTester {
	public static void main(String[] args) throws IOException {
		TextParser parser=new TextParser(MarkovTester.class.getResourceAsStream("seuss.txt"), true);
		TextGenerator generator=new TextGenerator(parser);
		generator.generateText(25);
		File file=new File("arrays/seuss.txt");
		file.mkdirs();
		file.createNewFile();
		parser.writeArray(file);
	}
}
