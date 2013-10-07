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
	private int[][] occurences;

	public TextParser(InputStream stream, boolean isRawText) {
		//Creates a new TextParser that will read stream.
		if(isRawText)
			readRawText(stream);
		else
			readArray(stream);
	}
	
	/*
	 * initializes the private HashMaps with string-int ID pairings
	 */
	private void analyzeUniqueStrings(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			for (String s: line.split("")) {
				
			}
		}
	}
	
	/*
	 * Parses raw text
	 */
	public void readRawText(InputStream in) {
		analyzeUniqueStrings(in);
		//Stuff
		occurences = new int[intAssignments.size()][intAssignments.size()];
		calculateProbabilities(occurences);
	}
	
	/*
	 * converts an int[][] of numbers of occurrences to probabilities
	 */
	private void calculateProbabilities(int[][] occs) {
		double[][] probabilities = new double[occs.length][occs[0].length];
		for (int row = 0; row < occs.length; row++)
		{
			double total = 0.0;
			for (int col = 0; col < occs[0].length; col++)
			{
				total += occs[row][col];
			}
			for (int col = 0; col < occs[0].length; col++)
			{
				probabilities[row][col] = occs[row][col]/total;
			}
		}
	}
	
	

	/*
	 * Reads a saved Markov array from a text file
	 */
	public void readArray(InputStream in) {
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
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
	Under the brown land, mixing (framed by the brush, her daughter)
	Winter
	With a burnished throne
	Burned green and gold.
	Where the faint moonlight, the chapel, only
	Fishing in soda water
	Endeavors to folly and puts a fortnight dead mountain mouth (\n?) of empty rooms
	 */
}
