/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import internal.inPenerimaanKas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sicat.DBConnect;

/**
 *
 * @author chris
 */
public class dlgTambahEdit extends javax.swing.JDialog {

    /**
     * Creates new form dlgTambahEdit
     */
    static int tipe = 0;
    static int id = 0;
    public dlgTambahEdit(java.awt.Frame parent, boolean modal, int tipe, int id) {
        super(parent, modal);
        initComponents();
        this.tipe = tipe;
        this.id = id;
        insertTipe();
        try {
            insertNama();
            insert();
        } catch (SQLException ex) {
            Logger.getLogger(dlgTambahEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tipe == 0){
            jLabel1.setText("Tambah Data");
            jButton2.setText("Tambah Data");
        } else {
            jLabel1.setText("Edit Data");
            jButton2.setText("Edit Data");
        }
    }
    
    private void insertTipe(){
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Tunai");
        jComboBox1.addItem("Kredit");
    }
    
    private void insertNama() throws SQLException{
        String SQL = "SELECT * FROM nama2";
        jComboBox3.removeAllItems();
        ResultSet rs = DBConnect.executeQuery(SQL);
        while(rs.next()){
            jComboBox3.addItem(rs.getString("nama"));
        }
    }
    
    private void insert() throws SQLException {
        String SQL = "SELECT * FROM penerimaankas "
                + "INNER JOIN nama2 ON nama2.namaId = penerimaanKas.namaId "
                + "WHERE id = '"+id+"'";
        ResultSet rs = DBConnect.executeQuery(SQL);
        if(rs.next()){
            jComboBox3.setSelectedItem(rs.getString("nama"));
            jTextField2.setText(rs.getString(3));
            jTextField3.setText(rs.getString(4));
            jDateChooser1.setDate(rs.getDate(5));
            String tipe = "";
                if(rs.getString(6).equals("0")){
                    tipe = "Tunai";
                } else {
                    tipe = "Kredit";
                }
            jComboBox1.setSelectedItem(tipe);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jLabel3.setText("Bakery");

        jLabel4.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jLabel4.setText("Catering");

        jLabel5.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jLabel5.setText("Tanggal");

        jTextField2.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N

        jTextField3.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jLabel6.setText("Rp.");

        jLabel7.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jLabel7.setText("Rp.");

        jLabel1.setText("jLabel1");

        jLabel8.setFont(new java.awt.Font("SimSun", 0, 14)); // NOI18N
        jLabel8.setText("Tipe Pembayaran");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox1, 0, 135, Short.MAX_VALUE)
                            .addGap(4, 4, 4))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7))
                                .addComponent(jLabel5)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField2)
                                .addComponent(jTextField3)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(57, 57, 57))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jTextField2, jTextField3});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nama = jComboBox3.getSelectedItem().toString();
        String bkr = jTextField2.getText().toString();
        String ctr = jTextField3.getText().toString();
        String tgl = sdf.format(jDateChooser1.getDate());
        int tipepem = 0;
        if (nama.isEmpty()||bkr.isEmpty()||ctr.isEmpty()||tgl == null){
            JOptionPane.showMessageDialog(this, "Harap lengkapi data terlebih dahulu"
                    ,"Error",JOptionPane.WARNING_MESSAGE);
        }if(tipe == 0){
        String SQL = "SELECT namaId FROM nama2 WHERE nama = '"+nama+"'";
        String namaId = "";
        ResultSet rs = DBConnect.executeQuery(SQL);
            try {
                if(rs.next()){
                    namaId = rs.getString("namaId");
                            String SQL2 = "INSERT INTO `penerimaankas` "
                                    + "(`namaId`, `bakeryIn`, `cateringIn`, `tglLengkap`, `tipePembayaran`) "
                                    + "VALUES ('"+namaId+"', '"+bkr+"', '"+ctr+"', '"+tgl+"', '"+tipepem+"')";
                            int status = DBConnect.execute(SQL2);
                            if(status == 1){
                                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan",
                                        "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Error", JOptionPane.WARNING_MESSAGE);
                            }
                        } 
                } catch (SQLException ex) {
                    Logger.getLogger(dlgTambahEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String SQL3 = "SELECT namaId FROM nama2 where nama = '"+nama+"'";
            String namaid = "";
            ResultSet rs = DBConnect.executeQuery(SQL3);
            try {
                if(rs.next()){
                    namaid = rs.getString("namaId");
                    String SQL4 = "UPDATE penerimaankas SET namaId='"+namaid+"', "
                            + "bakeryIn='"+bkr+"', "
                            + "cateringIn='"+ctr+"', "
                            + "tglLengkap='"+tgl+"', "
                            + "tipePembayaran='"+tipepem+"' "
                            + "WHERE id='"+this.id+"'";
                    int status = DBConnect.execute(SQL4);
                    if (status==1) {
                        JOptionPane.showMessageDialog(this, "Data berhasil dirubah",
                                "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Data gagal dirubah",
                                "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(dlgTambahEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(dlgTambahEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgTambahEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgTambahEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgTambahEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgTambahEdit dialog = new dlgTambahEdit(new javax.swing.JFrame(), true, tipe, id);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
