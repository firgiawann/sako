package com.klp4.sako.repository;

import com.klp4.sako.model.Pesanan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PesananRepository {
    public void tambahPesanan(Pesanan pesanan) {
        String sql = "INSERT INTO pesanan(id, antrian_id, catatan, status) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pesanan.getIdPesanan());
            pstmt.setString(2, pesanan.getAntrianId());
            pstmt.setString(3, pesanan.getCatatan());
            pstmt.setString(4, pesanan.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal tambah pesanan: " + e.getMessage());
        }
    }
    
    public void updateStatusPesanan(String id, String status) {
        String sql = "UPDATE pesanan SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal update pesanan: " + e.getMessage());
        }
    }
}
