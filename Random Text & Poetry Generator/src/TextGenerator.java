import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An object of class TextGenerator uses the Markov array generated by
 * a TextParser object to generate randomized text.
 * 
 * @author ML, MW, ZB
 */
public class TextGenerator {
	
	private TextParser tp;
	private String currentWord;
	private String poem;
	
	/*
	 * Standard constructor, uses a TextParser for easy access to data.
	 */
	public TextGenerator(TextParser parse)
	{
		if (parse == null){
			System.out.println("Error! Text Parser is null!");
		}
		tp = parse;
		currentWord = "\n";
	}
	
	/*
	 * Generates a poem based on the Textparser's data 
	 * and user inputed number of lines
	 */
	public String generateText(int numLines)
	{
		System.out.println("Generating text...");
		int maxLineLength = (int)(tp.getAvgLineLength()*1.3);
		poem = "";
		int wordNum = 0;
		int line = 1;
		while (line <= numLines)
		{
			poem += getNextWord() + " ";
			wordNum++;
			if (currentWord.equals("\n") || wordNum >= maxLineLength)
			{
				if(wordNum>=maxLineLength)
					poem+="\n";
				else
					poem=poem.substring(0, poem.length()-1);
				line++;
				wordNum = 0;
			}
		}
		return poem;
	}
	
	/*
	 * Given the previous word, generates a random number and 
	 * uses it to choose from the possible words in the Markov array.
	 */
	public String getNextWord()
	{
		String nextWord = "";
		int currWord;
		try {
		currWord = tp.getInt(currentWord);
		}catch(NullPointerException e) {
			return null;
		}
		double random = Math.random();
		double[][] chanceArray = tp.getMarkovArray();
		for (int x = 0; x < chanceArray[currWord].length; x++){
			random -= chanceArray[currWord][x];
			if (random <= 0.0){
				nextWord = tp.getString(x);
				System.out.println("Current word is: "+currentWord+", next word is: "+nextWord);
				break;
			}
		}
		if (nextWord == null /*|| nextWord.equals("")*/) //If it equals "", then there is still a bug somewhere.
		{											 //This is my current fix to this problem. -Zachary
			nextWord = "\n";
		}
		currentWord = nextWord;
		return nextWord;
	}

	public void saveLines(int chosenStart, int chosenEnd)
	{
		String[] lines = poem.split("\n");
		String toSave = "";
		for (int x = chosenStart; x < chosenEnd; x++)
		{
			toSave += lines[x]+" @ ";
		}
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File("Saved_Lines")));
			bw.write(toSave);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	public void readAloud() {
		try {
			Runtime.getRuntime().exec("say " + poem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
