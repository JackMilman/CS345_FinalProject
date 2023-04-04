package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import recipes.NutritionInfo;

public class UnitConversionWindow extends JDialog
{

  static final int DEFAULT_TEXT_FIELD_WIDTH = 10;
  private String[] units = {"", "DRAM", "OUNCE", "GRAM", "POUND", "PINCH", "TEASPOON", "TABLESPOON",
      "FLUID_OUNCE", "CUP", "PINT", "QUART", "GALLON"};
  static final String RESET = "reset";
  static final String CALCULATION_COMMAND= "calc";
  private JComboBox<String> fromunitBox = new JComboBox<String>();
  private JComboBox<String> tounitBox = new JComboBox<String>();
  private JTextField amount;
  private JPanel value;
  private static final long serialVersionUID = 1L;

  public UnitConversionWindow(final Window main)
  {
    Container unitConverter = new Container();
    unitConverter.setLayout(new FlowLayout());

    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    icons.add(new KitchIntelButton(KitchIntelButton.CALCULATE_IMAGE));
    icons.add(new KitchIntelButton(KitchIntelButton.RESET_IMAGE));
    getContentPane().add(icons, BorderLayout.NORTH);

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

    unitConverter.add(unitMenu, BorderLayout.CENTER);
    // Ingredients Drop Down
    NutritionInfo[] ingredients = NutritionInfo.values();
    JLabel ingredientLabel = new JLabel("Ingredient:");
    JComboBox<String> ingredientBox = new JComboBox<String>();
    ingredientBox.addItem("");
    for (int i = 0; i < ingredients.length; i++)
    {
      ingredientBox.addItem(ingredients[i].getName());
    }

    unitMenu.add(ingredientLabel);
    unitMenu.add(ingredientBox);
    JLabel fromAmount = new JLabel("From Amount:");
    amount = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);

    unitMenu.add(fromAmount);
    unitMenu.add(amount);
    getContentPane().add(unitConverter);
    setVisible(true);
    setResizable(true);
    pack();
  }

  private class UnitConversionListener implements ActionListener
  {
    private final UnitConversionWindow subject;

    public UnitConversionListener(UnitConversionWindow subject)
    {
      super();
      this.subject = subject;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      String command = e.getActionCommand();
      if (command.equals(CALCULATION_COMMAND)) {
        
      }
      
      // logic for calculating the unit conversion
    }
  }

  public static void main(final String[] args)
  {
    MainWindow main = new MainWindow();
    main.run();

    new UnitConversionWindow(main);
  }
}
