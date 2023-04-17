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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Meal;
import recipes.Recipe;

/**
 * The class for the meal editor window. All that another class needs to do is call the constructor.
 * 
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 *
 */
public class MealEditor extends Editor
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
  private static final String DELETE_ACTION_COMMAND = "md";

  private final TextArea display;

  private final JTextField nameField;

  private List<Recipe> recipes;

  /**
   * Creates a new MealEditor.
   * 
   * @param owner
   *          The JFrame which created this MealEditor. This should probably be the Main Window.
   */
  public MealEditor(final Window owner)
  {
    super(owner, Translator.translate("KiLowBites Meal Editor"));

    this.recipes = new ArrayList<Recipe>();

    this.display = new TextArea();
    this.display.setEditable(false);

    this.nameField = new JTextField(TEXT_WIDTH);

    MealEditorListener listener = new MealEditorListener();

    JButton addRecipeButton = new JButton(Translator.translate("Add Recipe"));
    JButton deleteButton = new JButton(Translator.translate("Delete"));

    newButton.setActionCommand(NEW_BUTTON_ACTION_COMMAND);
    openButton.setActionCommand(OPEN_BUTTON_ACTION_COMMAND);
    saveButton.setActionCommand(SAVE_BUTTON_ACTION_COMMAND);
    saveAsButton.setActionCommand(SAVE_AS_BUTTON_ACTION_COMMAND);
    closeButton.setActionCommand(CLOSE_BUTTON_ACTION_COMMAND);

    addRecipeButton.setActionCommand(ADD_RECIPE_ACTION_COMMAND);
    deleteButton.setActionCommand(DELETE_ACTION_COMMAND);

    newButton.addActionListener(listener);
    openButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    saveAsButton.addActionListener(listener);
    closeButton.addActionListener(listener);

    addRecipeButton.addActionListener(listener);
    deleteButton.addActionListener(listener);

    setLayout(new BorderLayout());

    JPanel buttons = new JPanel();
    buttons.setOpaque(false);
    buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
    buttons.add(newButton);
    buttons.add(openButton);
    buttons.add(saveButton);
    buttons.add(saveAsButton);
    buttons.add(closeButton);

    add(buttons, BorderLayout.NORTH);

    JPanel name = new JPanel();
    name.setOpaque(false);
    name.setLayout(new FlowLayout(FlowLayout.LEFT));
    name.add(new JLabel(Translator.translate("Name") + ":"));
    name.add(nameField);

    add(name, BorderLayout.CENTER);

    JPanel edit = new JPanel();
    edit.setOpaque(false);
    edit.setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Recipes")));
    edit.setLayout(new BorderLayout());
    edit.add(addRecipeButton, BorderLayout.NORTH);
    edit.add(this.display, BorderLayout.CENTER);
    edit.add(deleteButton, BorderLayout.EAST);

    add(edit, BorderLayout.SOUTH);

    setVisible(true);
    setResizable(true);
    pack();
  }

  private void updateDisplay()
  {
    String displayText = "";

    for (Recipe recipe : recipes)
    {
      displayText += recipe.getName() + "\n";
    }

    display.setText(displayText);
  }

  private void loadMeal(final Meal meal, final String fileName)
  {
    nameField.setText(meal.getName());
    recipes = meal.getRecipes();

    this.fileName = fileName;

    updateDisplay();
  }

  private Meal createMeal()
  {
    String name = nameField.getText();

    int servings = Integer.MAX_VALUE;

    for (Recipe recipe : recipes)
    {
      servings = Math.min(servings, recipe.getServings());
    }

    return new Meal(name, recipes, servings);
  }

  private void close()
  {
    state = DocumentState.NULL;

    updateButtons();

    dispose();
  }

  private void newButton()
  {
    new MealEditor(owner);

    updateButtons();
  }

  private void open()
  {
    JFileChooser chooser = new JFileChooser(new File(CURRENT_DIRECTORY));
    chooser.showOpenDialog(null);

    String fileName = chooser.getSelectedFile().getPath();
    fileName = fileName.substring(0, fileName.indexOf(CURRENT_DIRECTORY));

    Meal meal;

    try
    {
      meal = Meal.read(fileName);
      loadMeal(meal, fileName);
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
      createMeal().write(newFileName);

      fileName = newFileName;

      state = DocumentState.UNCHANGED;

      updateButtons();
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
      JOptionPane.showMessageDialog(null, ERROR_MESSAGE, "error", JOptionPane.PLAIN_MESSAGE);
    }
  }

  private void save()
  {
    if (nameField.getText().equals(""))
    {
      JOptionPane.showMessageDialog(null, Translator.translate("You must input a name"), 
          Translator.translate("Error"), JOptionPane.PLAIN_MESSAGE);
      return;
    }
    if (fileName == null)
      saveAs();
    try
    {
      createMeal().write(fileName);
      state = DocumentState.UNCHANGED;
      updateButtons();
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
      JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
    }
  }

  private void delete()
  {
    if (recipes.size() == 0)
      return;

    int selectionStart = display.getSelectionStart();
    int linesSelected = 0;
    int linesSkipped = 0;
    String selectedText = display.getSelectedText();

    if (selectedText == null || selectedText.length() < 0)
      return;

    char[] characters = selectedText.toCharArray();

    // counts the number of newline characters to determine the number of lines selected
    for (char character : characters)
    {
      if (character == '\n')
      {
        linesSelected++;
      }
    }

    // if the last selected character isn't a newline character, then there is one uncounted line.
    if (characters[characters.length - 1] != '\n')
      linesSelected++;

    String skipped = display.getText().substring(0, selectionStart);

    char[] skippedChars = skipped.toCharArray();

    for (char skippedChar : skippedChars)
    {
      if (skippedChar == '\n')
        linesSkipped++;
    }

    for (int i = 0; i < linesSelected; i++)
    {
      recipes.remove(linesSkipped);
    }

    state = DocumentState.CHANGED;
    updateButtons();

    updateDisplay();
  }

  private class MealEditorListener implements ActionListener
  {

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
      else if (command.equals(ADD_RECIPE_ACTION_COMMAND))
      {
        JFileChooser chooser = new JFileChooser(new File(CURRENT_DIRECTORY));
        chooser.showOpenDialog(null);

        String fileName = chooser.getSelectedFile().getPath();
        fileName = fileName.substring(0, fileName.indexOf(CURRENT_DIRECTORY));

        try
        {
          recipes.add(Recipe.read(fileName));
          state = DocumentState.CHANGED;
          updateButtons();
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }

        updateDisplay();
      }
      else if (command.equals(DELETE_ACTION_COMMAND))
      {
        delete();
      }
    }

  }

}
