package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import branding.KitchIntelColor;
import branding.KitchIntelJDialog;
import config.Translator;
import recipes.Ingredient;
import recipes.Inventory;
import recipes.Meal;
import recipes.Recipe;
import recipes.Unit;
import utilities.UnitConversion;

/*
 * TO DO:
 * Display prices
 * Add up prices in meals
 */

/**
 * Creates the GUI to view a shopping list.
 * 
 * @author Meara Patterson
 * @version 3/29/2023
 */
public class ShoppingListViewer extends KitchIntelJDialog
{

  private static final int DO_NOT_DISPLAY = -1;
  private static final long serialVersionUID = 1L;
  // Unit Conversions is currently broken so this is a workaround
  private static final Unit[] MASSES = {Unit.DRAM, Unit.OUNCE, Unit.GRAM, 
      Unit.POUND};
  private static final Unit[] VOLUMES = {Unit.PINCH, Unit.MILLILITER, 
      Unit.TEASPOON, Unit.TABLESPOON, Unit.FLUID_OUNCE, Unit.CUP, Unit.PINT, 
      Unit.QUART, Unit.GALLON};

  private Object obj;
  private JPanel contentPane;
  private JPanel inputNumPeoplePanel;
  private JTextField numPeopleField;
  private int numPeople;
  private JScrollPane scrollPane;
  private JPanel scrollArea;
  private ArrayList<Ingredient> allIngredients;
  private ArrayList<Ingredient> editedIngredients;

  /**
   * Creates a ShoppingListViewer panel that displays the ingredients of the given recipe.
   * 
   * @param obj should be a Recipe or Meal
   */
  public ShoppingListViewer(final Object obj)
  {

    super(Translator.translate("KiLowBites Shopping List Viewer") + "\t" + getName(obj));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    numPeopleField.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e)
      {
        try
        {
          numPeople = Integer.parseInt(numPeopleField.getText());
          if (numPeople == 0)
          {
            numPeople = DO_NOT_DISPLAY;
          }
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
    inputNumPeoplePanel.setOpaque(false); // allows the panel to take the background color
    contentPane.add(inputNumPeoplePanel);

    // create a scroll area with the ingredients
    updateScrollArea(numPeopleField.getText());

    setVisible(true);

  }

  private void updateScrollArea(final String info)
  {

    if (!allIngredients.isEmpty())
    {
      allIngredients.clear();
    }
    if (!editedIngredients.isEmpty())
    {
      editedIngredients.clear();
    }

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
      }
      for (Recipe recipe : meal.getRecipes())
      {
        editIngredientList(recipe);
      }
      // amounts get duplicated for some reason
      for (Ingredient ing : editedIngredients)
      {
        int index = editedIngredients.indexOf(ing);
        Ingredient newIng = new Ingredient(ing.getName(), ing.getDetails(), ing.getAmount() / 2,
            ing.getUnit(), ing.getCalories(), ing.getDensity(), ing.getPrice());
        editedIngredients.set(index, newIng);
      }
    }
    
