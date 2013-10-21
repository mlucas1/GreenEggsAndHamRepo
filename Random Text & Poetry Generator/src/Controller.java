import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Controller implements ActionListener {
	private TextParser parse;
	private TextGenerator generator;
	private PoemWindow window;
	private boolean textLoaded;
	private int lines;
	
	public Controller()
	{
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
				
			}
			else if (buttonText.equals("User Text"))
			{
				
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
	}
}
