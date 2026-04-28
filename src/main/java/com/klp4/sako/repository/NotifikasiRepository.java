package com.klp4.sako.repository;

import com.klp4.sako.model.Notifikasi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotifikasiRepository {
    public void tambahNotifikasi(Notifikasi notifikasi) {
        String sql = "INSERT INTO notifikasi(id, antrian_id, pesan, jenis, dibaca, waktu_kirim) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, notifikasi.getIdNotifikasi());
            pstmt.setString(2, notifikasi.getAntrianId());
            pstmt.setString(3, notifikasi.getPesan());
            pstmt.setString(4, notifikasi.getJenis());
            pstmt.setInt(5, notifikasi.isDibaca() ? 1 : 0);
            pstmt.setString(6, notifikasi.getWaktuKirim());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal simpan notifikasi: " + e.getMessage());
        }
    }

    public boolean existsByAntrianAndJenis(String antrianId, String jenis) {
        String sql = "SELECT 1 FROM notifikasi WHERE antrian_id = ? AND jenis = ? LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, antrianId);
            pstmt.setString(2, jenis);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Gagal cek notifikasi: " + e.getMessage());
        }
        return false;
    }

    public List<Notifikasi> getNotifikasiByAntrian(String antrianId) {
        List<Notifikasi> list = new ArrayList<>();
        String sql = "SELECT * FROM notifikasi WHERE antrian_id = ? ORDER BY waktu_kirim DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, antrianId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil notifikasi: " + e.getMessage());
        }
        return list;
    }

    public List<Notifikasi> getNotifikasiTerbaru(int limit) {
        List<Notifikasi> list = new ArrayList<>();
        String sql = "SELECT * FROM notifikasi ORDER BY waktu_kirim DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil notifikasi terbaru: " + e.getMessage());
        }
        return list;
    }

    public void deleteAll() {
        String sql = "DELETE FROM notifikasi";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal hapus notifikasi: " + e.getMessage());
        }
    }

    private Notifikasi mapResultSet(ResultSet rs) throws SQLException {
        return new Notifikasi(
                rs.getString("id"),
                rs.getString("antrian_id"),
                rs.getString("pesan"),
                rs.getString("jenis"),
                rs.getInt("dibaca") == 1,
                rs.getString("waktu_kirim")
        );
    }
}
