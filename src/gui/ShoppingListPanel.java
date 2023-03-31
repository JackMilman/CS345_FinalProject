package gui;

import java.awt.Dimension;
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
public class ShoppingListPanel extends JPanel /* implements ActionListener */
{
  
  private int numberOfPeople;
  
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
    
    JScrollPane scroll = createScrollPane(recipe);
    
    // A text field so the user can input the number of people
    JPanel numPeople = new JPanel();
    numPeople.add(new JLabel("Number of People:"));
    JTextField textField = new JTextField();
    textField.setPreferredSize(new Dimension(50, 20));
    textField.addActionListener(new ActionListener() 
    {
      public void actionPerformed(final ActionEvent event) 
      {
        try 
        {
          numberOfPeople = Integer.parseInt(textField.getText());
        } 
        catch (IllegalArgumentException e)
        {
          numberOfPeople = 0;
        }
      }
    });
    numPeople.add(textField);
    add(numPeople);
    
    add(scroll);
    
  }
  
  private JScrollPane createScrollPane(Recipe recipe)
  {
    // A scrollable, alphabetized list of ingredients in a recipe with prices
    List<Ingredient> ingredients = recipe.getIngredients();
    
    // text area with [number of ingredients] rows
    JTextArea messageArea = new JTextArea(ingredients.size(), 1);
    for (Ingredient ing : ingredients)
    {
      String info = String.format("%s\t%f\n", 
          ing.getName(), ing.getAmount() * numberOfPeople);
      messageArea.append(info);
    }
    
    JScrollPane scroll = new JScrollPane(messageArea);
    scroll.createVerticalScrollBar();
    return scroll;
  }
  
//  @Override
//  public void actionPerformed(final ActionEvent event)
//  {
//    try 
//    {
//      numberOfPeople = Integer.parseInt(textField.getText());
//    } 
//    catch (IllegalArgumentException e)
//    {
//      numberOfPeople = 0;
//    }
//  }

}
