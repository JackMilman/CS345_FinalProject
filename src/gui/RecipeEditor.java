package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class RecipeEditor extends JDialog
{

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
    getContentPane().setLayout(new BorderLayout());
    
    Container mainEditors = new Container();
    mainEditors.setLayout(new BorderLayout());
    mainEditors.add(new UtensilEditor(), BorderLayout.NORTH);
    mainEditors.add(new IngredientEditor(), BorderLayout.CENTER);
    mainEditors.add(new StepEditor(), BorderLayout.SOUTH);
    
    getContentPane().add(mainEditors, BorderLayout.SOUTH);
        
    Container icons = new Container();
    icons.setLayout(new FlowLayout());
    icons.add(new KitchIntelButton(KitchIntelButton.NEW_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.OPEN_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE));

    
    getContentPane().add(icons, BorderLayout.NORTH);
    
    Container nameAndServings = new Container();
    nameAndServings.setLayout(new FlowLayout());
    
    JLabel lblNewLabel = new JLabel("Name:");
    nameAndServings.add(lblNewLabel);
    JTextField textField = new JTextField();
    textField.setColumns(10);
    nameAndServings.add(textField);
    //nameAndServings.add(new JButton());
    
    JLabel lblNewLabel_1 = new JLabel("Serves:");
    nameAndServings.add(lblNewLabel_1);
    JTextField textField_1 = new JTextField();
    textField_1.setColumns(10);
    nameAndServings.add(textField_1);
   // nameAndServings.add(new JButton());
    
    getContentPane().add(nameAndServings, BorderLayout.CENTER);
    
    setVisible(true);
    setResizable(true);
    pack();
  }
}
