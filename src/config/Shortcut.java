package config;

import java.util.HashMap;
import java.util.Map;

import javax.swing.KeyStroke;

public class Shortcut
{
  private KeyStroke keyStroke;
  private String actionCommand;
  private Map<String, KeyStroke> shortcutMap;


  public Shortcut(String keyCombination, String actionCommand)
  {
    this.shortcutMap = new HashMap<>();
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
