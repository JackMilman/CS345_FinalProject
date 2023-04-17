package branding;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;


/**
 * An enumeration of the colors for KitchIntel. The values of each color can be changed during
 * the creation of the jar file for branding purposes.
 * 
 * @author Josiah Leach
 *
 */
public enum KitchIntelColor
{
  MENUBAR_COLOR("Menubar", null), BACKGROUND_COLOR("Background", null);
  
  private Color color;
  
  private KitchIntelColor(final String source, final Color defaultColor)
  {
    int r = -1;
    int g = -1;
    int b = -1;
    
    Scanner scanner;
    try
    {
      scanner = new Scanner(KitchIntelColor.class.getClassLoader().getResource("color.txt").
          openStream());
      
      while(scanner.hasNextLine())
      {
        String line = scanner.nextLine();
        
        if(line.startsWith(source))
        {
          Scanner intScanner = new Scanner(line);
          intScanner.next();
          
          r = intScanner.nextInt();
          g = intScanner.nextInt();
          b = intScanner.nextInt();
          
          intScanner.close();
          
          break;
        }
      }
    }
    catch(IOException ioe)
    {}
    finally
    {
      if(r == -1 || g == -1 || b == -1)
      {
        this.color = defaultColor;
      }
      else
      {
        this.color = new Color(r,g,b);
      }
    }
  }
  
  /**
   * Gets the color for the type of element specified by the color scheme.
   * @return A color.
   */
  public Color getColor()
  {
    return this.color;
  }
}
