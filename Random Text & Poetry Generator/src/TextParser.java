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
	private double averageLineLength;

	/*
	 * The first-order Markov array of token frequencies
	 * (current token ID is row, next token ID is col)
	 */
	private double[][] probabilities;
	private int[][] occurrences;

	public TextParser(InputStream stream, boolean isRawText) {
		//Creates a new TextParser that will read stream.
		numTokens = 0;
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
		
		/*
		 * Adding \n
		 */
		intAssignments.put("\n", numTokens);
		stringAssignments.put(numTokens, "\n");
		numTokens++;
		
		int totalWords = 0;
		int numLines = 0;
		
		while (line != null) {
			numLines++;
			
			/*
			 * Adding all the words to the HashMaps
			 */
			for (String s : line.split("[ ]+")) {
				totalWords ++;
				if (!intAssignments.containsKey(s)) {
					intAssignments.put(s, numTokens);
					stringAssignments.put(numTokens, s);
					numTokens++;
				}
			}
			
		}
		averageLineLength = totalWords/numLines;
	}

	/*
	 * Parses raw text
	 */
	public void readRawText(InputStream in) {
		analyzeUniqueStrings(in);
		occurrences = new int[intAssignments.size()][intAssignments.size()];
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (line != null) {
			/**
			 * stuff goes here.
			 */
			String[] tokens = line.split("[ ]+");
			
			occurrences[getInt("\n")][getInt(tokens[0])]++;
			for (int i = 1; i < tokens.length-1; i++) {
				occurrences[getInt(tokens[i])][getInt(tokens[i+1])]++;
			}
			
			
			
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		calculateProbabilities(occurrences);
	}

	/*
	 * converts an int[][] of numbers of occurrences to probabilities
	 */
	private void calculateProbabilities(int[][] occs) {
		probabilities = new double[occs.length][occs[0].length];
		for (int row = 0; row < occs.length; row++)
		{
			double total = 0.0;
			for (int col = 0; col < occs[0].length; col++)
			{
				total += occs[row][col];
			}
			for (int col = 0; col < occs[0].length; col++)
			{
				probabilities[row][col] = (double)(occs[row][col])/total;
			}
		}
	}



	/*
	 * Reads a saved Markov array from a text file
	 */
	public void readArray(InputStream in) {
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		try {
			String words=reader.readLine();
			StringTokenizer wordsTokenizer=new StringTokenizer(words);
			intAssignments=new HashMap<String, Integer>();
			stringAssignments=new HashMap<Integer, String>();
			for(int i=0; wordsTokenizer.hasMoreTokens(); i++) {
				String word=wordsTokenizer.nextToken();
				if(word.equals("\\n"))
					word.equals("\n");
				intAssignments.put(word, i);
				stringAssignments.put(i, word);
			}
			probabilities=new double[intAssignments.size()][intAssignments.size()];
			String line=reader.readLine();
			for(int r=0; line!=null; r++) {
				StringTokenizer probabilityTokenizer=new StringTokenizer(line);
				for(int c=0; probabilityTokenizer.hasMoreTokens(); c++)
					probabilities[r][c]=Double.parseDouble(probabilityTokenizer.nextToken());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Writes the Markov array to a text file
	 */
	public void writeArray(File file) {
		try {
			if(file.exists()) {
				TextParser merger=new TextParser(new FileInputStream(file), false);
				merge(merger);
			}
			else
				file.createNewFile();
			BufferedWriter out=new BufferedWriter(new FileWriter(file));
			for(int i=0; i<stringAssignments.size(); i++) {
				String word=stringAssignments.get(i);
				if(word.equals("\n"))
					word="\\n";
				out.write(word+" ");
			}
			out.write("\n");
			for(int r=0; r<occurrences.length; r++) {
				for(int c=0; c<occurrences[r].length; c++)
					out.write(occurrences[r][c]+" ");
				out.write("\n");
			}
			//out.write(averageLineLength+"");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void merge(TextParser merger) {
		merge(merger.getOccurrencesArray(), merger.getStringAssignments());
	}
	
	public void merge(int[][] merger, HashMap<Integer, String> words) {
		for(Integer i:words.keySet()) {
			String word=words.get(i);
			if(!intAssignments.keySet().contains(word)) {
				intAssignments.put(word, intAssignments.size());
				stringAssignments.put(stringAssignments.size(), word);
			}
		}
		int[][] newOccurrences=new int[intAssignments.size()][intAssignments.size()];
		for(int r=0; r<occurrences.length; r++)
			for(int c=0; c<occurrences[r].length; c++)
				newOccurrences[r][c]=occurrences[r][c];
		for(Integer originalRow:words.keySet()) {
			int newRow=intAssignments.get(words.get(originalRow));
			for(Integer originalCol:words.keySet()) {
				int newCol=intAssignments.get(words.get(originalCol));
				newOccurrences[newRow][newCol]+=merger[originalRow.intValue()][originalCol.intValue()];
			}
		}
		occurrences=newOccurrences;
	}

	//returns the ID for a token
	public int getInt(String s) {
		return intAssignments.get(s);
	}

	//returns the token with ID x
	public String getString(int x) {
		return stringAssignments.get(x);
	}

	//returns the Markov array
	public double[][] getMarkovArray() {
		return probabilities;
	}
	

	public double getAvgLineLength() {
		return averageLineLength;
	}
	
	public int[][] getOccurrencesArray() {
		return occurrences;
	}
	
	public HashMap<String, Integer> getIntAssignment() {
		return intAssignments;
	}
	
	public HashMap<Integer, String> getStringAssignments() {
		return stringAssignments;
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
