/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul08.controller;

/**
 *
 * @author ASUS  
 */

import id.ac.unpas.pp2_c_233040041.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040041.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    // Model dan View sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // Menghubungkan tombol di View dengan logic di Controller
        this.view.addHitungListener(new HitungListener());
        
        // Menghubungkan tombol Hitung Keliling - Tambahan untuk Latihan 2
        this.view.addHitungListener(new HitungKelilingListener());
        
        // Menghubungkan tombol Reset – Tambahan untuk Latihan 3
        this.view.addResetListener(new ResetListener());
    }

    // Inner class untuk menangani event klik tombol
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data dari View
                double p = view.getPanjang();
                double l = view.getLebar();

                // 2. Kirim data ke Model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Jalankan logika bisnis di Model
                model.hitungLuas();

                // 4. Ambil hasil dari Model dan tampilkan kembali di View
                double hasil = model.getLuas();
                view.setHasilLuas(hasil);
            } catch (NumberFormatException ex) {
                // Handle jika user memasukkan huruf
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }
    
    // Inner class untuk menangani event klik tombol Hitung Keliling - Tambahan Latihan 2
    class HitungKelilingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();
                
                model.setPanjang(p);
                model.setLebar(l);
                
                model.hitungKeliling();
                
                double hasil = model.getKeliling();
                view.setHasilKeliling(hasil);
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }
    
    // Inner class untuk menangani event klik tombol Reset - Tambahan untuk Latihan 3
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Langsung memanggil metode reset di View
            view.resetFields();
        }
    }
}


