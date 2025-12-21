/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.TugasModul10;

/**
 *
 * @author ASUS
 */

import id.ac.unpas.pp2_c_233040041.TugasModul10.model.MahasiswaAppModel;
import id.ac.unpas.pp2_c_233040041.TugasModul10.view.MahasiswaAppView;
import id.ac.unpas.pp2_c_233040041.TugasModul10.controller.MahasiswaAppController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Menjalankan aplikasi di Event Dispatch Thread agar GUI aman atau stabil
        SwingUtilities.invokeLater(() -> {
            MahasiswaAppView view = new MahasiswaAppView();// Inisialisasi View
            new MahasiswaAppController(view);// Inisialisasi Controller dan hubungkan dengan View
            view.setVisible(true);// Tampilkan Jendela Aplikasi
        });
    }
}
 