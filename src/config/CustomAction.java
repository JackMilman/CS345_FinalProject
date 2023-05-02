package config;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class CustomAction extends AbstractAction
{
  private static final long serialVersionUID = 1L;
  private String name;
  private KeyStroke keyStroke;
  private JFrame frame;
  private KeyStroke acceleratorKey;


  public CustomAction(String name, KeyStroke keyStroke)
  {
    this.name = name;
    this.keyStroke = keyStroke;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // Add the code that you want to execute when the button is clicked
  }

  /**
   * Set the KeyStroke of the shortcut
   * 
   * @param keyStroke
   */
  public void setAcceleratorKey(KeyStroke keyStroke)
  {
    this.keyStroke = keyStroke;
  }

  /**
   * get the KeyStroke of the shortcut
   * 
   * @return keyStroke
   */
  public KeyStroke getAcceleratorKey()
  {
    return keyStroke;
  }

  @Override
  public String toString()
  {
    return name;
  }

  /**
   * 
   */
  public void addToMenu()
  {
    frame.getRootPane().getActionMap().put(name, this);
    frame.getRootPane().getInputMap().put(keyStroke, name);
  }
}
