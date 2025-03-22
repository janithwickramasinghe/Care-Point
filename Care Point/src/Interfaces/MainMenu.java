package Interfaces;

import Codes.DBconnection;
import static Interfaces.Login.getUserID;
import static com.mysql.cj.util.StringUtils.isNullOrEmpty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class MainMenu extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private int userID;
    
    public MainMenu() {
        initComponents();
        conn = DBconnection.Connect();
        doctortableload();
        hospitaltableload();
        specialtableload();
        //getValuesForOnGoing();
        onGo();
    }
    
    
    public static String passAppointmentID;
    
    String doctorName;
    String Specialization;
    String Hospital;
    String DateAndTime;
    double HCharge;
    double DCharge;
    String Charge;
    String BookOrAvailable;
    String onlydate;
    String onlytime;
    String AvailabilityID;
    int Patient_Id = Login.userID;
    
    
    
        String ongoingNumber;
        String ongoingDoctor;
        String ongoingHospital;
        String ongoingSpecialization;
        String ongoingDate;
        String ongoingTime;
        String ongoingCharge;
    String Did;
    String Hid;
    
    String aviID;
    
    
    //Patient_Id = getUserID();
    
    
    public void createConfirmation(){
        String DoctorCharge = Double.toString(DCharge);
        String HospitalCharge = Double.toString(HCharge);
        
        CNum.setText(Charge);
        Cdoctor.setText(doctorName);
        CSpecial.setText(Specialization);
        Chospital.setText(Hospital);
        CDT.setText(DateAndTime);
        CDcost.setText(DoctorCharge);
        CHcost.setText(HospitalCharge);
        CTcost.setText(Charge);
                
    }
    
    public void clearConfirm(){
    
        DName.setText("");
        SName.setText("");
        HName.setText("");
        DTName.setText("");
        CName.setText("");
    }

    
    /**
     *
     */
    public void RunCharge(){
        
        if(doctorName != null || !doctorName.isEmpty() && Hospital != null || !Hospital.isEmpty()){
            try {
                String sql = "SELECT Doctor.Doctor_Charge , Hospital.Hospital_Charge FROM Doctor JOIN Availability ON Doctor.Doctor_ID = Availability.Doctor_ID JOIN Hospital ON Availability.Hospital_ID = Hospital.Hospital_ID WHERE Doctor.Name = '"+doctorName+"' AND Hospital.Name = '"+Hospital+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    DCharge = rs.getDouble("Doctor_Charge");
                    HCharge = rs.getDouble("Hospital_Charge");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }            
        }    
            

        double T = DCharge + HCharge;
        Charge = Double.toString(T);
        CName.setText(Charge);
    }
    
    public void Clean(){
        HName.setText(null);
        SName.setText(null);
        DTName.setText(null);
    }
       
    public void Confirm(){
        if (doctorName == null || doctorName.isEmpty() || doctorName.equals("")){
            jTabbedPane1.setSelectedIndex(1);
        }
        else if(Hospital == null || Hospital.isEmpty() || Hospital.equals("")){
            jTabbedPane1.setSelectedIndex(2);
        }
        else if(Specialization == null || Specialization.isEmpty() || Specialization.equals("")){
            jTabbedPane1.setSelectedIndex(3);
        }         
        else if(DateAndTime == null || DateAndTime.isEmpty() || DateAndTime.equals("")){
            jTabbedPane1.setSelectedIndex(4);
        }
        else if(DateAndTime != null || !DateAndTime.isEmpty() || !DateAndTime.equals("") && doctorName != null || !doctorName.isEmpty() || !doctorName.equals("") && Hospital != null || !Hospital.isEmpty() || !Hospital.equals("") && Specialization != null || !Specialization.isEmpty() || !Specialization.equals("")){
            jTabbedPane1.setSelectedIndex(5);
        }
        
    }
   
    
    
    
    
    public void doctortableload(){
        try {
            String sql = "SELECT Name FROM Doctor";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    boolean check(){
        return doctorName == null || doctorName.isEmpty();
    }
 
    public void hospitaltableload() {
                
        if (check()) {
            try {
                String sql2 = "SELECT Name, Location FROM Hospital"; // Removed GROUP BY for correctness
                pst = conn.prepareStatement(sql2);
                rs = pst.executeQuery();
                table2.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading hospital data: " + e.getMessage());
            }
        } else {
            try {
                String sql2 = "SELECT DISTINCT Hospital.Name, Hospital.Location FROM Availability JOIN Hospital ON Availability.Hospital_ID = Hospital.Hospital_ID WHERE Availability.Doctor_ID = (SELECT Doctor.Doctor_ID FROM Doctor WHERE Name = '"+doctorName+"')";

                pst = conn.prepareStatement(sql2);
                rs = pst.executeQuery();
                table2.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading hospital data for doctor: " + e.getMessage());
            }
        }
    } 
    
    
    public void specialtableload(){
        if(check()){
            try {
                String sql1 = "SELECT Specialization FROM Doctor";
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();
                table3.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }     
        }else{
            
             try {
                String sql2 = "SELECT Specialization From Doctor WHERE name = '"+doctorName+"'";
                pst = conn.prepareStatement(sql2);
                rs = pst.executeQuery();
                table3.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading hospital data for doctor: " + e.getMessage());
            }
        }

    }
    
    
    public void onGo(){

    try {
            String sql = "SELECT Appointment.Appointment_ID AS Appointment_Number,Doctor.Name AS Doctor_Name, Hospital.Name AS Hospital_Name, Doctor.Specialization, Availability.Available_Date AS Date, Availability.Available_Time AS Time, Appointment.Total_Charge AS Charge FROM Appointment JOIN Availability ON Appointment.Availability_ID = Availability.Availability_ID JOIN Doctor ON Availability.Doctor_ID = Doctor.Doctor_ID JOIN Hospital ON Availability.Hospital_ID = Hospital.Hospital_ID WHERE Appointment.Patient_ID = '"+Patient_Id+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table5.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    
    public void onGoingAppointmentLoad(){
        /*
        String ongoingNumber;
        String ongoingDoctor;
        String ongoingHospital;
        String ongoingSpecialization;
        String ongoingDate;
        String ongoingTime;
        String ongoingCharge;
        */
       
   
        
        try {
                String sql = "SELECT Availability_ID FROM appointment";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    aviID = rs.getString("Availability_ID");                    
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        
        try {    
    String sql = "SELECT availability.Doctor_ID, availability.Hospital_ID, " +
                 "availability.Available_Date, availability.Available_Time " +
                 "FROM appointment " +
                 "JOIN availability ON appointment.Availability_ID = availability.Availability_ID " +
                 "WHERE appointment.Availability_ID = '"+aviID+"'";
    pst = conn.prepareStatement(sql);
    pst.setString(1, aviID); // Set the aviID parameter
    rs = pst.executeQuery();
    
    if (rs.next()) { // Check if a record exists
        Did = rs.getString("Doctor_ID");
        Hid = rs.getString("Hospital_ID");
        ongoingDate = rs.getString("Available_Date");
        ongoingTime = rs.getString("Available_Time");
    } else {
        JOptionPane.showMessageDialog(null, "No matching records found.");
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e);
}


            /*
                String sql = "SELECT availability.Doctor_ID, availability.Hospital_ID, availability.Available_Date, availability.Available_Time FROM appointment JOIN  availability ON appointment.Availability_ID = availability.Availability_ID WHERE Availability_ID = '"+aviID+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    Did = rs.getString("Doctor_ID");
                    Hid = rs.getString("Hospital_ID");
                    ongoingDate = rs.getString("Available_Date");
                    ongoingTime = rs.getString("Available_Time");
                    
                
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        */
        
            try {
    String sql = "SELECT Name, Specialization FROM doctor WHERE Doctor_ID = '"+Did+"'";
    pst = conn.prepareStatement(sql);
    pst.setString(1, Did); // Set the Doctor_ID parameter
    rs = pst.executeQuery();
    
    if (rs.next()) { // Check if a record exists
        ongoingDoctor = rs.getString("Name");
        ongoingSpecialization = rs.getString("Specialization");
    } else {
        JOptionPane.showMessageDialog(null, "No matching doctor found for the given Doctor_ID.");
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e);
}

            /*
        try {
            
                String sql = "SELECT Name, Specialization FROM doctor WHERE Doctor_ID = '"+Did+"' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    ongoingDoctor = rs.getString("Name");
                    ongoingSpecialization = rs.getString("Specialization");                    
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);        
            }
            */
        
        
        try {
            
                String sql = "SELECT Name FROM hospital WHERE Hospital_ID = '"+Hid+"' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    ongoingHospital = rs.getString("Name");                    
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);        
            }
        
        try {
       
                String sql = "SELECT Appointment_ID,Total_Charge FROM Appointment";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    ongoingNumber = rs.getString("Appointment_ID"); 
                    ongoingCharge = rs.getString("Total_Charge"); 
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);        
            }
        
    }
    
    public void InsertValuesForOnGoing(){
        
        
         /*
        String ongoingNumber;
        String ongoingDoctor;
        String ongoingHospital;
        String ongoingSpecialization;
        String ongoingDate;
        String ongoingTime;
        String ongoingCharge;
        */
        
        int currentPatientId = Login.userID;
        
        try {

                String sql = "INSERT INTO ongoingtable(Patient_Id, Number, Doctor, Hospital, Specialization, Date, Time, Charge) VALUES ('"+currentPatientId+"', '"+ongoingNumber+"','"+ongoingDoctor+"','"+ongoingHospital+"','"+ongoingSpecialization+"','"+ongoingDate+"','"+ongoingTime+"', '"+ongoingCharge+"')";
                pst = conn.prepareStatement(sql);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Inserted");

            }
            
         catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void getValuesForOnGoing(){      
        
        int currentPatientId2 = Login.userID;
        
         try {
            String sql = "SELECT * FROM ongoingtable WHERE Patient_ID = '"+currentPatientId2+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
         /*
        try {
            String sql = "SELECT (Number, Doctor, Hospital, Specialization, Date, Time, Charge) FROM ongoingtable GROUP By Patient_ID";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        */
    }
    
    
    
    
    
    public void availabletableload(){
        
        if (doctorName != null && !doctorName.isEmpty() && Hospital != null && !Hospital.isEmpty()){
            try {
                String sql1 = """
                            SELECT Availability.Available_Date, 
                            Availability.Available_Time, 
                            Availability.Is_Booked_Status 
                            FROM Availability 
                            JOIN Doctor ON Availability.Doctor_ID = Doctor.Doctor_ID 
                            JOIN Hospital ON Availability.Hospital_ID = Hospital.Hospital_ID 
                            WHERE Doctor.Name = '"""+doctorName+"' \n" +
                            "AND Hospital.Name = '"+Hospital+"';";
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();
                table4.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }  
        }
    }   

    public void appointmenttableload(){
        try {
                String sql1 = "";
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();
                table4.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        DName = new javax.swing.JLabel();
        HName = new javax.swing.JLabel();
        SName = new javax.swing.JLabel();
        DTName = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        CName = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table5 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table4 = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        CSpecial = new javax.swing.JLabel();
        Cdoctor = new javax.swing.JLabel();
        CNum = new javax.swing.JLabel();
        Chospital = new javax.swing.JLabel();
        CDT = new javax.swing.JLabel();
        CDcost = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        CHcost = new javax.swing.JLabel();
        CTcost = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 110, 40));

        jButton2.setText("Doctor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 110, 40));

        jButton3.setText("Hospital");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 110, 40));

        jButton4.setText("Special");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 110, 40));

        jButton8.setText("Date And Time");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 110, 40));

        jButton12.setText("<--");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1111.png"))); // NOI18N
        jLabel23.setText("jLabel23");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(-290, 0, 560, 435));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 430));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(153, 153, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(153, 255, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Welcome To Care Point");
        jPanel15.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 50));

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 50));

        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Doctor's Name");
        jPanel16.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel7.setText("Hospital");
        jPanel16.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel8.setText("Specialization");
        jPanel16.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel9.setText("Date and Time");
        jPanel16.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));
        jPanel16.add(DName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));
        jPanel16.add(HName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));
        jPanel16.add(SName, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));
        jPanel16.add(DTName, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, -1));

        jButton10.setBackground(new java.awt.Color(255, 255, 51));
        jButton10.setText("Book");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 90, 40));

        jButton11.setText("Clear");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        jLabel15.setText("Charge");
        jPanel16.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));
        jPanel16.add(CName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 560, 150));

        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("On Going Appointments");
        jPanel17.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 560, 40));

        table5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Number", "Doctor", "Hospital", "Specialization", "Date", "Time", "Charge"
            }
        ));
        jScrollPane6.setViewportView(table5);

        jPanel17.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 560, 200));

        jLabel22.setText("jLabel22");
        jPanel17.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(-180, -190, 740, 440));

        jPanel2.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 560, 210));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(153, 153, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select a Doctor");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setText("Select");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 190, 40));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 560, 60));
        jPanel3.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));
        jPanel3.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel25.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 490, -1));

        jPanel3.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 560, 60));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Doctor Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table1);

        jPanel3.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 240));

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Select a Hospital");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setText("Select");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 190, 40));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 560, 60));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Hospital Name", "Location"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 240));

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel22.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 490, -1));

        jPanel4.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 560, 60));

        jTabbedPane1.addTab("tab3", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(153, 153, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Select Specialization");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setText("Select");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 190, 40));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 560, 60));

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Specialization"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table3);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 240));

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 490, -1));

        jPanel5.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 560, 60));

        jTabbedPane1.addTab("tab4", jPanel5);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setText("Select");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 190, 40));

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 560, 60));

        jPanel14.setBackground(new java.awt.Color(153, 153, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(102, 153, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Select Date And Time");
        jPanel14.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        jPanel12.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 40));

        table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Date", "Time", "Status"
            }
        ));
        table4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table4);

        jPanel12.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 240));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel24.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 490, -1));

        jPanel12.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 560, 60));

        jTabbedPane1.addTab("tab5", jPanel12);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("Number");
        jPanel21.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jLabel12.setText("Doctor");
        jPanel21.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jLabel13.setText("Specialization");
        jPanel21.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        jLabel16.setText("Hospital");
        jPanel21.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        jLabel17.setText("Date and Time");
        jPanel21.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        jLabel18.setText("Doctor Charge (Rs)");
        jPanel21.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, -1));

        jButton13.setText("Confirm");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 120, 60));

        jButton14.setText("Cancel");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, -1, -1));

        CSpecial.setText("jLabel8");
        jPanel21.add(CSpecial, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        Cdoctor.setText("jLabel9");
        jPanel21.add(Cdoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        CNum.setText("jLabel10");
        jPanel21.add(CNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        Chospital.setText("jLabel11");
        jPanel21.add(Chospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        CDT.setText("jLabel12");
        jPanel21.add(CDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        CDcost.setText("jLabel13");
        jPanel21.add(CDcost, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        jLabel19.setText("Hospital Charge (Rs)");
        jPanel21.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        jLabel20.setText("Total Cost (Rs)");
        jPanel21.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        CHcost.setText("jLabel21");
        jPanel21.add(CHcost, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        CTcost.setText("jLabel22");
        jPanel21.add(CTcost, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        jPanel19.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 560, 390));

        jLabel10.setBackground(new java.awt.Color(153, 153, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Confirm Your Appointment");
        jPanel19.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 50));

        jTabbedPane1.addTab("tab6", jPanel19);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -40, 560, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        Clean();
        
        DName.setText(doctorName);    
        hospitaltableload();
        specialtableload();
        availabletableload();
        RunCharge();
        if(Specialization != null && !Specialization.isEmpty()){
            jTabbedPane1.setSelectedIndex(0);
        }else{
            jTabbedPane1.setSelectedIndex(3);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
      
        HName.setText(Hospital);        
        availabletableload();
        RunCharge();
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
              
        SName.setText(Specialization);        
        RunCharge();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
        
        
        if(doctorName == null || doctorName.isEmpty() && Hospital == null || Hospital.isEmpty()){
            JOptionPane.showMessageDialog(null, "Plase Select Doctor And Hospital First");
        }
        else if(doctorName == null || doctorName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Plase Select Doctor First");
        }
        else if(Hospital == null || Hospital.isEmpty()){
            JOptionPane.showMessageDialog(null, "Plase Select Hospital First");
        }
        
        
        DTName.setText(DateAndTime);
        RunCharge();
        jTabbedPane1.setSelectedIndex(0);       
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int s = JOptionPane.showConfirmDialog(null, "Do you want to go Login Page?");
        
        if(s == 0){
            Login login = new Login();
            login.show();
            dispose();            
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        int r = table1.getSelectedRow();    
        doctorName = table1.getValueAt(r, 0).toString();

    }//GEN-LAST:event_table1MouseClicked

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:
        int r = table2.getSelectedRow();    
        Hospital = table2.getValueAt(r, 0).toString();
    }//GEN-LAST:event_table2MouseClicked

    private void table3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table3MouseClicked
        // TODO add your handling code here:
        int r = table3.getSelectedRow();    
        Specialization = table3.getValueAt(r, 0).toString();        
    }//GEN-LAST:event_table3MouseClicked

    private void table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table4MouseClicked
        // TODO add your handling code here:
        
        int k = table4.getSelectedRow();    
        BookOrAvailable = table4.getValueAt(k, 2).toString();
        
        
        if(BookOrAvailable.equals("Available")){
            int r = table4.getSelectedRow();
            String date = table4.getValueAt(r, 0).toString();
            String time = table4.getValueAt(r, 1).toString();
            String space = " : ";
            String Fname = date.concat(space);
            DateAndTime = Fname.concat(time);
            
            onlydate = date;
            onlytime = time;
            
        }else{
            JOptionPane.showMessageDialog(null, "This Date and Time is Booked!\n\nPlease Select another Time.");
        }
        
          
    }//GEN-LAST:event_table4MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
      
            
    
            try {
                String sql = "SELECT Availability_ID FROM availability WHERE Doctor_ID = (SELECT Doctor_ID FROM doctor WHERE Name = '"+doctorName+"' AND Specialization = '"+Specialization+"') AND Hospital_ID = (SELECT Hospital_ID FROM hospital WHERE Name = '"+Hospital+"') AND Available_Date = '"+onlydate+"' AND Available_Time = '"+onlytime+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    AvailabilityID = rs.getString("Availability_ID");                    
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
            int available = Integer.parseInt(AvailabilityID);
            double tcharge = Double.parseDouble(Charge);
            String book = "Booked";
            String Unbook = "Available";
            
            
            //Patient_Id = 1;
            
             try {           

                    String sql = "INSERT INTO appointment (Patient_ID, Availability_ID, Total_Charge) VALUES ('"+Patient_Id+"', '"+available+"','"+tcharge+"')";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    
                    
                    //onGoingAppointmentLoad();
                    //InsertValuesForOnGoing();
                    //getValuesForOnGoing();
                    
                    JOptionPane.showMessageDialog(null, "Appointment is Booked");
                    
                    onGo();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            
             //availabletableload();  available
            
             /*
            try {
                String sql = "SELECT Appointment_ID from appointment where Patient_ID = '"+Patient_Id+"' AND Availability_ID = '"+available+"' AND Total_Charge = '"+tcharge+"';";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    passAppointmentID = rs.getString("Appointment_ID");                    
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
            
            try {
                String sql = "UPDATE availability SET is_booked_status = '"+book+"' where availability_ID = '"+available+"' and is_booked_status = '"+Unbook+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    passAppointmentID = rs.getString("Appointment_ID");                    
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            */
             
             try {           
                    String sql = "UPDATE availability SET is_booked_status = '"+book+"' WHERE availability_ID = '"+available+"' AND is_booked_status = '"+Unbook+"'";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
             
            availabletableload();
            
            
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int clearCheck = JOptionPane.showConfirmDialog(null, "Do you want to clear?");

        if(clearCheck == 0){
            clearConfirm();
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        Confirm();
        createConfirmation();
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CDT;
    private javax.swing.JLabel CDcost;
    private javax.swing.JLabel CHcost;
    private javax.swing.JLabel CName;
    private javax.swing.JLabel CNum;
    private javax.swing.JLabel CSpecial;
    private javax.swing.JLabel CTcost;
    private javax.swing.JLabel Cdoctor;
    private javax.swing.JLabel Chospital;
    private javax.swing.JLabel DName;
    private javax.swing.JLabel DTName;
    private javax.swing.JLabel HName;
    private javax.swing.JLabel SName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JTable table4;
    private javax.swing.JTable table5;
    // End of variables declaration//GEN-END:variables
}
