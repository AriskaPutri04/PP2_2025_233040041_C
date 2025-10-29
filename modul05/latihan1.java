/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul05;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author ASUS
 */
public class latihan1 {
    
    public static void main(String[] args) {
        //Menjalankan kode GUI dI Event Dispatch Thread (EDT)
        // Ini adalah praktik terbaik untuk menghindari masalah thread
        // Akan dijelaskan lebih detai nanti
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //1. Buat objek JFrame
                JFrame frame = new JFrame("Jendela Pertama");
                
                //2, Atur ukuran jendela(lebar 400, tinggi 300)
                frame.setSize(400, 300);
                
                //3, Atur aksi saat tombol close (X) ditekan
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //4. Buat jendela terlihat
                frame.setVisible(true);
            }
        });
    }
    
}
