/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.TugasModul10.view;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaAppView extends JFrame {
    private JTextField txtNama = new JTextField();
    private JTextField txtNIM = new JTextField();
    private JTextField txtJurusan = new JTextField();
    private JTextField txtCari = new JTextField(15); 
    private JButton btnSimpan = new JButton("Simpan");
    private JButton btnEdit = new JButton("Edit");
    private JButton btnHapus = new JButton("Hapus");
    private JButton btnClear = new JButton("Clear");
    private JButton btnCari = new JButton("Cari"); 
    private JTable table = new JTable();
    private DefaultTableModel model = new DefaultTableModel();

    public MahasiswaAppView() {
        setTitle("Aplikasi Mahasiswa MVC");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel untuk Input
        JPanel pForm = new JPanel(new GridLayout(3, 2, 5, 5));
        pForm.setBorder(BorderFactory.createTitledBorder("Input Data"));
        pForm.add(new JLabel(" Nama:")); pForm.add(txtNama);
        pForm.add(new JLabel(" NIM:")); pForm.add(txtNIM);
        pForm.add(new JLabel(" Jurusan:")); pForm.add(txtJurusan);

        // Panel untuk Tombol
        JPanel pButtons = new JPanel();
        pButtons.add(btnSimpan); pButtons.add(btnEdit); pButtons.add(btnHapus); pButtons.add(btnClear);

        // Tambahan untuk Latihan 3: Panel Cari 
        JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pSearch.add(new JLabel("Cari Nama/NIM: ")); pSearch.add(txtCari); pSearch.add(btnCari);

        // Gabungkan dari Bagian yang Atas
        JPanel pTop = new JPanel(new BorderLayout());
        pTop.add(pForm, BorderLayout.NORTH);
        pTop.add(pButtons, BorderLayout.CENTER);
        pTop.add(pSearch, BorderLayout.SOUTH);

        // Konfigurasi Tabel
        model.addColumn("No"); model.addColumn("Nama"); model.addColumn("NIM"); model.addColumn("Jurusan");
        table.setModel(model);

        add(pTop, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Getter untuk yang diakses oleh Controller
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtNIM() { return txtNIM; }
    public JTextField getTxtJurusan() { return txtJurusan; }
    public JTextField getTxtCari() { return txtCari; }
    public JButton getBtnSimpan() { return btnSimpan; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnCari() { return btnCari; }
    public JTable getTable() { return table; }
    public DefaultTableModel getModel() { return model; }
}
