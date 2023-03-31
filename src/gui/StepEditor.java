package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class StepEditor extends JComponent implements ActionListener
{
  private static final String[] ACTIONS = new String[] {};
  
  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private JComboBox<String> actionSelect, onSelect, utensilSelect;
  private JTextField detailField;
  private List<Step> steps;
  private TextArea display;
  private List<Utensil> utensils;
  private List<Ingredient> ingredients;
  
  /**
   * Creates a new StepEditor.
   * @param utensils The utensils which can be used in a step. Must be a reference to
   * the list used by the corresponding UtensilEditor.
   * @param ingredients The ingredients which can be used in a step. Mustt be a reference to 
   * the list used by the corresponding IngredientEditor.
   */
  public StepEditor(final List<Utensil> utensils, final List<Ingredient> ingredients)
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder("Steps"));
    
    this.utensils = utensils;
    this.ingredients = ingredients;
    
    StepEditorListener listener = new StepEditorListener(this);
    
    actionSelect = new JComboBox<String>(ACTIONS);
    onSelect = new JComboBox<String>(new String[] {""});
    utensilSelect = new JComboBox<String>(new String[] {""});
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    
    JButton addButton = new JButton(ADD);
    JButton deleteButton = new JButton(DELETE);
    
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
    
    if(action.equals("") || on.equals("") || utensil.equals("") || details.equals("")) 
    {
      return;
    }
    
    Utensil destinationUtensil = null;
    
    for(int i = 0; i < utensilSelect.getItemCount(); i++)
    {
      if(utensil.equals(utensilSelect.getItemAt(i)))
      {
        destinationUtensil = utensils.get(i-1);
      }
    }
    
    Utensil sourceUtensil = null;
    Ingredient objectIngredient = null;
    
    for(int i = 1; i < utensils.size() + 1; i++)
    {
      if(on.equals(onSelect.getItemAt(i)))
      {
        sourceUtensil = utensils.get(i-1);
      }
    }
    //BAD CODE DOESNT WORK FIX LATER
    
    //Step step = new Step(action, ingredient, source, destination, details, time);
    //steps.add(step);
  }
  
  private void delete()
  {
    if(steps.size() == 0) return;
    
    steps.remove(steps.size() - 1);
    
    updateDisplay();
  }
  
  private void updateDisplay()
  {
    String displayText = "";
    
    for(Step step : steps)
    {
      displayText += String.format("%s\t%s\t%s\t%s\n", step.getAction(), 
          step.getSource().getName(), step.getDestination().getName(), step.getDetails());
    }
    
    display.setText(displayText);
  }
  
  private void updateOn()
  {
    onSelect.removeAll();
    
    for(Utensil utensil : utensils) 
    {
      onSelect.addItem(utensil.getName());
    }
    
    for(Ingredient ingredient : ingredients)
    {
      onSelect.addItem(ingredient.getName());
    }
  }
  
  private void updateUtensil()
  {
    utensilSelect.removeAll();
    
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
  public void actionPerformed(final ActionEvent e)
  {
    if(e.getActionCommand().equals(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND)
        ||e.getActionCommand().equals(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND))
    {
      updateOn();
    } 
    else if (e.getActionCommand().equals(RecipeEditor.UTENSIL_ADD_ACTION_COMMAND)
        || e.getActionCommand().equals(RecipeEditor.UTENSIL_DELETE_ACTION_COMMAND))
    {
      updateOn();
      updateUtensil();
    }
  }

}
