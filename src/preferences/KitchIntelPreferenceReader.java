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

public class KitchIntelPreferenceReader
{
  public static final String FONT = "FONT";
  public static final String DEFAULT = "DEFAULT";
  public static final String RECIPE = "RECIPE";
  public static final String MEAL = "MEAL";
  private static final File file = new File("preferences.txt");
  private static HashMap<String, String> preferences = loadPreferences();
  
  public static HashMap<String, String> loadPreferences() {
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
  public static String returnValue(String item) throws IOException {
    FileReader fr = (new FileReader(file));
    BufferedReader io = new BufferedReader(new FileReader(file));
    while(io.ready()) {
      String s = io.readLine();
      if (s.contains(item)) {
        String[] val = s.split(":");
        return val[1];
      }
    }
    return null;
  }
  
  public static void saveItem(String item, String amount) throws IOException {
    preferences.put(item, amount);

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preferences.txt")));
    out.flush();
    @SuppressWarnings("unchecked")
    ArrayList<String> keys = new ArrayList<>(preferences.keySet());
    for(int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String line = String.format("%s:%s\n", key, preferences.get(key));
      out.append(line);

    }
    out.close();
  }
  
  public static void main(String[] args) {
    try
    {
      System.out.println(KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT));
      KitchIntelPreferenceReader.saveItem(FONT, "12");
      System.out.println(KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT));
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
