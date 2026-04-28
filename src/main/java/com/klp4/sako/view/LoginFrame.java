package com.klp4.sako.view;

import com.klp4.sako.controller.AuthController;
import com.klp4.sako.model.User;
import com.klp4.sako.util.DialogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private AuthController authController;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtNamaPelanggan;

    // UI Colors
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);
    private final Color COLOR_BG = new Color(245, 247, 250);
    private final Color COLOR_TEXT_MAIN = new Color(44, 62, 80);

    public LoginFrame() {
        authController = new AuthController();
        
        setTitle("SAKO - Selamat Datang");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        
        JLabel lblTitle = new JLabel("SAKO", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblTitle.setForeground(COLOR_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSub = new JLabel("Sistem Antrian Kantin", SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSub.setForeground(COLOR_TEXT_MAIN);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        header.add(lblTitle);
        header.add(lblSub);
        header.add(Box.createVerticalStrut(30));
        
        mainPanel.add(header, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        // Panel Pelanggan
        JPanel panelPelanggan = new JPanel(new GridBagLayout());
        panelPelanggan.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        JLabel lblName = new JLabel("Siapa nama Anda?");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelPelanggan.add(lblName, gbc);

        gbc.gridy = 1;
        txtNamaPelanggan = new JTextField();
        txtNamaPelanggan.setPreferredSize(new Dimension(0, 40));
        txtNamaPelanggan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelPelanggan.add(txtNamaPelanggan, gbc);

        gbc.gridy = 2;
        JButton btnMasukPelanggan = new JButton("MULAI ANTRI SEKARANG");
        btnMasukPelanggan.setPreferredSize(new Dimension(0, 50));
        btnMasukPelanggan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnMasukPelanggan.setBackground(COLOR_PRIMARY);
        btnMasukPelanggan.setForeground(Color.WHITE);
        btnMasukPelanggan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPelanggan.add(btnMasukPelanggan, gbc);
        
        btnMasukPelanggan.addActionListener(e -> {
            String nama = txtNamaPelanggan.getText().trim();
            if (nama.isEmpty()) {
                DialogUtil.showError(LoginFrame.this, "Silakan masukkan nama Anda.");
                txtNamaPelanggan.requestFocus();
                return;
            }
            new DashboardPelangganFrame(nama).setVisible(true);
            dispose();
        });
        
        // Panel Staf
        JPanel panelStaf = new JPanel(new GridBagLayout());
        panelStaf.setBackground(Color.WHITE);
        
        gbc.gridy = 0;
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelStaf.add(lblUser, gbc);

        gbc.gridy = 1;
        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(0, 35));
        panelStaf.add(txtUsername, gbc);

        gbc.gridy = 2;
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelStaf.add(lblPass, gbc);

        gbc.gridy = 3;
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(0, 35));
        panelStaf.add(txtPassword, gbc);

        gbc.gridy = 4;
        JButton btnLoginStaf = new JButton("LOGIN PETUGAS");
        btnLoginStaf.setPreferredSize(new Dimension(0, 45));
        btnLoginStaf.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLoginStaf.setBackground(new Color(52, 73, 94));
        btnLoginStaf.setForeground(Color.WHITE);
        btnLoginStaf.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelStaf.add(btnLoginStaf, gbc);
        
        btnLoginStaf.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                DialogUtil.showError(LoginFrame.this, "Username dan password wajib diisi.");
                return;
            }
            
            User user = authController.login(username, password);
            if (user != null) {
                if (user.getRole().equals("ADMIN")) {
                    new DashboardAdminFrame(user).setVisible(true);
                } else if (user.getRole().equals("PENJUAL")) {
                    new DashboardPenjualFrame(user).setVisible(true);
                }
                dispose();
            } else {
                DialogUtil.showError(LoginFrame.this, "Username atau password salah.");
            }
        });
        
        tabbedPane.addTab("🙋 PELANGGAN", panelPelanggan);
        tabbedPane.addTab("👔 STAF KANTIN", panelStaf);
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }
}
