package com.example.MotorphPayrollSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferna
 */
public class Employee_Database extends javax.swing.JFrame {

    /**
     * Creates new form Employee_Database
     */
    public Employee_Database() {
        initComponents();
    
    // Load CSV data into the table
    loadCSVData("MotorPH Employee Data - Employee Details (2).csv");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("MotorPH Employee Database");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee Number", "Last Name", "First Name", "SSS Number", "PhilHealth Number", "Pag-IBIG Number", "TIN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Employee_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_Database().setVisible(true);
            }
        });
    }

public void loadCSVData(String filename) {
    List<String[]> data = new ArrayList<>();
    
    try {
        // Use absolute file path
        String absolutePath = "C:\\Users\\ferna\\Documents\\NetBeansProjects\\MotorphPayrollSystem\\app\\src\\main\\resources\\MotorPH Employee Data - Employee Details (2).csv";
        System.out.println("Attempting to load file: " + absolutePath);
        
        File file = new File(absolutePath);
        if (!file.exists()) {
            System.out.println("ERROR: File not found at path: " + absolutePath);
            return;
        }
        
        System.out.println("File found: " + absolutePath);
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String line;
        while ((line = br.readLine()) != null) {
            // Some CSV files may have commas within quoted fields, handle that case
            if (line.contains("\"")) {
                // This is a simple approach - for complex CSVs, consider a library like OpenCSV
                List<String> values = new ArrayList<>();
                boolean inQuotes = false;
                StringBuilder currentValue = new StringBuilder();
                
                for (char c : line.toCharArray()) {
                    if (c == '\"') {
                        inQuotes = !inQuotes;
                    } else if (c == ',' && !inQuotes) {
                        values.add(currentValue.toString());
                        currentValue = new StringBuilder();
                    } else {
                        currentValue.append(c);
                    }
                }
                values.add(currentValue.toString());
                
                data.add(values.toArray(new String[0]));
            } else {
                // Simple case - just split by comma
                String[] values = line.split(",");
                data.add(values);
            }
        }
        br.close();
        
        System.out.println("Total lines read: " + data.size());
        
        if (data.size() > 0) {
            populateTable(data);
        } else {
            System.out.println("No data found in file!");
        }
        
    } catch (Exception e) {
        System.out.println("ERROR reading file: " + e.getMessage());
        e.printStackTrace();
    }
}

public void populateTable(List<String[]> data) {
    // Define column names for your table
    String[] columnNames = {"Employee Number", "Last Name", "First Name", "SSS Number", "PhilHealth Number", "Pag-IBIG Number", "TIN"};
    
    // Create table model with your defined column names
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    
    // Skip header row (assume first row is header)
    for (int i = 1; i < data.size(); i++) {
        String[] csvRow = data.get(i);
        
        // Create a new row with only the columns we need
        // The indexes match the CSV column positions
        Object[] tableRow = new Object[7];
        if (csvRow.length >= 19) {  // Make sure we have enough columns
            tableRow[0] = csvRow[0];  // Employee #
            tableRow[1] = csvRow[1];  // Last Name
            tableRow[2] = csvRow[2];  // First Name
            tableRow[3] = csvRow[6];  // SSS #
            tableRow[4] = csvRow[7];  // Philhealth #
            tableRow[5] = csvRow[9];  // Pag-ibig #
            tableRow[6] = csvRow[8];  // TIN #
        }
        
        model.addRow(tableRow);
    }
    
    // Set the model to the table
    jTable2.setModel(model);
}
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}


