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
    int numPeople = 0;
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
    public void actionPerformed(ActionEvent e)
    {
      String command = e.getActionCommand();
    }
    
  }
  
}
