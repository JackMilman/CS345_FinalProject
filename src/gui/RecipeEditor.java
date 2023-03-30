package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RecipeEditor extends JDialog
{
  static final int defaultTextFieldWidth = 15;

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Creates a new RecipeEditor.
   * @param owner The JFrame which created this RecipeEditor. This should probably be
   * the Main Window.
   */
  public RecipeEditor(Window owner)
  {
    super(owner, "KiLowBites Recipe Editor");
    setLayout(new BorderLayout(5, 5));
    
    Container mainEditors = new Container();
    mainEditors.setLayout(new BorderLayout());
    mainEditors.add(new UtensilEditor(), BorderLayout.NORTH);
    mainEditors.add(new IngredientEditor(), BorderLayout.CENTER);
    mainEditors.add(new StepEditor(), BorderLayout.SOUTH);
    
    add(mainEditors, BorderLayout.SOUTH);
        
    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    icons.add(new KitchIntelButton(KitchIntelButton.NEW_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.OPEN_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE));

    
    add(icons, BorderLayout.NORTH);
    
    Container nameAndServings = new Container();
    nameAndServings.setLayout(new FlowLayout(FlowLayout.LEFT));
    nameAndServings.add(new JLabel("Name: "));
    nameAndServings.add(new JTextField(defaultTextFieldWidth));
    nameAndServings.add(new JLabel("Serves: "));
    nameAndServings.add(new JTextField(defaultTextFieldWidth));
    
    add(nameAndServings, BorderLayout.CENTER);
    
    setVisible(true);
    setResizable(true);
    pack();
  }
  
  public static void main(String[] args)
  {
    Window main = new MainWindow();
    
    new RecipeEditor(main);
  }
}
