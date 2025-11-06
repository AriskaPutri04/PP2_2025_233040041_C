/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul06;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class Latihan2KonverterSuhu {
        public static void main(String[] args) {
        // 1. Buat Frame
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        // 2. Atur layout frame menjadi GridLayout 3 baris, 2 kolom
        frame.setLayout(new GridLayout(3, 2, 10, 10)); // 3 baris, 2 kolom, dengan gap 10px

        // 3. Buat Komponen
        JLabel celciusLabel = new JLabel("Celcius:");
        JTextField celciusInput = new JTextField(10);
        JButton konversiButton = new JButton("Konversi");
        JLabel fahrenheitLabel = new JLabel("Fahrenheit:");
        JLabel hasilLabel = new JLabel(""); // JLabel kosong untuk hasil

        // Tambahkan komponen ke Frame sesuai urutan GridLayout (kiri-ke-kanan, atas-ke-bawah)
        frame.add(celciusLabel);
        frame.add(celciusInput);
        frame.add(konversiButton);
        frame.add(new JLabel("")); 
        frame.add(fahrenheitLabel);
        frame.add(hasilLabel);

        // 4. Buat Event Listener
        ActionListener konversiListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ambil teks dari JTextField [cite: 306]
                    String inputCelciusStr = celciusInput.getText();
                    
                   // Ubah teks ke double [cite: 307]
                    double celcius = Double.parseDouble(inputCelciusStr);
                    
                    // Hitung Fahrenheit: (celcius * 9/5) + 32 [cite: 308]
                    double fahrenheit = (celcius * 9.0/5.0) + 32;
                    
                    // Atur teks JLabel hasil dengan nilai Fahrenheit [cite: 308]
                    // Menggunakan String.format untuk membulatkan 2 angka di belakang koma
                    hasilLabel.setText(String.format("%.2f", fahrenheit) + " °F");

                } catch (NumberFormatException ex) {
                    // Buat penanganan jika input bukan angka [cite: 309]
                    JOptionPane.showMessageDialog(frame, "Input Celcius harus berupa angka!", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                    hasilLabel.setText("---");
                }
            }
        };

        // 5. Daftarkan listener ke tombol
        konversiButton.addActionListener(konversiListener);

        // 6. Tampilkan Frame
        frame.setVisible(true);   
    }
        
}
  