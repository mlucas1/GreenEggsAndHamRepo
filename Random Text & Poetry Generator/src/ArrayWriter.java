import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ArrayWriter {
	//For convenience...
	public static void main(String[]args)
	{
		String fileName = "Shakespeare.txt";
		String outputFileName = "Shakespeare.txt";
		try {
			TextParser parse = new TextParser(new BufferedInputStream(new FileInputStream(fileName)), new BufferedInputStream(new FileInputStream(fileName)), new BufferedInputStream(new FileInputStream(fileName)), true);
			parse.writeArray(new File(outputFileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Fool.");
			e.printStackTrace();
		}
	}

}
