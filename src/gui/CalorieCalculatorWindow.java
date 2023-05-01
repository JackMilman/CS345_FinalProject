package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import branding.KitchIntelIconButton;
import branding.KitchIntelJFrame;
import config.Translator;
import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Unit;

/**
 * This class is a GUI for calculating calories. The user is able to select and ingredient and unit
 * and enter the amount. This will then be used to calculate the number of calories. It uses the
 * singleton design pattern so only one window can be opened at a time.
 * 
 * @author Allie O'Keeffe
 *
 */
public class CalorieCalculatorWindow extends KitchIntelJFrame
{

  private static final long serialVersionUID = 1L;
  // private String selectedIngredient, selectedUnits, enteredAmount;
  private JLabel calorie;
  private JComboBox<String> ingredients, units;
  private JTextField amount;
  private static final String CALCULATION_COMMAND = "calc";
  private static final String RESET = "reset";
  private static CalorieCalculatorWindow calorieWindow;

  private CalorieCalculatorWindow(final Window main)
  {
    super(Translator.translate("KiLowBites Calorie Calculator"));
    setUp();
    setDefaultCloseOperation(HIDE_ON_CLOSE);
  }

  public static CalorieCalculatorWindow getCalorieCalculatorWindow()
  {
    if (calorieWindow == null)
    {
      calorieWindow = new CalorieCalculatorWindow(null);
    }

    calorieWindow.setVisible(true);

    return calorieWindow;
  }

  private Container createIcons()
  {
    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    JButton calcButton = new KitchIntelIconButton(KitchIntelIconButton.CALCULATE_IMAGE);
    JButton resetButton = new KitchIntelIconButton(KitchIntelIconButton.RESET_IMAGE);

    calcButton.setActionCommand(CALCULATION_COMMAND);
    resetButton.setActionCommand(RESET);
    Calories calcListener = new Calories();
    Calories resetListener = new Calories();
    calcButton.addActionListener(calcListener);
    resetButton.addActionListener(resetListener);
    icons.add(calcButton);
    icons.add(resetButton);

    return icons;
  }

  private JPanel inputMenu()
  {
    JPanel inputs = new JPanel();
    inputs.setOpaque(false);
    inputs.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputs.add(createLabels(Translator.translate("Ingredient")));
    inputs.add(ingredients);
    inputs.add(createLabels(Translator.translate("Amount")));
    inputs.add(amount);
    amount.setPreferredSize(new Dimension(100, 30));
    inputs.add(createLabels(Translator.translate("Units")));
    inputs.add(units);
    return inputs;
  }

  private JLabel createLabels(String name)
  {
    return new JLabel(String.format("%s:", name));
  }

  private String getSelectedIngredients()
  {
   String s = (String)ingredients.getSelectedItem();
   if (NutritionInfo.contains(s.toLowerCase())) {
     if (NutritionInfo.getCalPerGram(s) == 0.0)
     {
       return null;
     }
   }
   return s;
  }

  private String getSelectedUnits()
  {
    return (String) units.getSelectedItem();
  }

  private String getEnteredAmount()
  {
    return (String) amount.getText();
  }

  private JComboBox<String> setUpIngredients()
  {
    JComboBox<String> names = new JComboBox<>();
    names.addItem("");
    List<String> items = new ArrayList<String>(NutritionInfo.getIngredientsInMap());
    Collections.sort(items);
    for (String item : items)
    {
      names.addItem(item);
    }
    return names;
  }

  private void setUp()
  {

    Container icons = createIcons();
    Calories calories = new Calories();

    ingredients = setUpIngredients();
    ingredients.addActionListener(calories);

    amount = new JTextField();

    String[] unitNames = {"", "DRAM", "OUNCE", "GRAM", "POUND", "PINCH", "TEASPOON", "TABLESPOON",
        "FLUID OUNCE", "CUP", "PINT", "QUART", "GALLON"};
    units = new JComboBox<>(unitNames);
    units.addActionListener(calories);
    ingredients = setUpIngredients();
    ingredients.addActionListener(calories);

    amount = new JTextField();
    units = new JComboBox<>(unitNames);
    units.addActionListener(calories);

    Container c = getContentPane();
    c.setLayout(new BorderLayout());
    c.add(icons, BorderLayout.NORTH);
    JPanel inputs = inputMenu();
    c.add(inputs, BorderLayout.AFTER_LINE_ENDS);
    calorie = new JLabel(Translator.translate("Calories") + ":     ____________");
    c.add(calorie, BorderLayout.SOUTH);

    setResizable(false);
    setVisible(true);
    pack();
  }

  private class Calories extends JLabel implements ActionListener
  {

    private static final long serialVersionUID = 1L;

    public Calories()
    {
      super();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      String selectedIngredient = getSelectedIngredients();
      Unit selectedUnits = Unit.parseUnit(getSelectedUnits());
      String enteredText = getEnteredAmount();

      String command = e.getActionCommand();

      if (command.equals(RESET))
      {
        ingredients.setSelectedItem("");
        units.setSelectedItem("");
        amount.setText("");
        calorie.setText(Translator.translate("Calories") + ":     ____________");
      }

      if (command.equals(CALCULATION_COMMAND))
      {
        try
        {
          if (selectedIngredient == null) {
            calorie.setText(Translator.translate("Calories") + ": "
                + "Unsupported Ingredient");
          } else {
            double amountOfIngredients = Double.parseDouble(enteredText);
            Ingredient temp = new Ingredient(selectedIngredient, "", amountOfIngredients,
                selectedUnits);
            calorie.setText(Translator.translate("Calories") + ": "
                + (Math.round(temp.getCaloriesPerGram() * 10) / 10.0));
          }
        }
        catch (NumberFormatException exc)
        {
          calorie.setText(Translator.translate("Invalid Amount"));
        }
      }
    }

  }
}
