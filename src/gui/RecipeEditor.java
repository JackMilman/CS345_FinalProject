package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import recipes.Ingredient;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

/**
 * 
 * @author shelseyvega, Josiah Leach, KitchIntel
 *
 */
public class RecipeEditor extends JDialog
{
  static final int DEFAULT_TEXT_FIELD_WIDTH = 10;
  
  static final String INGREDIENT_ADD_ACTION_COMMAND = "ingr_add_act";
  static final String UTENSIL_ADD_ACTION_COMMAND = "uten_add_act";
  static final String INGREDIENT_DELETE_ACTION_COMMAND = "ingr_del_act";
  static final String UTENSIL_DELETE_ACTION_COMMAND = "uten_del_act";
  
  private static final String NEW_BUTTON_ACTION_COMMAND = "ren";
  private static final String OPEN_BUTTON_ACTION_COMMAND = "reo";
  private static final String SAVE_BUTTON_ACTION_COMMAND = "res";
  private static final String SAVE_AS_BUTTON_ACTION_COMMAND = "rea";
  private static final String CLOSE_BUTTON_ACTION_COMMAND = "rec";
  


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private final Window owner;
  
  private UtensilEditor utensilEditor;
  private IngredientEditor ingredientEditor;
  private StepEditor stepEditor;
  
  /**
   * Creates a new RecipeEditor.
   * @param owner The JFrame which created this RecipeEditor. This should probably be
   * the Main Window.
   */
  public RecipeEditor(final Window owner)
  {
    super(owner, "KiLowBites Recipe Editor");
    setLayout(new BorderLayout(5, 5));
    
    this.owner = owner;
    
    ActionListener listener = new RecipeEditorListener(this);
    
    JButton newButton = new KitchIntelButton(KitchIntelButton.NEW_IMAGE);
    JButton openButton = new KitchIntelButton(KitchIntelButton.OPEN_IMAGE);
    JButton saveButton = new KitchIntelButton(KitchIntelButton.SAVE_IMAGE);
    JButton saveAsButton = new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE);
    JButton closeButton = new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE);
    
    newButton.setActionCommand(NEW_BUTTON_ACTION_COMMAND);
    openButton.setActionCommand(OPEN_BUTTON_ACTION_COMMAND);
    saveButton.setActionCommand(SAVE_BUTTON_ACTION_COMMAND);
    saveAsButton.setActionCommand(SAVE_AS_BUTTON_ACTION_COMMAND);
    closeButton.setActionCommand(CLOSE_BUTTON_ACTION_COMMAND);
    
    newButton.addActionListener(listener);
    openButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    saveAsButton.addActionListener(listener);
    closeButton.addActionListener(listener);

    utensilEditor = new UtensilEditor();
    ingredientEditor = new IngredientEditor();
    stepEditor = new StepEditor(utensilEditor.getUtensils(), ingredientEditor.getIngredients());
    
    utensilEditor.addTextListener(stepEditor);
    ingredientEditor.addTextListener(stepEditor);
    
    Container mainEditors = new Container();
    mainEditors.setLayout(new BorderLayout());
    mainEditors.add(utensilEditor, BorderLayout.NORTH);
    mainEditors.add(ingredientEditor, BorderLayout.CENTER);
    mainEditors.add(stepEditor, BorderLayout.SOUTH);
    
    add(mainEditors, BorderLayout.SOUTH);
    
    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    icons.add(newButton);
    icons.add(openButton);
    icons.add(saveButton);
    icons.add(saveAsButton);
    icons.add(closeButton);

    add(icons, BorderLayout.NORTH);
    
    Container nameAndServings = new Container();
    
    JLabel lblNewLabel = new JLabel("Name:");
    nameAndServings.add(lblNewLabel);
    JTextField textField = new JTextField();
    textField.setColumns(DEFAULT_TEXT_FIELD_WIDTH);
    nameAndServings.add(textField);
    
    JLabel lblNewLabel1 = new JLabel("Serves:");
    nameAndServings.add(lblNewLabel1);
    JTextField textField1 = new JTextField();
    textField1.setColumns(DEFAULT_TEXT_FIELD_WIDTH);
    nameAndServings.add(textField1);
    
    add(nameAndServings, BorderLayout.CENTER);
    
    setVisible(true);
    setResizable(true);
    pack();
  }
  
  private Recipe createRecipe()
  {
    return new Recipe(null, 1, new ArrayList<Ingredient>(), new ArrayList<Utensil>(), new ArrayList<Step>());
  }
  
  private class RecipeEditorListener implements ActionListener
  {
    private final RecipeEditor subject;
    
    RecipeEditorListener(RecipeEditor subject)
    {
      super();
      this.subject = subject;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      String command = e.getActionCommand();
      
      if(command.equals(CLOSE_BUTTON_ACTION_COMMAND))
      {
        dispose();
      }
      else if(command.equals(NEW_BUTTON_ACTION_COMMAND))
      {
        new RecipeEditor(owner);
      }
      else if(command.equals(OPEN_BUTTON_ACTION_COMMAND))
      {
        
      }
      else if(command.equals(SAVE_AS_BUTTON_ACTION_COMMAND))
      {
        String fileName;
        fileName = JOptionPane.showInputDialog("File name:");
        
        try
        {
          subject.createRecipe().write(fileName);
        }
        catch(IOException ioe)
        {
          ioe.printStackTrace();
          JOptionPane.showMessageDialog(subject, "File could not be saved");
        }
      }
      else if(command.equals(SAVE_BUTTON_ACTION_COMMAND))
      {
        
      }
    }
    
  }
}
