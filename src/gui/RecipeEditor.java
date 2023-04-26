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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import branding.KitchIntelColor;
import config.Translator;
import recipes.Ingredient;
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
  static final String SUBSTITUTE_ADD_ACTION_COMMAND = "subs_add_act";
  static final String UTENSIL_ADD_ACTION_COMMAND = "uten_add_act";
  static final String INGREDIENT_DELETE_ACTION_COMMAND = "ingr_del_act";
  static final String SUBSTITUTE_DELETE_ACTION_COMMAND = "subs_del_act";
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
  private SubstituteEditor substituteEditor;
  private StepEditor stepEditor;

  private JTextField nameField;
  private JTextField servingsField;

  private Recipe workingRecipe;

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

    workingRecipe = new Recipe("New Recipe", 0);

    ActionListener listener = new RecipeEditorListener();
    ActionListener cListener = new ChangeListener();

    nameField = new JTextField();
    servingsField = new JTextField();
    nameField.setColumns(DEFAULT_TEXT_FIELD_WIDTH);
    servingsField.setColumns(DEFAULT_TEXT_FIELD_WIDTH);

    stepEditor = new StepEditor(workingRecipe);
    utensilEditor = new UtensilEditor(workingRecipe, stepEditor);
    ingredientEditor = new IngredientEditor(workingRecipe, stepEditor);
    substituteEditor = new SubstituteEditor(workingRecipe);

    // Sets up action listener stuff for file manipulation
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

    // Change listeners for when the file is being changed, i.e. when we add a new ingredient or add
    // a new step or something. This will affect the ability to save or edit the document,
    // specifically the buttons at the top.
    utensilEditor.addChangeListener(cListener);
    ingredientEditor.addChangeListener(cListener);
    substituteEditor.addChangeListener(cListener);
    stepEditor.addChangeListener(cListener);
    nameField.addActionListener(cListener);
    servingsField.addActionListener(cListener);
    nameField.addActionListener(cListener);
    servingsField.addActionListener(cListener);

    // Sets up all of the little editors in the layout of the window
    Container mainEditors = new Container();
    mainEditors.setLayout(new BorderLayout());
    mainEditors.add(utensilEditor, BorderLayout.NORTH);
    Container ingAndSub = new Container();
    ingAndSub.setLayout(new BorderLayout());
    ingAndSub.add(ingredientEditor, BorderLayout.NORTH);
    ingAndSub.add(substituteEditor, BorderLayout.SOUTH);
    mainEditors.add(ingAndSub);
    mainEditors.add(stepEditor, BorderLayout.SOUTH);

    // Adds the editors to the overall window and sets the colors
    JPanel p = new JPanel();
    p.setOpaque(true);
    p.setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());
    p.setLayout(new BorderLayout());
    p.add(mainEditors, BorderLayout.SOUTH);

    // Sets up the stuff unrelated to any of the editors
    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    icons.add(newButton);
    icons.add(openButton);
    icons.add(saveButton);
    icons.add(saveAsButton);
    icons.add(closeButton);
    JLabel nameLabel = new JLabel(Translator.translate("Name") + ":");
    JLabel servesLabel = new JLabel(Translator.translate("Serves") + ":");
    icons.add(nameLabel);
    icons.add(nameField);
    icons.add(servesLabel);
    icons.add(servingsField);
    p.add(icons, BorderLayout.NORTH);

    // Makes the entire window scrollable
    JScrollPane scrollPane = new JScrollPane(p);
    add(scrollPane);

    setVisible(true);
    setResizable(true);
    pack();
    
  }
  
  void updateEditors() {
    nameField.setText(workingRecipe.getName());
    servingsField.setText(workingRecipe.getServings() + "");
    utensilEditor.loadUtensils(workingRecipe.getUtensils());
    utensilEditor.loadUtensils(workingRecipe.getUtensils());
    ingredientEditor.updateIngredientDisplay();
    substituteEditor.updateSubstituteDisplay();
    stepEditor.loadSteps(workingRecipe.getSteps());
  }

  private void loadRecipe(final Recipe recipe, final String fileName)
  {
    this.workingRecipe = recipe;
    this.fileName = fileName;
    updateEditors();
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

    String path = chooser.getSelectedFile().getPath();
    path = path.substring(0, path.indexOf(CURRENT_DIRECTORY));

    Recipe recipe;

    try
    {
      recipe = Recipe.read(path);
      loadRecipe(recipe, path);
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

  /**
   * Saves the current workingRecipe to a file and directory selected.
   */
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
      workingRecipe.write(newFileName);

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

  /**
   * Saves the current workingRecipe to the file and directory currently selected.
   */
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
      workingRecipe.write(fileName);
      state = DocumentState.UNCHANGED;
      updateButtons();
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
      JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
    }
  }

  /**
   * ActionListener for the top buttons.
   * @author Josiah Leach
   *
   */
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
}
