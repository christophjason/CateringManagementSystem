/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internal;

import dialog.dlgKodePer;
import dialog.dlgNama;
import dialog.dlgTambahEditPengeluaranKas;
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
public class inPengeluaranKas extends javax.swing.JInternalFrame {

    /**
     * Creates new form inPengeluaranKas
     */
    DecimalFormat kursIndonesia;
    public inPengeluaranKas() {
        initComponents();
        decimalFormatRupiah();
    }
    public void decimalFormatRupiah(){
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        
        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
    
    public void ambilDataPetty(){
        int year = jYearChooser1.getYear();
        int month = jMonthChooser1.getMonth()+1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String kolom[] = {"ID","Tanggal","Nama","Kode Perkiraan","Nama Perkiraan","Nama Pegawai","Description","Debet","Kredit","Total Pengeluaran"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM pengeluarankas "
                + "INNER JOIN kodeperkiraan ON kodeperkiraan.kodePer = pengeluarankas.kodePer "
                + "INNER JOIN nama2 ON nama2.namaId = pengeluarankas.namaId "
                + "WHERE tipeId= '1' "
                + "AND year(tgl) = '"+year+"' "
                + "AND month(tgl) = '"+month+"'";
        ResultSet rs = DBConnect.executeQuery(SQL);
        try {
            while(rs.next()){
                String id = rs.getString(1);
                String tgl = rs.getString(2);
                String nama = rs.getString("nama");
                String kode = rs.getString(3);
                String kodeper = rs.getString("kodeperkiraan.namaPer");
                String nampeg = rs.getString(4);
                String descr = rs.getString(5);
                String deb =rs.getString(6);
                String kred = rs.getString(7);
                long total = Long.parseLong(deb)+Long.parseLong(kred);
                String[] data = {id,tgl,nama,kode,kodeper,nampeg,descr,
                    kursIndonesia.format(Long.parseLong(deb)),
                    kursIndonesia.format(Long.parseLong(kred)),
                    kursIndonesia.format(total)};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inPengeluaranKas.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(dtm);
    }
    
    public void ambilDataNonpetty(){
        int year = jYearChooser1.getYear();
        int month = jMonthChooser1.getMonth()+1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String kolom[] = {"ID","Tanggal","Nama","Kode Perkiraan","Nama Perkiraan","Nama Pegawai","Description","Debet","Kredit","Total Pengeluaran"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM pengeluarankas "
                + "INNER JOIN kodeperkiraan ON kodeperkiraan.kodePer = pengeluarankas.kodePer "
                + "INNER JOIN nama2 ON nama2.namaId = pengeluarankas.namaId "
                + "WHERE tipeId= '2' "
                + "AND year(tgl) = '"+year+"' "
                + "AND month(tgl) = '"+month+"'";
        ResultSet rs = DBConnect.executeQuery(SQL);
        try {
            while(rs.next()){
                String id = rs.getString(1);
                String tgl = rs.getString(2);
                String nama = rs.getString("nama");
                String kode = rs.getString(3);
                String kodeper = rs.getString("kodeperkiraan.namaPer");
                String nampeg = rs.getString(4);
                String descr = rs.getString(5);
                String deb =rs.getString(6);
                String kred = rs.getString(7);
                long total = Long.parseLong(deb)+Long.parseLong(kred);
                String[] data = {id,tgl,nama,kode,kodeper,nampeg,descr,
                    kursIndonesia.format(Long.parseLong(deb)),
                    kursIndonesia.format(Long.parseLong(kred)),
                    kursIndonesia.format(total)};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inPengeluaranKas.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(dtm);
    }
    
    public void search(){
        if(jRadioButton1.isSelected()){
        String search = jTextField1.getText().toString();
        String kolom[] = {"ID","Tanggal","Nama","Kode Perkiraan","Nama Perkiraan",
        "Nama Pegawai","Description","Debet","Kredit","Total Pengeluaran"};
        DefaultTableModel dtm = new DefaultTableModel(null,kolom);
        String SQL = "SELECT * FROM pengeluarankas "
                + "INNER JOIN kodeperkiraan on kodeperkiraan.kodePer = pengeluarankas.kodePer "
                + "INNER JOIN nama2 ON nama2.namaId = pengeluarankas.namaId "
                + "WHERE id LIKE '%"+search+"%' "
                + "OR kodeperkiraan.namaPer LIKE '%"+search+"%' "
                + "OR namaPegawai LIKE '%"+search+"%' "
                + "AND tipeId = '1' ";
        ResultSet rs = DBConnect.executeQuery(SQL);
        try {
            while(rs.next()){
                String id = rs.getString(1);
                String tgl = rs.getString(2);
                String nama = rs.getString("nama");
                String kodeper = rs.getString(3);
                String namaper = rs.getString("kodeperkiraan.namaPer");
                String nampeg = rs.getString(4);
                String descr = rs.getString(5);
                String deb = rs.getString(6);
                String kred = rs.getString(7);
                long total = Long.parseLong(deb)+Long.parseLong(kred);
                String[] data = {id,tgl,nama,kodeper,namaper,nampeg,descr,kursIndonesia.format(Long.parseLong(deb)),kursIndonesia.format(Long.parseLong(kred)),kursIndonesia.format(total)};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inPengeluaranKas.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(dtm);
        } else {
            String search = jTextField1.getText().toString();
        String kolom[] = {"ID","Tanggal","Nama","Kode Perkiraan","Nama Perkiraan",
        "Nama Pegawai","Description","Debet","Kredit","Total Pengeluaran"};
        DefaultTableModel dtm = new DefaultTableModel(null,kolom);
        String SQL = "SELECT * FROM pengeluarankas "
                + "INNER JOIN kodeperkiraan on kodeperkiraan.kodePer = pengeluarankas.kodePer "
                + "INNER JOIN nama2 ON nama2.namaId = pengeluarankas.namaId "
                + "WHERE id LIKE '%"+search+"%' "
                + "OR kodeperkiraan.namaPer LIKE '%"+search+"%' "
                + "OR namaPegawai LIKE '%"+search+"%' "
                + "AND tipeId = '2' ";
        ResultSet rs = DBConnect.executeQuery(SQL);
        try {
            while(rs.next()){
                String id = rs.getString(1);
                String tgl = rs.getString(2);
                String nama = rs.getString("nama");
                String kodeper = rs.getString(3);
                String namaper = rs.getString("kodeperkiraan.namaPer");
                String nampeg = rs.getString(4);
                String descr = rs.getString(5);
                String deb = rs.getString(6);
                String kred = rs.getString(7);
                long total = Long.parseLong(deb)+Long.parseLong(kred);
                String[] data = {id,tgl,nama,kodeper,namaper,nampeg,descr,kursIndonesia.format(Long.parseLong(deb)),kursIndonesia.format(Long.parseLong(kred)),kursIndonesia.format(total)};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inPengeluaranKas.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(dtm);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jButton1.setText("Lihat Kode Perkiraan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Laporan Biaya Petty Cash");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Laporan Biaya Non-Petty Cash");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton2.setText("Tambah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Lihat Nama");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton1)
                                .addComponent(jRadioButton2)
                                .addComponent(jButton7))
                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton6)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        ambilDataPetty();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        ambilDataNonpetty();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
            ambilDataPetty();
        } else if(jRadioButton2.isSelected()){
            ambilDataNonpetty();
    }//GEN-LAST:event_jButton6ActionPerformed
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int tipepeng =0;
        if(jRadioButton1.isSelected()){
            tipepeng = 0;
        } else {
            tipepeng = 1;
        }
        dlgTambahEditPengeluaranKas tambah = new dlgTambahEditPengeluaranKas(new javax.swing.JFrame(), closable, 0,tipepeng,0);
        tambah.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();
        
        if (baris != -1) {
            int id = Integer.parseInt(jTable1.getValueAt(baris, 0).toString());
            String SQL = "DELETE FROM pengeluarankas WHERE id = '"+id+"'";
            int status = DBConnect.execute(SQL);
             if(status == 1){
                 JOptionPane.showMessageDialog(this, "Data berhasil dihapus","Berhasil",JOptionPane.INFORMATION_MESSAGE);
                 if(jRadioButton1.isSelected()){
                     ambilDataPetty();
                 } else {
                     ambilDataNonpetty();
                 }
             } else {
                 JOptionPane.showMessageDialog(this, "Data gagal dihapus","Error",JOptionPane.WARNING_MESSAGE);
             }
        } else {
            JOptionPane.showMessageDialog(this, "Harap pilih data terlebih dahulu","Error",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dlgKodePer kode = new dlgKodePer(new javax.swing.JFrame(), closable);
        kode.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();
        if (baris != -1){
            int id = Integer.parseInt(jTable1.getValueAt(baris, 0).toString());
            int tipepeng =0;
            if(jRadioButton1.isSelected()){
                tipepeng = 0;
            } else {
                tipepeng = 1;
            }
        dlgTambahEditPengeluaranKas edit = new dlgTambahEditPengeluaranKas(new javax.swing.JFrame(), closable, 1,tipepeng, id);
        edit.setVisible(true);        
        } else {
            JOptionPane.showMessageDialog(this, "Harap pilih data terlebih dahulu", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        dlgNama nama = new dlgNama(new javax.swing.JFrame(), closable);
        nama.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
