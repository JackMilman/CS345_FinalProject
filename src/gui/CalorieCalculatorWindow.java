package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import recipes.Ingredient;
import recipes.NutritionInfo;

/**
 * This class is a GUI for calculating calories. The user is able to select and
 * ingredient and unit and enter the amount. This will then be used to calculate
 * the number of calories. It uses the singleton design pattern so only one
 * window can be opened at a time.
 * 
 * @author Allie O'Keeffe
 *
 */
public class CalorieCalculatorWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> ingredients, units;
	private JTextField amount;
	private static CalorieCalculatorWindow instance;
	private static boolean exists = false;

	/**
	 * Private constructor.
	 */
	private CalorieCalculatorWindow() {
		super("KiLowBites Calorie Calculator");
		setUp();
	}

	/*
	 * Uses the singleton pattern to make it so only one window can be opened at a
	 * time.
	 */
	public static CalorieCalculatorWindow createInstance() {
		if (exists) {
			return instance;
		} else {
			exists = true;
			instance = new CalorieCalculatorWindow();
			return instance;
		}
	}

	/**
	 * Creates a JLabel
	 * 
	 * @param name The name of the label
	 * @return A JLabel formatted as "name:"
	 */
	private JLabel createLabels(String name) {
		return new JLabel(String.format("%s:", name));
	}

	/**
	 * Creates JComboBox and adds all of the ingredients to it
	 * 
	 * @return Ingredients JComboBox
	 */
	private JComboBox<String> setUpIngredients() {
		JComboBox<String> names = new JComboBox<>();
		names.addItem("");
		for (NutritionInfo info : NutritionInfo.values()) {
			names.addItem(info.getName());
		}
		return names;
	}

	/**
	 * Sets up the three inputs and adds them to a JPanel.
	 * 
	 * @param calories The action listener
	 * @return A formatted JPanel
	 */
	private JPanel setUpInputs(Calories calories) {
		JPanel p = new JPanel();
		calories = new Calories();

		ingredients = setUpIngredients();
		ingredients.addActionListener(calories);

		amount = new JTextField();

		String[] unitNames = { "", "Dram", "Ounce", "Gram", "Pound", "Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce",
				"Cup", "Pint", "Quart", "Gallon" };
		units = new JComboBox<>(unitNames);
		units.addActionListener(calories);

		p.setLayout(new FlowLayout());
		p.add(createLabels("Ingredient"));
		p.add(ingredients);
		p.add(createLabels("Amount"));
		p.add(amount);
		amount.setPreferredSize(new Dimension(100, 30));
		p.add(createLabels("Units"));
		p.add(units);
		return p;
	}

	/**
	 * Combines the two JPanels on the JFrame.
	 */
	private void setUp() {
		Calories calories = new Calories();
		JPanel p = setUpInputs(calories);

		JPanel c = (JPanel) getContentPane();
		c.setLayout(new BorderLayout());

		c.add(p, BorderLayout.NORTH);
		c.add(calories, BorderLayout.SOUTH);

		setSize(600, 100);
		setVisible(true);
	}

	/**
	 * A class that is used to update and display the calculated calories. This
	 * class is a JPanel and an ActionListener and updates when the three inputs
	 * have been entered.
	 * 
	 * @author Allie O'Keeffe
	 * @version 4/5/23, version 1
	 */
	private class Calories extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JLabel cals;

		/**
		 * Constructor.
		 */
		public Calories() {
			super();
			cals = new JLabel();
			cals.setText("_____________");

			setLayout(new FlowLayout());
			add(createLabels("Calories"));
			add(cals);

		}

		/**
		 * Updates the JLabel when the three inputs have been entered.
		 * 
		 * @param e The action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedIngredient = (String) ingredients.getSelectedItem();
			String selectedUnits = (String) units.getSelectedItem();
			String enteredText = (String) amount.getText();
			System.out.print(selectedIngredient + selectedUnits + enteredText);

			if (selectedIngredient != null && selectedUnits != null && !enteredText.equals("")) {
				System.out.print("reached");
				try {
					double amountOfIngredients = Double.parseDouble(enteredText);
					Ingredient temp = new Ingredient(selectedIngredient, "", amountOfIngredients, selectedUnits);
					System.out.print("" + temp.getCaloriesPerGram());
					cals.setText(temp.getCaloriesPerGram() + "");
				} catch (NumberFormatException exc) {
					System.out.print("invalid");
					cals.setText("Invalid Amount");
				}
			}
		}

	}

}
