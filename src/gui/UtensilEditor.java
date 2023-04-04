package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import recipes.Utensil;
import utilities.SortLists;

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
  private final JButton addButton, deleteButton;
  private final List<Utensil> utensils;

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
    
    addButton = new JButton(ADD);
    addButton.setActionCommand(RecipeEditor.UTENSIL_ADD_ACTION_COMMAND);
    addButton.addActionListener(listener);

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    inputFields.add(new JLabel("Name:"));
    inputFields.add(nameField);
    
    inputFields.add(new JLabel("Details:"));
    inputFields.add(detailField);
    
    inputFields.add(addButton);
    
    add(inputFields, BorderLayout.NORTH);
    
    deleteButton = new JButton(DELETE);
    deleteButton.addActionListener(listener);
    deleteButton.setActionCommand(RecipeEditor.UTENSIL_DELETE_ACTION_COMMAND);
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
      displayText += String.format("%s\n", utensil.toString());
    }
    
    utensilDisplay.setText(displayText);
  }
  
  private void delete()
  {
    if(utensils.size() == 0) return;
    utensils.remove(utensils.size() - 1);
    updateText();
  }
  
  /**
   * Adds a text listener to the text area of the utensil editor.
   * 
   * @param listener the text listener to add to the display text area.
   */
  public void addTextListener(final TextListener listener)
  {
    utensilDisplay.addTextListener(listener);
  }
  
  List<Utensil> getUtensils()
  {
    return utensils;
  }
  
  void loadUtensils(final List<Utensil> newUtensils)
  {
    this.utensils.clear();
    
    for(Utensil utensil : newUtensils)
    {
      utensils.add(utensil);
    }
    
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
      if(e.getActionCommand().equals(RecipeEditor.UTENSIL_ADD_ACTION_COMMAND))
      {
        editor.add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.UTENSIL_DELETE_ACTION_COMMAND))
      {
        editor.delete();
      }
    }
    
  }

  /**
   * Adds an action listener to the buttons in this UtensilEditor which can cause the
   * document to change.
   * @param listener The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }
}
