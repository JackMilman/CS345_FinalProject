package gui;

import java.awt.*;
import javax.swing.*;

import recipes.NutritionInfo;

public class UnitConversionWindow extends JDialog
{
  private String[] units = {"", "DRAM", "OUNCE", "GRAM", "POUND", "PINCH", "TEASPOON", "TABLESPOON",
      "FLUID_OUNCE", "CUP", "PINT", "QUART", "GALLON"};
  private JComboBox<String> fromunitBox = new JComboBox<String>();
  private JComboBox<String> tounitBox = new JComboBox<String>();
  private JTextField amount;
  private static final long serialVersionUID = 1L;

  public UnitConversionWindow(final Window main)
  {
    Container unitConverter = new Container();
    unitConverter.setLayout(new BorderLayout());

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
    Container unitMenuUpper = new Container();
    unitMenuUpper.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    JLabel fromunitLabel = new JLabel("From Units:");
    unitMenuUpper.add(fromunitLabel);
    unitMenuUpper.add(fromunitBox);
    
    JLabel tounitLabel = new JLabel("To Units:");
    unitMenuUpper.add(tounitLabel);
    unitMenuUpper.add(tounitBox);
    
    unitConverter.add(unitMenuUpper, BorderLayout.CENTER);
    //Ingredients Drop Down
    NutritionInfo[] ingredients = NutritionInfo.values();
    JLabel ingredientLabel = new JLabel("Ingredient:");
    JComboBox<String> ingredientBox = new JComboBox<String>();
    ingredientBox.addItem("");
    for (int i = 0; i < ingredients.length; i++) {
      ingredientBox.addItem(ingredients[i].getName());
    }
    unitMenuUpper.add(ingredientLabel);
    unitMenuUpper.add(ingredientBox);
    
    Container unitMenuLower = new Container();
    unitMenuLower.setLayout(new FlowLayout(FlowLayout.LEFT));
    unitMenuLower.add(new JLabel("Amount:"));
    unitMenuLower.add(amount);
    
    getContentPane().add(unitConverter);
    setVisible(true);
    setResizable(true);
    pack();
  }

  public static void main(final String[] args)
  {
    MainWindow main = new MainWindow();
    main.run();

    new UnitConversionWindow(main);
  }
}
