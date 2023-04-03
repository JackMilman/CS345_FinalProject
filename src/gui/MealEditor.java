package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import recipes.Recipe;

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
  
  private static final int TEXT_WIDTH = 40;
  
  private static final String NEW_BUTTON_ACTION_COMMAND = "men";
  private static final String OPEN_BUTTON_ACTION_COMMAND = "meo";
  private static final String SAVE_BUTTON_ACTION_COMMAND = "mes";
  private static final String SAVE_AS_BUTTON_ACTION_COMMAND = "mea";
  private static final String CLOSE_BUTTON_ACTION_COMMAND = "mec";
  private static final String ADD_RECIPE_ACTION_COMMAND = "mar";
  
  private final Window owner;
  private final TextArea display;
  
  private List<Recipe> recipes;

  /**
   * Creates a new MealEditor.
   * @param owner The JFrame which created this MealEditor. This should probably be
   * the Main Window.
   */
  public MealEditor(final Window owner)
  {
    super(owner, "KiLowBites Meal Editor");
    
    this.owner = owner;
    
    this.recipes = new ArrayList<Recipe>();
    
    this.display = new TextArea();
    this.display.setEditable(false);
    
    MealEditorListener listener = new MealEditorListener();
    
    JButton newButton = new KitchIntelButton(KitchIntelButton.NEW_IMAGE);
    JButton openButton = new KitchIntelButton(KitchIntelButton.OPEN_IMAGE);
    JButton saveButton = new KitchIntelButton(KitchIntelButton.SAVE_IMAGE);
    JButton saveAsButton = new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE);
    JButton closeButton = new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE);
    
    JButton addRecipeButton = new JButton("Add Recipe");
    
    newButton.setActionCommand(NEW_BUTTON_ACTION_COMMAND);
    openButton.setActionCommand(OPEN_BUTTON_ACTION_COMMAND);
    saveButton.setActionCommand(SAVE_BUTTON_ACTION_COMMAND);
    saveAsButton.setActionCommand(SAVE_AS_BUTTON_ACTION_COMMAND);
    closeButton.setActionCommand(CLOSE_BUTTON_ACTION_COMMAND);
    
    addRecipeButton.setActionCommand(ADD_RECIPE_ACTION_COMMAND);
    
    newButton.addActionListener(listener);
    openButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    saveAsButton.addActionListener(listener);
    closeButton.addActionListener(listener);
    
    addRecipeButton.addActionListener(listener);
    
    setLayout(new BorderLayout());
    
    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
    buttons.add(newButton);
    buttons.add(openButton);
    buttons.add(saveButton);
    buttons.add(saveAsButton);
    buttons.add(closeButton);
    
    add(buttons, BorderLayout.NORTH);
    
    JPanel name = new JPanel();
    name.setLayout(new FlowLayout(FlowLayout.LEFT));
    name.add(new JLabel("Name:"));
    name.add(new JTextField(TEXT_WIDTH));
    
    add(name, BorderLayout.CENTER);
    
    JPanel edit = new JPanel();
    edit.setBorder(KitchIntelBorder.labeledBorder("Recipes"));
    edit.setLayout(new BorderLayout());
    edit.add(addRecipeButton, BorderLayout.NORTH);
    edit.add(this.display, BorderLayout.CENTER);
    edit.add(new JButton("Delete"), BorderLayout.EAST);
    
    
    add(edit, BorderLayout.SOUTH);
    
    setVisible(true);
    setResizable(true);
    pack();
  }
  
  private void updateDisplay()
  {
    String displayText = "";
    
    for(Recipe recipe : recipes)
    {
      displayText += recipe.getName() + "\n";
    }
    
    display.setText(displayText);
  }
  
  private class MealEditorListener implements ActionListener
  {

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
        new MealEditor(owner);
      }
      else if(command.equals(OPEN_BUTTON_ACTION_COMMAND))
      {
        
      }
      else if(command.equals(SAVE_AS_BUTTON_ACTION_COMMAND))
      {
        
      }
      else if(command.equals(SAVE_BUTTON_ACTION_COMMAND))
      {
        
      }
      else if(command.equals(ADD_RECIPE_ACTION_COMMAND))
      {
        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.showOpenDialog(null);
        
        String fileName = chooser.getSelectedFile().getPath();
        fileName = fileName.substring(0, fileName.indexOf("."));
        
        try
        {
          recipes.add(Recipe.read(fileName));
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
        
        updateDisplay();
      }
    }
    
  }
  
}
