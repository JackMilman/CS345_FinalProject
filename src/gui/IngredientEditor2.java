package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.CalorieGram;
import recipes.Ingredient; 
import recipes.NutritionInfo;
import recipes.Unit;
import recipes.Utensil;
import utilities.SortLists;

/**
 * Temporary class to make refactoring IngredientEditor easier.
 * 
 * @author Meara Patterson
 *
 */
public class IngredientEditor2 extends JPanel
{
  
  // probably move to RecipeEditor
  public static final String SELECT_INGREDIENT = "select_ingredient";
  public static final String MAKE_NEW_INGREDIENT = "make_new_ingredient";
  
  private JComboBox<String> ingredients;
  private JButton makeNewIngredient;
  
  public IngredientEditor2()
  {
    
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Ingredients")));
    
    IngredientEditorListener listener = new IngredientEditorListener(this);
    
    ingredients = new JComboBox<>();
    for (String name : NutritionInfo.getIngredientsInMap())
    {
      ingredients.addItem(name);
    }
    makeNewIngredient = new JButton();
    
    ingredients.setActionCommand(SELECT_INGREDIENT);
    makeNewIngredient.setActionCommand(MAKE_NEW_INGREDIENT);
    
    add(ingredients);
    add(makeNewIngredient);
    
    setVisible(true);
    setOpaque(false);
    
  }
  
  private class IngredientEditorListener implements ActionListener
  {
    
    private final IngredientEditor2 subject;

    IngredientEditorListener(final IngredientEditor2 subject)
    {
      this.subject = subject;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      
      if (e.getActionCommand().equals(SELECT_INGREDIENT))
      {
        
      }
      else if (e.getActionCommand().equals(MAKE_NEW_INGREDIENT))
      {
        
      }
      
    }
    
  }
  
}
