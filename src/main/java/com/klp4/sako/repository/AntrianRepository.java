package com.klp4.sako.repository;

import com.klp4.sako.model.Antrian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AntrianRepository {
    public void tambahAntrian(Antrian antrian) {
        String sql = "INSERT INTO antrian(id, nomor, nama_pelanggan, status, waktu_ambil, estimasi, pesanan) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, antrian.getId());
            pstmt.setInt(2, antrian.getNomor());
            pstmt.setString(3, antrian.getNamaPelanggan());
            pstmt.setString(4, antrian.getStatus());
            pstmt.setString(5, antrian.getWaktuAmbil());
            pstmt.setString(6, antrian.getEstimasiDilayani());
            pstmt.setString(7, antrian.getPesanan());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal tambah antrian: " + e.getMessage());
        }
    }

    public void updateStatusAntrian(String id, String status) {
        String sql = "UPDATE antrian SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal update antrian: " + e.getMessage());
        }
    }

    public List<Antrian> getAllAntrianAktif() {
        List<Antrian> list = new ArrayList<>();
        String sql = "SELECT * FROM antrian WHERE status != 'SELESAI' ORDER BY nomor ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Antrian antrian = new Antrian(
                        rs.getString("id"),
                        rs.getInt("nomor"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("status"),
                        rs.getString("waktu_ambil"),
                        rs.getString("estimasi"),
                        rs.getString("pesanan")
                );
                list.add(antrian);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil antrian aktif: " + e.getMessage());
        }
        return list;
    }

    public Antrian getAntrianById(String id) {
        String sql = "SELECT * FROM antrian WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil antrian: " + e.getMessage());
        }
        return null;
    }

    public int getJumlahAntrianSebelum(int nomor) {
        String sql = "SELECT COUNT(*) AS total FROM antrian WHERE nomor < ? AND status IN ('MENUNGGU', 'DIPROSES')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nomor);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Gagal hitung posisi antrian: " + e.getMessage());
        }
        return 0;
    }

    public Map<String, Integer> getRingkasanStatus() {
        Map<String, Integer> ringkasan = new LinkedHashMap<>();
        ringkasan.put("MENUNGGU", 0);
        ringkasan.put("DIPROSES", 0);
        ringkasan.put("SIAP", 0);
        ringkasan.put("SELESAI", 0);

        String sql = "SELECT status, COUNT(*) AS total FROM antrian GROUP BY status";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ringkasan.put(rs.getString("status"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil ringkasan antrian: " + e.getMessage());
        }
        return ringkasan;
    }
    
    public int getNomorAntrianTerakhir() {
        String sql = "SELECT MAX(nomor) as max_nomor FROM antrian";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("max_nomor");
            }
        } catch (SQLException e) {
             System.err.println("Gagal ambil nomor terakhir: " + e.getMessage());
        }
        return 0;
    }

    public void deleteAll() {
        String sql = "DELETE FROM antrian";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
             System.err.println("Gagal hapus semua antrian: " + e.getMessage());
        }
    }

    private Antrian mapResultSet(ResultSet rs) throws SQLException {
        return new Antrian(
                rs.getString("id"),
                rs.getInt("nomor"),
                rs.getString("nama_pelanggan"),
                rs.getString("status"),
                rs.getString("waktu_ambil"),
                rs.getString("estimasi"),
                rs.getString("pesanan")
        );
    }
}
