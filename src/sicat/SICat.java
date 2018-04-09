/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicat;

import dialog.login;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author chris
 */
public class SICat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DBConnect.setConnect();
        login log = new login(new javax.swing.JFrame(), true);
        log.setVisible(true);
    }
}
