package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import branding.KitchIntelBorder;
import branding.KitchIntelJDialog;
import config.Translator;
import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Recipe;
import recipes.Unit;
import recipes.Utensil;
import utilities.SortLists;

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
  // private TextArea ingredientDisplay;
  private JTable ingredientDisplay;

  private final Recipe workingRecipe;

  public IngredientEditor(Recipe workingRecipe)
  {
    super();

    this.workingRecipe = workingRecipe;

    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Ingredients")));

    IngredientEditorListener listener = new IngredientEditorListener(this);
    EnableUpdater addListener = new EnableUpdater();

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

    selectIngredient.addActionListener(addListener);
    detailField.addActionListener(addListener);
    amountField.addActionListener(addListener);
    unitSelect.addActionListener(addListener);

    ingredientDisplay = new JTable(new DefaultTableModel(3, 1));
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
  }

  private void delete()
  {
    if (workingRecipe.getIngredients().size() == 0)
    {
      return;
    }

    int index = ingredientDisplay.getSelectedRow();
        
    if (index < workingRecipe.getIngredients().size()) {
      Ingredient ingredient = workingRecipe.getIngredients().get(index);
      
      workingRecipe.removeIngredient(ingredient);
      
      updateIngredientDisplay();
    }

  }

  private void updateIngredientDisplay()
  {
    DefaultTableModel tableModel = new DefaultTableModel(workingRecipe.getIngredients().size() + 1,
        1)
    {

      private static final long serialVersionUID = 1L;

      @Override
      public boolean isCellEditable(final int row, final int col)
      {
        return false;
      }
    };

    ingredientDisplay.setModel(tableModel);

    for (int i = 0; i < workingRecipe.getIngredients().size(); i++)
    {
      ingredientDisplay.setValueAt(workingRecipe.getIngredients().get(i), i, 0);
    }

  }

  List<Ingredient> getIngredients()
  {
    return workingRecipe.getIngredients();
  }

  /**
   * Adds a text listener to the text area of the ingredient editor.
   * 
   * @param listener
   *          the text listener to add to the display text area.
   */
  public void addTextListener(final TextListener listener)
  {
    // TODO refactor for JTable
  }

  private class IngredientEditorListener implements ActionListener
  {

    private final IngredientEditor subject;

    IngredientEditorListener(final IngredientEditor subject)
    {
      this.subject = subject;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {

      // if (e.getActionCommand().equals(SELECT_INGREDIENT))
      // {
      //
      // }
      // else
      if (e.getActionCommand().equals(MAKE_NEW_INGREDIENT))
      {
        MakeNewIngredientEditor makeNew = new MakeNewIngredientEditor();
        updateIngredientSelect();
      }
      else if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND))
      {
        subject.add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND))
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
      if (selectIngredient.getSelectedIndex() != 0 && !amountField.getText().equals(""))
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
}