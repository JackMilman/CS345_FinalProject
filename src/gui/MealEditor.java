package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The class for the meal editor window. All that another class needs to do is call
 * the constructor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 *
 */
public class MealEditor extends JDialog
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private static final int TEXT_WIDTH = 40;
  
  /**
   * Creates a new MealEditor.
   * @param owner The JFrame which created this MealEditor. This should probably be
   * the Main Window.
   */
  public MealEditor(final Window owner)
  {
    super(owner, "KiLowBites Meal Editor");
    
    setLayout(new BorderLayout());
    
    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
    buttons.add(new KitchIntelButton(KitchIntelButton.NEW_IMAGE));
    buttons.add(new KitchIntelButton(KitchIntelButton.OPEN_IMAGE));
    buttons.add(new KitchIntelButton(KitchIntelButton.SAVE_IMAGE));
    buttons.add(new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE));
    buttons.add(new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE));
    
    add(buttons, BorderLayout.NORTH);
    
    JPanel name = new JPanel();
    name.setLayout(new FlowLayout(FlowLayout.LEFT));
    name.add(new JLabel("Name:"));
    name.add(new JTextField(TEXT_WIDTH));
    
    add(name, BorderLayout.CENTER);
    
    JPanel edit = new JPanel();
    edit.setBorder(KitchIntelBorder.labeledBorder("Recipes"));
    edit.setLayout(new BorderLayout());
    edit.add(new JButton("Add Recipe"), BorderLayout.NORTH);
    edit.add(new TextArea(), BorderLayout.CENTER);
    edit.add(new JButton("Delete"), BorderLayout.EAST);
    
    
    add(edit, BorderLayout.SOUTH);
    
    setVisible(true);
    setResizable(true);
    pack();
    
  }
  

}
