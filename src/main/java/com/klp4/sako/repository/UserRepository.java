package com.klp4.sako.repository;

import com.klp4.sako.model.User;
import com.klp4.sako.model.Admin;
import com.klp4.sako.model.Penjual;
import com.klp4.sako.model.Pelanggan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public void tambahUser(User user) {
        String sql = "INSERT INTO users(id, nama, username, password, role) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getNama());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getRole());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal tambah user: " + e.getMessage());
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                String password = rs.getString("password");
                String role = rs.getString("role");
                
                if (role.equals("ADMIN")) {
                    return new Admin(id, nama, username, password);
                } else if (role.equals("PENJUAL")) {
                    return new Penjual(id, nama, username, password);
                } else {
                    return new Pelanggan(id, nama, username, password);
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil user: " + e.getMessage());
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String role = rs.getString("role");
                if (role.equals("ADMIN")) {
                    list.add(new Admin(rs.getString("id"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
                } else if (role.equals("PENJUAL")) {
                    list.add(new Penjual(rs.getString("id"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
                } else {
                    list.add(new Pelanggan(rs.getString("id"), rs.getString("nama"), rs.getString("username"), rs.getString("password")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil semua user: " + e.getMessage());
        }
        return list;
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET nama = ?, username = ?, password = ?, role = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNama());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal update user: " + e.getMessage());
        }
    }

    public void deleteUser(String id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal hapus user: " + e.getMessage());
        }
    }
}
