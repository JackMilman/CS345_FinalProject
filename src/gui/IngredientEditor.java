package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import recipes.Ingredient;
import utilities.SortLists;

/**
 * A class for the ingredient editor component of the meal editor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class IngredientEditor extends JComponent
{
  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound",
      "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon",
      "Individual"};
  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private JTextField nameField;
  private JTextField detailField;
  private JTextField amountField;
  private TextArea ingredientDisplay;
  private final JComboBox<String> unitSelect;

  private List<Ingredient> ingredients;

  /**
   * 
   */
  public IngredientEditor()
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder("Ingredients"));
    
    IngredientEditorListener listener = new IngredientEditorListener(this);
    
    JButton addButton = new JButton(ADD);
    JButton deleteButton = new JButton(DELETE);
    
    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    amountField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    
    unitSelect = new JComboBox<String>(UNITS);
    
    ingredients = new ArrayList<Ingredient>();
    
    ingredientDisplay = new TextArea();
    
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
    
    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel("Name:"));
    inputFields.add(nameField);
    inputFields.add(new JLabel("Details:"));
    inputFields.add(detailField);
    inputFields.add(new JLabel("Amount:"));
    inputFields.add(amountField);
    inputFields.add(new JLabel("Units:"));
    inputFields.add(unitSelect);
    inputFields.add(addButton);
    
    add(inputFields, BorderLayout.NORTH);
    
    add(deleteButton, BorderLayout.EAST);
    
    ingredientDisplay.setEditable(false);
    add(ingredientDisplay, BorderLayout.CENTER);
    
    setVisible(true);
  }
  
  private void add()
  {
    String name = nameField.getText();
    String details = detailField.getText();
    String unit = unitSelect.getSelectedItem().toString();
    int amount;
    
    try
    {
      amount = Integer.valueOf(amountField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }
    
    if(name.equals("") || details.equals("") || unit.equals("")) return;
        
    Ingredient ingredient = new Ingredient(name, details, amount, unit);
    ingredients.add(ingredient);
    
    SortLists.sortIngredients(ingredients);
    
    nameField.setText("");
    detailField.setText("");
    unitSelect.setSelectedIndex(0);
    amountField.setText("");
    
    updateTextArea();
  }
  
  private void delete()
  {
    if(ingredients.size() == 0) return;
    ingredients.remove(ingredients.size() - 1);
    
    updateTextArea();
  }
  
  private void updateTextArea()
  {
    String text = "";
    
    for(Ingredient ingredient : ingredients)
    {
      text += String.format("%s\t%s\t%s\t%s\n", ingredient.getName(), ingredient.getDetails(), 
          ingredient.getAmount(), ingredient.getUnit());
    }
    
    ingredientDisplay.setText(text);
  }
  
  List<Ingredient> getIngredients()
  {
    return ingredients;
  }
  
  private class IngredientEditorListener implements ActionListener
  {
    private final IngredientEditor subject;
    
    IngredientEditorListener (final IngredientEditor subject)
    {
      this.subject = subject;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if(e.getActionCommand().equals(ADD))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(DELETE))
      {
        subject.delete();
      }
      
    }
    
  }
}
