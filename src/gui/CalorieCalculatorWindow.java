package gui;

import java.awt.BorderLayout;
import java.awt.Container;
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

public class CalorieCalculatorWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//private String selectedIngredient, selectedUnits, enteredAmount;
	private JComboBox<String> ingredients, units;
	private JTextField amount;
	
	public CalorieCalculatorWindow() {
		super("KiLowBites Calorie Calculator");
		setUp();
	}
	
	private JLabel createLabels(String name) {
		return new JLabel(String.format("%s:", name));
	}
	
	private String getSelectedIngredients() {
		return (String) ingredients.getSelectedItem();
	}
	
	private String getSelectedUnits() {
		return (String) units.getSelectedItem();
	}
	
	private String getEnteredAmount() {
		return (String) amount.getText();
	}
	
	private JComboBox<String> setUpIngredients() {
		JComboBox<String> names = new JComboBox<>();
		names.addItem("");
		for (NutritionInfo info: NutritionInfo.values()) {
			names.addItem(info.getName());
		}
		return names;
	}
	
	private void setUp() {
		
		JPanel p = new JPanel();
		Calories calories = new Calories();
		
		ingredients = setUpIngredients();
		ingredients.addActionListener(calories);
		
		amount = new JTextField();
		
		String[] unitNames = {"","Dram", "Ounce", "Gram", "Pound", "Pinch", "Teaspoon", "Tablespoon",
			      "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon"};
		units = new JComboBox<>(unitNames);
		units.addActionListener(calories);
		
		p.setLayout(new FlowLayout());
		p.add(createLabels("Ingredient"));
		p.add(ingredients);
		p.add(createLabels("Amount"));
		p.add(amount);
		amount.setPreferredSize(new Dimension(100,30));
		p.add(createLabels("Units"));
		p.add(units);
		
	    Container c = getContentPane();
	    c.setLayout(new BorderLayout());
	    
	    c.add(p, BorderLayout.NORTH);
	    p.add(createLabels("Calories"), BorderLayout.CENTER);
	    c.add(calories, BorderLayout.CENTER);
	    
	    setSize(600, 200);
	    setVisible(true);
	}
	
	private class Calories extends JLabel implements ActionListener {

		private static final long serialVersionUID = 1L;
		
		public Calories() {
			super();
			setText("");
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedIngredient = getSelectedIngredients();
			String selectedUnits = getSelectedUnits();
			String enteredText = getEnteredAmount();
			
			if (selectedIngredient != null && selectedUnits != null && !enteredText.equals("")){
				try {
					double amountOfIngredients = Double.parseDouble(enteredText);
					Ingredient temp = new Ingredient(selectedIngredient, "", amountOfIngredients, selectedUnits);
					setText("" + temp.getCaloriesPerGram());
				} catch (NumberFormatException exc) {
					setText("Invalid Amount");
				}
			}
		}
		
	}

}
