package gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    
    /**
     * UNFINISHED
     */
    
    JFrame frame = new JFrame("KiLowBites Shopping List Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel contentPane = (JPanel) frame.getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    contentPane.add(new ShoppingListPanel(recipe));
    
    frame.setSize(600, 400);
    frame.setVisible(true);

  }
  
}
