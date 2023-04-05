package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import recipes.Ingredient;
import recipes.NutritionInfo;

public class CalorieCalculatorWindow extends JFrame
{

  private static final long serialVersionUID = 1L;
  // private String selectedIngredient, selectedUnits, enteredAmount;
  private JComboBox<String> ingredients, units;
  private JTextField amount;
  private JLabel calorie;
  private static final String CALCULATION_COMMAND = "calc";
  private static final String RESET = "reset";
  private static boolean windowOpen = false;

  public CalorieCalculatorWindow()
  {
    super("KiLowBites Calorie Calculator");
    setUp();
  }
  private Container createIcons()
  {
    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    JButton calcButton = new KitchIntelButton(KitchIntelButton.CALCULATE_IMAGE);
    JButton resetButton = new KitchIntelButton(KitchIntelButton.RESET_IMAGE);

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

  private JLabel createLabels(String name)
  {
    return new JLabel(String.format("%s:", name));
  }

  private String getSelectedIngredients()
  {
    return (String) ingredients.getSelectedItem();
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
    for (NutritionInfo info : NutritionInfo.values())
    {
      names.addItem(info.getName());
    }
    return names;
  }
  private JPanel inputMenu() {
    JPanel inputs = new JPanel();
    inputs.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputs.add(createLabels("Ingredient"));
    inputs.add(ingredients);
    inputs.add(createLabels("Amount"));
    inputs.add(amount);
    amount.setPreferredSize(new Dimension(100, 30));
    inputs.add(createLabels("Units"));
    inputs.add(units);
    return inputs;
  }

  private void setUp()
  {
    if (!windowOpen)
    {
      windowOpen = true;
      Container icons = createIcons();
      Calories calories = new Calories();
      ingredients = setUpIngredients();
      ingredients.addActionListener(calories);

      amount = new JTextField();

      String[] unitNames = {"", "Dram", "Ounce", "Gram", "Pound", "Pinch", "Teaspoon", "Tablespoon",
          "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon"};
      units = new JComboBox<>(unitNames);
      units.addActionListener(calories);

      
     
      

      Container c = getContentPane();
      c.setLayout(new BorderLayout());
      c.add(icons, BorderLayout.NORTH);
      JPanel inputs = inputMenu();
      c.add(inputs, BorderLayout.AFTER_LINE_ENDS);
      calorie = new JLabel("Calories:     ____________");
      c.add(calorie, BorderLayout.SOUTH);
      
      setResizable(false);
      setVisible(true);
      pack();
      addWindowListener(new WindowAdapter()
      {
        public void windowClosing(final WindowEvent e)
        {
          CalorieCalculatorWindow.windowOpen = false;
        }
      });
    }
  }

  private class Calories extends JLabel implements ActionListener
  {

    private static final long serialVersionUID = 1L;

    public Calories()
    {
      super();
      setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      String selectedIngredient = getSelectedIngredients();
      String selectedUnits = getSelectedUnits();
      String enteredText = getEnteredAmount();
      
      String command = e.getActionCommand();

      if (command.equals(RESET))
      {
        
      }

      if (command.equals(CALCULATION_COMMAND))
      {
        try
        {
          double amountOfIngredients = Double.parseDouble(enteredText);
          Ingredient temp = new Ingredient(selectedIngredient, "", amountOfIngredients,
              selectedUnits);
          calorie.setText("Calories: " + temp.getCaloriesPerGram());
        }
        catch (NumberFormatException exc)
        {
          calorie.setText("Invalid Amount");
        }
      }
    }

  }

}
