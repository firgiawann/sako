package com.klp4.sako.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:sako.db";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                initializeDatabase(connection);
            }
        } catch (SQLException e) {
            System.err.println("Gagal koneksi ke database: " + e.getMessage());
        }
        return connection;
    }

    private static void initializeDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Tabel users
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id TEXT PRIMARY KEY, " +
                    "nama TEXT NOT NULL, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL)");

            // Tabel antrian
            stmt.execute("CREATE TABLE IF NOT EXISTS antrian (" +
                    "id TEXT PRIMARY KEY, " +
                    "nomor INTEGER NOT NULL, " +
                    "nama_pelanggan TEXT NOT NULL, " +
                    "status TEXT NOT NULL, " +
                    "waktu_ambil TEXT NOT NULL, " +
                    "estimasi TEXT)");

            // Tabel pesanan
            stmt.execute("CREATE TABLE IF NOT EXISTS pesanan (" +
                    "id TEXT PRIMARY KEY, " +
                    "antrian_id TEXT NOT NULL, " +
                    "catatan TEXT, " +
                    "status TEXT NOT NULL)");

            // Tabel notifikasi
            stmt.execute("CREATE TABLE IF NOT EXISTS notifikasi (" +
                    "id TEXT PRIMARY KEY, " +
                    "antrian_id TEXT NOT NULL, " +
                    "pesan TEXT NOT NULL, " +
                    "jenis TEXT NOT NULL, " +
                    "dibaca INTEGER NOT NULL DEFAULT 0, " +
                    "waktu_kirim TEXT NOT NULL)");

            // Tabel settings
            stmt.execute("CREATE TABLE IF NOT EXISTS settings (" +
                    "id INTEGER PRIMARY KEY, " +
                    "batas_notifikasi INTEGER NOT NULL DEFAULT 2, " +
                    "format_nomor TEXT NOT NULL DEFAULT 'A-', " +
                    "jam_buka TEXT NOT NULL DEFAULT '08:00', " +
                    "jam_tutup TEXT NOT NULL DEFAULT '17:00')");
                    
            // Tambahkan kolom pesanan ke tabel antrian jika belum ada
            try {
                stmt.execute("ALTER TABLE antrian ADD COLUMN pesanan TEXT");
            } catch (SQLException ignore) {
                // Kolom mungkin sudah ada, abaikan error
            }
                    
        } catch (SQLException e) {
            System.err.println("Gagal inisialisasi tabel: " + e.getMessage());
        }
    }
}
