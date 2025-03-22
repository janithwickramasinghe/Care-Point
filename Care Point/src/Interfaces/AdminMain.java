package Interfaces;

import Codes.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AdminMain extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void Assign(){
        String name;
        String age;
        String gender;
        String phone;
        String email;
        String password;
        String address;

        
        name = Assign_Patient_Name.getText();
        age = Assign_Patient_Age.getText();
        gender = Assign_GenderComboBox.getSelectedItem().toString();
        phone = Assign_Patient_Phone.getText();
        email = Assign_Patient_Email.getText();
        password = Assign_Patient_Password.getText();
        address = Assign_Patient_Address.getText();
        
    }  
    
    public AdminMain() {
        initComponents();
        conn = DBconnection.Connect();
        patienttableload();
        doctortableload();
        hospitaltableload();
        Availabilitytableload();
        Appointmenttableload();
        Admintableload();
    }

    String PassedAppointmentID = MainMenu.passAppointmentID;
    

    public void patienttableload(){
        try {
            String sql = "SELECT * FROM Patient";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void doctortableload(){
        try {
            String sql = "SELECT * FROM Doctor";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void hospitaltableload(){
        try {
            String sql = "SELECT * FROM Hospital";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table3.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  
 
    public void Availabilitytableload(){
        try {
            String sql = "SELECT * FROM Availability";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table4.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } 
    
    public static void A(){
      
    }
    
    public void Appointmenttableload(){
        try {
            String sql = "SELECT * FROM Appointment";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Admintableload(){
        try {
            String sql = "SELECT * FROM Admin";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table6.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private static final String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

     public static boolean isValidGmail(String email) {
        if (email == null || email.isEmpty()) {
            // Show error message if email is null or empty
            JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Pattern.matches(regex, email)) {
            // Show error message if email does not have "@gmail.com"
            JOptionPane.showMessageDialog(null, "Invalid Gmail address! Please enter a valid Gmail address.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
     public static boolean isValidSriLankaPhoneNumber(String phoneNumber) {
        
        String regex = "^(0(7\\d{8}|1\\d{8}))$"; 
        
        if (phoneNumber != null && phoneNumber.matches(regex)) {
            return true;
        } else {
            
            JOptionPane.showMessageDialog(null, "Invalid phone number! Please enter a valid Sri Lankan phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
     
     public static boolean isValidAge(String age) {
        if (age == null || age.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Age cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
        if (!age.matches("\\d+")) {
            
            JOptionPane.showMessageDialog(null, "Age must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
        if (Integer.parseInt(age)<0 ||Integer.parseInt(age)>120){
            
            JOptionPane.showMessageDialog(null, "Age must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
        
        return true;
    }
     
     
     public static boolean isValidDate(String date) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Disable lenient parsing (strict format validation)

        try {
            
            dateFormat.parse(date);
            return true; 
        } catch (ParseException e) {
            
            JOptionPane.showMessageDialog(null, "Invalid date format! Please enter date in dd/MM/yyyy format.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
    }
     
     
     
     public static boolean isValidTime(String time) {
 
    String timePattern = "^([01]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$";
    
    Pattern pattern = Pattern.compile(timePattern);
    Matcher matcher = pattern.matcher(time);
    
    if (matcher.matches()) {
        return true;
    } else {
        
        JOptionPane.showMessageDialog(null, "Invalid time format! Please enter time in HH:mm:ss format.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

     public boolean isNotEmpty(String str) {
    return str != null && !str.trim().isEmpty();
}
     
     
     
    
    int insertOrUpdate;
    
    //table 1
        String T1Id;
        String T1name;
        String T1age;
        String T1gender;
        String T1phone;
        String T1email;
        String T1password;
        String T1address;
    
    //table 2
        int T2Id;
        String T2name;
        String T2specialization;
        String T2email;
        double T2charge;
    
    //Table 3
        int T3Id;
        String T3name;
        String T3location;
        String T3contact;
        double T3charge;
    
    //Table 4
        int T4Id;
        int T4DId;
        int T4HId;
        String T4date;
        String T4time;
        String T4status;
        
    //Table 6
        int T6Id;
        String T6name;
        String T6password;
        String T6email;
        
        int NewAvailable;
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table4 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        table5 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        table6 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        Ubtn = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Assign_Patient_Phone = new javax.swing.JTextField();
        Assign_Patient_Email = new javax.swing.JTextField();
        Assign_Patient_Name = new javax.swing.JTextField();
        Assign_Patient_Address = new javax.swing.JTextField();
        ShowPasswordCheck = new javax.swing.JCheckBox();
        Assign_Patient_Password = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        Assign_Patient_Age = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Assign_GenderComboBox = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Assign_Doctor_Contact_Number = new javax.swing.JTextField();
        Assign_Doctor_Name = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Assign_Doctor_Specialization = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        Assign_Doctor_Change = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Assign_Hospital_Contact_Number = new javax.swing.JTextField();
        Assign_Hospital_Name = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        Assign_Hospital_Location = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        Assign_Hospital_Hospital_Charge = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Assign_Availability_Date = new javax.swing.JTextField();
        Assign_Availability_Doctor_ID = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        Assign_Availability_Hospital_ID = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        Assign_Availability_Status = new javax.swing.JComboBox<>();
        Assign_Availability_Time = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        Assign_Patient_ID = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        Assign_Avalibility_ID = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        Assign_Appointment_ID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        Assign_Email = new javax.swing.JTextField();
        Assign_User_Name = new javax.swing.JTextField();
        Assign_Password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("Patient");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 40));

        jButton3.setText("Doctor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 110, 40));

        jButton4.setText("Hospital");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 110, 40));

        jButton8.setText("Availability");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 110, 40));

        jButton12.setText("<--");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

        jButton5.setText("Appointment");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 110, 40));

        jButton1.setText("Admin");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 110, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1111.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-290, 0, 570, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 430));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/healthcare.png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 730, 430));

        jTabbedPane1.addTab("0", jPanel2);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(102, 153, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setText("Insert");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jButton11.setText("Update");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jButton13.setText("Delete");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 730, 130));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Doctor_ID", "Name", "Specialization", "Contact_Info", "Doctor_Charge"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 290));

        jTabbedPane1.addTab("1", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(102, 153, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton14.setText("Insert");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jButton15.setText("Update");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jButton16.setText("Delete");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 730, 130));

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Hospital_ID", "Name", "Location", "Contact_Info", "Hospital_Charge"
            }
        ));
        table3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table3);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 290));

        jTabbedPane1.addTab("2", jPanel5);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(102, 153, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton17.setText("Insert");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jButton18.setText("Update");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jButton19.setText("Delete");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        jPanel6.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 730, 130));

        table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Availability_ID", "Doctor_ID", "Hospital_ID", "Available_Date", "Available_Time", "Is_Booked"
            }
        ));
        table4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table4);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 290));

        jTabbedPane1.addTab("3", jPanel6);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(102, 153, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton22.setText("Delete");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        jPanel7.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 730, 110));

        table5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Appointment_ID", "Patient_ID", "Availability_ID", "Total_Charge"
            }
        ));
        table5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table5);

        jPanel7.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 310));

        jTabbedPane1.addTab("4", jPanel7);

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(102, 153, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton23.setText("Insert");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jButton24.setText("Update");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jButton25.setText("Delete");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        jPanel8.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 730, 130));

        table6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Admin_ID", "Username", "Password", "Email"
            }
        ));
        table6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(table6);

        jPanel8.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 290));

        jTabbedPane1.addTab("5", jPanel8);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient_ID", "Name", "Age", "Gender", "Phone", "Email", "Password", "Address"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 290));

        jPanel9.setBackground(new java.awt.Color(102, 153, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ubtn.setText("Update");
        Ubtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbtnActionPerformed(evt);
            }
        });
        jPanel9.add(Ubtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jButton7.setText("Insert");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jButton30.setText("Delete");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 730, 130));

        jTabbedPane1.addTab("6", jPanel3);

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(204, 255, 204));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Name");
        jPanel16.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel2.setText("Email");
        jPanel16.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel3.setText("Password");
        jPanel16.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jLabel4.setText("Phone Number");
        jPanel16.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jLabel5.setText("Address");
        jPanel16.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));
        jPanel16.add(Assign_Patient_Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 410, -1));

        Assign_Patient_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Patient_EmailActionPerformed(evt);
            }
        });
        jPanel16.add(Assign_Patient_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 410, -1));

        Assign_Patient_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Patient_NameActionPerformed(evt);
            }
        });
        jPanel16.add(Assign_Patient_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 410, -1));
        jPanel16.add(Assign_Patient_Address, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 410, -1));

        ShowPasswordCheck.setText("Show Password");
        ShowPasswordCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPasswordCheckActionPerformed(evt);
            }
        });
        jPanel16.add(ShowPasswordCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, -1, -1));

        Assign_Patient_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Patient_PasswordActionPerformed(evt);
            }
        });
        jPanel16.add(Assign_Patient_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 410, -1));

        jLabel7.setText("Age");
        jPanel16.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));
        jPanel16.add(Assign_Patient_Age, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 60, -1));

        jLabel8.setText("Gender");
        jPanel16.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        Assign_GenderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel16.add(Assign_GenderComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        jButton6.setText("Ok");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, -1, -1));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 420));

        jTabbedPane1.addTab("7", jPanel15);

        jPanel22.setBackground(new java.awt.Color(204, 255, 204));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Name");
        jPanel22.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel9.setText("Email");
        jPanel22.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel10.setText("Doctor Charge");
        jPanel22.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        Assign_Doctor_Contact_Number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Doctor_Contact_NumberActionPerformed(evt);
            }
        });
        jPanel22.add(Assign_Doctor_Contact_Number, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 410, -1));

        Assign_Doctor_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Doctor_NameActionPerformed(evt);
            }
        });
        jPanel22.add(Assign_Doctor_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 410, -1));

        jLabel13.setText("Specialization");
        jPanel22.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));
        jPanel22.add(Assign_Doctor_Specialization, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 410, -1));

        jButton9.setText("Ok");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel22.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, -1, -1));
        jPanel22.add(Assign_Doctor_Change, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 130, -1));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("8", jPanel17);

        jPanel23.setBackground(new java.awt.Color(204, 255, 204));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setText("Name");
        jPanel23.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel14.setText("Email Address");
        jPanel23.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel15.setText("Hospital Charge");
        jPanel23.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        Assign_Hospital_Contact_Number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Hospital_Contact_NumberActionPerformed(evt);
            }
        });
        jPanel23.add(Assign_Hospital_Contact_Number, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 410, -1));

        Assign_Hospital_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Hospital_NameActionPerformed(evt);
            }
        });
        jPanel23.add(Assign_Hospital_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 410, -1));

        jLabel17.setText("Location");
        jPanel23.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));
        jPanel23.add(Assign_Hospital_Location, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 410, -1));

        jButton26.setText("Ok");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, -1, -1));
        jPanel23.add(Assign_Hospital_Hospital_Charge, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 140, -1));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("9", jPanel18);

        jPanel24.setBackground(new java.awt.Color(204, 255, 204));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setText("Doctor ID");
        jPanel24.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel18.setText("Date");
        jPanel24.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel19.setText("Time");
        jPanel24.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jLabel20.setText("Status");
        jPanel24.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        Assign_Availability_Date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Availability_DateActionPerformed(evt);
            }
        });
        jPanel24.add(Assign_Availability_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 130, -1));

        Assign_Availability_Doctor_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Availability_Doctor_IDActionPerformed(evt);
            }
        });
        jPanel24.add(Assign_Availability_Doctor_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 130, -1));

        jLabel21.setText("Hospital ID");
        jPanel24.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));
        jPanel24.add(Assign_Availability_Hospital_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 130, -1));

        jButton27.setText("Ok");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, -1, -1));

        Assign_Availability_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Booked" }));
        jPanel24.add(Assign_Availability_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));
        jPanel24.add(Assign_Availability_Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 130, -1));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("10", jPanel19);

        jPanel25.setBackground(new java.awt.Color(204, 255, 204));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setText("Patient ID");
        jPanel25.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel24.setText("Total Charge");
        jPanel25.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel25.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 130, -1));

        Assign_Patient_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_Patient_IDActionPerformed(evt);
            }
        });
        jPanel25.add(Assign_Patient_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 130, -1));

        jLabel28.setText("Availibility ID");
        jPanel25.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));
        jPanel25.add(Assign_Avalibility_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 130, -1));

        jButton28.setText("Ok");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel25.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, -1, -1));
        jPanel25.add(Assign_Appointment_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 130, -1));

        jLabel25.setText("Appointment ID");
        jPanel25.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("11", jPanel20);

        jPanel26.setBackground(new java.awt.Color(204, 255, 204));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setText("User Name");
        jPanel26.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jButton29.setText("Ok");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, -1, -1));

        jLabel27.setText("Password");
        jPanel26.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel29.setText("Email");
        jPanel26.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));
        jPanel26.add(Assign_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 280, -1));
        jPanel26.add(Assign_User_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 280, -1));
        jPanel26.add(Assign_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 280, -1));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("12", jPanel21);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -30, 730, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to go Login Page?");
        
        if(s == 0){
            Login login = new Login();
            login.show();
            dispose();            
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void Assign_Patient_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Patient_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Patient_PasswordActionPerformed

    private void ShowPasswordCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowPasswordCheckActionPerformed
        if(ShowPasswordCheck.isSelected()){
            Assign_Patient_Password.setEchoChar((char)0);
        }
        else{
            Assign_Patient_Password.setEchoChar(('*'));
        }
    }//GEN-LAST:event_ShowPasswordCheckActionPerformed

    private void Assign_Patient_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Patient_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Patient_NameActionPerformed

    private void Assign_Patient_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Patient_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Patient_EmailActionPerformed
  
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        
        
        int s = JOptionPane.showConfirmDialog(null, "Do you want to insert a patient?");
        
        if(s == 0){
            insertOrUpdate = 1;
            jTabbedPane1.setSelectedIndex(7);
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void UbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbtnActionPerformed
        // TODO add your handling code here:        
        
        int s = JOptionPane.showConfirmDialog(null, "Do you want to Update?");
        
        if(s == 0){
            
            Assign_Patient_Name.setText(T1name);
            Assign_Patient_Age.setText(T1age);
            Assign_GenderComboBox.setSelectedItem(T1gender);
            Assign_Patient_Phone.setText(T1phone);
            Assign_Patient_Email.setText(T1email);
            Assign_Patient_Password.setText(T1password);
            Assign_Patient_Address.setText(T1address);
            
            insertOrUpdate = 2;
            jTabbedPane1.setSelectedIndex(7);
        }
       
    }//GEN-LAST:event_UbtnActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to insert hospital?");
        
        if(s == 0){
            insertOrUpdate = 1;
            jTabbedPane1.setSelectedIndex(9);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want Insert a doctor?");
        
        if(s == 0){
            insertOrUpdate = 1;
            jTabbedPane1.setSelectedIndex(8);
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:
        int r = table2.getSelectedRow();

        T2Id = Integer.parseInt(table2.getValueAt(r, 0).toString());
        T2name = table2.getValueAt(r, 1).toString();
        T2specialization = table2.getValueAt(r, 2).toString();
        T2email = table2.getValueAt(r, 3).toString();
        T2charge = Double.parseDouble(table2.getValueAt(r, 4).toString());
    }//GEN-LAST:event_table2MouseClicked

    private void table3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table3MouseClicked
        // TODO add your handling code here:
        
        int r = table3.getSelectedRow();

        T3Id = Integer.parseInt(table3.getValueAt(r, 0).toString());
        T3name = table3.getValueAt(r, 1).toString();
        T3location = table3.getValueAt(r, 2).toString();
        T3contact = table3.getValueAt(r, 3).toString();
        T3charge = Double.parseDouble(table3.getValueAt(r, 4).toString());
        
        
    }//GEN-LAST:event_table3MouseClicked

    private void table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table4MouseClicked
        // TODO add your handling code here:
        
        int r = table4.getSelectedRow();
        
        T4Id = Integer.parseInt(table4.getValueAt(r, 0).toString());
        T4DId = Integer.parseInt(table4.getValueAt(r, 1).toString());
        T4HId = Integer.parseInt(table4.getValueAt(r, 2).toString());
        T4date = table4.getValueAt(r, 3).toString();
        T4time = table4.getValueAt(r, 4).toString();
        T4status = table4.getValueAt(r, 5).toString();
            
    }//GEN-LAST:event_table4MouseClicked

    private void table5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table5MouseClicked
        // TODO add your handling code here: 
        int r = table5.getSelectedRow();
        
        NewAvailable = Integer.parseInt(table5.getValueAt(r, 2).toString());
    }//GEN-LAST:event_table5MouseClicked

    private void table6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table6MouseClicked
        // TODO add your handling code here:
        int r = table6.getSelectedRow();
        
        T6Id = Integer.parseInt(table6.getValueAt(r, 0).toString());
        T6name = table6.getValueAt(r, 1).toString();
        T6password = table6.getValueAt(r, 2).toString();
        T6email = table6.getValueAt(r, 3).toString();
    }//GEN-LAST:event_table6MouseClicked

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:     
        
        int r = table1.getSelectedRow();

        T1Id = table1.getValueAt(r, 0).toString();
        T1name = table1.getValueAt(r, 1).toString();
        T1age = table1.getValueAt(r, 2).toString();
        T1gender = table1.getValueAt(r, 3).toString();
        T1phone = table1.getValueAt(r, 4).toString();
        T1email = table1.getValueAt(r, 5).toString();
        T1password = table1.getValueAt(r, 6).toString();
        T1address = table1.getValueAt(r, 7).toString();
        
    }//GEN-LAST:event_table1MouseClicked

    private void Assign_Doctor_Contact_NumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Doctor_Contact_NumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Doctor_Contact_NumberActionPerformed

    private void Assign_Doctor_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Doctor_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Doctor_NameActionPerformed

    private void Assign_Hospital_Contact_NumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Hospital_Contact_NumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Hospital_Contact_NumberActionPerformed

    private void Assign_Hospital_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Hospital_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Hospital_NameActionPerformed

    private void Assign_Availability_DateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Availability_DateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Availability_DateActionPerformed

    private void Assign_Availability_Doctor_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Availability_Doctor_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Availability_Doctor_IDActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void Assign_Patient_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_Patient_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Assign_Patient_IDActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to insert a time?");
        
        if(s == 0){
            insertOrUpdate = 1;
            jTabbedPane1.setSelectedIndex(10);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to insert an admin?");
        
        if(s == 0){
            insertOrUpdate = 1;
            jTabbedPane1.setSelectedIndex(12);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        if(insertOrUpdate == 1){

            int s = JOptionPane.showConfirmDialog(null, "Want to insert this record?");

            if(s == 0){

                String name = Assign_Patient_Name.getText();
                int age = Integer.parseInt(Assign_Patient_Age.getText());
                String gender = Assign_GenderComboBox.getSelectedItem().toString();
                String phone = Assign_Patient_Phone.getText();
                String email = Assign_Patient_Email.getText();
                String password = Assign_Patient_Password.getText();
                String address = Assign_Patient_Address.getText();
                
                String agestring = String.valueOf(age);

                if(isValidGmail(email) && isValidSriLankaPhoneNumber(phone) && isValidAge(agestring) && isNotEmpty(name) && isNotEmpty(name) && isNotEmpty(address)){
                
                            try {           

                                String sql = "INSERT INTO patient(Name, Age, Gender, Phone_Number, Email, Password, Address) VALUES ('"+name+"', '"+age+"','"+gender+"','"+phone+"','"+email+"','"+password+"','"+address+"')";
                                pst = conn.prepareStatement(sql);
                                pst.execute();

                                JOptionPane.showMessageDialog(null, "Inserted");
                                
                                patienttableload();

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                }
            }
            patienttableload();

        }
        else if(insertOrUpdate == 2){
            int s = JOptionPane.showConfirmDialog(null, "Want to Update this record?");

            if(s == 0){

                String name = Assign_Patient_Name.getText();
                int age = Integer.parseInt(Assign_Patient_Age.getText());
                String gender = Assign_GenderComboBox.getSelectedItem().toString();
                String phone = Assign_Patient_Phone.getText();
                String email = Assign_Patient_Email.getText();
                String password = Assign_Patient_Password.getText();
                String address = Assign_Patient_Address.getText();
                
                String agestring = String.valueOf(age);

                if(isValidGmail(email) && isValidSriLankaPhoneNumber(phone) && isValidAge(agestring) && isNotEmpty(name) && isNotEmpty(name) && isNotEmpty(address)){
                try {           

                    String sql = "UPDATE patient SET Name = '"+name+"', Age = '"+age+"', Gender = '"+gender+"', Phone_Number = '"+phone+"', Email = '"+email+"', Password = '"+password+"', Address = '"+address+"' WHERE Patient_ID = '"+T1Id+"'";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Updated!");
                    
                    patienttableload();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                }
            }
            patienttableload();
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
        if(insertOrUpdate == 1){
        
            int s = JOptionPane.showConfirmDialog(null, "Want to insert this record?");

            if(s == 0){
                String name;
                String specialization;
                String Contact_Number;
                double Doctor_Charge;
                //int Hospital_ID;
                //String d_Charge;

                name = Assign_Doctor_Name.getText();
                specialization = Assign_Doctor_Specialization.getText();
                Contact_Number = Assign_Doctor_Contact_Number.getText();
                //d_Charge = Assign_Doctor_Change.getText();
                Doctor_Charge = Double.parseDouble(Assign_Doctor_Change.getText());
                //Hospital_ID = Integer.parseInt(Assign_Doctor_Hospital_ID.getText()); 

                //Doctor_Charge = Double.parseDouble(d_Charge);
                String dcharge = String.valueOf(Doctor_Charge);
                
                if(isValidGmail(Contact_Number) && isNotEmpty(name) && isNotEmpty(specialization) && isNotEmpty(dcharge)){
                try {           

                    String sql = "INSERT INTO doctor (Name, Specialization, Contact_Info, Doctor_Charge) VALUES ('"+name+"', '"+specialization+"','"+Contact_Number+"','"+Doctor_Charge+"')";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Inserted");
                    
                    doctortableload();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                }
            }
            doctortableload();
        }
        else if(insertOrUpdate == 2){
            int s = JOptionPane.showConfirmDialog(null, "Want to Update this record?");

            if(s == 0){
                String name;
                String specialization;
                String Contact_Number;
                double Doctor_Charge;
                //int Hospital_ID;
                //String d_Charge;

                name = Assign_Doctor_Name.getText();
                specialization = Assign_Doctor_Specialization.getText();
                Contact_Number = Assign_Doctor_Contact_Number.getText();
                //d_Charge = Assign_Doctor_Change.getText();
                Doctor_Charge = Double.parseDouble(Assign_Doctor_Change.getText());
                //Hospital_ID = Integer.parseInt(Assign_Doctor_Hospital_ID.getText()); 

                //Doctor_Charge = Double.parseDouble(d_Charge);
                String dcharge = String.valueOf(Doctor_Charge);
                
                if(isValidGmail(Contact_Number) && isNotEmpty(name) && isNotEmpty(specialization) && isNotEmpty(dcharge)){
                try {           

                    String sql = "UPDATE doctor SET Name = '"+name+"', Specialization = '"+specialization+"', Contact_Info = '"+Contact_Number+"', Doctor_Charge = '"+Doctor_Charge+"' WHERE Doctor_ID = '"+T2Id+"'";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Updated!");
                    
                    doctortableload();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                }
            }
            doctortableload();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:

        if(insertOrUpdate == 1){

            int s = JOptionPane.showConfirmDialog(null, "Want to insert this record?");

            if(s == 0){
                String name;
                String location;
                String Contact_Number;
                double Hospital_Charge;

                name = Assign_Hospital_Name.getText();
                location = Assign_Hospital_Location.getText();
                Contact_Number = Assign_Hospital_Contact_Number.getText();
                Hospital_Charge = Double.parseDouble(Assign_Hospital_Hospital_Charge.getText());

                String hcharge = String.valueOf(Hospital_Charge);
                
                if(isValidGmail(Contact_Number) && isNotEmpty(name) && isNotEmpty(location) && isNotEmpty(hcharge)){
                
                try {           

                    String sql = "INSERT INTO hospital (Name, Location, Contact_Info, Hospital_Charge) VALUES ('"+name+"', '"+location+"','"+Contact_Number+"','"+Hospital_Charge+"')";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Inserted");
                    hospitaltableload();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            }
            hospitaltableload();
        }
        else if(insertOrUpdate == 2){
            int s = JOptionPane.showConfirmDialog(null, "Want to Update this record?");

            if(s == 0){
                String name;
                String location;
                String Contact_Number;
                double Hospital_Charge;

                name = Assign_Hospital_Name.getText();
                location = Assign_Hospital_Location.getText();
                Contact_Number = Assign_Hospital_Contact_Number.getText();
                Hospital_Charge = Double.parseDouble(Assign_Hospital_Hospital_Charge.getText());
                
                String hcharge = String.valueOf(Hospital_Charge);

                if(isValidGmail(Contact_Number) && isNotEmpty(name) && isNotEmpty(location) && isNotEmpty(hcharge)){
                try {           

                    String sql = "UPDATE hospital SET Name = '"+name+"', Location = '"+location+"', Contact_Info = '"+Contact_Number+"', Hospital_Charge = '"+Hospital_Charge+"' WHERE Hospital_ID = '"+T3Id+"'";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Updated!");
                    hospitaltableload();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            }
            hospitaltableload();
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        
        if(insertOrUpdate == 1){
            
            int s = JOptionPane.showConfirmDialog(null, "Want to insert this record?");

            if (s == 0) {
                int Doctor_ID;
                int Hospital_ID;
                String Date;
                String Time;
                String Status;

                
                // Retrieve inputs
                    Doctor_ID = Integer.parseInt(Assign_Availability_Doctor_ID.getText());
                    Hospital_ID = Integer.parseInt(Assign_Availability_Hospital_ID.getText());
                    Date = Assign_Availability_Date.getText();
                    Time = Assign_Availability_Time.getText(); // Corrected field mapping
                    Status = Assign_Availability_Status.getSelectedItem().toString(); // Value from combo box
                
                if(isValidTime(Time)  && isValidDate(Date)){
                try {
                    
                    /*
                    // Retrieve inputs
                    Doctor_ID = Integer.parseInt(Assign_Availability_Doctor_ID.getText());
                    Hospital_ID = Integer.parseInt(Assign_Availability_Hospital_ID.getText());
                    Date = Assign_Availability_Date.getText();
                    Time = Assign_Availability_Time.getText(); // Corrected field mapping
                    Status = Assign_Availability_Status.getSelectedItem().toString(); // Value from combo box
                    */
                    
                    
                    // Prepare SQL query
                    String sql = "INSERT INTO availability (Doctor_ID, Hospital_ID, Available_Date, Available_Time, Is_Booked_Status) VALUES ('" + Doctor_ID + "', '" + Hospital_ID + "', '" + Date + "', '" + Time + "', '" + Status + "')";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Inserted Successfully!");
                    
                    Availabilitytableload();
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Doctor ID and Hospital ID must be numeric.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                }
            }

            Availabilitytableload();
        }
        else if(insertOrUpdate == 2){
            int s = JOptionPane.showConfirmDialog(null, "Want to Update this record?");

            if (s == 0) {
                int Doctor_ID;
                int Hospital_ID;
                String Date;
                String Time;
                String Status;

                try {
                    // Retrieve inputs
                    Doctor_ID = Integer.parseInt(Assign_Availability_Doctor_ID.getText());
                    Hospital_ID = Integer.parseInt(Assign_Availability_Hospital_ID.getText());
                    Date = Assign_Availability_Date.getText();
                    Time = Assign_Availability_Time.getText(); // Corrected field mapping
                    Status = Assign_Availability_Status.getSelectedItem().toString(); // Value from combo box

                    // Prepare SQL query
                    String sql = "UPDATE availability SET Doctor_ID = '"+Doctor_ID+"', Hospital_ID = '"+Hospital_ID+"', Available_Date = '"+Date+"', Available_Time = '"+Time+"', Is_Booked_Status = '"+Status+"' WHERE Availability_ID = '"+T4Id+"' ";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Update Successfully!");
                    
                    Availabilitytableload();
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Doctor ID and Hospital ID must be numeric.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

            Availabilitytableload();
        }
 
        
        /*
        int s = JOptionPane.showConfirmDialog(null, "Want to insert this record?");
        
        if(s == 0){
            int Doctor_ID;
            int Hospital_ID;
            String Date;
            String Time;
            String Status;

            Doctor_ID = Integer.parseInt(Assign_Availability_Doctor_ID.getText());
            Hospital_ID = Integer.parseInt(Assign_Availability_Hospital_ID.getText());
            Date = Assign_Availability_Date.getText();
            Time = Assign_Availability_Date.getText();
            Status = Assign_Availability_Status.getSelectedItem().toString();
            
            try {           
                
                String sql = "INSERT INTO availability (Doctor_ID, Hospital_ID, Available_Date, Available_Time, Is_Booked_Status) VALUES ('"+Doctor_ID+"', '"+Hospital_ID+"','"+Date+"','"+Time+"','"+Status+"')";
                pst = conn.prepareStatement(sql);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Inserted");
 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        Availabilitytableload();
        */
        
        
        
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        
        int s = JOptionPane.showConfirmDialog(null, "Want to insert this record?");
        
        if(s == 0){
            String name;
            String location;
            String Contact_Number;
            String Hospital_Charge;

            name = Assign_Hospital_Name.getText();
            location = Assign_Hospital_Location.getText();
            Contact_Number = Assign_Hospital_Contact_Number.getText();
            Hospital_Charge = Assign_Hospital_Hospital_Charge.getText();
            
            try {           
                
                String sql = "INSERT INTO availability Name, Location, Contact_Info, Hospital_Charge VALUES ('"+name+"', '"+location+"','"+Contact_Number+"','"+Hospital_Charge+"')";
                pst = conn.prepareStatement(sql);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Inserted");
                
                Appointmenttableload();
 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        Appointmenttableload();
        
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        if(insertOrUpdate == 1){
            

            int s = JOptionPane.showConfirmDialog(null, "Want to insert this record?");

            if(s == 0){
                String User_Name;
                String Password;
                String Email;

                User_Name = Assign_User_Name.getText();
                Password = Assign_Password.getText();
                Email = Assign_Email.getText();

                if(isValidGmail(Email) && isNotEmpty(User_Name) && isNotEmpty(Password)){
                
                try {           

                    String sql = "INSERT INTO admin (Username, Password, Email) VALUES ('"+User_Name+"','"+Password+"','"+Email+"')";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Inserted");
                    Admintableload();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                
            }
            }
            Admintableload();
        }
        else if(insertOrUpdate == 2){
            int s = JOptionPane.showConfirmDialog(null, "Want to Update this record?");

            if(s == 0){
                String User_Name;
                String Password;
                String Email;

                User_Name = Assign_User_Name.getText();
                Password = Assign_Password.getText();
                Email = Assign_Email.getText();
                
                if(isValidGmail(Email) && isNotEmpty(User_Name) && isNotEmpty(Password)){

                try {           

                    String sql = "UPDATE admin SET Username = '"+User_Name+"', Password = '"+Password+"', Email = '"+Email+"' WHERE Admin_ID = '"+T6Id+"' ";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Updated");
                    Admintableload();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            }
            Admintableload();
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    
    
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to Update?");
        
        if(s == 0){

            Assign_Doctor_Name.setText(T2name);
            Assign_Doctor_Specialization.setText(T2specialization);
            Assign_Doctor_Contact_Number.setText(T2email);
            Assign_Doctor_Change.setText(Double.toString(T2charge));

            insertOrUpdate = 2;
            jTabbedPane1.setSelectedIndex(8);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        
        int s = JOptionPane.showConfirmDialog(null, "Do you want to Update?");
        
        if(s == 0){
            
            Assign_Hospital_Name.setText(T3name);
            Assign_Hospital_Location.setText(T3location);
            Assign_Hospital_Contact_Number.setText(T3contact);
            Assign_Hospital_Hospital_Charge.setText(Double.toString(T3charge));
            
            insertOrUpdate = 2;
            jTabbedPane1.setSelectedIndex(9);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to Update?");
        
        if(s == 0){
            
            
            Assign_Availability_Doctor_ID.setText(Integer.toString(T4DId));
            Assign_Availability_Hospital_ID.setText(Integer.toString(T4HId));
            Assign_Availability_Date.setText(T4date);
            Assign_Availability_Time.setText(T4time);
            Assign_Availability_Status.setToolTipText(T4status);
            
            insertOrUpdate = 2;
            jTabbedPane1.setSelectedIndex(10);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to Update?");
        
        if(s == 0){
           
            Assign_User_Name.setText(T6name);
            Assign_Password.setText(T6password);
            Assign_Email.setText(T6email);
            
            insertOrUpdate = 2;
            jTabbedPane1.setSelectedIndex(12);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
             
            int check=JOptionPane.showConfirmDialog(null,"do you want to delete the data?");
            int r= table1.getSelectedRow();

            String id= table1.getValueAt(r,0).toString();

            if(check==0){
                try {
                    String sql = "DELETE FROM patient WHERE Patient_ID = '"+id+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute(); 
                    JOptionPane.showMessageDialog(null,"Data deleted"); 
                    } catch (Exception e) {  
                     JOptionPane.showMessageDialog(null,e);   
                    }
            } 
            patienttableload();
  
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int check=JOptionPane.showConfirmDialog(null,"do you want to delete the data?");
            int r= table2.getSelectedRow();

            String id= table2.getValueAt(r,0).toString();

            if(check==0){
                try {
                    String sql = "DELETE FROM doctor WHERE Doctor_ID = '"+id+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute(); 
                    JOptionPane.showMessageDialog(null,"Data deleted"); 
                    doctortableload();
                    } catch (Exception e) {  
                     JOptionPane.showMessageDialog(null,e);   
                    }
            } 
            doctortableload();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        int check=JOptionPane.showConfirmDialog(null,"do you want to delete the data?");
            int r= table3.getSelectedRow();

            String id= table3.getValueAt(r,0).toString();

            if(check==0){
                try {
                    String sql = "DELETE FROM hospital WHERE Hospital_ID = '"+id+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute(); 
                    JOptionPane.showMessageDialog(null,"Data deleted"); 
                     hospitaltableload();
                    } catch (Exception e) {  
                     JOptionPane.showMessageDialog(null,e);   
                    }
            } 
            hospitaltableload();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        int check=JOptionPane.showConfirmDialog(null,"do you want to delete the data?");
            int r= table3.getSelectedRow();

            String id= table3.getValueAt(r,0).toString();

            if(check==0){
                try {
                    String sql = "DELETE FROM availability WHERE Availability_ID = '"+id+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute(); 
                    JOptionPane.showMessageDialog(null,"Data deleted"); 
                    Availabilitytableload();
                    } catch (Exception e) {  
                     JOptionPane.showMessageDialog(null,e);   
                    }
            } 
            Availabilitytableload();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        int check=JOptionPane.showConfirmDialog(null,"do you want to delete the data?");
            int r= table6.getSelectedRow();

            String id= table6.getValueAt(r,0).toString();

            if(check==0){
                try {
                    String sql = "DELETE FROM availability WHERE Availability_ID = '"+id+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute(); 
                    JOptionPane.showMessageDialog(null,"Data deleted"); 
                    } catch (Exception e) {  
                     JOptionPane.showMessageDialog(null,e);   
                    }
            } 
            Admintableload();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        
        String book = "Booked";
        String available = "Available";
        
                try {           
                    String sql = "UPDATE availability SET is_booked_status = '"+available+"' WHERE availability_ID = '"+NewAvailable+"' AND is_booked_status = '"+book+"'";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                Availabilitytableload();
                
                
        int check=JOptionPane.showConfirmDialog(null,"do you want to delete the data?");
            int r= table5.getSelectedRow();

            String id= table5.getValueAt(r,0).toString();

            if(check==0){
                try {
                    String sql = "DELETE FROM appointment WHERE Appointment_ID = '"+id+"'";
                    pst=conn.prepareStatement(sql);
                    pst.execute(); 
                    JOptionPane.showMessageDialog(null,"Data deleted"); 
                    } catch (Exception e) {  
                     JOptionPane.showMessageDialog(null,e);   
                    }
            } 
            Appointmenttableload();        
                
        
    }//GEN-LAST:event_jButton22ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Assign_Appointment_ID;
    private javax.swing.JTextField Assign_Availability_Date;
    private javax.swing.JTextField Assign_Availability_Doctor_ID;
    private javax.swing.JTextField Assign_Availability_Hospital_ID;
    private javax.swing.JComboBox<String> Assign_Availability_Status;
    private javax.swing.JTextField Assign_Availability_Time;
    private javax.swing.JTextField Assign_Avalibility_ID;
    private javax.swing.JTextField Assign_Doctor_Change;
    private javax.swing.JTextField Assign_Doctor_Contact_Number;
    private javax.swing.JTextField Assign_Doctor_Name;
    private javax.swing.JTextField Assign_Doctor_Specialization;
    private javax.swing.JTextField Assign_Email;
    private javax.swing.JComboBox<String> Assign_GenderComboBox;
    private javax.swing.JTextField Assign_Hospital_Contact_Number;
    private javax.swing.JTextField Assign_Hospital_Hospital_Charge;
    private javax.swing.JTextField Assign_Hospital_Location;
    private javax.swing.JTextField Assign_Hospital_Name;
    private javax.swing.JPasswordField Assign_Password;
    private javax.swing.JTextField Assign_Patient_Address;
    private javax.swing.JTextField Assign_Patient_Age;
    private javax.swing.JTextField Assign_Patient_Email;
    private javax.swing.JTextField Assign_Patient_ID;
    private javax.swing.JTextField Assign_Patient_Name;
    private javax.swing.JPasswordField Assign_Patient_Password;
    private javax.swing.JTextField Assign_Patient_Phone;
    private javax.swing.JTextField Assign_User_Name;
    private javax.swing.JCheckBox ShowPasswordCheck;
    private javax.swing.JButton Ubtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JTable table4;
    private javax.swing.JTable table5;
    private javax.swing.JTable table6;
    // End of variables declaration//GEN-END:variables
}
