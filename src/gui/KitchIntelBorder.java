package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * This class allows every border of the same type to look the same across the 
 * application
 * @author Josiah Leacj, KitchIntel
 * @version 03.29.2023
 */
public class KitchIntelBorder
{
  public static Border labeledBorder(String label)
  {
    return BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), label);
  }
}
