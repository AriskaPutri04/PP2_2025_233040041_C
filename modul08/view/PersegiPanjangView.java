/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040041.modul08.view;

/**
 *
 * @author ASUS
 */
 
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {

    // Komponen UI
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-"); // Mengubah nama lblHasil menjadi lblHasilLuas
    private JLabel lblHasilKeliling = new JLabel("-"); // Tambahan untuk Latihan 2
    private JButton btnHitung = new JButton("Hitung"); // Mengubah nama tombol Hitung Luas menjadi Hitung (disesuaikan tampilan)
    private JButton btnReset = new JButton("Reset"); // Tambahan untuk Latihan 3

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
        this.setLayout(new GridLayout(6, 2, 10, 10)); // Grid 6 baris
        this.setTitle("MVC Kalkulator");

        this.add(new JLabel("Panjang:")); // Baris 1
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));  // Baris 2
        this.add(txtLebar);
        this.add(new JLabel("Hasil Luas:"));  // Baris 3
        this.add(lblHasilLuas);
        this.add(new JLabel("Hasil Keliling:")); // Baris 4 Tambahan untuk Latihan 2
        this.add(lblHasilKeliling);             
        this.add(btnHitung);
        this.add(btnReset); // // Baris 5 tombol Hitung & Reset - Tambahan untuk Latihan 3
        this.add(new JLabel("")); // Baris 6 Spacer kosong
        this.add(new JLabel("")); // Baris 6 Spacer kosong
    } 

    // Mengambil nilai panjang dari Textfield
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai lebar dari Textfield
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Menampilkan hasil luas ke Label
    public void setHasilLuas(double hasil) {
        lblHasilLuas.setText(String.valueOf(hasil));
    }

    // Menampilkan hasil keliling ke Label (Latihan 2)
    public void setHasilKeliling(double hasil) { // Tambahan untuk Latihan 2
        lblHasilKeliling.setText(String.valueOf(hasil)); // Tambahan untuk Latihan 2
    }

    // Mereset semua input dan hasil (Latihan 3)
    public void resetFields() { // Tambahan untuk Latihan 3
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
    }

    // Menampilkan pesan error
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Listener tombol Hitung
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }

    // Listener tombol Reset (Latihan 3)
    public void addResetListener(ActionListener listener) { // Tambahan untuk Latihan 3
        btnReset.addActionListener(listener); // Tambahan untuk Latihan 3
    }
}

