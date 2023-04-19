package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import config.Shortcut;

public class ShortcutsParser
{
  public List<Shortcut> parse(String filePath) throws IOException
  {
    List<Shortcut> shortcuts = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line = reader.readLine();
    while (line != null)
    {
      String[] parts = line.split("=");
      if (parts.length == 2)
      {
        String keyCombination = parts[0];
        String command = parts[1];
        Shortcut shortcut = new Shortcut(keyCombination, command);
        shortcuts.add(shortcut);
      }
      line = reader.readLine();
    }
    reader.close();
    return shortcuts;
  }
}
