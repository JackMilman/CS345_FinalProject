package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import recipes.Ingredient;
import recipes.Recipe;

/**
 * Creates the GUI to view a shopping list.
 * 
 * @author Meara Patterson
 * @version 3/29/2023, Version 1
 */
public class ShoppingListViewer
{
  
  private JTextArea messageArea;

  /**
   * Creates a ShoppingListViewer panel that displays the ingredients of the given recipe.
   * @param recipe
   */
  public ShoppingListViewer(final Recipe recipe)
  {
    
    JFrame frame = new JFrame("KiLowBites Shopping List Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);
    
    JPanel contentPane = (JPanel) frame.getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    JPanel inputNumPeople = new JPanel();
    inputNumPeople.add(new JLabel("Number of People:"));
    
    JTextField textField = new JTextField();
    textField.setPreferredSize(new Dimension(50, 20));
    textField.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent event)
      {
        try
        {
          updateMessageArea(recipe, textField.getText());
        }
        catch (NumberFormatException e)
        {
          updateMessageArea(recipe, "0");
        }
      }
    });
    inputNumPeople.add(textField);
    
    messageArea = new JTextArea();
    updateMessageArea(recipe, textField.getText());
    JScrollPane scrollPane = new JScrollPane(messageArea);
    scrollPane.createVerticalScrollBar();
    
    contentPane.add(inputNumPeople);
    contentPane.add(scrollPane);
    
    frame.setVisible(true);

  }
  
  /**
   * Updates the ingredient list of the scroll pane.
   * 
   * @param recipe gives the list of ingredients
   * @param text to be parsed for a number
   */
  public void updateMessageArea(final Recipe recipe, final String text)
  {
    int numPeople = 0;
    try
    {
      numPeople = Integer.parseInt(text);
    }
    catch (NumberFormatException e)
    {
      numPeople = 0;
    }
    messageArea.setText(null);
    for (Ingredient ing : recipe.getIngredients())
    {
      String info = String.format("%s\t%f\n", ing.getName(), ing.getAmount() * numPeople);
      messageArea.append(info);
    }
  }
  
}
