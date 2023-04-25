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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import branding.KitchIntelBorder;
import config.Translator;
import gui.IngredientEditor.EnableUpdater;
import recipes.CalorieGram;
import recipes.Ingredient; 
import recipes.NutritionInfo;
import recipes.Unit;
import recipes.Utensil;
import utilities.SortLists;

/**
 * Temporary class to make refactoring IngredientEditor easier.
 * 
 * @author Meara Patterson
 *
 */
public class IngredientEditor2 extends JPanel
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
  private JTextArea ingredientDisplay;
  private JTextArea substituteDisplay;
  
  private List<Ingredient> ingredients;
  private HashMap<Ingredient, List<Ingredient>> substitutes;
  
  public IngredientEditor2()
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
    addButton.setEnabled(false);
    deleteButton = new JButton(Translator.translate(DELETE));
    
    addButton.setActionCommand(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND);
    addButton.addActionListener(listener);
    deleteButton.setActionCommand(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND);
    selectIngredient.setActionCommand(SELECT_INGREDIENT);
    makeNewIngredient.setActionCommand(MAKE_NEW_INGREDIENT);
    
    selectIngredient.addActionListener(addListener);
    detailField.addActionListener(addListener);
    amountField.addActionListener(addListener);
    unitSelect.addActionListener(addListener);
    
    ingredients = new ArrayList<>();
    substitutes = new HashMap<>();
    
    substituteSelect = new JComboBox<>();
    ingredientDisplay = new JTextArea(0, 0);
    substituteDisplay = new JTextArea(0, 0);
    
    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel(Translator.translate("Ingredient") + ":"));
    inputFields.add(selectIngredient);
    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);
    inputFields.add(new JLabel(Translator.translate("Amount") + ":"));
    inputFields.add(amountField);
    inputFields.add(makeNewIngredient);
    inputFields.add(addButton);
    
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
    String substitute = substituteSelect.getSelectedItem().toString();
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
    substituteSelect.setSelectedIndex(0);
//    updateSubstituteSelect();
    amountField.setText("");
    
//    updateTextArea();
//    updateSubstituteArea();
    
  }
  
  private void delete()
  {
    
    if (ingredients.size() == 0)
    {
      return;
    }
    
    
    
  }
  
  
  private class IngredientEditorListener implements ActionListener
  {
    
    private final IngredientEditor2 subject;

    IngredientEditorListener(final IngredientEditor2 subject)
    {
      this.subject = subject;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      
      if (e.getActionCommand().equals(SELECT_INGREDIENT))
      {
        
      }
      else if (e.getActionCommand().equals(MAKE_NEW_INGREDIENT))
      {
        
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
  
}
