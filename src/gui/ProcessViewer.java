package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import config.Translator;
import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;
import utilities.SortLists;

/**
 * GUI for the process viewer. This allows the user to view the list of utensils and steps in a
 * recipe.
 * 
 * @version 3/29/23
 * @author Allie O'Keeffe, KichIntel
 *
 */
public class ProcessViewer extends JFrame implements Serializable
{

  private static final long serialVersionUID = 1L;
  private static final String RECIPEEXT = "rcp";
  private static final String MEALEXT = "mel";

  /**
   * Recipe constructor.
   * 
   * @param recipe
   */
  public ProcessViewer(final Recipe recipe)
  {
    super(String.format("%s	%s", Translator.translate("KiLowBites Process Viewer"), 
        recipe.getName()));
    setUp(recipe);
  }

  /**
   * Meal constructor.
   * 
   * @param meal
   */
  public ProcessViewer(final Meal meal)
  {
    super(String.format("%s %s", Translator.translate("KiLowBites Process Viewer"), 
        meal.getName()));
    setUp(meal);
  }

  /**
   * Sets up to panel for the utensils.
   * 
   * @param utensils
   *          The list of utensils used in a recipe
   * @return A scrollable panel with a border and list of utensils
   */
  private JScrollPane setUpUtensils(final List<Utensil> utensils)
  {
    JTextArea textArea = new JTextArea();
    SortLists.sortUtensils(utensils); // Added since change to Recipe's get() methods do not return
                                      // an automatically sorted list anymore - Jack, 3/30
    for (Utensil item : utensils)
    {
      textArea.append(String.format("- %s\n", item.toString()));
    }
    textArea.setEditable(false);
    JScrollPane p = new JScrollPane(textArea);
    p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    p.setBorder(BorderFactory.createTitledBorder(Translator.translate("Utensils")));
    p.setPreferredSize(new Dimension(575, 100));
    return p;
  }

  /**
   * Sets up to panel for the steps.
   * 
   * @param steps
   *          The list of steps used in a recipe
   * @return A scrollable panel with a border and list of steps
   */
  private JScrollPane setUpSteps(final List<Step> steps)
  {
    JTextArea textArea = new JTextArea();
    for (Step item : steps)
    {
      textArea.append(String.format("- %s\n", item.toString()));
    }
    textArea.setEditable(false);
    JScrollPane p = new JScrollPane(textArea);
    p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    p.setBorder(BorderFactory.createTitledBorder(Translator.translate("Steps")));
    p.setPreferredSize(new Dimension(575, 300));
    return p;
  }
  
  private JPanel setUpCaloriesAndInventory(double calories) {
	  JPanel p = new JPanel();
	  p.add(new JTextField("Calories: " + Math.round(calories * 10)/10.0));
	  
	  JButton removeIngredients = new JButton("Recipe Complete");
	  removeIngredients.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: Remove ingredients from inventory
			JOptionPane.showMessageDialog(null, "Successful removal of ingredients.", "Ingredient Removal", JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getClassLoader().getResource("KILowBites_Logo.png")));
		}
	  });
	  p.add(removeIngredients);
	  return p;
  }

  /**
   * Sets up the main frame for the process viewer. This adds utensils and steps to the main frame.
   * 
   * @param recipe
   *          The recipe the process viewer is looking at
   */
  private void setUp(final Recipe recipe)
  {
    JScrollPane p;
    Container c;

    c = getContentPane();
    p = setUpUtensils(recipe.getUtensils());
    c.setLayout(new BorderLayout());
    c.add(p, BorderLayout.NORTH);

    p = setUpSteps(recipe.getSteps());
    c.add(p, BorderLayout.CENTER);
    
    c.add(setUpCaloriesAndInventory(recipe.calculateCalories()), BorderLayout.SOUTH);
    
    
    setSize(600, 450);
    setVisible(true);
  }

  /**
   * Sets up the main frame for the process viewer. This adds utensils and steps to the main frame.
   * 
   * @param recipe
   *          The recipe the process viewer is looking at
   */
  private void setUp(final Meal meal)
  {
    JScrollPane p;
    Container c;

    c = getContentPane();

    // Gets each utensil and step in the meals
    ArrayList<Utensil> utensils = new ArrayList<>();
    ArrayList<Step> steps = new ArrayList<>();
    for (Recipe recipe : meal.getRecipes())
    {
      for (Utensil utensil : recipe.getUtensils())
      {
        if (!utensils.contains(utensil))
          utensils.add(utensil);
      }
      for (Step step : recipe.getSteps())
      {
        steps.add(step);
      }
    }

    p = setUpUtensils(utensils);
    c.setLayout(new FlowLayout());
    c.add(p);

    p = setUpSteps(steps);
    c.add(p);
    setSize(600, 450);
    setVisible(true);
  }
  
}
