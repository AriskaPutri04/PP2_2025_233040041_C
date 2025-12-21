/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul10;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MahasiswaApp extends JFrame {

    // Komponen GUI
    JTextField txtNama, txtNIM, txtJurusan;
    // Tambahan untuk Latihan 3: Field dan Tombol Pencarian
    JTextField txtCari; 
    JButton btnSimpan, btnEdit, btnHapus, btnClear;
    JButton btnCari; 
    JTable tableMahasiswa;
    DefaultTableModel model;

    public MahasiswaApp() {
        // Setup Frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(800, 500); // Ukuran diperbesar untuk menampung komponen cari
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel Form (Input Data)
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol CRUD
        JPanel panelTombolCRUD = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombolCRUD.add(btnSimpan);
        panelTombolCRUD.add(btnEdit);
        panelTombolCRUD.add(btnHapus);
        panelTombolCRUD.add(btnClear);
        
        // Tambahan untuk Latihan 3: Panel Pencarian
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari Nama/NIM:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari); 
        
        // Gabungkan Panel Form, Tombol CRUD, dan Pencarian
        JPanel panelTengahAtas = new JPanel(new BorderLayout());
        panelTengahAtas.add(panelForm, BorderLayout.NORTH);
        panelTengahAtas.add(panelTombolCRUD, BorderLayout.CENTER);
        
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelTengahAtas, BorderLayout.NORTH);
        panelAtas.add(panelCari, BorderLayout.SOUTH); // Tambahan untuk Latihan 3: Penempatan panel cari
        
        add(panelAtas, BorderLayout.NORTH);

        // 2. Tabel Data (Menampilkan Data)
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");
        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });

        // Aksi Tombol
        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        
        btnCari.addActionListener(e -> cariData(txtCari.getText())); // Tambahan untuk Latihan 3

        loadData();
    }
    
    // Tambahan untuk Latihan 3: Method Pencarian
    private void cariData(String keyword) {
        model.setRowCount(0); 
        
        try {
            Connection conn = KoneksiDB.configDB();
            // Query mencari data yang namanya ATAU nim mengandung keyword
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ?"; 
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, "%" + keyword + "%"); 
            pst.setString(2, "%" + keyword + "%"); 
            
            ResultSet res = pst.executeQuery();
            
            int no = 1;
            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
            if (model.getRowCount() == 0) {
                 JOptionPane.showMessageDialog(this, "Data dengan kata kunci '" + keyword + "' tidak ditemukan.");
                 loadData(); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Mencari Data: " + e.getMessage());
        }
    }

    // 1. READ (Menampilkan Data)
    private void loadData() {
        model.setRowCount(0); 

        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");

            int no = 1;
            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }

    // 2. CREATE (Menambah Data)
    private void tambahData() {
        // Tambahan untuk Latihan 2: Validasi Input Kosong
        if (txtNama.getText().isEmpty() || txtNIM.getText().isEmpty() || txtJurusan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        try {
            // Tambahan untuk Latihan 4: Pengecekan Duplikasi NIM
            if (cekDuplikasiNIM(txtNIM.getText())) {
                JOptionPane.showMessageDialog(this, "NIM sudah terdaftar! Gunakan NIM lain.", "Duplikasi Data", JOptionPane.WARNING_MESSAGE);
                return; 
            }
            
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        } catch (Exception e) {          // Tambahan untuk Latihan 2: Validasi Input Kosong
            JOptionPane.showMessageDialog(this, "Gagal Simpan:" + e.getMessage());
        }
    }

    // Tambahan untuk Latihan 4: Method Pengecekan Duplikasi NIM
    private boolean cekDuplikasiNIM(String nim) throws Exception {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        ResultSet res = pst.executeQuery();
        
        if (res.next()) {
            return res.getInt(1) > 0;
        }
        return false;
    }

    // 3. UPDATE (Mengubah Data berdasarkan NIM)
    private void ubahData() {
        // Tambahan untuk Latihan 2: Validasi Input Kosong (diterapkan juga di edit)
        if (txtNama.getText().isEmpty() || txtNIM.getText().isEmpty() || txtJurusan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText()); 
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
            
        } catch (Exception e) {         // Tambahan untuk Latihan 2: Validasi Input Kosong
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }

    // 4. DELETE (Menghapus Data)
    private void hapusData() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNIM.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
        }
    }

    // Reset Form
    private void kosongkanForm() {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
        // Tambahan untuk Latihan 3: Kosongkan juga field pencarian
        txtCari.setText(null); 
    } 
    
    public static void main(String[] args) {
        // Menjalankan Aplikasi
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}   

