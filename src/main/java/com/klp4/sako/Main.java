package com.klp4.sako;

import com.formdev.flatlaf.FlatLightLaf;
import com.klp4.sako.model.Admin;
import com.klp4.sako.model.Penjual;
import com.klp4.sako.repository.DatabaseConnection;
import com.klp4.sako.repository.UserRepository;
import com.klp4.sako.view.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Memulai aplikasi SAKO...");
        
        // Setup FlatLaf Theme
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        // Inisialisasi database
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Koneksi database berhasil!");
        } else {
            System.err.println("Aplikasi gagal terhubung ke database.");
            return;
        }

        // Seeding Data Awal
        UserRepository userRepository = new UserRepository();
        Admin seedAdmin = (Admin) userRepository.getUserByUsername("admin");
        if (seedAdmin == null) {
            seedAdmin = new Admin("A001", "Administrator", "admin", "admin123");
            userRepository.tambahUser(seedAdmin);
            System.out.println("User admin berhasil ditambahkan.");
        }
        
        Penjual seedPenjual = (Penjual) userRepository.getUserByUsername("penjual1");
        if (seedPenjual == null) {
            seedPenjual = new Penjual("P001", "Pak Budi", "penjual1", "penjual123");
            userRepository.tambahUser(seedPenjual);
            System.out.println("User penjual berhasil ditambahkan.");
        }

        // Buka MainFrame secara langsung sebagai hub universal (Pelanggan, Penjual, Admin)
        final Admin finalAdmin = seedAdmin;
        final Penjual finalPenjual = seedPenjual;
        
        SwingUtilities.invokeLater(() -> {
            new com.klp4.sako.view.MainFrame(finalAdmin, finalPenjual).setVisible(true);
        });
        
        System.out.println("Sistem siap.");
    }
}
