package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import config.Translator;
import recipes.CompositeRecipe;
import recipes.Ingredient;
import recipes.LeafRecipe;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

/**
 * 
 * @author shelseyvega, Josiah Leach, KitchIntel
 *
 */
public class RecipeEditor extends Editor
{
  static final int DEFAULT_TEXT_FIELD_WIDTH = 6;

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

  private UtensilEditor utensilEditor;
  private IngredientEditor ingredientEditor;
  private StepEditor stepEditor;

  private JTextField nameField;
  private JTextField servingsField;

  /**
   * Creates a new RecipeEditor.
   * 
   * @param owner
   *          The JFrame which created this RecipeEditor. This should probably be the Main Window.
   */
  public RecipeEditor(final Window owner)
  {
    super(owner, Translator.translate("KiLowBites Recipe Editor"));
    setLayout(new BorderLayout());

    ActionListener listener = new RecipeEditorListener();
    ActionListener cListener = new ChangeListener();

    nameField = new JTextField();
    servingsField = new JTextField();

    utensilEditor = new UtensilEditor();
    ingredientEditor = new IngredientEditor();
    stepEditor = new StepEditor(utensilEditor.getUtensils(), ingredientEditor.getIngredients());

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

    nameField.setColumns(DEFAULT_TEXT_FIELD_WIDTH);
    servingsField.setColumns(DEFAULT_TEXT_FIELD_WIDTH);

    utensilEditor.addTextListener(stepEditor);
    ingredientEditor.addTextListener(stepEditor);

    utensilEditor.addChangeListener(cListener);
    ingredientEditor.addChangeListener(cListener);
    stepEditor.addChangeListener(cListener);
    nameField.addActionListener(cListener);
    servingsField.addActionListener(cListener);

    nameField.addActionListener(listener);
    servingsField.addActionListener(listener);

    JLabel nameLabel = new JLabel(Translator.translate("Name") + ":");
    JLabel servesLabel = new JLabel(Translator.translate("Serves") + ":");

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

    icons.add(nameLabel);
    icons.add(nameField);
    icons.add(servesLabel);
    icons.add(servingsField);

    add(icons, BorderLayout.NORTH);

    // Container nameAndServings = new Container();
    // nameAndServings.setLayout(new FlowLayout(FlowLayout.LEFT));
    // nameAndServings.add(nameLabel);
    // nameAndServings.add(nameField);
    // nameAndServings.add(servesLabel);
    // nameAndServings.add(servingsField);
    //
    // add(nameAndServings, BorderLayout.CENTER);

    setVisible(true);
    setResizable(true);
    pack();
  }

  private Recipe createRecipe()
  {
    String name;
    int servings;
    List<Ingredient> ingredients;
    HashMap<Ingredient, List<Ingredient>> substitutes;
    List<Utensil> utensils;
    List<Step> steps;

    name = nameField.getText();

    try
    {
      servings = Integer.valueOf(servingsField.getText());
    }
    catch (NumberFormatException e)
    {
      servings = 1;
    }

    ingredients = ingredientEditor.getIngredients();
    substitutes = ingredientEditor.getSubstitutes();
    utensils = utensilEditor.getUtensils();
    steps = stepEditor.getSteps();

    Recipe result = new CompositeRecipe(name, servings);
    result.addAllIngredients(ingredients);
    result.addAllSubstitutes(substitutes);
    result.addAllUtensils(utensils);
    result.addAllSteps(steps);
    return result;
  }

  private void loadRecipe(final Recipe recipe, final String fileName)
  {
    nameField.setText(recipe.getName());
    servingsField.setText(recipe.getServings() + "");
    utensilEditor.loadUtensils(recipe.getUtensils());
    ingredientEditor.loadIngredients(recipe.getIngredients());
    ingredientEditor.loadSubstitutes(recipe.getSubstitutes());
    stepEditor.loadSteps(recipe.getSteps());

    this.fileName = fileName;
  }

  private void close()
  {
    state = DocumentState.NULL;

    updateButtons();

    dispose();
  }

  private void newButton()
  {
    new RecipeEditor(owner);

    updateButtons();
  }

  private void open()
  {
    JFileChooser chooser = new JFileChooser(new File(CURRENT_DIRECTORY));
    chooser.showOpenDialog(null);

    String fileName = chooser.getSelectedFile().getPath();
    fileName = fileName.substring(0, fileName.indexOf(CURRENT_DIRECTORY));

    Recipe recipe;

    try
    {
      recipe = Recipe.read(fileName);
      loadRecipe(recipe, fileName);
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
    }
    finally
    {
      updateButtons();
    }

  }

  private void saveAs()
  {
    if (nameField.getText().equals(""))
    {
      JOptionPane.showMessageDialog(null, Translator.translate("You must input a name"), 
          Translator.translate("Error"), JOptionPane.PLAIN_MESSAGE);
      return;
    }

    String newFileName;
    newFileName = JOptionPane.showInputDialog(Translator.translate("File name") + ":");

    try
    {
      createRecipe().write(newFileName);

      fileName = newFileName;

      state = DocumentState.UNCHANGED;

      updateButtons();
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
      JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
    }
  }

  private void save()
  {
    if (nameField.getText().equals(""))
    {
      JOptionPane.showMessageDialog(null, Translator.translate("You must input a name"));
      return;
    }
    if (fileName == null)
      saveAs();
    try
    {
      createRecipe().write(fileName);
      state = DocumentState.UNCHANGED;
      updateButtons();
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
      JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
    }
  }

  private class RecipeEditorListener implements ActionListener
  {

    public RecipeEditorListener()
    {
      super();
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      String command = e.getActionCommand();

      if (command.equals(CLOSE_BUTTON_ACTION_COMMAND))
      {
        close();
      }
      else if (command.equals(NEW_BUTTON_ACTION_COMMAND))
      {
        newButton();
      }
      else if (command.equals(OPEN_BUTTON_ACTION_COMMAND))
      {
        open();
      }
      else if (command.equals(SAVE_AS_BUTTON_ACTION_COMMAND))
      {
        saveAs();
      }
      else if (command.equals(SAVE_BUTTON_ACTION_COMMAND))
      {
        save();
      }
    }

  }

  private class ChangeListener implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      state = DocumentState.CHANGED;
      updateButtons();
    }

  }
  public static void main(String[] args)
  {
    MainWindow main = new MainWindow();
    RecipeEditor frame = new RecipeEditor(main);
    frame.setVisible(true);
  }
}
