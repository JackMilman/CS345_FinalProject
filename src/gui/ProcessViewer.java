package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

/**
 * GUI for the process viewer. This allows the user to view the list of utensils and steps in a
 * recipe.
 * 
 * @version 3/29/23
 * @author Allie O'Keeffe, KichIntel
 *
 */
public class ProcessViewer extends JFrame
{

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @param recipe
   */
  public ProcessViewer(final Recipe recipe)
  {
    super(String.format("KiLowBites Process Viewer	%s", recipe.getName()));
    setUp(recipe);
  }

  // TODO Overload methods with meals once the class has been created.

  // public ProcessViewer(Meal meal) {
  // super();
  // setUp();
  // }

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
    for (Utensil item : utensils)
    {
      textArea.append(String.format("- %s\n", item.getName()));
    }
    textArea.setEditable(false);
    JScrollPane p = new JScrollPane(textArea);
    p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    p.setBorder(BorderFactory.createTitledBorder("Utensils"));
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
      textArea.append(String.format("- %s\n", item.getDetails()));
    }
    textArea.setEditable(false);
    JScrollPane p = new JScrollPane(textArea);
    p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    p.setBorder(BorderFactory.createTitledBorder("Steps"));
    p.setPreferredSize(new Dimension(575, 300));
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

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    c = getContentPane();
    p = setUpUtensils(recipe.getUtensils());
    c.setLayout(new FlowLayout());
    c.add(p);

    p = setUpSteps(recipe.getSteps());
    c.add(p);
    setSize(600, 450);
    setVisible(true);
  }

}
