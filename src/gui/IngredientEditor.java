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
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Unit;
import recipes.Utensil;
import utilities.SortLists;

/**
 * A class for the ingredient editor component of the meal editor.
 * 
 * @author Josiah Leach, Meara Patterson, KitchIntel
 * @version 03.29.2023
 */
public class IngredientEditor extends JPanel
{

  public static final Double NO_INPUT = -1.0;
  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound", "Pinch",
      "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Individual"};
  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  private static final String BLANK = "            ";

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private JTextField nameField;
  private JTextField detailField;
  private JTextField amountField;
  private JTextField calorieField;
  private JTextField densityField;
  private JTextField priceField;
  private TextArea ingredientDisplay;
  private TextArea substituteDisplay;
  private JButton addButton, deleteButton;
  private final JComboBox<String> unitSelect;
  private final JComboBox<String> substituteSelect;

  private List<Ingredient> ingredients;
  private HashMap<Ingredient, List<Ingredient>> substitutes;

  /**
   * 
   */
  public IngredientEditor()
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Ingredients")));

    IngredientEditorListener listener = new IngredientEditorListener(this);
    EnableUpdater addListener = new EnableUpdater();

    addButton = new JButton(Translator.translate(ADD));
    deleteButton = new JButton(Translator.translate(DELETE));    

    addButton.setActionCommand(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND);
    deleteButton.setActionCommand(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND);

    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    amountField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    calorieField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    densityField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    priceField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    
    unitSelect = new JComboBox<String>(UNITS);
    
    nameField.addActionListener(addListener);
    detailField.addActionListener(addListener);
    amountField.addActionListener(addListener);
    calorieField.addActionListener(addListener);
    densityField.addActionListener(addListener);
    unitSelect.addActionListener(addListener);

    ingredients = new ArrayList<Ingredient>();
    substitutes = new HashMap<Ingredient, List<Ingredient>>();


    substituteSelect = new JComboBox<String>();

    ingredientDisplay = new TextArea(0, 0);
    substituteDisplay = new TextArea(0, 0);

    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
    
    calorieField.setEnabled(false);
    densityField.setEnabled(false);
    addButton.setEnabled(false);
    
    addButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent evt)
      {
        try
        {
          NutritionInfo.addIngredient(nameField.getText(), 
              Double.parseDouble(calorieField.getText()), 
              Double.parseDouble(densityField.getText()));
        }
        catch (NumberFormatException nfe)
        {
          NutritionInfo.addIngredient(nameField.getText(), 
              NO_INPUT, NO_INPUT);
        }
      }
    });

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel(Translator.translate("Name") + ":"));
    inputFields.add(nameField);
    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);
    inputFields.add(new JLabel(Translator.translate("Amount") + ":"));
    inputFields.add(amountField);
    inputFields.add(new JLabel(Translator.translate("Units") + ":"));
    inputFields.add(unitSelect);

    inputFields.add(new JLabel(Translator.translate("Price") + ": $"));
    inputFields.add(priceField);

    inputFields.add(new JLabel(Translator.translate("Calories") + ":"));
    inputFields.add(calorieField);
    inputFields.add(new JLabel(Translator.translate("g/mL") + ":"));
    inputFields.add(densityField);
    inputFields.add(addButton);

    add(inputFields, BorderLayout.NORTH);

    add(deleteButton, BorderLayout.EAST);

    ingredientDisplay.setEditable(false);
    substituteDisplay.setEditable(false);
    add(ingredientDisplay, BorderLayout.CENTER);
    add(substituteDisplay, BorderLayout.SOUTH);

    setVisible(true);
    setOpaque(false);
  }

  private void add()
  {
    String name = nameField.getText();
    String details = detailField.getText();
    String unit = unitSelect.getSelectedItem().toString();
    String substitute = substituteSelect.getSelectedItem().toString();

    double amount;
    double calories;
    double density;
    double price;

    try
    {
      amount = Double.valueOf(amountField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }
    
    try
    {
      price = Double.valueOf(priceField.getText());
    }
    catch (NumberFormatException nfe)
    {
      price = 0;
    }

    // User is allowed to not input calories or density.
    // If they don't, those values are set to NO_INPUT
    try
    {
      calories = Double.valueOf(calorieField.getText());
    }
    catch (NumberFormatException nfe)
    {
      calories = NO_INPUT;
    }
    catch (NullPointerException npe)
    {
      calories = NO_INPUT;
    }

    try
    {
      density = Double.valueOf(densityField.getText());
    }
    catch (NumberFormatException nfe)
    {

      density = NO_INPUT;

    }
    catch (NullPointerException npe)
    {
      density = NO_INPUT;
    }
    

    if (name.equals("") || unit.equals(""))
      return;


    Ingredient ingredient = new Ingredient(name, details, amount, Unit.parseUnit(unit), 
        calories, density, price);
    ingredients.add(ingredient);


      if (substitutes.containsKey(original))
      {
        substitutes.get(original).add(ingredient);
      }
      else
      {
        List<Ingredient> newSubstitutes = new ArrayList<Ingredient>();
        newSubstitutes.add(ingredient);
        substitutes.put(original, newSubstitutes);
      }

    SortLists.sortIngredients(ingredients);

    nameField.setText("");
    detailField.setText("");
    unitSelect.setSelectedIndex(0);
    substituteSelect.setSelectedIndex(0);
    updateSubstituteSelect();
    amountField.setText("");
    priceField.setText("");
    calorieField.setText("");
    densityField.setText("");

    updateTextArea();
    updateSubstituteArea();
  }

  private void delete()
  {
    if (ingredients.size() == 0)
      return;

    int selectionStart = ingredientDisplay.getSelectionStart();
    int linesSelected = 0;
    int linesSkipped = 0;
    String selectedText = ingredientDisplay.getSelectedText();

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
      if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND))
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
      if(NutritionInfo.contains(nameField.getText()) || nameField.getText().equals(""))
      {
        calorieField.setEnabled(false);
        densityField.setEnabled(false);
        calorieField.setText("");
        densityField.setText("");
      }
      else
      {
        calorieField.setEnabled(true);
        densityField.setEnabled(true);
      }
      
      if(nameField.getText().length() == 0 || amountField.getText().length() == 0 
          || unitSelect.getSelectedIndex() == 0)
      {
        addButton.setEnabled(false);
        return;
      }
      else
      {
        addButton.setEnabled(true);
      }
      
      if(NutritionInfo.contains(nameField.getText()))
      {
        addButton.setEnabled(true);
      }
      // You can input an ingredient without giving the calorie and density
//      else
//      {
//        boolean filled = calorieField.getText().length() > 0 && densityField.getText().length() > 0;
//        addButton.setEnabled(filled);
//      }
    }
  }

  /**
   * Adds an action listener to the buttons in this IngredientEditor which can cause the document to
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
