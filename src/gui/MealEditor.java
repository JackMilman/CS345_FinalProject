package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

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
    super(owner, "KiLowBites Recipe Editor");
    setLayout(new BorderLayout());
    
    Container mainEditors = new Container();
    mainEditors.setLayout(new BorderLayout());
    mainEditors.add(new UtensilEditor(), BorderLayout.NORTH);
    mainEditors.add(new IngredientEditor(), BorderLayout.CENTER);
    mainEditors.add(new StepEditor(), BorderLayout.SOUTH);
    
    add(mainEditors, BorderLayout.SOUTH);
        
    Container icons = new Container();
    icons.setLayout(new FlowLayout());
    icons.add(new KitchIntelButton(KitchIntelButton.NEW_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.OPEN_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE));

    
    add(icons, BorderLayout.NORTH);
    
    Container nameAndServings = new Container();
    nameAndServings.setLayout(new FlowLayout());
    nameAndServings.add(new JTextField("Name: "));
    nameAndServings.add(new JButton());
    nameAndServings.add(new JTextField("Serves: "));
    nameAndServings.add(new JButton());
    
    add(nameAndServings, BorderLayout.CENTER);
    
    setVisible(true);
    setResizable(true);
    pack();
  }
  
  public static void main(String[] args)
  {
    Window main = new MainWindow();
    
    new KitchIntelButton(KitchIntelButton.NEW_IMAGE);

    
    new MealEditor(main);
  }

}
