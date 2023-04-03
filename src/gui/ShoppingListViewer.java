package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import recipes.Recipe;

/**
 * Creates the GUI to view a shopping list.
 * 
 * @author Meara Patterson
 * @version 3/29/2023, Version 1
 */
public class ShoppingListViewer
{

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
    
    ShoppingListScrollPane scrollPane = new ShoppingListScrollPane(recipe, 0);
    
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
          int numPeople = Integer.parseInt(textField.getText());
          scrollPane.updateScrollPane(numPeople);
        } catch (NumberFormatException e)
        {
          int numPeople = 0;
          scrollPane.updateScrollPane(0);
        }
      }
    });
    inputNumPeople.add(textField);
    
    contentPane.add(inputNumPeople);
    contentPane.add(scrollPane);
    
    frame.setVisible(true);

  }
  
}
