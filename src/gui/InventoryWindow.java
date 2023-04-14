package gui;

import java.awt.*;
import javax.swing.*;

import recipes.Ingredient;

public class InventoryWindow extends JFrame
{
  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_TEXT_FIELD_WIDTH = 8;
  private JComboBox<String> inventoryBox = new JComboBox<String>(); 
  JButton addButton = new JButton();
  JButton subButton = new JButton();
  private static final String ADD = "add";
  private static final String REMOVE = "remove";
  
  private void ingredientsChoicesSetup() {
    
  }
  private void setUp(final Ingredient ingredient)
  {
    JScrollPane p;
    Container c;
    c = getContentPane();
    
    
    
  }
  private void topMenuBar() {
    Container unitMenu = new Container();
    unitMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
    unitMenu.add(inventoryBox);
    unitMenu.add(addButton);
    unitMenu.add(subButton);
    
  }
}
