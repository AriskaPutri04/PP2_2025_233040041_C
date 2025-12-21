/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.TugasModul10.controller;

/**
 *
 * @author ASUS
 */
import id.ac.unpas.pp2_c_233040041.TugasModul10.model.MahasiswaAppModel;
import id.ac.unpas.pp2_c_233040041.TugasModul10.view.MahasiswaAppView;

import javax.swing.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MahasiswaAppController {
    private MahasiswaAppView view;
    private MahasiswaAppModel modelHelper = new MahasiswaAppModel();

    public MahasiswaAppController(MahasiswaAppView view) {
        this.view = view;
        
        // Menghubungkan tombol di View dengan fungsi di Controller
        this.view.getBtnSimpan().addActionListener(e -> simpanData());
        this.view.getBtnEdit().addActionListener(e -> ubahData());
        this.view.getBtnHapus().addActionListener(e -> hapusData());
        this.view.getBtnClear().addActionListener(e -> resetForm());
        this.view.getBtnCari().addActionListener(e -> cariData()); // Latihan 3

        // Listener Klik Tabel (Memindahkan data tabel ke form input)
        this.view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                view.getTxtNama().setText(view.getModel().getValueAt(row, 1).toString());
                view.getTxtNIM().setText(view.getModel().getValueAt(row, 2).toString());
                view.getTxtJurusan().setText(view.getModel().getValueAt(row, 3).toString());
            }
        });

        // Load data awal saat aplikasi dibuka
        refreshTable();
    }

    // Tambahan untuk Latihan 2 & 4 Logika Simpan 
    private void simpanData() {
        String nama = view.getTxtNama().getText();
        String nim = view.getTxtNIM().getText();
        String jurusan = view.getTxtJurusan().getText();

        // Tambahan untuk Latihan 2: Validasi Input Kosong
        if(nama.isEmpty() || nim.isEmpty() || jurusan.isEmpty()){
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Tambahan untuk Latihan 4: Cek Duplikasi NIM
            if(modelHelper.isExists(nim)){
                JOptionPane.showMessageDialog(view, "NIM sudah terdaftar! Gunakan NIM lain.", "Duplikasi Data", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MahasiswaAppModel m = new MahasiswaAppModel(nama, nim, jurusan);
            if(m.save()){
                JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
                refreshTable();
                resetForm();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error Simpan: " + ex.getMessage());
        }
    }

    private void ubahData() {
        try {
            MahasiswaAppModel m = new MahasiswaAppModel(view.getTxtNama().getText(), view.getTxtNIM().getText(), view.getTxtJurusan().getText());
            if(m.update()){
                JOptionPane.showMessageDialog(view, "Data Berhasil Diubah");
                refreshTable();
                resetForm();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error Edit: " + ex.getMessage());
        }
    }

    private void hapusData() {
        try {
            MahasiswaAppModel m = new MahasiswaAppModel();
            m.setNim(view.getTxtNIM().getText());
            if(m.delete()){
                JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
                refreshTable();
                resetForm();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error Hapus: " + ex.getMessage());
        }
    }

    // Tambahan untuk Latihan 3: Logika Pencarian
    private void cariData() {
        String keyword = view.getTxtCari().getText();
        try {
            List<MahasiswaAppModel> list = modelHelper.search(keyword);
            tampilkanKeTabel(list);
            if(list.isEmpty()){
                JOptionPane.showMessageDialog(view, "Data tidak ditemukan.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void refreshTable() {
        try {
            List<MahasiswaAppModel> list = modelHelper.getAll();
            tampilkanKeTabel(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void tampilkanKeTabel(List<MahasiswaAppModel> list) {
        view.getModel().setRowCount(0);
        int no = 1;
        for(MahasiswaAppModel m : list){
            view.getModel().addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
        }
    }

    private void resetForm() {
        view.getTxtNama().setText("");
        view.getTxtNIM().setText("");
        view.getTxtJurusan().setText("");
        view.getTxtCari().setText("");
        refreshTable();
    }
}