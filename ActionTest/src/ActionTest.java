import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionTest {
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						ActionFrame frame = new ActionFrame();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
					}
				});
	}
}

class ActionFrame extends JFrame
{
	public ActionFrame()
	{
		setTitle("ActionTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		buttonPanel = new JPanel();
		
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("yellow-ball.gif"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("blue-ball.gif"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("red-ball.gif"), Color.RED);
		
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));
		add(buttonPanel);
		
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "red");
		
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("yellow", yellowAction);
		amap.put("blue", blueAction);
		amap.put("red", redAction);
	}
	
	public class ColorAction extends AbstractAction
	{
		public ColorAction(String name, Icon icon, Color c)
		{
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
			putValue("color",c);
		}
		public void actionPerformed(ActionEvent event)
		{
			Color c = (Color)getValue("color");
			buttonPanel.setBackground(c);
		}
	}
	
	private JPanel buttonPanel;
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
}
