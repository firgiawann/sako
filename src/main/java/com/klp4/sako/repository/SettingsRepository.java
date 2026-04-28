package com.klp4.sako.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SettingsRepository {
    public Map<String, String> getSettings() {
        Map<String, String> settings = new HashMap<>();
        String sql = "SELECT * FROM settings WHERE id = 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                settings.put("batas_notifikasi", String.valueOf(rs.getInt("batas_notifikasi")));
                settings.put("format_nomor", rs.getString("format_nomor"));
                settings.put("jam_buka", rs.getString("jam_buka"));
                settings.put("jam_tutup", rs.getString("jam_tutup"));
            } else {
                // Default settings if not exists
                settings.put("batas_notifikasi", "2");
                settings.put("format_nomor", "A-");
                settings.put("jam_buka", "08:00");
                settings.put("jam_tutup", "17:00");
                saveSettings(2, "A-", "08:00", "17:00");
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil settings: " + e.getMessage());
        }
        return settings;
    }

    public void saveSettings(int batas, String format, String buka, String tutup) {
        String sql = "INSERT OR REPLACE INTO settings(id, batas_notifikasi, format_nomor, jam_buka, jam_tutup) VALUES(1, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, batas);
            pstmt.setString(2, format);
            pstmt.setString(3, buka);
            pstmt.setString(4, tutup);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal simpan settings: " + e.getMessage());
        }
    }
}
