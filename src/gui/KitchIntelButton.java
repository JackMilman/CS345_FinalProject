package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A JButton with one of the logos from the Google Material Library
 * @author Josiah Leach
 * @version 03.29.2023
 */
public class KitchIntelButton extends JButton
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public static final String CALCULATE_IMAGE = "calculate.png";
  public static final String CLOSE_IMAGE = "close.png";
  public static final String NEW_IMAGE = "new.png";
  public static final String OPEN_IMAGE = "open.png";
  public static final String PRINT_IMAGE = "print.png";
  public static final String RESET_IMAGE = "reset.png";
  public static final String SAVE_IMAGE = "save.png";
  public static final String SAVE_AS_IMAGE = "save_as.png";
  
  private static final String PATH = "images/";

  
  public KitchIntelButton(String buttonImage)
  {
    super(new ImageIcon(PATH + buttonImage));
    setBorderPainted(false);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setOpaque(false);
    
    setPressedIcon(new ImageIcon(PATH + "white_" + buttonImage));
  }
}
