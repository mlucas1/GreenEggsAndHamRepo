import java.io.*;
import java.util.*;

/**
 * An object of class TextParser analyzes text, generating 
 * a list of all the tokens, and a 2D Markov array containing
 * the state probabilities.
 * 
 * @author ML, ZB, MW
 */
public class TextParser {

	private BufferedReader reader; 
	private BufferedWriter writer;
	
	/*
	 * Each significant token occurring in the text is 
	 * assigned a unique, sequential integer ID.
	 */
	private HashMap<String, Integer> intAssignments;
	private HashMap<Integer, String> stringAssignments;
	private int numTokens;
	private int averageLineLength;
	
	/*
	 * The first-order Markov array of token frequencies
	 * (current token ID is row, next token ID is col)
	 */
	private double[][] probabilities;

	public TextParser(String path) {
		//Creates a new TextParser that will read from the file specified by path.
		try {
			reader=new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Path: "+path);
			e.printStackTrace();
		}
		try {
			//TODO decide on an output path
			String outputPath="";
			File file=new File(path);
			file.createNewFile();
			writer=new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println("IOException when creating output file.");
			e.printStackTrace();
		}
	}
	
	/*
	 * Parses raw text
	 */
	public void readRawText(InputStream in) {
		
	}
	
	/*
	 * Reads a saved Markov array from a text file
	 */
	public void readArray(InputStream in) {
		
	}
	
	/*
	 * Writes the Markov array to a text file
	 */
	public void writeArray(String path) throws IOException {
		File file=new File(path);
		if(file.exists()) {
			//TODO do something to deal with merging arrays
		}
		else
			file.createNewFile();
		BufferedWriter out=new BufferedWriter(new FileWriter(file));
		for(int i=0; i<stringAssignments.size(); i++)
			out.write(stringAssignments.get(i)+" ");
		out.write("\n");
		for(int r=0; r<probabilities.length; r++) {
			for(int c=0; c<probabilities[r].length; c++)
				out.write(probabilities[r][c]+" ");
			out.write("\n");
		}
		//out.write(averageLineLength+"");
	}
	
	//returns the ID for a token
	public int getInt(String s) {
		return intAssignments.get(s);
	}
	
	//returns the token with ID x
	public String getString(int x) {
		//TODO 
		return null;
	}
	
	//returns the Markov array
	public double[][] getMarkovArray() {
		return probabilities;
	}
	
	/*
	Under the brown land, mixing
	Winter
	With a burnished throne
	Burned green and gold.
	Where the faint moonlight, the chapel, only
	Fishing in soda water
	Endeavors to folly and puts a fortnight dead mountain mouth (\n?) of empty rooms
	*/
}
