package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import branding.KitchIntelJFrame;
import config.Translator;
import recipes.Ingredient;
import recipes.Inventory;
import recipes.Meal;
import recipes.Recipe;
import utilities.UnitConversion;

/*
 * TO DO:
 * Fix calculations
 * Test with a meal
 * Possibly create a private listener class
 * Check acceptance criteria
 * Add javadocs
 */

/**
 * Creates the GUI to view a shopping list.
 * 
 * @author Meara Patterson
 * @version 3/29/2023
 */
public class ShoppingListViewer extends KitchIntelJFrame
{
  
//  private static final String CHANGE_UNITS = "change_units";
//  private static final String CHANGE_NUMBER_OF_PEOPLE = "number_of_people";
//  private static final String PURCHASED_INGREDIENT = "purchased_ingredient";
  private static final int DO_NOT_DISPLAY = -1;
  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound",
      "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon",
      "Individual"};
  
  private Object obj;
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
    this.obj = obj;
    allIngredients = new ArrayList<Ingredient>();
    editedIngredients = new ArrayList<Ingredient>();
    numPeople = DO_NOT_DISPLAY;
    
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    // create a panel to input the number of people served
    inputNumPeoplePanel = new JPanel();
    inputNumPeoplePanel.add(new JLabel(Translator.translate("Number of People") + ":"));
    numPeopleField = new JTextField();
    numPeopleField.setColumns(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
//    numPeopleField.setActionCommand(CHANGE_NUMBER_OF_PEOPLE);
    numPeopleField.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e)
      {
        try
        {
          numPeople = Integer.parseInt(numPeopleField.getText());
          updateScrollArea("" + numPeople);
        }
        catch (NumberFormatException nfe)
        {
          numPeople = DO_NOT_DISPLAY;
          updateScrollArea("" + numPeople);
        }
      }
    });
    inputNumPeoplePanel.add(numPeopleField);
    contentPane.add(inputNumPeoplePanel);
    
    // create a scroll area with the ingredients
    updateScrollArea(numPeopleField.getText());
    
    setVisible(true);
    
  }
  
  private void updateScrollArea(final String info)
  {
    
    if (scrollPane != null)
    {
      contentPane.remove(scrollPane);
    }

    if (!allIngredients.isEmpty())
    {
      allIngredients.clear();
    }
//    if (!editedIngredients.isEmpty())
//    {
//      editedIngredients.clear();
//    }

    if (obj instanceof Recipe)
    {
      Recipe recipe = (Recipe) obj;
      addToAllIngredients(recipe);
      editIngredientList(recipe);
    }
    else if (obj instanceof Meal)
    {
      Meal meal = (Meal) obj;
      for (Recipe recipe : meal.getRecipes())
      {
        addToAllIngredients(recipe);
        editIngredientList(recipe);
      }
    }

    if (numPeople != DO_NOT_DISPLAY)
    {
//      editIngredientList(recipe);
      // editedIngredients should have all ingredients added up
      // will probably need to change logic

      scrollArea = new JTextArea(editedIngredients.size(), 1);

      for (Ingredient ing : editedIngredients)
      {
        scrollArea.add(new ShoppingListIngredient(ing));
      }

      scrollArea.setLayout(new BoxLayout(scrollArea, BoxLayout.Y_AXIS));
      scrollPane = new JScrollPane(scrollArea);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      contentPane.add(scrollPane);

    }

    contentPane.revalidate();
    contentPane.repaint();
    
  }
  
  private void addToAllIngredients(final Recipe recipe)
  {
    for (Ingredient ing : recipe.getIngredients())
    {
      allIngredients.add(ing);
    }
  }
  
  private void editIngredientList(final Recipe recipe)
  {
    
    if (numPeople != DO_NOT_DISPLAY)
    {
    
      if (recipe.getServings() == 0)
      {
        numPeople = DO_NOT_DISPLAY;
        return;
      }
      
      double numBatches = (double) numPeople / (double) recipe.getServings();
      
      for (Ingredient ing : allIngredients)
      {
        if (!editedIngredients.contains(ing))
        {
          Ingredient newIng = new Ingredient(ing.getName(), ing.getDetails(), 
              ing.getAmount() * numBatches, ing.getUnit(), ing.getCalories(), ing.getDensity());
          editedIngredients.add(newIng);
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
              // add some kind of check for if an ingredient needs to be changed
              duplicate = editedIng;
              double newAmount = UnitConversion.convert(ing.getName(), ing.getUnit(), 
                  duplicate.getUnit(), ing.getAmount()) + duplicate.getAmount();
              Ingredient addIng = new Ingredient(ing.getName(), ing.getDetails(), 
                  newAmount * numBatches, duplicate.getUnit(), ing.getCalories(), ing.getDensity());
              int index = editedIngredients.indexOf(duplicate);
              editedIngredients.set(index, addIng);
            }
          }
        }
      }
    }
  }
  
  /**
   * Get the name of a Recipe or Meal.
   * 
   * @param obj should be a Recipe or Meal
   * @return name
   */
  public static String getName(final Object obj)
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
      System.exit(1); // not a good exit strategy
    }
    return name;
  }
  
  private class ShoppingListIngredient extends JPanel
  {
    
    JLabel label;
    JComboBox<String> units;
    JCheckBox checkBox;
//    Ingredient ingredient;
    
    ShoppingListIngredient(final Ingredient ingredient)
    {
      
      super();
      setSize(600, 50);
      label = new JLabel(ingredient.toString());
//      this.ingredient = ingredient;
      
      units = new JComboBox<>();
      for (String unit : UNITS)
      {
        units.addItem(unit);
      }
      units.setSelectedItem(ingredient.getUnit());
//      units.setActionCommand(CHANGE_UNITS);
//      units.addActionListener(new ShoppingListListener());
      units.addActionListener(new ActionListener() 
      {
        public void actionPerformed(final ActionEvent e)
        {
          int index = editedIngredients.indexOf(ingredient);
          String newUnit = (String) units.getSelectedItem();
          Ingredient newIng = new Ingredient(ingredient.getName(), ingredient.getDetails(), 
              ingredient.getAmount(), newUnit, ingredient.getCalories(), ingredient.getDensity());
          editedIngredients.set(index, newIng);
          updateScrollArea("" + numPeople);
          label = new JLabel(newIng.toString());
          updateShoppingListIngredient();
        }
      });
      
      checkBox = new JCheckBox("Purchased?");
//      checkBox.setActionCommand(PURCHASED_INGREDIENT);
      checkBox.addActionListener(new ActionListener()
      {
        public void actionPerformed(final ActionEvent e)
        {
          Inventory inventory = Inventory.createInstance();
          inventory.addIngredient(ingredient);
        }
      });
      
      updateShoppingListIngredient();
      
    }
    
    public void updateShoppingListIngredient()
    {
      removeAll();
      add(label);
      add(units);
      add(checkBox);
    }
    
//    public String toString()
//    {
//      return ingredient.getName() + " " + ingredient.getUnit();
//    }
    
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
