/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import internal.inLapPiutang;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sicat.DBConnect;

/**
 *
 * @author chris
 */
public class dlgPembayaran extends javax.swing.JDialog {

    /**
     * Creates new form dlgPembayaran
     */
    DecimalFormat kursIndonesia;
    static int id =0;
    public dlgPembayaran(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        this.id = id;
        initComponents();
        insertDat();
        insertSejarah();
        decimalFormatRupiah();
        insertSelisih();
    }
    
    public dlgPembayaran(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        insertDat();
        insertSejarah();
        decimalFormatRupiah();
        insertSelisih();
    }
    
    public void insertDat(){
        String SQL = "SELECT * FROM lappihut WHERE noInvoice = '"+id+"'";
        jTextField2.removeAll();
        ResultSet rs = DBConnect.executeQuery(SQL);
        try {
            while (rs.next()){
                jTextField4.setText(rs.getString("tglAwal"));
                jTextField5.setText(rs.getString("tglAkhir"));
                jTextField2.setText(rs.getString("noInvoice"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dlgPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void decimalFormatRupiah(){
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        
        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
    
    private void insertSejarah(){
        String kolom[] = {"ID","Nomor Invoice", "Nilai Pembayaran", "Tanggal Pembayaran"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM detaillappihut WHERE noInvoice = '"+id+"'";
        ResultSet rs = DBConnect.executeQuery(SQL);
        try {
            while(rs.next()){
                String id = rs.getString(1);
                String no = rs.getString(2);
                String nilai = rs.getString(3);
                String tgl = rs.getString(4);
                String data[] = {id,no,nilai,tgl};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dlgPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(dtm);
    }
    
    private void insertSelisih(){
        jTextField3.removeAll();
        int selisih =0;
        String SQL = "SELECT nilai FROM lappihut WHERE noInvoice = '"+id+"'";
        ResultSet rs = DBConnect.executeQuery(SQL);
        try {
            if(rs.next()){
                int nilai = Integer.parseInt(rs.getString(1));
                String SQL2 = "SELECT SUM(nilaiPembayaran) FROM detaillappihut WHERE noInvoice = '"+id+"'";
                ResultSet rs2 = DBConnect.executeQuery(SQL2);
                if(rs2.next()){
                    if(rs2.getString(1) == null){
                        jTextField3.setText(kursIndonesia.format(nilai));
                    } else {
                        int nilaipem = Integer.parseInt(rs2.getString(1));
                        selisih = nilai - nilaipem;
                        if(selisih==0){
                            jTextField3.setText("LUNAS");
                            String SQL3 = "UPDATE lappihut SET ket = '1' WHERE noInvoice = '"+id+"'";
                            int status = DBConnect.execute(SQL3);
                            if(status == 1){
                                JOptionPane.showMessageDialog(this, "Nomor Invoice: "+id+" LUNAS");
                            } else {
                                JOptionPane.showMessageDialog(this, "Error",
                                        "Error",JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            jTextField3.setText(kursIndonesia.format(selisih));
                        }
                    }
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(dlgPembayaran.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pembayaran");

        jLabel2.setText("Nomor Invoice");

        jLabel3.setText("Nilai Pembayaran");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Bayar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tanggal Pembayaran");

        jTextField2.setEditable(false);

        jLabel5.setText("Sejarah Pembayaran");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Selisih:");

        jTextField3.setEditable(false);

        jLabel7.setText("Periode");

        jTextField4.setEditable(false);

        jLabel8.setText("-");

        jTextField5.setEditable(false);

        jLabel9.setText("Rp.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int noinvoice = this.id;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nilai = jTextField1.getText().toString();
        java.util.Date tang = jDateChooser1.getDate();
        String tgl = sdf.format(tang);
        if(nilai.isEmpty()||tgl.isEmpty()){
            JOptionPane.showMessageDialog(this, "Harap lengkapi data terlebih dahulu"
            , "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQ = "SELECT tglAwal, tglAkhir FROM lappihut WHERE noInvoice = '"+noinvoice+"'";
            ResultSet rs3 = DBConnect.executeQuery(SQ);
            try {
                if(rs3.next()){
                    Date tglawal = rs3.getDate(1);
                    Date tglakhir = rs3.getDate(2);
                    if(tang.after(tglawal)&&tang.before(tglakhir)){
                        String SQL = "SELECT nilai FROM lappihut WHERE noInvoice = '"+noinvoice+"'";
                        ResultSet rs = DBConnect.executeQuery(SQL);
                        if(rs.next()){
                            int niltol = Integer.parseInt(rs.getString(1));
                            String SQL2 = "SELECT SUM(nilaiPembayaran) FROM detaillappihut WHERE noInvoice = '"+noinvoice+"'";
                            ResultSet rs2 = DBConnect.executeQuery(SQL2);
                            if(rs2.next()){
                                if(rs2.getString(1) == null){
                                    int seli = niltol - Integer.parseInt(nilai);
                                    String SQL4 = "INSERT INTO detaillappihut "
                                            + "(noInvoice,nilaiPembayaran,tglPembayaran) "
                                            + "VALUES('"+noinvoice+"','"+nilai+"','"+tgl+"')";
                                    int status = DBConnect.execute(SQL4);
                                    if(status == 1){
                                        JOptionPane.showMessageDialog(this,
                                                "Nomor invoice "+noinvoice+" berhasil terbayar Rp."+kursIndonesia.format(Integer.parseInt(nilai))+".\n"
                                                        + "Selisih dari pembayaran yang harus dibayar: Rp. "+kursIndonesia.format(seli)+"",
                                                "Berhasil",JOptionPane.INFORMATION_MESSAGE);
                                        insertSelisih();
                                        insertSejarah();
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Pembayaran gagal",
                                                "Error",JOptionPane.WARNING_MESSAGE);
                                    }
                                } else {
                                int nilpem = Integer.parseInt(rs2.getString(1));
                                int sel = niltol - nilpem;
                                if(sel == 0){
                                    JOptionPane.showMessageDialog(this, "Nomor Invoice ini sudah lunas","Lunas!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    String SQL3 = "INSERT INTO detaillappihut "
                                            + "(noInvoice,nilaiPembayaran,tglPembayaran) "
                                            + "VALUES ('"+noinvoice+"','"+nilai+"','"+tgl+"')";
                                    int status = DBConnect.execute(SQL3);
                                    if(status == 1){
                                        String SQL5 = "SELECT SUM(nilaiPembayaran) FROM detaillappihut WHERE noInvoice = '"+noinvoice+"'";
                                        ResultSet rs5 = DBConnect.executeQuery(SQL5);
                                        if(rs5.next()){
                                            int nilpe = Integer.parseInt(rs5.getString(1));
                                            int se = niltol - nilpe;
                                            JOptionPane.showMessageDialog(this,
                                                "Nomor invoice '"+noinvoice+"' berhasil terbayar Rp."+kursIndonesia.format(Integer.parseInt(nilai))+".\n"
                                                        + "Selisih dari pembayaran yang harus dibayar: Rp. "+kursIndonesia.format(se)+"",
                                                "Berhasil",JOptionPane.INFORMATION_MESSAGE);
                                            insertSelisih();
                                            insertSejarah();
                                        }
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Pembayaran gagal",
                                                "Error",JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                                }
                                
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(this,"Nomor Invoice: '"+noinvoice+"' sudah jatuh tempo","Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal ditambahkan","Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(dlgPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        insertSejarah();
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
            java.util.logging.Logger.getLogger(dlgPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgPembayaran dialog = new dlgPembayaran(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
