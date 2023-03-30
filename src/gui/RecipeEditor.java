package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * 
 * @author shelseyvega
 *
 */
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
  public RecipeEditor(final Window owner)
  {
    super(owner, "KiLowBites Recipe Editor");
    setLayout(new BorderLayout(5, 5));

    
    Container mainEditors = new Container();
    mainEditors.setLayout(new BorderLayout());
    mainEditors.add(new UtensilEditor(), BorderLayout.NORTH);
    mainEditors.add(new IngredientEditor(), BorderLayout.CENTER);
    mainEditors.add(new StepEditor(), BorderLayout.SOUTH);
    
    getContentPane().add(mainEditors, BorderLayout.SOUTH);
        
    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    icons.add(new KitchIntelButton(KitchIntelButton.NEW_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.OPEN_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE));

    
    getContentPane().add(icons, BorderLayout.NORTH);
    
    Container nameAndServings = new Container();
    nameAndServings.setLayout(new FlowLayout(FlowLayout.LEFT));
    nameAndServings.add(new JLabel("Name: "));
    nameAndServings.add(new JTextField(defaultTextFieldWidth));
    nameAndServings.add(new JLabel("Serves: "));
    nameAndServings.add(new JTextField(defaultTextFieldWidth));
    
    JLabel lblNewLabel = new JLabel("Name:");
    nameAndServings.add(lblNewLabel);
    JTextField textField = new JTextField();
    textField.setColumns(10);
    nameAndServings.add(textField);
    //nameAndServings.add(new JButton());
    
    JLabel lblNewLabel1 = new JLabel("Serves:");
    nameAndServings.add(lblNewLabel1);
    JTextField textField1 = new JTextField();
    textField1.setColumns(10);
    nameAndServings.add(textField1);
   // nameAndServings.add(new JButton());
    
    getContentPane().add(nameAndServings, BorderLayout.CENTER);
    
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
