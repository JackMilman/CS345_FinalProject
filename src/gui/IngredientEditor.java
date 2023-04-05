package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
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
  private JButton addButton, deleteButton;
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
    
    addButton = new JButton(ADD);
    deleteButton = new JButton(DELETE);
        
    addButton.setActionCommand(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND);
    deleteButton.setActionCommand(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND);
    
    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    amountField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    
    unitSelect = new JComboBox<String>(UNITS);
    
    ingredients = new ArrayList<Ingredient>();
    
    ingredientDisplay = new TextArea(0, 0);
    
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
    double amount;
    
    try
    {
      amount = Double.valueOf(amountField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }
    
    if(name.equals("") || unit.equals("")) return;
        
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
    
    int selectionStart = ingredientDisplay.getSelectionStart();
    int linesSelected = 0;
    int linesSkipped = 0;
    String selectedText = ingredientDisplay.getSelectedText();
    
    if(selectedText == null || selectedText.length() < 0) return;
    
    char[] characters = selectedText.toCharArray();
    
    //counts the number of newline characters to determine the number of lines selected
    for(char character : characters)
    {
      if(character == '\n')
      {
        linesSelected++;
      }
    }
    
    //if the last selected character isn't a newline character, then there is one uncounted line.
    if(characters[characters.length - 1] != '\n') linesSelected++;
    
    String skipped = ingredientDisplay.getText().substring(0, selectionStart);
    
    char[] skippedChars = skipped.toCharArray();
    
    for(char skippedChar : skippedChars)
    {
      if(skippedChar == '\n') linesSkipped++;
    }
        
    for(int i = 0; i < linesSelected; i++)
    {
      ingredients.remove(linesSkipped);
    }
    
    updateTextArea();
  }
  
  private void updateTextArea()
  {
    String text = "";
    
    for(Ingredient ingredient : ingredients)
    {
      text += String.format("%s\n", ingredient.toString());
    }
    
    ingredientDisplay.setText(text);
  }
  
  List<Ingredient> getIngredients()
  {
    return ingredients;
  }
  
  /**
   * Adds a text listener to the text area of the ingredient editor.
   * 
   * @param listener the text listener to add to the display text area.
   */
  public void addTextListener(final TextListener listener)
  {
    ingredientDisplay.addTextListener(listener);
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
      if(e.getActionCommand().equals(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND))
      {
        subject.delete();
      }
      
    }
    
  }

  /**
   * Adds an action listener to the buttons in this IngredientEditor which can cause the
   * document to change.
   * @param listener The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }
  
  void loadIngredients(final List<Ingredient> newIngredients)
  {
    this.ingredients.clear();
    
    for(Ingredient ingredient : newIngredients)
    {
      ingredients.add(ingredient);
    }
    
    updateTextArea();
  }
}
