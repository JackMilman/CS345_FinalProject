package preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class KitchIntelPreferenceReader
{
  public static final String FONT = "FONT";
  public static final String DEFAULT = "DEFAULT";
  public static final String RECIPE = "RECIPE";
  public static final String MEAL = "MEAL";
  private static final File file = new File("preferences.txt");
  private static HashMap<String, String> preferences = new HashMap<>();
  
  public static  int findItem(String item) throws IOException {
    FileReader fr = (new FileReader(file));
    BufferedReader io = new BufferedReader(new FileReader(file));
    int location = 0;
    while(io.ready()) {
      String s = io.readLine();
      if (s.contains(item)) {
        String[] val = s.split(":");
        location += val[0].length() + 1;
        return location;
      }
      location += s.length();
    }
    return 0;
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
    
    RandomAccessFile writer = new RandomAccessFile(file, "rw");
    writer.seek(findItem(item));
    writer.writeChars(" " + amount + "");
    writer.close();
  }
  
//  public static void main(String[] args) {
//    try
//    {
//      System.out.println(KitchIntelFont.returnValue(KitchIntelFont.FONT));
//      KitchIntelFont.saveItem(FONT, "18");
//      System.out.println(KitchIntelFont.returnValue(KitchIntelFont.FONT));
//    }
//    catch (IOException e)
//    {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//  }

}
