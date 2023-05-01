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
        String actionCommand = parts[0].trim();
        String keyCombination = parts[1].trim();

        //Shortcut shortcut = new Shortcut(keyCombination, actionCommand);
       // shortcuts.add(shortcut);
      }
      line = reader.readLine();
    }
    reader.close();
    return shortcuts;
  }

}
