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
	
	public Controller()
	{
		window = new PoemWindow(this);
		textLoaded = false;
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		Window sourceFrame = SwingUtilities.getWindowAncestor((JButton)(source));
		String buttonText = ((JButton)(source)).getText();
		if (sourceFrame instanceof PoemWindow)
		{
			if (buttonText.equals("Refresh Poem"))
			{
				 
			}
			else if (buttonText.equals("Read Aloud"))
			{
				
			}
			else if (buttonText.equals("Preset Styles"))
			{
				
			}
			else if (buttonText.equals("User Text"))
			{
				
			}
			else if (buttonText.equals("Save Line"))
			{
				
			}
			else if (buttonText.equals("Settings"))
			{
				
			}
		}
	}
}
