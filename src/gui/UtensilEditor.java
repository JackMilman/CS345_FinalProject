package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import recipes.SortLists;
import recipes.Utensil;

/**
 * A class for the UtensilEditor component of the recipe editor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class UtensilEditor extends JComponent
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private static final String ADD = "Add";
  private static final String DELETE = "Delete";
  
  private final JTextField nameField;
  private final JTextField detailField;
  private final TextArea utensilDisplay;
  private List<Utensil> utensils;



  /**
   * 
   */
  public UtensilEditor()
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder("Utensils"));
    utensils = new ArrayList<Utensil>();
    
    UtensilEditorListener listener = new UtensilEditorListener(this);
    
    
    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    
    JButton addButton = new JButton(ADD);
    addButton.addActionListener(listener);

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    inputFields.add(new JLabel("Name:"));
    inputFields.add(nameField);
    
    inputFields.add(new JLabel("Details:"));
    inputFields.add(detailField);
    
    inputFields.add(addButton);
    
    add(inputFields, BorderLayout.NORTH);
    
    JButton deleteButton = new JButton(DELETE);
    deleteButton.addActionListener(listener);
    add(deleteButton, BorderLayout.EAST);
    
    utensilDisplay = new TextArea();
    utensilDisplay.setEditable(false);
    add(utensilDisplay, BorderLayout.CENTER);
    
    setVisible(true);
  }
  
  private void add()
  {
    if(nameField.getText().equals("")) return;
    
    Utensil utensil = new Utensil(nameField.getText(), detailField.getText());
    utensils.add(utensil);
    nameField.setText("");
    detailField.setText("");
    
    SortLists.sortUtensils(utensils);
    
    updateText();
  }
  
  private void updateText()
  {
    String displayText = "";
    
    for(Utensil utensil : utensils)
    {
      displayText += String.format("%s\t%s\n", utensil.getName(), utensil.getDetails());
    }
    
    utensilDisplay.setText(displayText);
  }
  
  private void delete()
  {
    if(utensils.size() == 0) return;
    utensils.remove(utensils.size() - 1);
    updateText();
  }
  
  private class UtensilEditorListener implements ActionListener
  {
    UtensilEditor editor;
    
    private UtensilEditorListener(final UtensilEditor editor)
    {
      this.editor = editor;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if(e.getActionCommand().equals(ADD))
      {
        editor.add();
      }
      else if (e.getActionCommand().equals(DELETE))
      {
        editor.delete();
      }
    }
    
  }
}
