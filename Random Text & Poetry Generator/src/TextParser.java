import java.io.*;
import java.util.*;

public class TextParser {

	private BufferedReader reader;
	private BufferedWriter writer;
	
	private HashMap<String, Map<Integer, String>> map;

	public TextParser(String path) {
		//Creates a new TextParser that will read from the file specified by path.
		try {
			reader=new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Path: "+path);
			e.printStackTrace();
		}
		try {
			String outputPath=""; //TODO decide on an output path
			File file=new File(path);
			file.createNewFile();
			writer=new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println("IOException when creating output file.");
			e.printStackTrace();
		}
	}

}
