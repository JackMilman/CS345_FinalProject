package branding;

import java.awt.Window;

import javax.swing.JDialog;

/**
 * The parent class of all JDialogs in KitchIntel. This will always have the correct color scheme.
 * @author Josiah Leach
 *
 */
public abstract class KitchIntelJDialog extends JDialog
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new KitchIntelJDialog with the proper color scheme.
   * @param owner the owner of this JDialog
   * @param title the title of the JDialog
   */
  public KitchIntelJDialog(final Window owner, final String title)
  {
    super(owner, title);
    
    getContentPane().setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());
  }
  
  /**
   * Creates a new KitchIntelJDialog with the proper color scheme.
   * @param title The title of the JDialog
   */
  public KitchIntelJDialog(final String title)
  {
    this(null, title);
  }
}
