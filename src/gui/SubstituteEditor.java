package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Recipe;
import recipes.Unit;
import utilities.SortLists;

/**
 * A class for the ingredient editor component of the meal editor.
 * 
 * @author Josiah Leach, Meara Patterson, Jack Milman, KitchIntel
 * @version 03.29.2023
 */
public class SubstituteEditor extends JPanel
{
  private static final long serialVersionUID = 1L;
  public static final String SELECT_SUBSTITUTE = "select_substitute";
  public static final String SELECT_INGREDIENT = "select_ingredient";
  public static final String MAKE_NEW_SUBSTITUTE = "make_new_substitute";

  private static final String ADD = "Add";
  private static final String DELETE = "Delete";

  private JComboBox<String> selectSubstitute;
  private JComboBox<String> selectIngredient;
  private JComboBox<String> unitSelect;
  private JTextField detailField;
  private JTextField amountField;
  private JButton makeNewIngredient;
  private JButton addButton;
  private JButton deleteButton;

  private JTable substituteDisplay;

  private Recipe workingRecipe;

  private final RecipeEditor parent;

  private final List<Ingredient> validIngredients = new ArrayList<Ingredient>();

  /**
   * Creates a new SubstituteEditor.
   * 
   * @param workingRecipe
   *          the Recipe which saves the data of the Recipe being edited.
   * @param parent
   *          The RecipeEditor which this SubstituteEditor is a part of, required for this to resize
   *          the RecipeEditor.
   */
  public SubstituteEditor(final Recipe workingRecipe, final RecipeEditor parent)
  {
    super();

    this.workingRecipe = workingRecipe;
    this.parent = parent;

    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Substitutes")));

    SubstituteEditorListener listener = new SubstituteEditorListener(this);
    EnableUpdater addListener = new EnableUpdater();

    updateSubstituteSelect();
    updateIngredientSelect();

    makeNewIngredient = new JButton(Translator.translate("Make New Ingredient"));
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    amountField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    unitSelect = new JComboBox<>();
    for (Unit unit : Unit.values())
    {
      unitSelect.addItem(unit.getName());
    }

    addButton = new JButton(Translator.translate(ADD));
    deleteButton = new JButton(Translator.translate(DELETE));

    addButton.setActionCommand(RecipeEditor.SUBSTITUTE_ADD_ACTION_COMMAND);
    addButton.addActionListener(listener);
    addButton.setEnabled(false);
    deleteButton.setActionCommand(RecipeEditor.SUBSTITUTE_DELETE_ACTION_COMMAND);
    deleteButton.addActionListener(listener);
    selectSubstitute.setActionCommand(SELECT_SUBSTITUTE);
    selectIngredient.setActionCommand(SELECT_INGREDIENT);
    makeNewIngredient.setActionCommand(MAKE_NEW_SUBSTITUTE);
    makeNewIngredient.addActionListener(listener);

    selectSubstitute.addActionListener(addListener);
    selectIngredient.addActionListener(addListener);
    detailField.addActionListener(addListener);
    amountField.addActionListener(addListener);
    unitSelect.addActionListener(addListener);

    substituteDisplay = new JTable(new DefaultTableModel(3, 1));
    updateSubstituteDisplay();

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel(Translator.translate("Substitute") + ":"));
    inputFields.add(selectSubstitute);
    inputFields.add(new JLabel(Translator.translate("Ingredient") + ":"));
    inputFields.add(selectIngredient);
    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);
    inputFields.add(new JLabel(Translator.translate("Amount") + ":"));
    inputFields.add(amountField);
    inputFields.add(new JLabel(Translator.translate("Units") + ":"));
    inputFields.add(unitSelect);
    inputFields.add(addButton);
    inputFields.add(makeNewIngredient);

    add(inputFields, BorderLayout.NORTH);
    add(deleteButton, BorderLayout.EAST);
    add(substituteDisplay, BorderLayout.CENTER);

    setVisible(true);
    setOpaque(false);
  }

  void updateSubstituteSelect()
  {
    if (selectSubstitute != null)
    {
      selectSubstitute.removeAllItems();
    }
    else
    {
      selectSubstitute = new JComboBox<>();
    }
    selectSubstitute.addItem("");
    // Gets the list of ingredients presently in the recipe.
    validIngredients.clear();
    validIngredients.addAll(workingRecipe.getIngredients());
    SortLists.sortIngredients(validIngredients);
    for (Ingredient item : validIngredients)
    {
      selectSubstitute.addItem(item.getName());
    }
    selectSubstitute.revalidate();
    selectSubstitute.repaint();
  }

  private void add()
  {
    // Subtracting 1 from the index gets us the index in the ingredients list, since we have the
    // empty first item in the substitute selector.
    int substituteIndex = selectSubstitute.getSelectedIndex() - 1;
    Ingredient ingredientToSubstituteFor = validIngredients.get(substituteIndex);
    String name = selectIngredient.getSelectedItem().toString();
    String details = detailField.getText();
    String unit = unitSelect.getSelectedItem().toString();
    double amount;

    try
    {
      amount = Double.valueOf(amountField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }

    Ingredient substitute = new Ingredient(name, details, amount, Unit.parseUnit(unit));
    workingRecipe.addSubstitute(ingredientToSubstituteFor, substitute);

    selectIngredient.setSelectedIndex(0);
    detailField.setText("");
    unitSelect.setSelectedIndex(0);
    amountField.setText("");

    updateSubstituteDisplay();
  }

  // TODO: Currently not working for some reason
  private void delete()
  {
    int numSubstitutes = workingRecipe.getNumSubstitutes();
    if (numSubstitutes == 0)
    {
      return;
    }

    int row = substituteDisplay.getSelectedRow();

    // The ingredient being substituted for
    Ingredient normalIngredient = (Ingredient) substituteDisplay.getValueAt(row, 0);
    // The ingredient to substitute for normalIngredient
    Ingredient substituteIngredient = (Ingredient) substituteDisplay.getValueAt(row, 1);


    workingRecipe.removeSubstitute(normalIngredient, substituteIngredient);

    updateSubstituteDisplay();

  }

  void updateSubstituteDisplay()
  {
    DefaultTableModel tableModel = new DefaultTableModel(workingRecipe.getNumSubstitutes(), 2)
    {

      private static final long serialVersionUID = 1L;

      @Override
      public boolean isCellEditable(final int row, final int col)
      {
        return false;
      }
    };

    substituteDisplay.setModel(tableModel);

    HashMap<Ingredient, List<Ingredient>> substitutes = workingRecipe.getSubstitutes();
    List<Ingredient> ingredients = new ArrayList<Ingredient>(substitutes.keySet());
    SortLists.sortIngredients(ingredients);
    // Index used to keep track of where we are in the table
    int index = 0;

    for (int i = 0; i < ingredients.size(); i++)
    {
      Ingredient currentIngredient = ingredients.get(i);
      List<Ingredient> ingredientSubstitutes = substitutes.get(currentIngredient);
      for (int f = 0; f < ingredientSubstitutes.size(); f++)
      {
        // Sets the ingredient being substituted for
        substituteDisplay.setValueAt(ingredients.get(i), index, 0);
        // Sets what we are substituting
        substituteDisplay.setValueAt(ingredientSubstitutes.get(f), index, 1);
        // Increases the index so we can go to the next row
        index++;
      }
    }

    parent.pack();

  }

  private void updateIngredientSelect()
  {
    if (selectIngredient != null)
    {
      selectIngredient.removeAllItems();
    }
    else
    {
      selectIngredient = new JComboBox<>();
    }
    selectIngredient.addItem("");
    ArrayList<String> names = new ArrayList<>();
    names.addAll(NutritionInfo.getIngredientsInMap());
    Collections.sort(names);
    for (String name : names)
    {
      selectIngredient.addItem(name);
    }
    selectIngredient.revalidate();
    selectIngredient.repaint();
  }

  private class SubstituteEditorListener implements ActionListener
  {
    private final SubstituteEditor subject;

    SubstituteEditorListener(final SubstituteEditor subject)
    {
      this.subject = subject;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (e.getActionCommand().equals(RecipeEditor.SUBSTITUTE_ADD_ACTION_COMMAND))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.SUBSTITUTE_DELETE_ACTION_COMMAND))
      {
        subject.delete();
      }

    }

  }

  private class EnableUpdater implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (selectSubstitute.getSelectedIndex() != 0 && selectIngredient.getSelectedIndex() != 0
          && !amountField.getText().equals("") && unitSelect.getSelectedIndex() != 0)
      {
        addButton.setEnabled(true);
      }
      else
      {
        addButton.setEnabled(false);
      }
    }
  }

  /**
   * Adds an action listener to the buttons in this IngredientEditor which can cause the document to
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

  public void setWorkingRecipe(Recipe workingRecipe)
  {
    this.workingRecipe = workingRecipe;
  }
}
