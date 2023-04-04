package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import recipes.Ingredient;
import recipes.Step;
import recipes.Utensil;

/**
 * A class for the step editor component of the recipe editor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class StepEditor extends JComponent implements TextListener
{
  private static final String[] ACTIONS = new String[] {"", "Put", "Melt", "Simmer",
      "Heat", "Ignite", "Boil", "Drain", "Saute", "Cook", "Bake", "Dip"};
  
  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  private static final String BLANK = "            ";
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private JComboBox<String> actionSelect, onSelect, utensilSelect;
  private JTextField detailField, timeField;
  private List<Step> steps;
  private TextArea display;
  private List<Utensil> utensils;
  private List<Ingredient> ingredients;
  private JButton addButton, deleteButton;
  
  /**
   * Creates a new StepEditor.
   * @param utensils The utensils which can be used in a step. Must be a reference to
   * the list used by the corresponding UtensilEditor.
   * @param ingredients The ingredients which can be used in a step. Must be a reference to 
   * the list used by the corresponding IngredientEditor.
   */
  public StepEditor(final List<Utensil> utensils, final List<Ingredient> ingredients)
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder("Steps"));
    
    this.utensils = utensils;
    this.ingredients = ingredients;
    this.steps = new ArrayList<Step>();
    
    StepEditorListener listener = new StepEditorListener(this);
    
    actionSelect = new JComboBox<String>(ACTIONS);
    onSelect = new JComboBox<String>(new String[] {BLANK});
    utensilSelect = new JComboBox<String>(new String[] {BLANK});
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    timeField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH / 2);
        
    addButton = new JButton(ADD);
    deleteButton = new JButton(DELETE);
    
    addButton.addActionListener(listener);
    addButton.addActionListener(listener);
    
    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel("Action:"));
    inputFields.add(actionSelect);
    inputFields.add(new JLabel("On:"));
    inputFields.add(onSelect);
    inputFields.add(new JLabel("Utensil:"));
    inputFields.add(utensilSelect);
    inputFields.add(new JLabel("Details:"));
    inputFields.add(detailField);
    inputFields.add(new JLabel("Minutes:"));
    inputFields.add(timeField);
    inputFields.add(addButton);
    
    add(inputFields, BorderLayout.NORTH);
    
    add(deleteButton, BorderLayout.EAST);
    
    display = new TextArea();
    display.setEditable(false);
    add(display, BorderLayout.CENTER);
    
    setVisible(true);
  }
  
  private void add()
  {
    String action =  actionSelect.getSelectedItem().toString();
    String on =      onSelect.getSelectedItem().toString();
    String utensil = utensilSelect.getSelectedItem().toString();
    String details = detailField.getText();
    int time;
    
    try
    {
      time = Integer.valueOf(timeField.getText());
    }
    catch(NumberFormatException nfe)
    {
      return;
    }
    
    if(action.equals("") || on.equals("") || utensil.equals("")) 
    {
      return;
    }
    
    Utensil destinationUtensil = null;
    Utensil sourceUtensil = null;
    
    for(int i = 0; i < utensils.size(); i++)
    {
      if(on.equals(utensils.get(i).getName()))
      {
        sourceUtensil = utensils.get(i);
      }
      if(utensil.equals(utensils.get(i).getName()))
      {
        destinationUtensil = utensils.get(i);
      }
    }
    
    Ingredient objectIngredient = null;
        
    for(int i = 0; i < ingredients.size(); i++)
    {
      if(on.equals(ingredients.get(i).getName()))
      {
        objectIngredient = ingredients.get(i);
      }
    }
    
    Step step = new Step(action, objectIngredient, sourceUtensil, destinationUtensil, details, 
        time);
    steps.add(step);
        
    updateDisplay();
    
    actionSelect.setSelectedIndex(0);
    onSelect.setSelectedIndex(0);
    utensilSelect.setSelectedIndex(0);
    timeField.setText("");
    detailField.setText("");
  }
  
  private void delete()
  {
    if(steps.size() == 0) return;
    
    int selectionStart = display.getSelectionStart();
    int selectionEnd = display.getSelectionEnd();
    String selectedText = display.getSelectedText();
    
    //TODO
    
    steps.remove(steps.size() - 1);
    
    updateDisplay();
  }
  
  private void updateDisplay()
  {
    String displayText = "";
    
    for(Step step : steps)
    {
      displayText += String.format("%s\n", step.toString());
    }
    
    display.setText(displayText);
  }
  
  /**
   * updates the selectable "on" options. Should be called after loadUtensil or loadIngredient is 
   * called on the corresponding UtensilEditor or IngredientEditor.
   */
  public void updateOn()
  {
    onSelect.removeAllItems();
    
    onSelect.addItem(BLANK);
    
    for(Utensil utensil : utensils) 
    {
      onSelect.addItem(utensil.getName());
    }
    
    for(Ingredient ingredient : ingredients)
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
        
    for(Utensil utensil : utensils) 
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
      if(e.getActionCommand().equals(ADD))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(DELETE))
      {
        subject.delete();
      }
    }
    
  }



  @Override
  public void textValueChanged(final TextEvent e)
  {
    updateOn();
    updateUtensil();
  }
  
  List<Step> getSteps()
  {
    return steps;
  }

  /**
   * Adds an action listener to the buttons in this StepEditor which can cause the
   * document to change.
   * @param listener The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }
  
  /**
   * Loads the given steps.
   * @param newSteps The new steps for this StepEditor to display.
   */
  public void loadSteps(final List<Step> newSteps)
  {
    this.steps = newSteps;
    
    updateDisplay();
  }

}
