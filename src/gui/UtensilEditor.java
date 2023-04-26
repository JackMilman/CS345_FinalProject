package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Ingredient;
import recipes.Recipe;
import recipes.Utensil;
import utilities.SortLists;

/**
 * A class for the UtensilEditor component of the recipe editor.
 * 
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
  private final JTable utensilDisplay;
  private final JButton addButton, deleteButton;
  private Recipe workingRecipe;
  private StepEditor stepEditor;

  /**
   * Creates a new UtensilEditor.
   * @param workingRecipe the recipe to edit the utensils of.
   * @param the corresponding StepEditor
   */
  public UtensilEditor(final Recipe workingRecipe, final StepEditor stepEditor)
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Utensils")));
    
    this.workingRecipe = workingRecipe;
    this.stepEditor = stepEditor;

    UtensilEditorListener listener = new UtensilEditorListener();

    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    nameField.addActionListener(new UpdateListener());

    addButton = new JButton(Translator.translate(ADD));
    addButton.setActionCommand(RecipeEditor.UTENSIL_ADD_ACTION_COMMAND);
    addButton.addActionListener(listener);
    
    addButton.setEnabled(false);

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));

    inputFields.add(new JLabel(Translator.translate("Name") + ":"));
    inputFields.add(nameField);

    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);

    inputFields.add(addButton);

    add(inputFields, BorderLayout.NORTH);

    deleteButton = new JButton(DELETE);
    deleteButton.addActionListener(listener);
    deleteButton.setActionCommand(RecipeEditor.UTENSIL_DELETE_ACTION_COMMAND);
    add(deleteButton, BorderLayout.EAST);

    utensilDisplay = new JTable(new DefaultTableModel(1,1));
    add(utensilDisplay, BorderLayout.CENTER);

    setVisible(true);
  }

  private void add()
  {
    if (nameField.getText().equals(""))
      return;

    Utensil utensil = new Utensil(nameField.getText(), detailField.getText());
    workingRecipe.addUtensil(utensil);
    nameField.setText("");
    detailField.setText("");

    updateUtensilDisplay();
    
    addButton.setEnabled(false);
    
    stepEditor.update();
  }

  void updateUtensilDisplay()
  {
    
    DefaultTableModel tableModel = new DefaultTableModel(workingRecipe.getUtensils().size(), 1)
    {

      private static final long serialVersionUID = 1L;

      @Override
      public boolean isCellEditable(final int row, final int col)
      {
        return false;
      }
    };
    
    utensilDisplay.setModel(tableModel);
    List<Utensil> utensilsList = workingRecipe.getUtensils();

    for (int i = 0; i < utensilsList.size(); i++)
    {
      utensilDisplay.setValueAt(utensilsList.get(i), i, 0);
    }

  }
  
  private void delete() 
  {
    //TODO
  }

  List<Utensil> getUtensils()
  {
    return workingRecipe.getUtensils();
  }

  void loadUtensils(final List<Utensil> newUtensils)
  {
    workingRecipe.getUtensils().clear();
    workingRecipe.addAllUtensils(newUtensils);    

    updateUtensilDisplay();
  }

  private class UtensilEditorListener implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (e.getActionCommand().equals(RecipeEditor.UTENSIL_ADD_ACTION_COMMAND))
      {
        add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.UTENSIL_DELETE_ACTION_COMMAND))
      {
        delete();
      }
    }

  }
  
  private class UpdateListener implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      System.out.println("Action performed");
      addButton.setEnabled(false);
      if(nameField.getText().length() > 0)
      {
        addButton.setEnabled(true);
      }    
    }
    
  }

  /**
   * Adds an action listener to the buttons in this UtensilEditor which can cause the document to
   * change.
   * 
   * @param listener
   *          The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }
}
