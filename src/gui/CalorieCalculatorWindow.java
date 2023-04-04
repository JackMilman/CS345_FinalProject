package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import recipes.NutritionInfo;

public class CalorieCalculatorWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//private String selectedIngredient, selectedUnits, enteredAmount;
	private JComboBox ingredients, units;
	private JTextField amount;
	
	public CalorieCalculatorWindow() {
		super("KiLowBites Calorie Calculator");
		setUp();
	}
	
	private JLabel createLabels(String name) {
		return new JLabel(String.format("%s:", name));
	}
	
	private NutritionInfo getSelectedIngredients() {
		return (NutritionInfo) ingredients.getSelectedItem();
	}
	
	private String getSelectedUnits() {
		return (String) units.getSelectedItem();
	}
	
	private String getEnteredAmount() {
		return (String) amount.getText();
	}
	
	private void setUp() {
		
		JPanel p = new JPanel();
		ActionListener calories = new Calories();
		
		ingredients = new JComboBox(NutritionInfo.values());
		ingredients.addActionListener(calories);
		
		amount = new JTextField();
		
		String[] unitNames = {"","Dram", "Ounce", "Gram", "Pound", "Pinch", "Teaspoon", "Tablespoon",
			      "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon"};
		units = new JComboBox(unitNames);
		units.addActionListener(calories);
		
		p.add(createLabels("Ingredient"));
		p.add(ingredients);
		p.add(createLabels("Amount"));
		p.add(amount);
		amount.setPreferredSize(new Dimension(100,30));
		p.add(createLabels("Units"));
		p.add(units);
		p.add((JPanel) calories);
		
	    Container c;

	    c = getContentPane();
	    c.setLayout(new FlowLayout());
	    
	    c.add(p);
	    
	    setSize(650, 200);
	    setVisible(true);
	}
	
	private class Calories extends JPanel implements ActionListener {

		private JLabel calories;
		
		public Calories() {
			super();
			calories = new JLabel();
			calories.setText("Calories:    ___________");
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			NutritionInfo selectedIngredients = getSelectedIngredients();
			String selectedUnits = getSelectedUnits();
			String enteredText = getEnteredAmount();
			
			if (selectedIngredients != null && selectedUnits != null && !enteredText.equals("")){
				
			}
		}
		
	}

	
}
