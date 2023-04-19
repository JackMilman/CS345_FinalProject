package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import branding.KitchIntelJFrame;
import config.Translator;
import recipes.Ingredient;
import recipes.Inventory;
import recipes.Unit;

import java.util.*;
import java.util.List;

public class InventoryWindow extends KitchIntelJFrame
{
  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_TEXT_FIELD_WIDTH = 8;
  public Inventory inventory = Inventory.createInstance();

  private JTextField ingredientName = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
  private JTextField ingredientDetails = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
  private JTextField ingredientAmount = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
  private JLabel amountItems = new JLabel();

  private String[] units = {"", "DRAM", "OUNCE", "GRAM", "POUND", "PINCH", "TEASPOON", "TABLESPOON",
      "FLUID OUNCE", "CUP", "PINT", "QUART", "GALLON", "MILLILITER"};
  private JComboBox<String> ingredientUnit = new JComboBox<String>();

  JButton addButton = new JButton();
  JButton subButton = new JButton();
  JTextArea inventoryPanel = new JTextArea(25, 70);
  JScrollPane scrollPane = new JScrollPane(inventoryPanel);

  String name;
  String details;
  double amount;
  String unit;
  Ingredient inventoryItem;

  public InventoryWindow(final Window main)
  {
    super(Translator.translate("KiLowBites Inventory"));
    setUp();
    setDefaultCloseOperation(HIDE_ON_CLOSE);
  }

  private void setUp()
  {
    Container c;
    c = getContentPane();
    c.setLayout(new FlowLayout(FlowLayout.LEFT));
    c.add(topMenuBar(), BorderLayout.NORTH);
    c.add(infoMenuBar(), BorderLayout.AFTER_LINE_ENDS);
    c.add(infoContainer(), BorderLayout.SOUTH);
    setVisible(true);
    setSize(810, 500);
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
    for (String unit : units)
    {
      ingredientUnit.addItem(unit);
    }
    JLabel unit = new JLabel("Unit:");
    unitMenu.add(unit);
    unitMenu.add(ingredientUnit);
    ingredientUnit.addItemListener(new unitBoxHandler());
    addButton.setText("+");
    addButton.setEnabled(false);
    addButton.addActionListener(new addOperationHandler());
    subButton.setText("-");
    subButton.addActionListener(new subOperationHandler());
    subButton.setEnabled(false);
    unitMenu.add(addButton);
    unitMenu.add(subButton);
    return unitMenu;
  }

  private Container infoContainer()
  {
    Container infoContainer = new Container();
    infoContainer.setLayout(new BorderLayout());
    infoContainer.add(scrollPane, BorderLayout.WEST);
    return infoContainer;
  }

  private class unitBoxHandler implements ItemListener
  {

    @Override
    public void itemStateChanged(ItemEvent e)
    {
      addButton.setEnabled(true);
      subButton.setEnabled(true);
      unit = (String) e.getItem();
    }

  }

  private class addOperationHandler implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      inventoryPanel.setText("");
      name = ingredientName.getText();
      details = ingredientDetails.getText();
      amount = Double.parseDouble(ingredientAmount.getText());
      if (amount < 0)
        amount = 0;
      inventoryItem = new Ingredient(name, details, amount, Unit.parseUnit(unit), 0.0, 0.0);
      inventory.addIngredient(inventoryItem);
      ingredientName.setText("");
      ingredientDetails.setText("");
      ingredientAmount.setText("");
      ingredientUnit.setSelectedItem("");
      addButton.setEnabled(false);
      subButton.setEnabled(false);
      for (Ingredient info : inventory.getIngredientList())
        inventoryPanel.append(String.format(info.getName() + " " + info.getDetails() + " "
            + info.getAmount() + " " + info.getUnit().getName().toLowerCase() + "\n"));
      amountItems.setText(String.format("%d", inventory.size()));
      amountItems.setEnabled(true);
    }
  }

  private class subOperationHandler implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      inventoryPanel.setText("");
      name = ingredientName.getText();
      details = ingredientDetails.getText();
      amount = Double.parseDouble(ingredientAmount.getText());
      inventoryItem = new Ingredient(name, details, amount, Unit.parseUnit(unit), 0.0, 0.0);
      inventory.reduceIngredient(inventoryItem);
      ingredientName.setText("");
      ingredientDetails.setText("");
      ingredientAmount.setText("");
      ingredientUnit.setSelectedItem("");
      addButton.setEnabled(false);
      subButton.setEnabled(false);
      for (Ingredient info : inventory.getIngredientList())
        inventoryPanel.append(String.format(info.getName() + " " + info.getDetails() + " "
            + info.getAmount() + " " + info.getUnit().getName().toLowerCase() + "\n"));

    }
  }
}
