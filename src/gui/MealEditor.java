package gui;

import java.awt.Window;

import javax.swing.JDialog;

/**
 * The class for the meal editor window. All that another class needs to do is call
 * the constructor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 *
 */
public class MealEditor extends JDialog
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Creates a new MealEditor.
   * @param owner The JFrame which created this MealEditor. This should probably be
   * the Main Window.
   */
  public MealEditor(Window owner)
  {
    super(owner, "KiLowBites Meal Editor");

  }
  
  public static void main(String[] args)
  {
    Window main = new MainWindow();

    new MealEditor(main);
  }

}
