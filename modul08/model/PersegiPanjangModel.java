/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul08.model;

/**
 *
 * @author ASUS 
 */

public class PersegiPanjangModel {
    private double panjang;
    private double lebar;
    private double luas;
    private double keliling; // Tambahan untuk Latihan 2

    // Menghitung luas (Logika Bisnis)
    public void hitungLuas() {
        this.luas = this.panjang * this.lebar;
    }
    
    // Menghitung keliling (Logika Bisnis) - Tambahan Latihan 2
    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }
    
    // Getters dan Setters
    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public double getLuas() {
        return luas;
    }
    
    // Getter untuk keliling - Tambahan untuk Latihan 2
    public double getKeliling() {
        return keliling;
    }
}

