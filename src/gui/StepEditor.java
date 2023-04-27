package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Ingredient;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

/**
 * A class for the step editor component of the recipe editor.
 * 
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class StepEditor extends JComponent
{
  protected static final String CURRENT_DIRECTORY = ".";
  
  private static final String[] ACTIONS = new String[] {"", "Put", "Melt", "Simmer", "Heat",
      "Ignite", "Boil", "Drain", "Saute", "Cook", "Bake", "Dip"};

  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  private static final String BLANK = "            ";
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private JComboBox<String> actionSelect, onSelect, utensilSelect;
  private JTextField detailField, timeField;
  private JTable display;
  private JButton addButton, deleteButton, embeddedRecipe;
  private Recipe workingRecipe;
  private String fileName;

  /**
   * Creates a new StepEditor.
   * 
   * @param workingRecipe
   *          the recipe to edit the steps of.
   */
  public StepEditor(final Recipe workingRecipe)
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Steps")));

    this.workingRecipe = workingRecipe;

    StepEditorListener listener = new StepEditorListener(this);
    EnableListener enabler = new EnableListener();

    actionSelect = new JComboBox<String>(ACTIONS);
    onSelect = new JComboBox<String>(new String[] {BLANK});
    utensilSelect = new JComboBox<String>(new String[] {BLANK});
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    timeField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH / 2);

    addButton = new JButton(Translator.translate(ADD));
    deleteButton = new JButton(Translator.translate(DELETE));
    // embeddedRecipe= new JButton("EmbeddedRecipe");
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
    // embeddedRecipe.addActionListener(listener);

    actionSelect.addActionListener(enabler);
    onSelect.addActionListener(enabler);
    utensilSelect.addActionListener(enabler);
    timeField.addActionListener(enabler);

    addButton.setEnabled(false);

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel(Translator.translate("Action") + ":"));
    inputFields.add(actionSelect);
    inputFields.add(new JLabel(Translator.translate("On") + ":"));
    inputFields.add(onSelect);
    inputFields.add(new JLabel(Translator.translate("Utensil") + ":"));
    inputFields.add(utensilSelect);
    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);
    inputFields.add(new JLabel(Translator.translate("Minutes") + ":"));
    inputFields.add(timeField);
    // inputFields.add(embeddedRecipe);
    inputFields.add(addButton);

    add(inputFields, BorderLayout.NORTH);

    add(deleteButton, BorderLayout.EAST);

    display = new JTable(new DefaultTableModel(1,1));
    updateStepDisplay();
    add(display, BorderLayout.CENTER);

    setVisible(true);
  }

  private void add()
  {
    String action = actionSelect.getSelectedItem().toString();
    String on = onSelect.getSelectedItem().toString();
    String utensil = utensilSelect.getSelectedItem().toString();
    String details = detailField.getText();
    int time;

    try
    {
      time = Integer.valueOf(timeField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }

    if (action.equals("") || on.equals("") || utensil.equals(""))
    {
      return;
    }

    Utensil destinationUtensil = null;
    Utensil sourceUtensil = null;

    for (int i = 0; i < workingRecipe.getUtensils().size(); i++)
    {
      if (on.equals(workingRecipe.getUtensils().get(i).getName()))
      {
        sourceUtensil = workingRecipe.getUtensils().get(i);
      }
      if (utensil.equals(workingRecipe.getUtensils().get(i).getName()))
      {
        destinationUtensil = workingRecipe.getUtensils().get(i);
      }
    }
    if (on.startsWith("*"))
    {
      try
      {
        Recipe objectIngredient = Recipe.read(fileName);
        Step step = new Step(action, objectIngredient, sourceUtensil, destinationUtensil, details,
            time);
        workingRecipe.getSteps().add(step);
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else
    {
      Ingredient objectIngredient = null;
      for (int i = 0; i < workingRecipe.getIngredients().size(); i++)
      {
        if (on.equals(workingRecipe.getIngredients().get(i).getName()))
        {
          objectIngredient = workingRecipe.getIngredients().get(i);
        }

      }
      Step step = new Step(action, objectIngredient, sourceUtensil, destinationUtensil, details,
          time);
      workingRecipe.addStep(step);
    }

    updateStepDisplay();

    actionSelect.setSelectedIndex(0);
    onSelect.setSelectedIndex(0);
    utensilSelect.setSelectedIndex(0);
    timeField.setText("");
    detailField.setText("");
  }

  private void delete()
  {
    //TODO
  }

  /**
   * Updates the JTable which displays the steps of this Recipe. Must be called every time the
   * Steps of this recipe change.
   */
  public void updateStepDisplay()
  {
    
    DefaultTableModel tableModel = new DefaultTableModel(workingRecipe.getSteps().size(), 1)
    {

      private static final long serialVersionUID = 1L;

      @Override
      public boolean isCellEditable(final int row, final int col)
      {
        return false;
      }
    };
    
    display.setModel(tableModel);
    List<Step> stepsList = workingRecipe.getSteps();

    for (int i = 0; i < stepsList.size(); i++)
    {
      display.setValueAt(stepsList.get(i), i, 0);
    }
  }

  /**
   * updates the selectable "on" options. Should be called after loadUtensil or loadIngredient is
   * called on the corresponding UtensilEditor or IngredientEditor.
   */
  public void updateOn()
  {    
    onSelect.removeAllItems();

    onSelect.addItem(BLANK);
        
    for (Utensil utensil : workingRecipe.getUtensils())
    {
      onSelect.addItem(utensil.getName());
    }

    for (Ingredient ingredient : workingRecipe.getIngredients())
    {
      onSelect.addItem(ingredient.getName());
    }

  }

  /**
   * updates the selectable "utensil" options. Should be called after loadUtensil is called on the
   * corresponding UtensilEditor.
   */
  public void updateUtensil()
  {
    utensilSelect.removeAllItems();

    utensilSelect.addItem(BLANK);

    for (Utensil utensil : workingRecipe.getUtensils())
    {
      utensilSelect.addItem(utensil.getName());
    }
  }

  private class StepEditorListener implements ActionListener
  {
    private StepEditor subject;

    private StepEditorListener(final StepEditor subject)
    {
      this.subject = subject;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (e.getActionCommand().equals(ADD))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(DELETE))
      {
        subject.delete();
      }
      else if (e.getActionCommand().equals("EmbeddedRecipe"))
      {
        JFileChooser chooser = new JFileChooser(new File(CURRENT_DIRECTORY));
        chooser.showOpenDialog(null);

        fileName = chooser.getSelectedFile().getPath();
        fileName = fileName.substring(0, fileName.indexOf(CURRENT_DIRECTORY));
        try
        {

          String eRecipe = "*" + Recipe.read(fileName).getName();
          onSelect.addItem(eRecipe);
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
      }
    }

  }

  private class EnableListener implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      boolean filled = onSelect.getSelectedIndex() != 0 && actionSelect.getSelectedIndex() != 0
          && utensilSelect.getSelectedIndex() != 0 && timeField.getText().length() > 0;
      addButton.setEnabled(filled);
    }
  }

  /**
   * Gets the steps of the Recipe being edited by this StepEditor.
   * @return The List of Steps from the REcipe being edited by this StepEditor.
   */
  public List<Step> getSteps()
  {
    return workingRecipe.getSteps();
  }

  /**
   * Adds an action listener to the buttons in this StepEditor which can cause the document to
   * change.
   * 
   * @param listener
   *          The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }

  /**
   * Loads the given steps.
   * 
   * @param newSteps
   *          The new steps for this StepEditor to display.
   */
  public void loadSteps(final List<Step> newSteps)
  {
    workingRecipe.getSteps().clear();
    workingRecipe.addAllSteps(newSteps);

    updateStepDisplay();
  }

  /**
   * This StepEditor updates its dropdown boxes to reflect the change in list of ingredients or 
   * utensils.
   */
  public void updateSelects()
  {
    updateOn();
    updateUtensil();
  }

  /**
   * Sets the Recipe which this StepEditor is editing.
   * @param workingRecipe The Recipe which this StepEditor will edit.
   */
  public void setWorkingRecipe(Recipe workingRecipe)
  {
    this.workingRecipe = workingRecipe;
  }
}
