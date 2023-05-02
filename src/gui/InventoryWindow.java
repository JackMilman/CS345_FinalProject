package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import config.Translator;
import recipes.Ingredient;
import recipes.Inventory;
import recipes.NutritionInfo;
import recipes.Unit;

/**
 * The window to view the inventory.
 * 
 * @author KitchIntel
 * @version
 */
public class InventoryWindow extends Editor
{
  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_TEXT_FIELD_WIDTH = 8;
  public Inventory inventory;

  private JComboBox<String> ingredientName;
  private JTextField ingredientDetails = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
  private JTextField ingredientAmount = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
  private JLabel amountItems = new JLabel();

  private JComboBox<String> ingredientUnit = new JComboBox<String>();

  private JButton addButton = new JButton();
  private JButton subButton = new JButton();
  private JTextArea inventoryPanel = new JTextArea(25, 70);
  private JScrollPane scrollPane = new JScrollPane(inventoryPanel);

  private String name;
  private String details;
  private double amount;
  private Unit unit;
  private Ingredient inventoryItem;

  /**
   * Create a new inventory or return an existing inventory.
   * 
   * @param main
   */
  public InventoryWindow(final Window main)
  {

    super(main, Translator.translate("KiLowBites Inventory"));
    inventory = Inventory.createInstance();
    setUp();
    setDefaultCloseOperation(HIDE_ON_CLOSE);
  }

  private void setUp()
  {
    ingredientName = new JComboBox<>();
    ingredientName.addItem("");
    for (String ing : NutritionInfo.getIngredientsInMap())
    {
      ingredientName.addItem(ing);
    }
    Container c;
    c = getContentPane();
    c.setLayout(new FlowLayout(FlowLayout.LEFT));
    c.add(topMenuBar(), BorderLayout.NORTH);
    c.add(infoMenuBar(), BorderLayout.AFTER_LINE_ENDS);
    c.add(infoContainer(), BorderLayout.SOUTH);
    PreferenceWindow.changeFont(this);
    setVisible(true);
    setSize(900, 500);
    setResizable(false);

  }

  private Container topMenuBar()
  {
    Container basicInfo = new Container();
    basicInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel items = new JLabel("Amount of Items:");
    basicInfo.add(items);
    amountItems.setText(String.format("%d", inventory.size()));
    basicInfo.add(amountItems);
    amountItems.setEnabled(false);
    return basicInfo;
  }

  private Container infoMenuBar()
  {
    Container unitMenu = new Container();
    unitMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel name = new JLabel("Ingredient Name:");
    unitMenu.add(name);
    unitMenu.add(ingredientName);
    JLabel details = new JLabel("Ingredient Details:");
    unitMenu.add(details);
    unitMenu.add(ingredientDetails);
    ingredientDetails.setText("");
    JLabel amount = new JLabel("Amount:");
    unitMenu.add(amount);
    unitMenu.add(ingredientAmount);
    for (Unit unit : Unit.values())
    {
      ingredientUnit.addItem(unit.getName());
    }
    JLabel unit = new JLabel("Unit:");
    unitMenu.add(unit);
    unitMenu.add(ingredientUnit);
    ingredientUnit.addItemListener(new UnitBoxHandler());
    addButton.setText("+");
    addButton.setEnabled(false);
    addButton.addActionListener(new AddOperationHandler());
    subButton.setText("-");
    subButton.addActionListener(new SubOperationHandler());
    subButton.setEnabled(false);
    unitMenu.add(addButton);
    unitMenu.add(subButton);
    return unitMenu;
  }

  private Container infoContainer()
  {
    Container infoContainer = new Container();
    infoContainer.setLayout(new BorderLayout());
    inventoryPanel.setText("");
    for (Ingredient info : inventory.getIngredientList())
      inventoryPanel.append(info.toString() + "\n");
    infoContainer.add(scrollPane, BorderLayout.WEST);
    return infoContainer;
  }

  private class UnitBoxHandler implements ItemListener
  {

    @Override
    public void itemStateChanged(final ItemEvent e)
    {
      addButton.setEnabled(true);
      subButton.setEnabled(true);
      unit = Unit.parseUnit((String) e.getItem());
    }

  }

  private class AddOperationHandler implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      inventoryPanel.setText("");
      name = (String) ingredientName.getSelectedItem();
      details = ingredientDetails.getText();
      amount = Double.parseDouble(ingredientAmount.getText());
      if (amount < 0)
        amount = 0;
      inventoryItem = new Ingredient(name, details, amount, unit);
      inventory.addIngredient(inventoryItem);
      ingredientName.setSelectedIndex(0);
      ingredientDetails.setText("");
      ingredientAmount.setText("");
      ingredientUnit.setSelectedItem("");
      addButton.setEnabled(false);
      subButton.setEnabled(false);
      for (Ingredient info : inventory.getIngredientList())
        inventoryPanel.append(info.toString() + "\n");
      amountItems.setText(String.format("%d", inventory.size()));
      amountItems.setEnabled(true);
    }
  }

  private class SubOperationHandler implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      inventoryPanel.setText("");
      name = (String) ingredientName.getSelectedItem();
      details = ingredientDetails.getText();
      amount = Double.parseDouble(ingredientAmount.getText());
      inventoryItem = new Ingredient(name, details, amount, unit);
      inventory.reduceIngredient(inventoryItem);
      ingredientName.setSelectedIndex(0);
      ingredientDetails.setText("");
      ingredientAmount.setText("");
      ingredientUnit.setSelectedItem("");
      addButton.setEnabled(false);
      subButton.setEnabled(false);
      for (Ingredient info : inventory.getIngredientList())
        inventoryPanel.append(info.toString() + "\n");
      amountItems.setText(String.format("%d", inventory.size()));
    }
  }

  @Override
  public void enableEditing(final boolean editable)
  {
  }

}
