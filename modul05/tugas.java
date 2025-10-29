/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
/**
 *
 * @author ASUS
 */
public class tugas {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout dengan Aksi Tombol");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Atur Layout Manager ke BorderLayout
                // Sebernarnya ini tidak perlu\
                // Karena BorderLayout adalah Layout Manager Deafult
                frame.setLayout(new BorderLayout());

                // 2. Buat komponen
                JLabel label = new JLabel("Silakan klik tombol yang ada.");
                JButton buttonSouth = new JButton("SOUTH");
                JButton buttonNorth = new JButton("NORTH");
                JButton buttonWest = new JButton("WEST");
                JButton buttonEast = new JButton("EAST");
                JButton buttonCenter = new JButton("CENTER");

                // Tambahkan Aksi tombol (ActionListener) untuk tombol
                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol SOUTH diklik!");
                });

                // Aksi tombol NORTH
                buttonNorth.addActionListener(e -> {
                    label.setText("Tombol NORTH diklik!");
                });

                // Aksi tombol WEST
                buttonWest.addActionListener(e -> {
                    label.setText("Tombol WEST diklik!");
                });

                // Aksi tombol EAST
                buttonEast.addActionListener(e -> {
                    label.setText("Tombol EAST diklik!");
                });

                // Aksi tombol CENTER
                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol CENTER diklik!");
                });

                // Tambahkan semua komponen ke frame sesuai DENGAN POSISI
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    } 
    
}
