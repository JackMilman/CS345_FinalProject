package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import branding.KitchIntelJDialog;
import config.Translator;
import recipes.NutritionInfo;

/**
 * GUI for making a new ingredient and adding it to NutritionInfo.
 * 
 * @author Meara Patterson, KitchIntel
 * @version 4/25/2023
 */
public class MakeNewIngredientEditor extends KitchIntelJDialog
{

  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound", "Pinch",
      "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Individual"};
  private static final String DESC = "Make New Ingredient";
  
  private JTextField nameField;
  private JTextField priceField;
  private JTextField calorieField;
  private JTextField densityField;
  private JButton addButton;

  /**
   * Makes a dialog with fields to input an ingredient's name, price, calories, and density,
   * and adds it to NutritionInfo's map.
   */
  public MakeNewIngredientEditor()
  {
    
    super(Translator.translate(DESC));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.LEFT));
    setSize(new Dimension(800, 200));
    
    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    priceField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    calorieField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    densityField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    addButton = new JButton(DESC);
    
    EnableUpdater addListener = new EnableUpdater();
    nameField.addActionListener(addListener);
    priceField.addActionListener(addListener);
    calorieField.addActionListener(addListener);
    densityField.addActionListener(addListener);
    addButton.setEnabled(false);
    
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
          nameField.setText("");
          priceField.setText("");
          calorieField.setText("");
          densityField.setText("");
          addButton.setEnabled(false);
        }
        catch (NumberFormatException nfe)
        {
          return;
        }
      }
    });
    
    add(new JLabel("Name:"));
    add(nameField);
    add(new JLabel("Price per tablespoon: $"));
    add(priceField);
    add(new JLabel("Calories:"));
    add(calorieField);
    add(new JLabel("Density:"));
    add(densityField);
    add(addButton);
    
    setVisible(true);
    
  }
  
  private class EnableUpdater implements ActionListener
  {
    
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (!nameField.getText().equals("") && !priceField.getText().equals("")
          && !calorieField.getText().equals("") && !densityField.getText().equals(""))
      {
        addButton.setEnabled(true);
      }
      else
      {
        addButton.setEnabled(false);
      }
    }
  
  }
  
}
