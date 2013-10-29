import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Controller implements ActionListener {
	private TextParser parse;
	private TextGenerator generator;
	private PoemWindow window;
	private boolean textLoaded;
	private int lines;
	private String[] presets;
	public String userText;
	StringPromptScreen userTextPrompt;
	
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
		lines = 25;
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
					 window.setPoemText(generator.generateText(lines));
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
				String fileName = (String)(JOptionPane.showInputDialog(null, "Choose a preset:", "Preset Options", JOptionPane.INFORMATION_MESSAGE, null, presets, presets[0]));
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
				if (!textLoaded)
				{
					window.setPoemText("You must load and generate some text to" +
	" analyze before saving poetry. \n Preset Styles gives a list of " +
	"example text files for analysis, \n while User Text allows you to " +
	"enter or upload your own text files. ");
				}
				else
				{
					
				}
			}
			else if (buttonText.equals("Settings"))
			{
				
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
}
