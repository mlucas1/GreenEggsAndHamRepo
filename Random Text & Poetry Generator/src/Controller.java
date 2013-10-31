import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Controller implements ActionListener {
	private TextParser parse;
	private TextGenerator generator;
	private PoemWindow window;
	private boolean textLoaded;
	private String[] presets;
	public String userText;
	StringPromptScreen userTextPrompt;
	IntPromptScreen saveLinePrompt;
	
	public static void main(String[]args)
	{ 
		Controller c = new Controller();
	} 
	
	public Controller()
	{
		presets=new File("arrays").list();
		for(int i=0; i<presets.length; i++)
			presets[i]=presets[i].substring(0, presets[i].indexOf("."));
		window = new PoemWindow(this);
		textLoaded = false;
	}
	
	public void loadText()
	{
		//TODO: get presets...
		textLoaded = true;
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		Window sourceFrame = SwingUtilities.getWindowAncestor((JButton)(source));
		String buttonText = ((JButton)(source)).getText();
		if (sourceFrame instanceof PoemWindow)
		{
			if (buttonText.equals("Refresh Poem"))
			{
				 if (!textLoaded)
				 {
					 window.setPoemText("You must load some text to " +
	"analyze before generating poetry. \n Preset Styles gives a list of " +
	"example text files for analysis, \n while User Text allows you to " +
	"enter or upload your own text files. ");
				 }
				 else
				 {
					 window.setPoemText(generator.generateText());
				 }
			}
			else if (buttonText.equals("Read Aloud"))
			{
				if (!textLoaded)
				{
					window.setPoemText("You must load and generate some text to" +
	" analyze before generating poetry. \n Preset Styles gives a list of " +
	"example text files for analysis, \n while User Text allows you to " +
	"enter or upload your own text files. ");
				}
				else
				{
					generator.readAloud(window.getPoemText());
				}
			}
			else if (buttonText.equals("Preset Styles"))
			{
				String fileName = (String)(JOptionPane.showInputDialog(null, "Choose a preset:", "Preset Options", JOptionPane.INFORMATION_MESSAGE, null, presets, presets[0]))+".txt";
				try {
					parse = new TextParser(new BufferedInputStream(new FileInputStream(fileName)), new BufferedInputStream(new FileInputStream(fileName)), new BufferedInputStream(new FileInputStream(fileName)), true);
				} catch (FileNotFoundException e) {
					window.setPoemText("Cannot find file. ");
				}
				generator = new TextGenerator(parse);
				textLoaded = true;
			}
			else if (buttonText.equals("User Text"))
			{
				userTextPrompt = new StringPromptScreen(this);
			}
			else if (buttonText.equals("Save Line"))
			{
				if (!textLoaded || window.getPoemText().length() < 1)
				{
					window.setPoemText("You must load and generate some text to" +
	" analyze before saving poetry. \n Preset Styles gives a list of " +
	"example text files for analysis, \n while User Text allows you to " +
	"enter or upload your own text files. ");
				}
				else
				{
					saveLinePrompt = new IntPromptScreen(this); 
				}
			}
			else if (buttonText.equals("Guessing Game")) {
				//TODO
			}
			else if (buttonText.equals("Hall of Fame"))
			{
				window.setPoemText(generator.hallOfFame());
			}
			else if (buttonText.equals("Settings"))
			{
				new SettingsWindow(this);
			}
		}
		else if (sourceFrame instanceof StringPromptScreen)
		{
			if (buttonText.equals("OK"))
			{
				parse = new TextParser(new BufferedInputStream(new ByteArrayInputStream(userText.getBytes())), new BufferedInputStream(new ByteArrayInputStream(userText.getBytes())), new BufferedInputStream(new ByteArrayInputStream(userText.getBytes())), true);
				generator = new TextGenerator(parse); 
				textLoaded = true;
			}
		}
	}
	
	public TextGenerator getGenerator()
	{
		return generator;
	}
	
	public void setUserText(String s)
	{
		userText = s;
	}

	public void setNumLines(int num) {
		generator.numLines = num;
	}
	
	public int getNumLines()
	{
		return generator.numLines;
	}

	public void setMaxLineLength(int num) {
		if (num == -1)
		{
			generator.maxLineLength = 20;
		}
		else
		{
			generator.maxLineLength = num;
		}
	}
	
	public int getMaxLinelength()
	{
		return generator.maxLineLength;
	}
	
	public void setMinLineLength(int num)
	{
		if (num == -1)
		{
			generator.minLineLength = 0;
		}
		else if (num <= generator.maxLineLength)
		{
			generator.minLineLength = num;
		}
	}
	
	public int getMinLineLength()
	{
		return generator.minLineLength;
	}
	
	public PoemWindow getWindow()
	{
		return window;
	}
	
	//TODO: continue adding these methods... 
}
/* 	Drip drop 
	And if you feel free. 
	There is the key, each in his nails he'll 
	dig it off, she cried, and dusty trees.
*/ 
