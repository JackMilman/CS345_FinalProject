package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A JButton with one of the logos from the Google Material Library.
 * 
 * @author Josiah Leach
 * @version 03.29.2023
 */
public class KitchIntelButton extends JButton
{

  public static final String CALCULATE_IMAGE = "calculate.png";
  public static final String CLOSE_IMAGE = "close.png";
  public static final String NEW_IMAGE = "new.png";
  public static final String OPEN_IMAGE = "open.png";
  public static final String PRINT_IMAGE = "print.png";
  public static final String RESET_IMAGE = "reset.png";
  public static final String SAVE_IMAGE = "save.png";
  public static final String SAVE_AS_IMAGE = "save_as.png";

  private static final long serialVersionUID = 1L;

  private static final String PATH = "images/";
  private static final String PRESSED_MODIFIER = "white_";

  /**
   * 
   * @param buttonImage
   */
  public KitchIntelButton(final String buttonImage)
  {
    super();
    setBorderPainted(false);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setOpaque(false);

    setIcon(new ImageIcon(getClass().getClassLoader().getResource(buttonImage)));
    
    setPressedIcon(new ImageIcon(
        getClass().getClassLoader().getResource(PRESSED_MODIFIER + buttonImage)));
    setDisabledIcon(new ImageIcon(
        getClass().getClassLoader().getResource(PRESSED_MODIFIER + buttonImage)));
  }
}
