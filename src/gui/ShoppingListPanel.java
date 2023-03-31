package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import recipes.Ingredient;
import recipes.Recipe;

/**
 * The main panel of the ShoppingListViewer.
 * 
 * @author Meara Patterson
 * @version 3/31/2023, Version 1
 *
 */
public class ShoppingListPanel extends JPanel
{
  
  private String numberOfPeople;
  
  /**
   * Creates a panel with a text field to input the number of people a recipe should serve
   * and a scrollable list of the ingredients in the given recipe, adjusted for the 
   * number of people.
   * 
   * @param recipe the recipe to use for information
   */
  public ShoppingListPanel(final Recipe recipe)
  {
    
    /**
     * UNFINISHED
     */
    
    super();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    // A text field so the user can input the number of people
    JPanel numPeople = new JPanel();
    numPeople.add(new JLabel("Number of People:"));
    JTextField textField = new JTextField("\t\t");
    textField.addActionListener(new ActionListener() 
    {
      public void actionPerformed(final ActionEvent event) 
      {
        numberOfPeople = textField.getText();
      }
    });
    numPeople.add(textField);
    add(numPeople);
    
    // A scrollable, alphabetized list of ingredients in a recipe with prices
    List<Ingredient> ingredients = recipe.getIngredients();
    
    // text area with [number of ingredients] rows
    JTextArea messageArea = new JTextArea(ingredients.size(), 1);
    for (Ingredient ing : ingredients)
    {
      messageArea.append(ing.getName() + "\n");
    }
    
    JScrollPane scroll = new JScrollPane(messageArea);
    scroll.createVerticalScrollBar();
    add(scroll);
    
  }

}
