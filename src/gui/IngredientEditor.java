package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A class for the ingredient editor component of the meal editor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class IngredientEditor extends JComponent
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public IngredientEditor()
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder("Ingredients"));
    
    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel("Name:"));
    inputFields.add(new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH));
    inputFields.add(new JLabel("Details:"));
    inputFields.add(new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH));
    inputFields.add(new JLabel("Amount:"));
    inputFields.add(new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH));
    inputFields.add(new JLabel("Units:"));
    inputFields.add(new JComboBox<String>(new String[] {"", "Ounces", "Pounds"}));
    inputFields.add(new JButton("Add"));
    
    add(inputFields, BorderLayout.NORTH);
    
    add(new JButton("Delete"), BorderLayout.EAST);
    
    TextArea ingredientDisplay = new TextArea();
    ingredientDisplay.setEditable(false);
    add(ingredientDisplay, BorderLayout.CENTER);
    
    setVisible(true);
  }
}
