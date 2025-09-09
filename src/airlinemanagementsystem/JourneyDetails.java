package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
//import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener{
	
    JTable table;
    JTextField pnr;
    JButton show;
    DefaultTableModel model;
    
    public JourneyDetails() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        String[] columnHeaders = {"c_name", "c_nationality", "c_source", "c_dest" ,"c_fname","c_fcode","c_date","PNR","Ticket","c_aadhar"};
        
        // Initialize DefaultTableModel with column headers
        model = new DefaultTableModel(columnHeaders, 0);
        
        
        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 100, 25);
        add(lblpnr);
        
        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        add(pnr);
        
        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);
        
        table = new JTable(model);
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        jsp.setBackground(Color.WHITE);
        add(jsp);
        
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn conn = new Conn(); // your connection class

            String enteredPNR = pnr.getText().trim().toUpperCase(); // sanitize and normalize
            System.out.println("Entered PNR: '" + enteredPNR + "'");

            String query = "SELECT * FROM reservation WHERE UPPER(PNR) = ?";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, enteredPNR);

            ResultSet rs = pst.executeQuery();
 
            
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            while (rs.next()) {
            	
                String c_name = rs.getString("name");
                String c_nationality = rs.getString("nationality");
                String c_source = rs.getString("src");
                String c_dest = rs.getString("des");
                String c_fname = rs.getString("flightname");
                String c_fcode = rs.getString("flightcode");
                String c_date = rs.getString("ddate");
                String pnr = rs.getString("PNR");
                String tick = rs.getString("TICKET");
                String c_aadhar = rs.getString("aadhar");
                Object[] rowData = {c_name, c_nationality, c_source, c_dest ,c_fname,c_fcode,c_date,pnr,tick,c_aadhar};
                model.addRow(rowData);
                System.out.print("fasklf");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}
