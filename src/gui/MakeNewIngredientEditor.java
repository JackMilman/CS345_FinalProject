package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import branding.KitchIntelJDialog;
import config.Translator;
import recipes.NutritionInfo;

/**
 * GUI for making a new ingredient and adding it to NutritionInfo.
 * 
 * @author Meara Patterson, KitchIntel
 * @version 4/25/2023
 *
 */
public class MakeNewIngredientEditor extends KitchIntelJDialog
{

  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound", "Pinch",
      "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Individual"};
  
  private JTextField nameField;
  private JTextField priceField;
  private JTextField calorieField;
  private JTextField densityField;
  private JButton addButton;

  public MakeNewIngredientEditor()
  {
    
    super(Translator.translate("Make New Ingredient"));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.LEFT));
    
    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    priceField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    calorieField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    densityField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    addButton = new JButton("Make New Ingredient");
    
    addButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent e)
      {
        try
        {
          NutritionInfo.addIngredient(nameField.getText().toLowerCase(),
              Double.parseDouble(priceField.getText()),
              Double.parseDouble(calorieField.getText()), 
              Double.parseDouble(densityField.getText()));
        }
        catch (NumberFormatException nfe)
        {
          return;
        }
      }
    });
    
  }
  
}
