package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.CompositeRecipe;
import recipes.Ingredient;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

/**
 * A class for the step editor component of the recipe editor.
 * 
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class StepEditor extends JComponent implements TextListener
{
  private static final String[] ACTIONS = new String[] {"", "Put", "Melt", "Simmer", "Heat",
      "Ignite", "Boil", "Drain", "Saute", "Cook", "Bake", "Dip"};

  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  private static final String BLANK = "            ";
  protected static final String CURRENT_DIRECTORY = ".";
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private JComboBox<String> actionSelect, onSelect, utensilSelect;
  private JTextField detailField, timeField;
  private List<Step> steps;
  private TextArea display;
  private List<Utensil> utensils;
  private List<Ingredient> ingredients;
  private JButton addButton, deleteButton, embeddedRecipe;
  public String fileName;
  private CompositeRecipe embedded;
  private List<CompositeRecipe> embeddedRecipes;

  /**
   * Creates a new StepEditor.
   * 
   * @param utensils
   *          The utensils which can be used in a step. Must be a reference to the list used by the
   *          corresponding UtensilEditor.
   * @param ingredients
   *          The ingredients which can be used in a step. Must be a reference to the list used by
   *          the corresponding IngredientEditor.
   */
  public StepEditor(final List<Utensil> utensils, final List<Ingredient> ingredients)
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Steps")));

    this.utensils = utensils;
    this.ingredients = ingredients;
    this.steps = new ArrayList<Step>();

    StepEditorListener listener = new StepEditorListener(this);
    EnableListener enabler = new EnableListener();

    actionSelect = new JComboBox<String>(ACTIONS);
    onSelect = new JComboBox<String>(new String[] {BLANK});
    utensilSelect = new JComboBox<String>(new String[] {BLANK});
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    timeField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH / 2);

    addButton = new JButton(Translator.translate(ADD));
    deleteButton = new JButton(Translator.translate(DELETE));
    embeddedRecipe= new JButton("EmbeddedRecipe");
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
    embeddedRecipe.addActionListener(listener);
    
    actionSelect.addActionListener(enabler);
    onSelect.addActionListener(enabler);
    utensilSelect.addActionListener(enabler);
    timeField.addActionListener(enabler);
    
    addButton.setEnabled(false);

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel(Translator.translate("Action") + ":"));
    inputFields.add(actionSelect);
    inputFields.add(new JLabel(Translator.translate("On") + ":"));
    inputFields.add(onSelect);
    inputFields.add(new JLabel(Translator.translate("Utensil") + ":"));
    inputFields.add(utensilSelect);
    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);
    inputFields.add(new JLabel(Translator.translate("Minutes") + ":"));
    inputFields.add(timeField);
    inputFields.add(embeddedRecipe);
    inputFields.add(addButton);

    add(inputFields, BorderLayout.NORTH);

    add(deleteButton, BorderLayout.EAST);

    display = new TextArea();
    display.setEditable(false);
    add(display, BorderLayout.CENTER);

    setVisible(true);
  }

  private void add()
  {
    String action = actionSelect.getSelectedItem().toString();
    String on = onSelect.getSelectedItem().toString();
    String utensil = utensilSelect.getSelectedItem().toString();
    String details = detailField.getText();
    int time;

    try
    {
      time = Integer.valueOf(timeField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }

    if (action.equals("") || on.equals("") || utensil.equals(""))
    {
      return;
    }

    Utensil destinationUtensil = null;
    Utensil sourceUtensil = null;

    for (int i = 0; i < utensils.size(); i++)
    {
      if (on.equals(utensils.get(i).getName()))
      {
        sourceUtensil = utensils.get(i);
      }
      if (utensil.equals(utensils.get(i).getName()))
      {
        destinationUtensil = utensils.get(i);
      }
    }
    if (on.startsWith("*")) {
      try {
      Recipe objectIngredient =  Recipe.read(fileName);
      Step step = new Step(action, objectIngredient, sourceUtensil, destinationUtensil, details,
          time);
      steps.add(step);
      System.out.println(step);
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else {
    Ingredient objectIngredient = null;
    for (int i = 0; i < ingredients.size(); i++)
    {
      if (on.equals(ingredients.get(i).getName()))
      {
        objectIngredient = ingredients.get(i);
      }
      
    }
    Step step = new Step(action, objectIngredient, sourceUtensil, destinationUtensil, details,
        time);
    steps.add(step);
    System.out.println(step);
    }
   

    updateDisplay();

    actionSelect.setSelectedIndex(0);
    onSelect.setSelectedIndex(0);
    utensilSelect.setSelectedIndex(0);
    timeField.setText("");
    detailField.setText("");
  }

  private void delete()
  {
    if (steps.size() == 0)
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
      steps.remove(linesSkipped);
    }

    updateDisplay();
  }

  private void updateDisplay()
  {
    String displayText = "";

    for (Step step : steps)
    {
      displayText += String.format("%s\n", step.toString());
    }

    display.setText(displayText);
  }

  /**
   * updates the selectable "on" options. Should be called after loadUtensil or loadIngredient is
   * called on the corresponding UtensilEditor or IngredientEditor.
   */
  public void updateOn()
  {
    onSelect.removeAllItems();

    onSelect.addItem(BLANK);

    for (Utensil utensil : utensils)
    {
      onSelect.addItem(utensil.getName());
    }

    for (Ingredient ingredient : ingredients)
    {
      onSelect.addItem(ingredient.getName());
    }
    for (CompositeRecipe recipes: embeddedRecipes) {
      onSelect.addItem("*"+recipes.getName());
    }

  }

  /**
   * updates the selectable "utensil" options. Should be called after loadUtensil is called on the
   * corresponding UtensilEditor.
   */
  public void updateUtensil()
  {
    utensilSelect.removeAllItems();

    utensilSelect.addItem(BLANK);

    for (Utensil utensil : utensils)
    {
      utensilSelect.addItem(utensil.getName());
    }
  }

  private class StepEditorListener implements ActionListener
  {
    private StepEditor subject;

    private StepEditorListener(final StepEditor subject)
    {
      this.subject = subject;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (e.getActionCommand().equals(ADD))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(DELETE))
      {
        subject.delete();
      }
      else if (e.getActionCommand().equals("EmbeddedRecipe")) {
        JFileChooser chooser = new JFileChooser(new File(CURRENT_DIRECTORY));
        chooser.showOpenDialog(null);

        fileName = chooser.getSelectedFile().getPath();
        fileName = fileName.substring(0, fileName.indexOf(CURRENT_DIRECTORY));
        try {
        String eRecipe = Recipe.read(fileName).getName();
        embedded = new CompositeRecipe(eRecipe, Recipe.read(fileName).getServings());
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
        embeddedRecipes.add(embedded);
        updateOn();
      }
    }

  }
  
  private class EnableListener implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      boolean filled = onSelect.getSelectedIndex() != 0 && actionSelect.getSelectedIndex() != 0 
          && utensilSelect.getSelectedIndex() != 0 && timeField.getText().length() > 0;
      addButton.setEnabled(filled);
    }
  }

  @Override
  public void textValueChanged(final TextEvent e)
  {
    updateOn();
    updateUtensil();
  }

  List<Step> getSteps()
  {
    return steps;
  }

  /**
   * Adds an action listener to the buttons in this StepEditor which can cause the document to
   * change.
   * 
   * @param listener
   *          The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }

  /**
   * Loads the given steps.
   * 
   * @param newSteps
   *          The new steps for this StepEditor to display.
   */
  public void loadSteps(final List<Step> newSteps)
  {
    this.steps = newSteps;

    updateDisplay();
  }

}