    updateScrollAreaHelper();

  }
  
  private void updateScrollAreaHelper()
  {
    if (scrollPane != null)
    {
      contentPane.add(scrollPane); 
    }
    contentPane.setSize(getPreferredSize());
    if (scrollPane != null)
    {
      contentPane.remove(scrollPane);
    }
      
    if (numPeople != DO_NOT_DISPLAY)
    {

      scrollArea = new JPanel();

      for (Ingredient ing : editedIngredients)
      {
        scrollArea.add(new ShoppingListIngredient(ing));
      }

      scrollArea.setLayout(new BoxLayout(scrollArea, BoxLayout.Y_AXIS));
      scrollPane = new JScrollPane(scrollArea);
      scrollPane.createVerticalScrollBar();
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setPreferredSize(new Dimension(600, 200));
      
      contentPane.add(scrollPane);

    }
    contentPane.revalidate();
    contentPane.repaint();
    
  }

  private void addToAllIngredients(final Recipe recipe)
  {
    double numBatches = (double) numPeople / (double) recipe.getServings();
    for (Ingredient ing : recipe.getIngredients())
    {
      allIngredients.add(new Ingredient(ing.getName(), ing.getDetails(), 
          ing.getAmount() * numBatches, ing.getUnit(), ing.getCalories(), 
          ing.getDensity(), ing.getPrice()));
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


      for (Ingredient ing : allIngredients)
      {
        if (!editedIngredients.contains(ing))
        {
          Ingredient newIng = new Ingredient(ing.getName(), ing.getDetails(),
              ing.getAmount(), ing.getUnit(), ing.getCalories(), 
              ing.getDensity(), ing.getPrice());
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
              duplicate = editedIng;
              double newAmount = UnitConversion.convert(ing.getName(), ing.getUnit(),
                  duplicate.getUnit(), ing.getAmount()) + duplicate.getAmount();
              Ingredient addIng = new Ingredient(ing.getName(), ing.getDetails(),
                  newAmount, duplicate.getUnit(), ing.getCalories(), 
                  ing.getDensity(), ing.getPrice());
              int index = editedIngredients.indexOf(duplicate);
              editedIngredients.set(index, addIng);
            }
          }
        }
      }
    }
    
    // sort alphabetically
    Collections.sort(editedIngredients);
    
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
      name = ((Recipe) obj).getName();
    }
    else if (obj instanceof Meal)
    {
      name = ((Meal) obj).getName();
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

    private static final long serialVersionUID = 1L;
    
    JLabel label;
    JComboBox<String> units;
    JCheckBox checkBox;

    ShoppingListIngredient(final Ingredient ingredient)
    {

      super();
      setSize(600, 50);
      label = new JLabel(ingredient.toString());
      setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());

      units = new JComboBox<>();
//      for (Unit unit : Unit.values())
//      {
//        units.addItem(unit.getName());
//      }
      if (Arrays.asList(MASSES).contains(ingredient.getUnit()))
      {
        for (Unit unit : MASSES)
        {
          units.addItem(unit.getName());
        }
      } 
      else if (Arrays.asList(VOLUMES).contains(ingredient.getUnit()))
      {
        for (Unit unit : VOLUMES)
        {
          units.addItem(unit.getName());
        }
      }
      else
      {
        units.addItem(ingredient.getUnit().getName());
      }
      units.setSelectedItem(ingredient.getUnit().getName());
      units.addActionListener(new ActionListener()
      {
        public void actionPerformed(final ActionEvent e)
        {
          int index = editedIngredients.indexOf(ingredient);
          Unit newUnit = Unit.parseUnit((String)units.getSelectedItem());
          Ingredient newIng = new Ingredient(ingredient.getName(), ingredient.getDetails(),
              UnitConversion.convert(ingredient.getName(), ingredient.getUnit(), 
                  newUnit, ingredient.getAmount()), newUnit, ingredient.getCalories(), 
              ingredient.getDensity(), ingredient.getPrice());
          editedIngredients.set(index, newIng);
          updateScrollAreaHelper();
          label = new JLabel(newIng.toString());
          updateShoppingListIngredient();
        }
      });

      checkBox = new JCheckBox("Purchased?");
      checkBox.setOpaque(false); // required to make the background color correct
      checkBox.addActionListener(new ActionListener()
      {
        public void actionPerformed(final ActionEvent e)
        {
          Inventory inventory = Inventory.createInstance();
          if (checkBox.isSelected())
          {
            inventory.addIngredient(ingredient);
          }
          else
          {
            inventory.reduceIngredient(ingredient);
          }
        }
      });

      updateShoppingListIngredient();

    }

    private void updateShoppingListIngredient()
    {
      removeAll();
      add(label);
      add(units);
      add(checkBox);
    }

  }
  
}
