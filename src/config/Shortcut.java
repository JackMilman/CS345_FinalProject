package config;

import javax.swing.KeyStroke;

public class Shortcut
{
  private KeyStroke keyStroke;
  private String actionCommand;

  public Shortcut(String keyCombination, String actionCommand)
  {
    this.keyStroke = KeyStroke.getKeyStroke(keyCombination);
    this.actionCommand = actionCommand;
  }

  public KeyStroke getKeyStroke()
  {
    return keyStroke;
  }

  public String getActionCommand()
  {
    return actionCommand;
  }
}
