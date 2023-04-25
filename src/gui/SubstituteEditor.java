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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Unit;

public class SubstituteEditor extends JPanel
{
  /**
   * A class for the ingredient editor component of the meal editor.
   * 
   * @author Josiah Leach, Meara Patterson, KitchIntel
   * @version 03.29.2023
   */

  public static final Double NO_INPUT = null; // Changed 4/13: Updated to Double and value to null.
                                              // -Jack
  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound", "Pinch",
      "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Individual"};
  private static final String ADD = "Add";
  private static final String DELETE = "Delete";

  private static final long serialVersionUID = 1L;

  private JTextField nameField;
  private JTextField detailField;
  private JTextField amountField;
  private JTextField calorieField;
  private JTextField densityField;
  private JList<Ingredient> substituteDisplay;
  private DefaultListModel<Ingredient> listModel;
  private JButton addButton, deleteButton;
  private final JComboBox<String> unitSelect;
  private final JComboBox<Ingredient> substituteSelect;

  private HashMap<Ingredient, List<Ingredient>> substitutes;

  /**
     * 
     */
  public SubstituteEditor()
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Substitutes")));

    SubstituteEditorListener listener = new SubstituteEditorListener(this);
    EnableUpdater addListener = new EnableUpdater();

    addButton = new JButton(Translator.translate(ADD));
    deleteButton = new JButton(Translator.translate(DELETE));

    addButton.setActionCommand(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND);
    deleteButton.setActionCommand(RecipeEditor.INGREDIENT_DELETE_ACTION_COMMAND);

    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    amountField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    calorieField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    densityField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);

    unitSelect = new JComboBox<String>(UNITS);
    substituteSelect = new JComboBox<Ingredient>();

    nameField.addActionListener(addListener);
    detailField.addActionListener(addListener);
    amountField.addActionListener(addListener);
    calorieField.addActionListener(addListener);
    densityField.addActionListener(addListener);
    unitSelect.addActionListener(addListener);

    substitutes = new HashMap<Ingredient, List<Ingredient>>();

    listModel = new DefaultListModel<Ingredient>();
    substituteDisplay = new JList<Ingredient>(listModel);
    substituteDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    substituteDisplay.setLayoutOrientation(JList.VERTICAL_WRAP);

    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);

    calorieField.setEnabled(false);
    densityField.setEnabled(false);
    addButton.setEnabled(false);

    addButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent evt)
      {
        try
        {
          NutritionInfo.addIngredient(nameField.getText(),
              Double.parseDouble(calorieField.getText()),
              Double.parseDouble(densityField.getText()));
        }
        catch (NumberFormatException nfe)
        {
          NutritionInfo.addIngredient(nameField.getText(), NO_INPUT, NO_INPUT);
        }
      }
    });

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel(Translator.translate("Name") + ":"));
    inputFields.add(nameField);
    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);
    inputFields.add(new JLabel(Translator.translate("Amount") + ":"));
    inputFields.add(amountField);
    inputFields.add(new JLabel(Translator.translate("Units") + ":"));
    inputFields.add(unitSelect);
    inputFields.add(new JLabel(Translator.translate("Substitute") + ":"));
    inputFields.add(substituteSelect);
    inputFields.add(new JLabel(Translator.translate("Calories") + ":"));
    inputFields.add(calorieField);
    inputFields.add(new JLabel(Translator.translate("g/mL") + ":"));
    inputFields.add(densityField);
    inputFields.add(addButton);

    add(inputFields, BorderLayout.NORTH);

    add(deleteButton, BorderLayout.EAST);

    add(substituteDisplay, BorderLayout.CENTER);

    setVisible(true);
    setOpaque(false);
  }

  private void add()
  {
    String name = nameField.getText();
    String details = detailField.getText();
    String unit = unitSelect.getSelectedItem().toString();
    Ingredient substitute = (Ingredient) substituteSelect.getSelectedItem();
    double amount;
    double calories;
    double density;

    try
    {
      amount = Double.valueOf(amountField.getText());
    }
    catch (NumberFormatException nfe)
    {
      return;
    }

    // User is allowed to not input calories or density.
    // If they don't, those values are set to NO_INPUT
    try
    {
      calories = Double.valueOf(calorieField.getText());
    }
    catch (NumberFormatException nfe)
    {
      calories = NO_INPUT;
    }

    try
    {
      density = Double.valueOf(densityField.getText());
    }
    catch (NumberFormatException nfe)
    {
      density = NO_INPUT;
    }
    catch (NullPointerException npe)
    {
      density = NO_INPUT;
    }

    if (name.equals("") || unit.equals(""))
      return;

    Ingredient ingredient = new Ingredient(name, details, amount, Unit.parseUnit(unit), calories,
        density, 0.0);
    
    if (substitutes.containsKey(substitute)) {
      substitutes.get(substitute).add(ingredient);
    } else {
      List<Ingredient> newList = new ArrayList<Ingredient>();
      newList.add(ingredient);
      substitutes.put(substitute, newList);
    }

    nameField.setText("");
    detailField.setText("");
    unitSelect.setSelectedIndex(0);
    substituteSelect.setSelectedIndex(0);
    amountField.setText("");
    calorieField.setText("");
    densityField.setText("");

    updateSubstitutes();
  }

  private void delete()
  {
    int index = listModel.indexOf(substituteDisplay.getSelectedValue());
    Ingredient substitute = (Ingredient) substituteDisplay.getSelectedValue();
    substitutes.remove(substitute);
    
    listModel.remove(index);
  }

  HashMap<Ingredient, List<Ingredient>> getSubstitutes()
  {
    return substitutes;
  }
  
  private void updateSubstitutes() {
    substituteDisplay.removeAll();
    
    for (Ingredient key : substitutes.keySet()) {
      for (Ingredient substitute : substitutes.get(key)) {
        listModel.addElement(substitute);
      }
    }
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
      if (e.getActionCommand().equals(RecipeEditor.INGREDIENT_ADD_ACTION_COMMAND))
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
      if (NutritionInfo.contains(nameField.getText()) || nameField.getText().equals(""))
      {
        calorieField.setEnabled(false);
        densityField.setEnabled(false);
        calorieField.setText("");
        densityField.setText("");
      }
      else
      {
        calorieField.setEnabled(true);
        densityField.setEnabled(true);
      }

      if (nameField.getText().length() == 0 || amountField.getText().length() == 0
          || unitSelect.getSelectedIndex() == 0)
      {
        addButton.setEnabled(false);
        return;
      }
      else
      {
        addButton.setEnabled(true);
      }

      if (NutritionInfo.contains(nameField.getText()))
      {
        addButton.setEnabled(true);
      }
      // You can input an ingredient without giving the calorie and density
      // else
      // {
      // boolean filled = calorieField.getText().length() > 0 && densityField.getText().length() >
      // 0;
      // addButton.setEnabled(filled);
      // }
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
  
  void loadIngredients(List<Ingredient> ingredients) {
    for (Ingredient item : ingredients) {
      substituteSelect.addItem(item);
    }
  }

  void loadSubstitutes(final HashMap<Ingredient, List<Ingredient>> map)
  {
    this.substitutes.clear();

    for (Ingredient key : map.keySet())
    {
      substitutes.put(key, map.get(key));
    }

    updateSubstitutes();
  }

}
