package gui;

import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.JComponent;

/**
 * A class for the ingredient editor component of the meal editor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class IngredientEditor extends JComponent
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public IngredientEditor()
  {
    super();
    setLayout(new BorderLayout());
    add(new TextArea("ingredient editor"));
    setVisible(true);
  }
}
