package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomShortcutsGUI extends JFrame
{
  private Map<String, String> shortcutsMap;
  private JTable shortcutsTable;

  public CustomShortcutsGUI(Map<String, String> shortcutsMap)
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
    
    // Create a button to reset shortcuts to defaults
    JButton resetButton = new JButton("Reset to Defaults");
    resetButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
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
      public void actionPerformed(ActionEvent e)
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
      public void actionPerformed(ActionEvent e)
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
}
