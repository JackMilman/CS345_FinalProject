package branding;

import java.io.IOException;
import java.util.Scanner;

/**
 * A class for referencing the logo of the program.
 * 
 * @author Josiah Leach, KitchIntel
 * @version
 */
public class Logo
{
  private static String PATH = null;

  /**
   * Gets the path of the logo image.
   * 
   * @return the path of the Logo image as a String
   */
  public static String path()
  {
    if (PATH == null)
    {
      try
      {
        Scanner scanner = new Scanner(
            Logo.class.getClassLoader().getResource("logo.txt").openStream());
        PATH = scanner.nextLine();
        scanner.close();
      }
      catch (IOException e)
      {
        PATH = "";
      }
    }
    return PATH;
  }
}
