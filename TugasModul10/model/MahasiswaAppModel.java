/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.TugasModul10.model;

/**
 *
 * @author ASUS
 */
import id.ac.unpas.pp2_c_233040041.modul10.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaAppModel {
    private String nama;
    private String nim;
    private String jurusan;

    // Konstruktor
    public MahasiswaAppModel() {}
    public MahasiswaAppModel(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    // Getter dan Setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }

    // --- LOGIKA DATABASE ---
    public List<MahasiswaAppModel> getAll() throws SQLException {
        List<MahasiswaAppModel> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa";
        try (Connection conn = KoneksiDB.configDB(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MahasiswaAppModel(rs.getString("nama"), rs.getString("nim"), rs.getString("jurusan")));
            }
        }
        return list;
    }

    public boolean save() throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        try (Connection conn = KoneksiDB.configDB(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, this.nama);
            ps.setString(2, this.nim);
            ps.setString(3, this.jurusan);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update() throws SQLException {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, this.nama);
            ps.setString(2, this.jurusan);
            ps.setString(3, this.nim);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete() throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, this.nim);
            return ps.executeUpdate() > 0;
        }
    }

    // Tambahan untuk Latihan 3: Cari Data
    public List<MahasiswaAppModel> search(String keyword) throws SQLException {
        List<MahasiswaAppModel> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ?";
        try (Connection conn = KoneksiDB.configDB(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new MahasiswaAppModel(rs.getString("nama"), rs.getString("nim"), rs.getString("jurusan")));
                }
            }
        }
        return list;
    }

    // Tambahan untuk Latihan 4: Cek Duplikasi NIM
    public boolean isExists(String nim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}