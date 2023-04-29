package branding;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;

import preferences.KitchIntelPreferenceReader;

/**
 * The super class for all JFrames in KitchIntel. This will always have the correct background
 * color.
 * 
 * @author Josiah Leach
 *
 */
public class KitchIntelJFrame extends JFrame
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static int fontSize;

  /**
   * Creates a new KitchIntelJFrame with the correct background color.
   * 
   * @param name
   *          the name of the KitchIntelJFrame
   */
  public KitchIntelJFrame(final String name)
  {
    super(name);

    getContentPane().setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());
    try
    {
      fontSize = Integer.parseInt(KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT));
      changeFont(this, fontSize);
    }
    catch (NumberFormatException | IOException e)
    {
      System.out.print("ERROR");
    }
    
  }

  /**
   * Creates a new KitchIntelJFrame with no name. This JFrame has the background color to match the
   * color scheme.
   */
  public KitchIntelJFrame()
  {
    super();

    getContentPane().setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());
  }
  
  public static void changeFont(Component component, int fontSize)
  {
      component.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
      if ( component instanceof Container )
      {
          for (Component child : ((Container) component).getComponents ())
          {
              changeFont(child, fontSize);
          }
      }
  }
}
