package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import config.Translator;
import recipes.Ingredient;
import recipes.Meal;
import recipes.Recipe;
import utilities.UnitConversion;

/**
 * Creates the GUI to view a shopping list.
 * 
 * @author Meara Patterson
 * @version 3/29/2023, Version 1
 */
public class ShoppingListViewer extends JFrame
{
  
  private static final String UPDATE_SCROLL_AREA = "update_scroll_area";
  private static final int NO_DISPLAY = -1;
  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound",
      "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon",
      "Individual"};
  
  private JPanel contentPane;
  private JPanel inputNumPeoplePanel;
  private JTextField numPeopleField;
  private int numPeople;
  private JScrollPane scrollPane;
  private JTextArea scrollArea;
  private ArrayList<Ingredient> allIngredients;
  private ArrayList<Ingredient> editedIngredients;

  /**
   * Creates a ShoppingListViewer panel that displays the ingredients of the given recipe.
   * @param obj should be a Recipe or Meal
   */
  public ShoppingListViewer(final Object obj)
  {
    
    super(Translator.translate("KiLowBites Shopping List Viewer") + "\t" + getName(obj));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    allIngredients = new ArrayList<Ingredient>();
    editedIngredients = new ArrayList<Ingredient>();
    
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    // create a panel to input the number of people served
    inputNumPeoplePanel = new JPanel();
    inputNumPeoplePanel.add(new JLabel(Translator.translate("Number of People") + ":"));
    numPeopleField = new JTextField();
    numPeopleField.setColumns(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    numPeopleField.addActionListener(new ShoppingListListener());
    inputNumPeoplePanel.add(numPeopleField);
    contentPane.add(inputNumPeoplePanel);
    
    // create a scroll area with the ingredients
    updateScrollArea(obj, numPeopleField.getText());
    scrollPane = new JScrollPane(scrollArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    contentPane.add(scrollPane);
    
    setVisible(true);
    
  }
  
  private void updateScrollArea(final Object obj, final String info)
  {
    
    if (scrollArea != null)
    {
      scrollArea.setText(null);
    }
    if (!allIngredients.isEmpty())
    {
      allIngredients.clear();
    }
    
    // get the number of people you are serving with a Recipe/Meal
    try
    {
      numPeople = Integer.parseInt(info);
    }
    catch (NumberFormatException nfe)
    {
      numPeople = NO_DISPLAY;
    }
    
    // collect all ingredients in an unedited ArrayList (may contain duplicates)
    if (obj instanceof Recipe)
    {
      Recipe recipe = (Recipe) obj;
      addToAllIngredients(recipe);
    }
    else if (obj instanceof Meal)
    {
      Meal meal = (Meal) obj;
      for (Recipe recipe : meal.getRecipes())
      {
        addToAllIngredients(recipe);
      }
    }
    
    // edit ingredients by adding up duplicates and changing their units
    editIngredientList();
    
    scrollArea = new JTextArea(editedIngredients.size(), 1);
    
    // display ingredients as a ShoppingListIngredient with a dropdown menu to change units
    if (obj instanceof Recipe)
    {
      Recipe recipe = (Recipe) obj;
      for (Ingredient ing : editedIngredients)
      {
        scrollArea.add(new ShoppingListIngredient(ing));
      }
    }
    else if (obj instanceof Meal)
    {
      Meal meal = (Meal) obj;
      for (Recipe recipe : meal.getRecipes())
      {
        for (Ingredient ing : editedIngredients)
        {
          scrollArea.add(new ShoppingListIngredient(ing));
        }
      }
    }
    
    scrollArea.setLayout(new BoxLayout(scrollArea, BoxLayout.Y_AXIS));
    
  }
  
  private void addToAllIngredients(final Recipe recipe)
  {
    for (Ingredient ing : recipe.getIngredients())
    {
      allIngredients.add(ing);
    }
  }
  
  private void editIngredientList()
  {
    for (Ingredient ing : allIngredients)
    {
      if (!editedIngredients.contains(ing))
      {
        editedIngredients.add(ing);
      }
      else
      {
        // change duplicate ingredient's units to units of ingredient in list
        // add the two together in editedIngredients list
        Ingredient duplicate = null;
        for (Ingredient editedIng : editedIngredients)
        {
          if (editedIng.equals(ing))
          {
            duplicate = editedIng;
          }
        }
        double newAmount = UnitConversion.convert(ing.getName(), ing.getUnit(), 
            duplicate.getUnit(), ing.getAmount()) + duplicate.getAmount();
        Ingredient addIng = new Ingredient(ing.getName(), ing.getDetails(), newAmount, 
            duplicate.getUnit(), ing.getCalories(), ing.getDensity());
        int index = editedIngredients.indexOf(duplicate);
        editedIngredients.set(index, addIng);
      }
    }
  }
  
  private static String getName(final Object obj)
  {
    String name = "";
    if (obj instanceof Recipe)
    {
      name = ((Recipe)obj).getName();
    }
    else if (obj instanceof Meal)
    {
      name = ((Meal)obj).getName();
    }
    else
    {
      System.out.println(Translator.translate("Invalid file"));
      System.exit(1);
    }
    return name;
  }
  
  private class ShoppingListListener implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      String command = e.getActionCommand();
    }
    
  }
  
  private class ShoppingListIngredient extends JPanel
  {
    ShoppingListIngredient(final Ingredient ingredient)
    {
      super();
      setSize(600, 50);
      add(new JLabel(ingredient.toString()));
      JComboBox<String> units = new JComboBox<>();
      for (String unit : UNITS)
      {
        units.addItem(unit);
      }
      units.addActionListener(new ShoppingListListener());
      add(units);
    }
  }
  
//must account for the fact that recipes are designed to serves multiple people
  //e.g. if a recipe of two servings is used to feed five people, each ingredient must be 
  //multiplied by 2.5
//  double numberOfBatches = (double) numPeople / (double) recipe.getServings();
//  for (Ingredient ing : recipe.getIngredients())
//  {
//    String info = String.format("%.1f %ss of %s\n", ing.getAmount() * numberOfBatches, 
//        ing.getUnit(), ing.getName());
//    messageArea.append(info);
  
}
