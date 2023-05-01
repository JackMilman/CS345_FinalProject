package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import branding.KitchIntelBorder;
import branding.KitchIntelJDialog;
import config.Translator;
import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Recipe;
import recipes.Unit;

/**
 * Temporary class to make refactoring IngredientEditor easier.
 * 
 * @author Josiah Leach, Meara Patterson, KitchIntel
 * @version 4/25/2023
 *
 */
public class IngredientEditor extends JPanel
{

  // probably move to RecipeEditor
  public static final String SELECT_INGREDIENT = "select_ingredient";
  public static final String MAKE_NEW_INGREDIENT = "make_new_ingredient";

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  private static final String BLANK = "            ";

  private JComboBox<String> selectIngredient;
  private JComboBox<String> unitSelect;
  private JTextField detailField;
  private JTextField amountField;
  private JButton makeNewIngredient;
  private JButton addButton;
  private JButton deleteButton;
  private JTable ingredientDisplay;
  private Recipe workingRecipe;

  private final StepEditor stepEditor;
  private final SubstituteEditor substituteEditor;
  private final RecipeEditor parent;

  private final EnableUpdater enableUpdater;

  /**
   * Creates an IngredientEditor for the given Recipe.
   * 
   * @param workingRecipe
   *          A reference to the Recipe being used by other components of the RecipeEditor.
   * @param stepEditor
   *          The corresponding StepEditor in the same RecipeEditor. This will notify the StepEditor
   *          when the list of Ingredients changes.
   * @param substituteEditor
   *          The corresponding SubstitueEditor in the same RecipeEditor. This will notify the
   *          SubstitueEditor when the list of Ingredients changes.
   * @param parent
   *          The RecipeEditor which this is a part of. This will resize the parent when its size
   *          changes.
   */
  public IngredientEditor(final Recipe workingRecipe, final StepEditor stepEditor,
      final SubstituteEditor substituteEditor, final RecipeEditor parent)
  {
    super();

    this.workingRecipe = workingRecipe;
    this.stepEditor = stepEditor;
    this.substituteEditor = substituteEditor;
    this.parent = parent;

    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Ingredients")));

    IngredientEditorListener listener = new IngredientEditorListener();
    enableUpdater = new EnableUpdater();

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

    addButton.setActionCommand(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND);
    addButton.addActionListener(listener);
    addButton.setEnabled(false);

    deleteButton.setActionCommand(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND);
    deleteButton.addActionListener(listener);

    selectIngredient.setActionCommand(SELECT_INGREDIENT);
    makeNewIngredient.setActionCommand(MAKE_NEW_INGREDIENT);
    makeNewIngredient.addActionListener(listener);

    selectIngredient.addActionListener(enableUpdater);
    detailField.addActionListener(enableUpdater);
    amountField.addActionListener(enableUpdater);
    unitSelect.addActionListener(enableUpdater);

    ingredientDisplay = new JTable(new DefaultTableModel(1, 1));
    updateIngredientDisplay();

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
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
    add(ingredientDisplay, BorderLayout.CENTER);

    setVisible(true);
    setOpaque(false);

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

  private void add()
  {
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

    Ingredient ingredient = new Ingredient(name, details, amount, Unit.parseUnit(unit));
    workingRecipe.addIngredient(ingredient);

    selectIngredient.setSelectedIndex(0); // will cause problems when selecting ""
    detailField.setText("");
    unitSelect.setSelectedIndex(0);
    amountField.setText("");

    updateIngredientDisplay();
    substituteEditor.updateSubstituteSelect();
    stepEditor.updateSelects();
  }

  private void delete()
  {
    List<Ingredient> ingredients = workingRecipe.getIngredients();
    int numIngredients = ingredients.size();
    if (numIngredients == 0)
    {
      return;
    }

    int index = ingredientDisplay.getSelectedRow();

    if (index < numIngredients)
    {
      Ingredient ingredient = ingredients.get(index);

      workingRecipe.removeIngredient(ingredient);

      updateIngredientDisplay();
    }

    stepEditor.updateSelects();
    substituteEditor.updateSubstituteSelect();

  }

  /**
   * Updates the JTable which displays ingredients. If ingredients have been added since the last
   * call to this method, the size of the JTable will be increased. This method should be called
   * every time the Ingredients in the recipe changes.
   */
  public void updateIngredientDisplay()
  {

    DefaultTableModel tableModel = new DefaultTableModel(workingRecipe.getIngredients().size(), 1)
    {

      private static final long serialVersionUID = 1L;

      @Override
      public boolean isCellEditable(final int row, final int col)
      {
        return false;
      }
    };

    ingredientDisplay.setModel(tableModel);
    List<Ingredient> ingredientsList = workingRecipe.getIngredients();

    for (int i = 0; i < ingredientsList.size(); i++)
    {
      ingredientDisplay.setValueAt(ingredientsList.get(i), i, 0);
    }

    parent.pack();
  }

  List<Ingredient> getIngredients()
  {
    return workingRecipe.getIngredients();
  }

  /**
   * ActionListener for the Add and Delete buttons.
   * 
   * @author Josiah Leach
   *
   */
  private class IngredientEditorListener implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (e.getActionCommand().equals(MAKE_NEW_INGREDIENT))
      {
        new MakeNewIngredientEditor();
        updateIngredientSelect();
      }
      else if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND))
      {
        add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND))
      {
        delete();
      }
    }
  }

  /**
   * ActionListener which controls the enabling and disabling of the add button.
   * 
   * @author Josiah Leach
   *
   */
  private class EnableUpdater implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (selectIngredient.getSelectedIndex() != 0 && !amountField.getText().equals("")
          && unitSelect.getSelectedIndex() != 0)
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

  void loadIngredients(final List<Ingredient> newIngredients)
  {
    workingRecipe.getIngredients().clear();
    workingRecipe.addAllIngredients(newIngredients);

    updateIngredientDisplay();
  }

  /**
   * GUI for making a new ingredient and adding it to NutritionInfo.
   * 
   * @author Meara Patterson, KitchIntel
   * @version 4/25/2023
   */
  private class MakeNewIngredientEditor extends KitchIntelJDialog
  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String DESC = "Make New Ingredient";

    private JTextField nameField;
    private JTextField priceField;
    private JTextField calorieField;
    private JTextField densityField;
    private JButton addButton;

    /**
     * Makes a dialog with fields to input an ingredient's name, price, calories, and density, and
     * adds it to NutritionInfo's map.
     */
    public MakeNewIngredientEditor()
    {

      super(Translator.translate(DESC));
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setSize(new Dimension(800, 200));
      setOpaque(false);

      nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
      priceField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
      calorieField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
      densityField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
      addButton = new JButton(DESC);

      // nameField.setActionCommand(MAKE_NEW_INGREDIENT_COMMAND);
      // priceField.setActionCommand(MAKE_NEW_INGREDIENT_COMMAND);
      // calorieField.setActionCommand(MAKE_NEW_INGREDIENT_COMMAND);
      // densityField.setActionCommand(MAKE_NEW_INGREDIENT_COMMAND);

      Updater listener = new Updater();
      nameField.addActionListener(listener);
      priceField.addActionListener(listener);
      calorieField.addActionListener(listener);
      densityField.addActionListener(listener);
      addButton.setEnabled(false);

      addButton.addActionListener(new ActionListener()
      {
        @Override
        public void actionPerformed(final ActionEvent e)
        {
          try
          {
            NutritionInfo.addIngredient(nameField.getText().toLowerCase(),
                Double.parseDouble(calorieField.getText()),
                Double.parseDouble(densityField.getText()));
            nameField.setText("");
            priceField.setText("");
            calorieField.setText("");
            densityField.setText("");
            addButton.setEnabled(false);
            updateIngredientSelect();
          }
          catch (NumberFormatException nfe)
          {
            return;
          }
        }
      });

      add(new JLabel("Name:"));
      add(nameField);
      add(new JLabel("Price per tablespoon: $"));
      add(priceField);
      add(new JLabel("Calories:"));
      add(calorieField);
      add(new JLabel("Density:"));
      add(densityField);
      add(addButton);

      setVisible(true);

    }

    private class Updater implements ActionListener
    {

      @Override
      public void actionPerformed(final ActionEvent e)
      {
        if (!nameField.getText().equals("") && !priceField.getText().equals("")
            && !calorieField.getText().equals("") && !densityField.getText().equals(""))
        {
          addButton.setEnabled(true);
        }
        else
        {
          addButton.setEnabled(false);
        }
      }
    }
  }

  /**
   * Sets the Recipe which this IngredientEditor is editing.
   * 
   * @param workingRecipe
   *          The Recipe for this IngredientEditor to edit.
   */
  public void setWorkingRecip(Recipe workingRecipe)
  {
    this.workingRecipe = workingRecipe;
  }

  /**
   * Sets whether the components of this IngredientEditor can be interacted with by the user.
   * 
   * @param editable
   */
  public void setEditable(final boolean editable)
  {
    addButton.setEnabled(editable);
    deleteButton.setEnabled(editable);
    selectIngredient.setEnabled(editable);
    detailField.setEnabled(editable);
    amountField.setEnabled(editable);
    unitSelect.setEnabled(editable);
    makeNewIngredient.setEnabled(editable);

    enableUpdater.actionPerformed(null);
  }

}
