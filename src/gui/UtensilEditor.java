package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Recipe;
import recipes.Utensil;

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
  private final RecipeEditor parent;
  private final UpdateListener updateListener;
  private final DeleteEnabler delListener;

  /**
   * Creates a new UtensilEditor.
   * @param workingRecipe the recipe to edit the utensils of.
   * @param stepEditor the corresponding StepEditor
   * @param parent The RecipeEditor which this UtensilEditor belongs to. Required for this to resize
   * its parent.
   */
  public UtensilEditor(final Recipe workingRecipe, final StepEditor stepEditor, 
      final RecipeEditor parent)
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Utensils")));
    
    this.workingRecipe = workingRecipe;
    this.stepEditor = stepEditor;
    this.parent = parent;

    UtensilEditorListener listener = new UtensilEditorListener();
    updateListener = new UpdateListener();

    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    nameField.addActionListener(updateListener);
    detailField.addActionListener(updateListener);

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
    updateUtensilDisplay();
    add(utensilDisplay, BorderLayout.CENTER);
    
    delListener = new DeleteEnabler(utensilDisplay, deleteButton);
    utensilDisplay.getSelectionModel().addListSelectionListener(delListener);

    setVisible(true);
    
    delListener.valueChanged(null);
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
    
    stepEditor.updateSelects();
  }

  /**
   * Updates the JTable which displays the Utensils in this recipe. This must be called whenever the
   * Utensils of the recipe change.
   */
  public void updateUtensilDisplay()
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
    
    
    parent.pack();
  }
  
  private void delete() 
  {
    if (workingRecipe.getUtensils().size() == 0)
    {
      return;
    }

    int index = utensilDisplay.getSelectedRow();
        
    if (index < workingRecipe.getUtensils().size()) 
    {
      Utensil utensil = workingRecipe.getUtensils().get(index);
      
      workingRecipe.removeUtensil(utensil);

      updateUtensilDisplay();
    }

    stepEditor.updateSelects();  
  }

  /**
   * Returns the utensils in the Recipe being edited by this UtensilEditor.
   * @return The List of Utensils in the Recipe referenced by this editor.
   */
  public List<Utensil> getUtensils()
  {
    return workingRecipe.getUtensils();
  }

  /**
   * Replaces the Utensils in the Recipe being edited by this UtensilEditor with the given Utensils.
   * @param newUtensils The Utensils to put in the Recipe being edited by this UtensilEditor.
   */
  public void loadUtensils(final List<Utensil> newUtensils)
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
      addButton.setEnabled(nameField.getText().length() > 0);  
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
  
  /**
   * Changes the Recipe which this UtensilEditor edits.
   * @param recipe The Recipe which this UtensilEditor will now edit.
   */
  public void setWorkingRecipe (Recipe recipe) 
  {
    this.workingRecipe = recipe;
  }
  
  public void setEditable(final boolean editable)
  {
    addButton.setEnabled(editable);
    deleteButton.setEnabled(editable);
    detailField.setEditable(editable);
    nameField.setEditable(editable);
    
    updateListener.actionPerformed(null);
    delListener.valueChanged(null);
  }
  
}
