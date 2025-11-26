/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul07;

/**
 *
 * @author ASUS
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TugasManajemenNilaiSiswaApp extends JFrame {

    private JTextField inputNama;
    private JTextField inputNilai;
    private JComboBox<String> inputMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabPane;

    // Panel Input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Nama Mahasiswa:"));
        inputNama = new JTextField();
        panel.add(inputNama);

        panel.add(new JLabel("Nilai:"));
        inputNilai = new JTextField();
        panel.add(inputNilai);

        panel.add(new JLabel("Mata Kuliah:"));
        inputMatkul = new JComboBox<>(new String[]{
                "Matematika Dasar", "Bahasa Indonesia",
                "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"
        });
        panel.add(inputMatkul);

        //  Tambahan tombol Reset
        JButton btnSimpan = new JButton("Simpan Data");
        JButton btnReset = new JButton("Reset");

        panel.add(btnSimpan);
        panel.add(btnReset);

        btnSimpan.addActionListener(e -> prosesSimpan());

        // Tambahan aksi Tombol Reset  
        btnReset.addActionListener(e -> {
            inputNama.setText("");
            inputNilai.setText("");  
            inputMatkul.setSelectedIndex(0);
        });

        return panel;
    }

    // Panel Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama", "Mata Kuliah", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(tableData);
        panel.add(scroll, BorderLayout.CENTER);

        // Tambahan tombol Hapus
        JButton btnHapus = new JButton("Hapus Baris");
        panel.add(btnHapus, BorderLayout.SOUTH);

        btnHapus.addActionListener(e -> {
            int row = tableData.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row); 
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
            }
        });

        return panel;
    }

    // Logika Simpan Data
    private void prosesSimpan() {
        String nama = inputNama.getText().trim();
        String strNilai = inputNilai.getText().trim();
        String matkul = inputMatkul.getSelectedItem().toString();

        // Tambahan validasi nama siswa minimal 3 karakter 
        if (nama.isEmpty() || nama.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "Nama minimal 3 karakter dan tidak boleh kosong!");
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!");
            return;
        }

        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this, "Nilai harus 0 - 100!");
            return;
        }

        // Tambahan grade diubah ke switch-case 
        String grade;
        switch (nilai / 10) {
            case 10:
            case 9:
            case 8:
                grade = "A";
                break;
            case 7:
                grade = "B";
                break;
            case 6:
                grade = "C";
                break;
            case 5:
                grade = "D";
                break;
            default:
                grade = "E";
        }

        tableModel.addRow(new Object[]{nama, matkul, nilai, grade});
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
    }
    
    public TugasManajemenNilaiSiswaApp() {
        setTitle("Manajemen Nilai Siswa");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabPane = new JTabbedPane();
        tabPane.addTab("Input Data", createInputPanel());
        tabPane.addTab("Daftar Nilai", createTablePanel());

        add(tabPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TugasManajemenNilaiSiswaApp().setVisible(true));
    }
} 
