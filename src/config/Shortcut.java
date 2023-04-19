package config;

public class Shortcut
{
  private String keyCombination;
  private String command;

  public Shortcut(String keyCombination, String command)
  {
    this.keyCombination = keyCombination;
    this.command = command;
  }

  public String getKeyCombination()
  {
    return keyCombination;
  }

  public String getCommand()
  {
    return command;
  }
}
