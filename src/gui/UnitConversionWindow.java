package gui;

import java.awt.*;
import java.awt.event.*;
import utilities.UnitConversion;
import javax.swing.*;

import recipes.NutritionInfo;

public class UnitConversionWindow extends JDialog
{

  static final int DEFAULT_TEXT_FIELD_WIDTH = 8;
  private String[] units = {"", "DRAM", "OUNCE", "GRAM", "POUND", "PINCH", "TEASPOON", "TABLESPOON",
      "FLUID_OUNCE", "CUP", "PINT", "QUART", "GALLON", "MILLILITER"};

  static final String CALCULATION_COMMAND = "calc";
  static final String RESET = "reset";

  private JComboBox<String> fromunitBox = new JComboBox<String>();
  private JComboBox<String> tounitBox = new JComboBox<String>();
  private JComboBox<String> ingredientBox = new JComboBox<String>();
  private JTextField amount;
  private JLabel resultLabel;

  private String fromUnit;
  private String toUnit;
  private String ingredient;
  private int amountvalue;

  private static boolean windowOpen = false;

  private static final long serialVersionUID = 1L;

  public UnitConversionWindow(final Window main)
  {
    if (!windowOpen)
    {
      windowOpen = true;
      // icons
      Container icons = new Container();
      icons.setLayout(new FlowLayout(FlowLayout.LEFT));
      JButton calcButton = new KitchIntelButton(KitchIntelButton.CALCULATE_IMAGE);
      JButton resetButton = new KitchIntelButton(KitchIntelButton.RESET_IMAGE);

      calcButton.setActionCommand(CALCULATION_COMMAND);
      resetButton.setActionCommand(RESET);
      UnitConversionListener calcListener = new UnitConversionListener(this);
      UnitConversionListener resetListener = new UnitConversionListener(this);
      calcButton.addActionListener(calcListener);
      resetButton.addActionListener(resetListener);
      icons.add(calcButton);
      icons.add(resetButton);
      add(icons, BorderLayout.NORTH);

      // unit drop down boxes
      for (int i = 0; i < units.length; i++)
      {
        fromunitBox.addItem(units[i]);
        tounitBox.addItem(units[i]);
      }
      Container unitMenu = new Container();
      unitMenu.setLayout(new FlowLayout(FlowLayout.LEFT));

      JLabel fromunitLabel = new JLabel("From Units:");

      unitMenu.add(fromunitLabel);
      unitMenu.add(fromunitBox);

      JLabel tounitLabel = new JLabel("To Units:");
      unitMenu.add(tounitLabel);
      unitMenu.add(tounitBox);
      fromunitBox.addItemListener(new FromComboBoxHandler());
      tounitBox.addItemListener(new ToComboBoxHandler());

      // add(unitMenu, BorderLayout.NORTH);

      // Ingredients Drop Down
      NutritionInfo[] ingredients = NutritionInfo.values();
      JLabel ingredientLabel = new JLabel("Ingredient:");

      ingredientBox.addItem("");
      for (int i = 0; i < ingredients.length; i++)
      {
        ingredientBox.addItem(ingredients[i].getName());
      }
      unitMenu.add(ingredientLabel);
      unitMenu.add(ingredientBox);
      ingredientBox.addItemListener(new IngredientComboBoxHandler());

      add(unitMenu);
      // Textfield for amount
      JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      JLabel fromAmount = new JLabel("From Amount:");
      amount = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);

      inputPanel.add(fromAmount);
      inputPanel.add(amount);

      // Result
      resultLabel = new JLabel("To Amount:        ");
      inputPanel.add(resultLabel);
      add(inputPanel, BorderLayout.SOUTH);
      setVisible(true);
      setResizable(false);

      addWindowListener(new WindowAdapter()
      {
        public void windowClosing(final WindowEvent e)
        {
          UnitConversionWindow.windowOpen = false;
        }
      });
      pack();
    }
  }

  private class FromComboBoxHandler implements ItemListener
  {

    public void itemStateChanged(ItemEvent e)
    {
      fromUnit = (String) e.getItem();
    }
  }

  private class ToComboBoxHandler implements ItemListener
  {

    public void itemStateChanged(ItemEvent e)
    {
      toUnit = (String) e.getItem();
    }
  }

  private class IngredientComboBoxHandler implements ItemListener
  {

    public void itemStateChanged(ItemEvent e)
    {
      ingredient = (String) e.getItem();
    }
  }

  private class UnitConversionListener implements ActionListener
  {
    private final UnitConversionWindow subject;

    public UnitConversionListener(final UnitConversionWindow subject)
    {
      super();
      this.subject = subject;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      String command = e.getActionCommand();

      if (command.equals(RESET))
      {
        fromunitBox.setSelectedItem("");
        tounitBox.setSelectedItem("");
        amount.setText("");
        resultLabel.setText("Result:        ");
      }
      else if (command.equals(CALCULATION_COMMAND))
      {
        amountvalue = Integer.parseInt(amount.getText());
        double value = UnitConversion.convert(ingredient, fromUnit, toUnit, amountvalue);
        resultLabel.setText("To Amount:   " + Double.toString(value));
      }

    }
  }

  public static void main(final String[] args)
  {
    MainWindow main = new MainWindow();
    main.run();

    new UnitConversionWindow(main);
  }

}
