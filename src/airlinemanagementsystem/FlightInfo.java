package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class FlightInfo extends JFrame {
    
    DefaultTableModel model;

    public FlightInfo() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Column headers for the table
        String[] columnHeaders = {"Flight Code", "Flight Name", "Source", "Destination"};
        
        // Initialize DefaultTableModel with column headers
        model = new DefaultTableModel(columnHeaders, 0);
        
        // Set up JTable with the model
        JTable table = new JTable(model);

        try {
            Conn c = new Conn();
            String sql = "SELECT * FROM flight";
            ResultSet rs = c.s.executeQuery(sql);
            
            // Populate table with data from ResultSet
            while (rs.next()) {
                String code = rs.getString("f_code");
                String name = rs.getString("f_name");
                String source = rs.getString("source");
                String dest = rs.getString("destination");
                
                Object[] rowData = {code, name, source, dest};
                model.addRow(rowData);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Add table to JScrollPane and set bounds
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);
        
        // Frame settings
        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}
