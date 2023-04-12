package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import config.Translator;
import recipes.Ingredient;
import recipes.Meal;
import recipes.Recipe;

/**
 * Creates the GUI to view a shopping list.
 * 
 * @author Meara Patterson
 * @version 3/29/2023, Version 1
 */
public class ShoppingListViewer extends JDialog
{
  
  private static final long serialVersionUID = 1L;
  private JTextArea messageArea;

  /**
   * Creates a ShoppingListViewer panel that displays the ingredients of the given recipe.
   * @param obj should be a Recipe or Meal
   */
  public ShoppingListViewer(final Object obj)
  {
    String name = "";
    if (obj instanceof Recipe)
    {
      name = ((Recipe)obj).getName();
    }
    else if (obj instanceof Meal)
    {
      name = ((Meal)obj).getName();
    } 
    else
    {
      System.out.println(Translator.translate("Invalid file"));
      System.exit(1);
    }
    JFrame frame = new JFrame(Translator.translate("KiLowBites Shopping List Viewer") 
        + "\t" + name);
    frame.setSize(600, 400);
    
    JPanel contentPane = (JPanel) frame.getContentPane();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    KitchIntelButton button = new KitchIntelButton(KitchIntelButton.PRINT_IMAGE);
    button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent event)
      {
        PrinterJob print = PrinterJob.getPrinterJob();
        if(print.printDialog())
        {
          try
          {
            print.print();
          } 
          catch (PrinterException e)
          {
            System.out.println(Translator.translate("Printer Error"));
          }
        }
      }
    });
    
    JPanel inputNumPeople = new JPanel();
    inputNumPeople.add(new JLabel(Translator.translate("Number of People") + ":"));
    
    JTextField textField = new JTextField();
    textField.setPreferredSize(new Dimension(50, 20));
    textField.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent event)
      {
        try
        {
          updateMessageArea(obj, textField.getText());
        }
        catch (NumberFormatException e)
        {
          updateMessageArea(obj, "0");
        }
      }
    });
    inputNumPeople.add(textField);
    
    messageArea = new JTextArea();
    updateMessageArea(obj, textField.getText());
    JScrollPane scrollPane = new JScrollPane(messageArea);
    scrollPane.createVerticalScrollBar();
    
    contentPane.add(button);
    contentPane.add(inputNumPeople);
    contentPane.add(scrollPane);
    
    frame.setVisible(true);

  }
  
  /**
   * Updates the ingredient list of the scroll pane.
   * 
   * @param obj should be a Recipe or Meal
   * @param text to be parsed for a number of people to serve
   */
  private void updateMessageArea(final Object obj, final String text)
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
    
    if (obj instanceof Recipe)
    {
      Recipe recipe = (Recipe) obj;
      updateMessageAreaHelper(recipe, numPeople);
    } 
    else if (obj instanceof Meal)
    {
      Meal meal = (Meal) obj;
      for (Recipe recipe : meal.getRecipes())
      {
        updateMessageAreaHelper(recipe, numPeople);
      }
    }
  }
  
  /**
   * Adds ingredients to the scroll pane.
   * 
   * @param recipe to pull ingredients from
   * @param numPeople to serve
   */
  private void updateMessageAreaHelper(final Recipe recipe, final int numPeople)
  {
    //must account for the fact that recipes are designed to serves multiple people
    //e.g. if a recipe of two servings is used to feed five people, each ingredient must be 
    //multiplied by 2.5
    double numberOfBatches = (double) numPeople / (double) recipe.getServings();
    for (Ingredient ing : recipe.getIngredients())
    {
      String info = String.format("%.1f %ss of %s\n", ing.getAmount() * numberOfBatches, 
          ing.getUnit(), ing.getName());
      messageArea.append(info);
    }
  }
  
}
