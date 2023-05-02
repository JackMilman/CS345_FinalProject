package gui;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;

/**
 * GUI for creating custom shortcuts.
 * 
 * @author KitchIntel
 * @version
 */
public class CustomShortcutsGUI extends JFrame implements TableModelListener
{
  private Map<String, String> shortcutsMap;
  private JTable shortcutsTable;

  /**
   * Create a GUI for custom shortcuts.
   * 
   * @param shortcutsMap
   */
  public CustomShortcutsGUI(final Map<String, String> shortcutsMap)
  {
    super("Customize Shortcuts");
    this.shortcutsMap = shortcutsMap;
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    JPanel contentPane = new JPanel(new BorderLayout());

    // Create a table to display the current shortcuts
    String[] columnNames = {"Action", "Shortcut"};
    Object[][] data = new Object[shortcutsMap.size()][2];
    int i = 0;
    for (String action : shortcutsMap.keySet())
    {
      data[i][0] = action;
      data[i][1] = shortcutsMap.get(action);
      i++;
    }

    shortcutsTable = new JTable(data, columnNames);
    JScrollPane tableScrollPane = new JScrollPane(shortcutsTable);
    tableScrollPane.setPreferredSize(new Dimension(300, 150));
    contentPane.add(tableScrollPane, BorderLayout.CENTER);
    
    // Add table model listener to detect changes in the table
    shortcutsTable.getModel().addTableModelListener(this);

    // Create a button to reset shortcuts to defaults
    JButton resetButton = new JButton("Reset to Defaults");
    resetButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e)
      {
        // fill in the defaults
        shortcutsMap.clear();
        shortcutsMap.put(KiLowBitesController.RECIPE, "ctrl R");
        shortcutsMap.put(KiLowBitesController.MEAL, "ctrl M");
        shortcutsMap.put(KiLowBitesController.SHOPPING, "ctrl L");
        shortcutsMap.put(KiLowBitesController.PROCESS, "ctrl P");
        shortcutsMap.put(KiLowBitesController.INVENTORY, "ctrl I");
        shortcutsMap.put(KiLowBitesController.CALORIECALCULATOR, "ctrl O");
        shortcutsMap.put(KiLowBitesController.UNITSCONVERTER, "ctrl U");
        shortcutsMap.put(KiLowBitesController.PREFERENCES, "ctrl F");
        shortcutsMap.put("Shortcuts", "ctrl K");
        shortcutsMap.put(KiLowBitesController.USERGUIDE, "ctrl G");
        refreshTableData();
      }
    });

    // Create a button to save the current shortcuts
    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e)
      {
        try
        {
          FileWriter writer = new FileWriter("shortcuts.cfg");
          for (String action : shortcutsMap.keySet())
          {
            writer.write(action + "=" + shortcutsMap.get(action) + "\n");
          }
          writer.close();
          JOptionPane.showMessageDialog(CustomShortcutsGUI.this, "Shortcuts saved successfully.");
        }
        catch (IOException ex)
        {
          JOptionPane.showMessageDialog(CustomShortcutsGUI.this,
              "Error saving shortcuts: " + ex.getMessage());
        }
      }
    });

    // Create a button to close the window
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e)
      {
        dispose();
      }
    });

    // Create a panel to hold the buttons
    JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
    buttonPanel.add(resetButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(closeButton);
    contentPane.add(buttonPanel, BorderLayout.SOUTH);

    setContentPane(contentPane);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void refreshTableData()
  {
    String[] columnNames = {"Action", "Shortcut"};
    Object[][] data = new Object[shortcutsMap.size()][2];
    int i = 0;
    for (String action : shortcutsMap.keySet())
    {
      data[i][0] = action;
      data[i][1] = shortcutsMap.get(action);
      i++;
    }
    shortcutsTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
  }

  @Override
  public void tableChanged(TableModelEvent e)
  {
    int row = e.getFirstRow();
    int column = e.getColumn();
    TableModel model = (TableModel) e.getSource();
    String columnName = model.getColumnName(column);
    if (columnName.equals("Shortcut"))
    {
      String action = (String) model.getValueAt(row, 0);
      String shortcut = (String) model.getValueAt(row, column);
      shortcutsMap.put(action, shortcut);
    }
  }
}
