package Interfaces;

import Codes.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
//import net.proteanit.sql.DbUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Login() {
        initComponents();
        conn = DBconnection.Connect();
//        Tableload();
    }
    
    public static int userID;
    
    public static int getUserID(){
        return userID;
    }

/*   
    public void Tableload(){
        try {
            String sql = "SELECT ID AS ID, Name, Age FROM student";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
*/   
    
    
    public void Exit(){
        System.exit(0);
    }
    
     // Regex pattern for validating Gmail addresses
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EmailBox = new javax.swing.JTextField();
        LoginBtn = new javax.swing.JButton();
        SignUpBtn = new javax.swing.JButton();
        ShowPasswordCheck = new javax.swing.JCheckBox();
        ExitBtn = new javax.swing.JButton();
        PasswordBox = new javax.swing.JPasswordField();
        SwitchComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Email");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Login");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 50));

        EmailBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailBoxActionPerformed(evt);
            }
        });
        jPanel1.add(EmailBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 260, -1));

        LoginBtn.setBackground(new java.awt.Color(102, 153, 255));
        LoginBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LoginBtn.setText("Login");
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(LoginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 200, -1));

        SignUpBtn.setBackground(new java.awt.Color(102, 153, 255));
        SignUpBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SignUpBtn.setText("Sign Up");
        SignUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBtnActionPerformed(evt);
            }
        });
        jPanel1.add(SignUpBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 200, -1));

        ShowPasswordCheck.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ShowPasswordCheck.setText("Show Password");
        ShowPasswordCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPasswordCheckActionPerformed(evt);
            }
        });
        jPanel1.add(ShowPasswordCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

        ExitBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExitBtn.setText("Exit");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(ExitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, -1, -1));
        jPanel1.add(PasswordBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 260, -1));

        SwitchComboBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SwitchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));
        SwitchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(SwitchComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-230, -280, 950, 720));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpBtnActionPerformed
        // TODO add your handling code here:
        SignUp signup = new SignUp();
        signup.show();
        dispose();
    }//GEN-LAST:event_SignUpBtnActionPerformed

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed

        String userEmail = null;
        String userPassword = null;
        String adminEmail = null;
        String adminPassword = null;
        String email;
        String password;
        String type;
        String User_ID;
        
        
        
        email = EmailBox.getText();
        password = PasswordBox.getText();
        type = SwitchComboBox.getSelectedItem().toString();
        
        
                
        if(type.equals("Admin")){
            try {
                String sql = "SELECT Email, Password FROM Admin WHERE Email = '"+email+"' AND Password = '"+password+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    adminEmail = rs.getString("Email");
                    
                    adminPassword = rs.getString("Password");
                    
                }
                
                if( email.equals(adminEmail) && password.equals(adminPassword)){              
                    AdminMain adminmain = new AdminMain();
                    adminmain.show();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Email or Password incorrect");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }     
            
        }else{
            try {
                String sql = "SELECT Email, Password, Patient_ID FROM Patient WHERE Email = '"+email+"' AND Password = '"+password+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    userEmail = rs.getString("Email");
                    userPassword = rs.getString("Password");
                    User_ID = rs.getString("Patient_ID");
                    userID = Integer.parseInt(User_ID);
                }
                
                if( email.equals(userEmail) && password.equals(userPassword)){              
                    MainMenu mainmenu = new MainMenu();
                    mainmenu.show();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Email or Password incorrect");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }         
        }
        

/*        
        if(UserNameBox.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter username!");
        }
        else if(PasswordBox.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter password!");    
        }
        else{
            JOptionPane.showMessageDialog(null, "Wrong username or password!" , "Message" , JOptionPane.ERROR_MESSAGE);
        }
*/        
        
    }//GEN-LAST:event_LoginBtnActionPerformed

    private void ShowPasswordCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowPasswordCheckActionPerformed
        
        if(ShowPasswordCheck.isSelected()){
            PasswordBox.setEchoChar((char)0);
        }
        else{
            PasswordBox.setEchoChar(('*'));
        }   
    }//GEN-LAST:event_ShowPasswordCheckActionPerformed

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        Exit();
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void SwitchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwitchComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SwitchComboBoxActionPerformed

    private void EmailBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailBoxActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EmailBox;
    private javax.swing.JButton ExitBtn;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPasswordField PasswordBox;
    private javax.swing.JCheckBox ShowPasswordCheck;
    private javax.swing.JButton SignUpBtn;
    private javax.swing.JComboBox<String> SwitchComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
