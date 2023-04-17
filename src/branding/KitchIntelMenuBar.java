package branding;

import javax.swing.JMenuBar;

/**
 * The MenuBar which should be used for KitchIntel. The color of this MenuBar will fit the required
 * color scheme.
 * @author Josiah Leach
 *
 */
public class KitchIntelMenuBar extends JMenuBar
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Creates a new KitchIntelMenuBar with the colors specified by the colors of this version of
   * the product.
   */
  public KitchIntelMenuBar() 
  {
    super();
    setBackground(KitchIntelColor.MENUBAR_COLOR.getColor());
  }

}
