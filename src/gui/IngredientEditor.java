package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Ingredient; 
import recipes.NutritionInfo;
import recipes.Unit;
import utilities.SortLists;

/**
 * Temporary class to make refactoring IngredientEditor easier.
 * 
 * @author Josiah Leach, Meara Patterson, KitchIntel
 * @version 4/25/2023
 *
 */
public class IngredientEditor extends JPanel
{
  
  // probably move to RecipeEditor
  public static final String SELECT_INGREDIENT = "select_ingredient";
  public static final String MAKE_NEW_INGREDIENT = "make_new_ingredient";
  
  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  private static final String BLANK = "            ";
  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound", "Pinch",
      "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Individual"};
  
  private JComboBox<String> selectIngredient;
  private JTextField detailField;
  private JTextField amountField;
  private JComboBox<String> unitSelect;
  private JButton makeNewIngredient;
  private JButton addButton;
  private JButton deleteButton;
  private JComboBox<String> substituteSelect;
  private TextArea ingredientDisplay;
  private TextArea substituteDisplay;
  
  private List<Ingredient> ingredients;
  private HashMap<Ingredient, List<Ingredient>> substitutes;
  
  public IngredientEditor()
  {
    
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Ingredients")));
    
    IngredientEditorListener listener = new IngredientEditorListener(this);
    EnableUpdater addListener = new EnableUpdater();
    
    selectIngredient = new JComboBox<>();
    selectIngredient.addItem("");
    for (String name : NutritionInfo.getIngredientsInMap())
    {
      selectIngredient.addItem(name);
    }
    makeNewIngredient = new JButton(Translator.translate("Make New Ingredient"));
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    amountField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    unitSelect = new JComboBox<>(UNITS); // should change
    
    addButton = new JButton(Translator.translate(ADD));
    deleteButton = new JButton(Translator.translate(DELETE));
    
    addButton.setActionCommand(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND);
    addButton.addActionListener(listener);
    deleteButton.setActionCommand(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND);
    selectIngredient.setActionCommand(SELECT_INGREDIENT);
    makeNewIngredient.setActionCommand(MAKE_NEW_INGREDIENT);
    makeNewIngredient.addActionListener(listener);
    
    selectIngredient.addActionListener(addListener);
    detailField.addActionListener(addListener);
    amountField.addActionListener(addListener);
    unitSelect.addActionListener(addListener);
    
    ingredients = new ArrayList<>();
    substitutes = new HashMap<>();
    
    substituteSelect = new JComboBox<>();
    ingredientDisplay = new TextArea(0, 0);
    substituteDisplay = new TextArea(0, 0);
    
    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel(Translator.translate("Ingredient") + ":"));
    inputFields.add(selectIngredient);
    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);
    inputFields.add(new JLabel(Translator.translate("Amount") + ":"));
    inputFields.add(amountField);
    inputFields.add(new JLabel(Translator.translate("Units") + ":"));
    inputFields.add(unitSelect);
    inputFields.add(addButton);
    inputFields.add(makeNewIngredient);
    
    add(inputFields, BorderLayout.NORTH);
    add(deleteButton, BorderLayout.EAST);
    add(ingredientDisplay, BorderLayout.CENTER);
    add(substituteDisplay, BorderLayout.SOUTH);
    
    setVisible(true);
    setOpaque(false);
    
  }
  
  private void add()
  {
    
    String name = selectIngredient.getSelectedItem().toString();
    String details = detailField.getText();
    String unit = unitSelect.getSelectedItem().toString();
//    String substitute = substituteSelect.getSelectedItem().toString();
    double amount;
    
    try
    {
      amount = Double.valueOf(amountField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }
    
    Ingredient ingredient = new Ingredient(name, details, amount, Unit.parseUnit(unit));
    ingredients.add(ingredient);
    
    SortLists.sortIngredients(ingredients);
    
    selectIngredient.setSelectedIndex(0); // will cause problems when selecting ""
    detailField.setText("");
    unitSelect.setSelectedIndex(0);
//    substituteSelect.setSelectedInsdex(0);
//    updateSubstituteSelect();
    amountField.setText("");
    
    updateTextArea();
    updateSubstituteArea();
    
  }
  
  private void delete()
  {
    
    if (ingredients.size() == 0)
    {
      return;
    }
    
    int selectionStart = ingredientDisplay.getSelectionStart();
    int linesSelected = 0;
    int linesSkipped = 0;
    String selectedText=  ingredientDisplay.getSelectedText();
    
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

    String skipped = ingredientDisplay.getText().substring(0, selectionStart);

    char[] skippedChars = skipped.toCharArray();

    for (char skippedChar : skippedChars)
    {
      if (skippedChar == '\n')
        linesSkipped++;
    }

    for (int i = 0; i < linesSelected; i++)
    {
      ingredients.remove(linesSkipped);
    }

    updateTextArea();
    
  }
  
  private void updateTextArea()
  {
    String text = "";

    for (Ingredient ingredient : ingredients)
    {
      text += String.format("%s\n", ingredient.toString());
    }

    ingredientDisplay.setText(text);
  }
  
  private void updateSubstituteArea()
  {
    String text = "";

    Set<Ingredient> substitutableIngredients = substitutes.keySet();
    for (Ingredient ingredient : substitutableIngredients)
    {
      List<Ingredient> substitutesForIngredient = substitutes.get(ingredient);
      if (substitutesForIngredient != null)
      {
        for (Ingredient substitute : substitutesForIngredient)
        {
          text += String.format("Substitute %s for %s\n", substitute.toString(),
              ingredient.toString());
        }
      }
    }
    substituteDisplay.setText(text);
  }
  
  /**
   * updates the selectable "substitute" options. Should be called after loadIngredient is called.
   */
  private void updateSubstituteSelect()
  {
    substituteSelect.removeAllItems();

    substituteSelect.addItem(BLANK);

    for (Ingredient ingredient : ingredients)
    {
      substituteSelect.addItem(ingredient.getName());
    }

  }
  
  List<Ingredient> getIngredients()
  {
    return ingredients;
  }

  /**
   * Gets the substitute Ingredients in the Recipe.
   * 
   * @return the substitute ingredients in the recipe.
   */
  HashMap<Ingredient, List<Ingredient>> getSubstitutes()
  {
    return substitutes;
  }

  /**
   * Adds a text listener to the text area of the ingredient editor.
   * 
   * @param listener
   *          the text listener to add to the display text area.
   */
  public void addTextListener(final TextListener listener)
  {
    ingredientDisplay.addTextListener(listener);
  }

  
  
  private class IngredientEditorListener implements ActionListener
  {
    
    private final IngredientEditor subject;

    IngredientEditorListener(final IngredientEditor subject)
    {
      this.subject = subject;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      
//      if (e.getActionCommand().equals(SELECT_INGREDIENT))
//      {
//        
//      }
//      else 
      if (e.getActionCommand().equals(MAKE_NEW_INGREDIENT))
      {
        MakeNewIngredientEditor makeNew = new MakeNewIngredientEditor();
      }
      else if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND))
      {
        subject.delete();
      }
      
    }
    
  }
  
  private class EnableUpdater implements ActionListener
  {
    
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      
    }
    
  }
  

  /**
   * Adds an action listener to the buttons in this IngredientEditor which can cause the document to
   * change.
   *
   * @param listener
   *        The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }
    
  void loadIngredients(final List<Ingredient> newIngredients)
  {
    this.ingredients.clear();
    
    for (Ingredient ingredient : newIngredients)
    {
      ingredients.add(ingredient);
    }
    
    updateTextArea();
    updateSubstituteSelect();
  }
  
  void loadSubstitutes(final HashMap<Ingredient, List<Ingredient>> newSubs)
  {
    this.substitutes.clear();
    
    for (Ingredient key : newSubs.keySet())
    {
      substitutes.put(key, newSubs.get(key));
    }
    
    updateSubstituteArea();
  }
  
  }
  
