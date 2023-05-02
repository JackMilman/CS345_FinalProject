package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import branding.KitchIntelColor;
import branding.KitchIntelIconButton;
import branding.KitchIntelJFrame;
import config.Translator;
import recipes.NutritionInfo;
import recipes.Unit;
import utilities.UnitConversion;

/**
 * GUI for unit conversions.
 * 
 * @author KitchIntel
 * @version
 */
public class UnitConversionWindow extends KitchIntelJFrame
{
  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_TEXT_FIELD_WIDTH = 8;
  private static final String CALCULATION_COMMAND = "calc";
  private static final String RESET = "reset";

  private static UnitConversionWindow unitWindow = null;

  private JComboBox<String> fromUnitBox = new JComboBox<String>();
  private JComboBox<String> toUnitBox = new JComboBox<String>();
  private JComboBox<String> ingredientBox = new JComboBox<String>();
  private JTextField amount;
  private JLabel resultLabel;

  private Unit fromUnit;
  private Unit toUnit;
  private String ingredient;
  private int amountvalue;
  private boolean open = false;

  private UnitConversionWindow(final Window main)
  {
    super(Translator.translate("KiLowBites Unit Converter"));
    setUp();
    ingredientBox.setEnabled(false);
    PreferenceWindow.changeFont(this);
    
    setDefaultCloseOperation(HIDE_ON_CLOSE);
  }

  /**
   * Get the instance of a unit conversion window, or make a new one.
   * 
   * @return unit conversion window
   */
  public static UnitConversionWindow getUnitConversionWindow()
  {
    if (unitWindow == null)
    {
      unitWindow = new UnitConversionWindow(null);

    }
    unitWindow.setVisible(true);

    return unitWindow;
  }

  private Container createIcons()
  {
    Container icons = new Container();
    icons.setLayout(new FlowLayout(FlowLayout.LEFT));
    JButton calcButton = new KitchIntelIconButton(KitchIntelIconButton.CALCULATE_IMAGE);
    JButton resetButton = new KitchIntelIconButton(KitchIntelIconButton.RESET_IMAGE);

    calcButton.setActionCommand(CALCULATION_COMMAND);
    resetButton.setActionCommand(RESET);
    UnitConversionListener calcListener = new UnitConversionListener(this);
    UnitConversionListener resetListener = new UnitConversionListener(this);
    calcButton.addActionListener(calcListener);
    resetButton.addActionListener(resetListener);
    icons.add(calcButton);
    icons.add(resetButton);

    return icons;
  }

  private Container createUnitMenu()
  {
    Container unitMenu = new Container();
    unitMenu.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel fromUnitLabel = new JLabel(Translator.translate("From units") + ":");
    fromUnitBox = new JComboBox<String>();
    JLabel toUnitLabel = new JLabel(Translator.translate("To units") + ":");
    toUnitBox = new JComboBox<String>();
    for (Unit unit : Unit.values())
    {
      fromUnitBox.addItem(unit.getName());
      toUnitBox.addItem(unit.getName());
    }

    fromUnitBox.addItemListener(new FromComboBoxHandler());
    toUnitBox.addItemListener(new ToComboBoxHandler());
    Set<String> ingredients = NutritionInfo.getIngredientsInMap();
    JLabel ingredientLabel = new JLabel(Translator.translate("Ingredient") + ":");
    ingredientBox = new JComboBox<String>();
    ingredientBox.addItem("");
    for (String info : ingredients)
    {
      ingredientBox.addItem(info);
    }
    unitMenu.add(fromUnitLabel);
    unitMenu.add(fromUnitBox);
    unitMenu.add(toUnitLabel);
    unitMenu.add(toUnitBox);
    unitMenu.add(ingredientLabel);
    unitMenu.add(ingredientBox);

    ingredientBox.addItemListener(new IngredientComboBoxHandler());

    return unitMenu;
  }

  private JPanel createInputPanel()
  {
    JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel fromAmount = new JLabel(Translator.translate("From amount") + ":");
    amount = new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
    resultLabel = new JLabel(Translator.translate("Result") + ":  ___________");

    // inputPanel.setOpaque(false);
    inputPanel.setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());

    inputPanel.add(fromAmount);
    inputPanel.add(amount);
    inputPanel.add(resultLabel);
    return inputPanel;
  }

  private void setUp()
  {
    Container c = getContentPane();

    Container icons = createIcons();
    c.add(icons, BorderLayout.NORTH);
    Container unitMenu = createUnitMenu();
    c.add(unitMenu, BorderLayout.AFTER_LINE_ENDS);
    JPanel inputPanel = createInputPanel();
    c.add(inputPanel, BorderLayout.AFTER_LAST_LINE);
    inputPanel.setOpaque(false);

    unitMenu.setBackground(KitchIntelColor.BACKGROUND_COLOR.getColor());

    // Result
    setVisible(true);
    pack();
  }

  private void updateIngredientAvailability()
  {
    ingredientBox.setEnabled(fromUnit != null && toUnit != null);

  }

  private class FromComboBoxHandler implements ItemListener
  {
    public void itemStateChanged(final ItemEvent e)
    {
      fromUnit = Unit.parseUnit((String) e.getItem());
      updateIngredientAvailability();
    }
  }

  private class ToComboBoxHandler implements ItemListener
  {
    public void itemStateChanged(final ItemEvent e)
    {
      toUnit = Unit.parseUnit((String) e.getItem());
      updateIngredientAvailability();
    }
  }

  private class IngredientComboBoxHandler implements ItemListener
  {
    public void itemStateChanged(final ItemEvent e)
    {
      ingredient = (String) e.getItem();
    }
  }

  private class UnitConversionListener implements ActionListener
  {
    private final UnitConversionWindow subject;

    public UnitConversionListener(final UnitConversionWindow subject)
    {
      super();
      this.subject = subject;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      String command = e.getActionCommand();

      if (command.equals(RESET))
      {
        fromUnitBox.setSelectedItem("");
        toUnitBox.setSelectedItem("");
        ingredientBox.setSelectedItem("");
        amount.setText("");
        resultLabel.setText(Translator.translate("Result") + ":  ___________ ");
        ingredientBox.setEnabled(false);
      }
      else if (command.equals(CALCULATION_COMMAND))
      {
        amountvalue = Integer.parseInt(amount.getText());
        if (amountvalue < 0)
          amountvalue = 0;
        double value = UnitConversion.convert(ingredient, fromUnit, toUnit, amountvalue);
        double truncate = Math.floor(value * 100) / 100;
        resultLabel.setText(Translator.translate("Result") + ":   "
            + String.format("%.2f", truncate) + " " + toUnit.getName());
      }

    }

  }

}
