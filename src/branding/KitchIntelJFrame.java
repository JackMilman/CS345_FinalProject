package branding;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;

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
    
  }

  /**
   * Creates a new KitchIntelJFrame with no name. This JFrame has the background color to match the
   * color scheme.
   */
  public KitchIntelJFrame()
  {
    super();

    getContentPane().setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());
    changeFont(this, );
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
