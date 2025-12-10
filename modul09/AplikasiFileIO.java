/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul09;

/**
 *
 * @author ASUS
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {

    // Komponen UI
    private JTextArea textArea;
    //private JButton btnOpenText, btnSaveText;
    private JButton btnOpenText, btnSaveText, btnAppendText; // Tambahan untuk Latihan 4
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;

    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        
        //btnSaveText = new JButton("Simpan Text");
        btnSaveText = new JButton("Simpan Text (Overwrite)"); // Label disesuaikan   
        btnAppendText = new JButton("Tambah Text (Append)"); // Tambahan untuk Latihan 4: Inisialisasi tombol baru
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // Latihan 4: Tambahkan tombol ke panel
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary); 

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // -- Event Handling --
        
        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());

        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());

        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        
        // Latihan 4. Tambahkan listener untuk tombol Append
         btnAppendText.addActionListener(e -> tambahFileTeks()); 
         
         btnSaveBinary.addActionListener(e -> simpanConfigBinary()); 
         btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Latihan 2. Otomatis membaca last_notes.txt
        muatLastNotes();
             
        setVisible(true); 
    }

    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area

                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());

                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

// Tambahan untuk Latihan 4: Menambahkan Teks ke akhir file (Append)
    private void tambahFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Try-with-resources: Gunakan FileWriter(file, true) untuk mode append
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                
                // Tambahkan baris baru sebelum konten baru
                writer.write("\n\n--- [Append] ---\n"); 
                writer.write(textArea.getText());

                JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan ke file!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan teks ke file: " + ex.getMessage());
            }
        }
    } 

    // Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            // Kita simpan ukuran font saat ini (Integer)
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);

            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }

    // Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            // Membaca data Integer mentah
            int fontSize = dis.readInt(); 

            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));

            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }

// Tambahan untuk Latihan 2: Membaca file default (last_notes.txt) secara otomatis
    private void muatLastNotes() {
        // Nama file yang spesifik
        File file = new File("last_notes.txt");
        
        // Gunakan Try-with-Resources. [cite_start]Gunakan try-catch agar jika file tidak ada, aplikasi tidak akan error. [cite: 115]
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            
            textArea.setText(""); 
            String line;
            
            // Baca baris demi baris
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }

        } catch (FileNotFoundException ex) {
            // Jika file tidak ditemukan, sistem hanya diam saja (sesuai instruksi latihan). 
            System.out.println("File last_notes.txt belum ditemukan. Aplikasi dimulai dengan kosong.");
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca file last_notes.txt: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}

