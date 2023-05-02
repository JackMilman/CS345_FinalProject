package preferences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reads the saved preferences.
 * 
 * @author KitchIntel
 * @version
 */
public class KitchIntelPreferenceReader
{
  public static final String FONT = "FONT";
  public static final String DEFAULT = "DEFAULT";
  public static final String RECIPE = "RECIPE";
  public static final String MEAL = "MEAL";
  private static final File FILE = new File("preferences.txt");
  private static HashMap<String, String> preferences = loadPreferences();
  
  /**
   * Load preferences and save in a HashMap.
   * 
   * @return HashMap
   */
  public static HashMap<String, String> loadPreferences() 
  {
    try
    {
      HashMap<String, String> load = new HashMap<>();
      load.put(DEFAULT, returnValue(DEFAULT));
      load.put(FONT, returnValue(FONT));
      load.put(RECIPE, returnValue(RECIPE));
      load.put(MEAL, returnValue(MEAL));
      return load;
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * Return value from preferences file.
   * 
   * @param item 
   * @return value
   * @throws IOException
   */
  public static String returnValue(final String item) throws IOException
  {
//    FileReader fr = (new FileReader(FILE));
    BufferedReader io = new BufferedReader(new FileReader(FILE));
    while(io.ready()) 
    {
      String s = io.readLine();
      if (s.contains(item)) 
      {
        String[] val = s.split(":");
        io.close();
        return val[1];
      }
    }
    io.close();
    return null;
  }
  
  /**
   * Save an item in the preferences file.
   * 
   * @param item
   * @param amount
   * @throws IOException
   */
  public static void saveItem(final String item, final String amount) throws IOException
  {
    preferences.put(item, amount);

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preferences.txt")));
    out.flush();
    @SuppressWarnings("unchecked")
    ArrayList<String> keys = new ArrayList<>(preferences.keySet());
    for(int i = 0; i < keys.size(); i++)
    {
      String key = keys.get(i);
      String line = String.format("%s:%s\n", key, preferences.get(key));
      out.append(line);

    }
    out.close();
  }
  
  public static void main(String[] args)
  {
    try
    {
      System.out.println(KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT));
      KitchIntelPreferenceReader.saveItem(FONT, "12");
      System.out.println(KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
