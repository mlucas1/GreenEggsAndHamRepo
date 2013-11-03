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
	public int numUniqueTokens;
	public int numWords;
	public int numLines;
	private int averageLineLength;
	
	private InputStream rawText;

	/*
	 * The first-order Markov array of token frequencies
	 * (current token ID is row, next token ID is col)
	 */
	private short[][] probabilities;
	private short[][] occurrences;

	public TextParser(InputStream streamOne, InputStream streamTwo, InputStream streamThree, boolean isRawText) {
		//Creates a new TextParser that will read stream.
		rawText = streamThree;
		
		numUniqueTokens = 0;
		intAssignments=new HashMap<String, Integer>();
		stringAssignments=new HashMap<Integer, String>();
		if(isRawText)
			readRawText(streamOne, streamTwo);
		else
			readArray(streamOne);
	}

	/*
	 * initializes the private HashMaps with string-int ID pairings
	 */
	private void analyzeUniqueStrings(InputStream in) {
		//System.out.println("Analyzing unique strings...");
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
		intAssignments.put("\n", numUniqueTokens);
		stringAssignments.put(numUniqueTokens, "\n");
		numUniqueTokens++;
		
		int totalWords = 0;
		numLines = 0;
		
		while (line != null) {
			
			numLines++;
			//System.out.println("Analyzing line number "+numLines);
			
			/*
			 * Adding all the words to the HashMaps
			 */
			for (String s : line.split("[ ]+")) {
				totalWords ++;
				if (!intAssignments.containsKey(s)&&!s.equals("")) {
					intAssignments.put(s, numUniqueTokens);
					stringAssignments.put(numUniqueTokens, s);
					numUniqueTokens++;
				}
			}
			try {
				line=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		numWords = totalWords;
		averageLineLength = totalWords/numLines;
		System.out.println("Total Words: " + numWords);
		System.out.println("Unique Words: " + numUniqueTokens);
	}

	/*
	 * Parses raw text
	 */
	public void readRawText(InputStream inOne, InputStream inTwo) {
		//System.out.println("Reading raw text...");
		analyzeUniqueStrings(inOne);
		occurrences = new short[intAssignments.size()][intAssignments.size()];
		BufferedReader reader = new BufferedReader(new InputStreamReader(inTwo));
		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (line != null) {
			boolean isWhitespace=true;
			for(int i=0; i<line.length(); i++) {
				if(!Character.isWhitespace(line.charAt(i))) {
					isWhitespace=false;
				}
			}
			if(line.equals("")||isWhitespace) {
				try {
					line=reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				continue;
			}
			line=line.trim();
			String[] tokens = line.split("[ ]+");
			//System.out.print("[");
			for(int i=0; i<tokens.length; i++)
				//System.out.print(tokens[i]+", ");
			//System.out.println("]");
			occurrences[getInt("\n")][getInt(tokens[0])]++;
			occurrences[getInt(tokens[tokens.length-1])][getInt("\n")]++;
			for (int i = 0; i < tokens.length-1; i++) {
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
	private void calculateProbabilities(short[][] occs) {
		//System.out.println(occs.length);
		probabilities = new short[occs.length][occs[0].length];
		for (int row = 0; row < occs.length; row++)
		{
			int total = 0;
			for (int col = 0; col < occs[0].length; col++)
			{
				total += occs[row][col];
			}
			for (int col = 0; col < occs[0].length; col++)
			{
				if(total!=0) {
					//System.out.println("Total: " + total);
					//System.out.println("occs[" + row + "][" + col + "] == " + occs[row][col]);
					
					probabilities[row][col] = (short) Math.ceil((Short.MAX_VALUE * ((double) occs[row][col]/total)));
					//System.out.println("probabilities[" + row + "][" + col + "] == " + probabilities[row][col]);
				}
				else {
					/*for(int r=0; r<occs.length; r++) {
						//System.out.print("[");
						for(int c=0; c<occs[r].length; c++)
							//System.out.print(occs[r][c]+", ");
						//System.out.println("]");
					}*/
					probabilities[row][col]=0;
				}
				if(total!=0&&occs[row][col]!=0&&probabilities[row][col]==0)
					System.out.println("ksajdhflksajhdf");
				//probabilities[row][col] = (double)(occs[row][col])/total;
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
					word="\n";
				intAssignments.put(word, i);
				stringAssignments.put(i, word);
			}
			String line=reader.readLine();
			StringTokenizer occurrencesTokenizer=new StringTokenizer(line);
			occurrences=new short[occurrencesTokenizer.countTokens()][occurrencesTokenizer.countTokens()];
			for(int r=0; r<occurrences.length; r++) {
				occurrencesTokenizer=new StringTokenizer(line);
				for(int c=0; c<occurrences[r].length; c++)
					occurrences[r][c]=Short.parseShort(occurrencesTokenizer.nextToken());
				line=reader.readLine();
			}
			//line=reader.readLine();
			averageLineLength= (int) Double.parseDouble(reader.readLine());
			calculateProbabilities(occurrences);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Writes the Markov array to a text file
	 */
	public void writeArray(File file) {
		try {
			/*if(file.length()!=0) {
				TextParser merger=new TextParser(new FileInputStream(file), new FileInputStream(file), new FileInputStream(file), false);
				merge(merger);
			}
			else if(!file.exists())*/
			//	file.createNewFile();
			BufferedWriter out=new BufferedWriter(new FileWriter(file));
			for(int i=0; i<stringAssignments.size(); i++) {
				String word=stringAssignments.get(i);
				if(word.equals("\n"))
					word="\\n";
				out.write(word+" ");
				System.out.println("Writing " + word + " ");
			}
			out.write("\n");
			for(int r=0; r<occurrences.length; r++) {
				for(int c=0; c<occurrences[r].length; c++) {
					out.write(occurrences[r][c]+" ");
					System.out.println("Writing " + occurrences[r][c] + " ");
				}
				out.write("\n");
			}
			out.write("\n"+averageLineLength);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void merge(TextParser merger) {
		merge(merger.getOccurrencesArray(), merger.getStringAssignments());
	}
	
	public void merge(short[][] merger, HashMap<Integer, String> words) {
		int newLineInt=-1;
		for(Integer i:words.keySet()) {
			String word=words.get(i);
			if(word.equals("\\n")) {
				word="\n";
				newLineInt=i.intValue();
			}
			if(!intAssignments.keySet().contains(word)) {
				intAssignments.put(word, intAssignments.size());
				stringAssignments.put(stringAssignments.size(), word);
			}
		}
		if(newLineInt!=-1)
			words.put(new Integer(newLineInt), "\n");
		short[][] newOccurrences=new short[intAssignments.size()][intAssignments.size()];
		for(int r=0; r<occurrences.length; r++)
			for(int c=0; c<occurrences[r].length; c++)
				newOccurrences[r][c]=occurrences[r][c];
		//System.out.println("Words:");
		for(Integer originalRow:words.keySet()) {
			//System.out.println(words.get(originalRow));
			int newRow=intAssignments.get(words.get(originalRow));
			for(Integer originalCol:words.keySet()) {
				int newCol=intAssignments.get(words.get(originalCol));
				try {
				newOccurrences[newRow][newCol]+=merger[originalRow.intValue()][originalCol.intValue()];
				}catch(Exception e){
					//System.out.println("Out of bounds: newRow="+newRow+", newCol="+newCol+", oRow="+originalRow+", oCol="+originalCol);
				}
				if(newRow!=originalRow) {
					//System.out.println("Different rows: "+originalRow+" is now "+newRow);
					return;
				}
				if(newCol!=originalCol) {
					//System.out.println("Different cols: "+originalCol+" is now "+newCol);
					return;
				}
			}
		}
		occurrences=newOccurrences;
	}

	//returns the ID for a token
	public int getInt(String s) {
		try {
		return intAssignments.get(s);
		} catch(NullPointerException e) {
			System.out.println("NullPointerException in getInt(String s). s is: \""+s+"\"");
			e.printStackTrace();
			return -1;
		}
	}

	//returns the token with ID x
	public String getString(int x) {
		return stringAssignments.get(x);
	}

	//returns the Markov array
	public short[][] getMarkovArray() {
		return probabilities;
	}
	

	public double getAvgLineLength() {
		return averageLineLength;
	}
	
	public int getNumWords() {
		return numWords;
	}
	
	public int getNumLines() {
		return numLines;
	}
	
	public short[][] getOccurrencesArray() {
		return occurrences;
	}
	
	public HashMap<String, Integer> getIntAssignment() {
		return intAssignments;
	}
	
	public HashMap<Integer, String> getStringAssignments() {
		return stringAssignments;
	}
	
	public InputStream getRawTextStream() {
		return rawText;
	}


	/*
	Under the brown land, framed by the brush, her daughter ("mixing" originally)
	Winter
	With a burnished throne
	Burned green and gold.
	Where the faint moonlight, the chapel, only
	Fishing in soda water
	Endeavors to folly //(stop here lol) and puts a fortnight dead mountain mouth (\n?) of empty rooms
	 */
}

