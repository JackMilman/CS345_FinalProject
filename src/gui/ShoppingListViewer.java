package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
  
  private JPanel contentPane;
  private JPanel inputNumPeople;
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
    setSize(600, 400);
    
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    inputNumPeople = new JPanel();
    inputNumPeople.add(new JLabel(Translator.translate("Number of People") + ":"));
    numPeopleField = new JTextField();
    numPeopleField.setColumns(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    numPeopleField.addActionListener(new ShoppingListListener());
    inputNumPeople.add(numPeopleField);
    
    scrollArea = new JTextArea();
    updateScrollArea(obj, numPeopleField.getText());
    
  }
  
  private void updateScrollArea(final Object obj, final String info)
  {
    
    scrollArea.setText(null);
    allIngredients.clear();
    
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
    
    // display ingredients as a ShoppingListIngredient with a dropdown menu to change units
    
    
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
