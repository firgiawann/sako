package com.klp4.sako.view;

import com.klp4.sako.controller.AntrianController;
import com.klp4.sako.model.Admin;
import com.klp4.sako.model.AntrianStatus;
import com.klp4.sako.model.Notifikasi;
import com.klp4.sako.model.Penjual;
import com.klp4.sako.model.User;
import com.klp4.sako.repository.UserRepository;
import com.klp4.sako.repository.SettingsRepository;
import com.klp4.sako.util.DialogUtil;
import com.klp4.sako.util.IdGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DashboardAdminPanel extends JPanel {
    private final User admin;
    private final UserRepository userRepository;
    private final SettingsRepository settingsRepository;
    private final AntrianController antrianController;

    private JTable tableUsers;
    private JTable tableNotifikasi;
    private DefaultTableModel tableModelUsers;
    private DefaultTableModel tableModelNotifikasi;
    private JLabel lblTotalAktif, lblMenunggu, lblDiproses, lblSiap;
    
    // Settings fields
    private JTextField txtBatasNotif, txtFormatNomor, txtJamBuka, txtJamTutup;

    // UI Colors
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);
    private final Color COLOR_BG = new Color(240, 243, 245);
    private final Color COLOR_CARD_BG = Color.WHITE;
    private final Color COLOR_TEXT_MAIN = new Color(44, 62, 80);

    public DashboardAdminPanel(User admin) {
        this.admin = admin;
        this.userRepository = new UserRepository();
        this.settingsRepository = new SettingsRepository();
        this.antrianController = new AntrianController();

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        setBackground(COLOR_BG);

        initComponents();
        loadDataUsers();
        loadDataSistem();

        Timer timer = new Timer(15000, e -> loadDataSistem());
        timer.start();
    }

    private void initComponents() {
        add(createHeader(), BorderLayout.NORTH);
        
        JTabbedPane innerTab = new JTabbedPane();
        innerTab.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        innerTab.addTab("👥 Kelola Pengguna", createUsersPanel());
        innerTab.addTab("🔔 Riwayat Notifikasi", createNotifHistoryPanel());
        innerTab.addTab("⚙️ Pengaturan Sistem", createSettingsPanel());
        
        add(innerTab, BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(false);

        JLabel lblWelcome = new JLabel("🛡️ Dashboard Admin - " + (admin != null ? admin.getNama() : "System"));
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblWelcome.setForeground(COLOR_TEXT_MAIN);
        panel.add(lblWelcome, BorderLayout.NORTH);

        JPanel stats = new JPanel(new GridLayout(1, 4, 15, 0));
        stats.setOpaque(false);
        lblTotalAktif = createStatCard("Total Aktif", new Color(52, 73, 94));
        lblMenunggu = createStatCard("Menunggu", COLOR_PRIMARY);
        lblDiproses = createStatCard("Diproses", new Color(243, 156, 18));
        lblSiap = createStatCard("Siap", new Color(39, 174, 96));
        
        stats.add(lblTotalAktif);
        stats.add(lblMenunggu);
        stats.add(lblDiproses);
        stats.add(lblSiap);
        panel.add(stats, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createStatCard(String title, Color color) {
        JLabel label = new JLabel("<html><center><font size='3'>" + title + "</font><br><font size='5'><b>0</b></font></center></html>", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(COLOR_CARD_BG);
        label.setForeground(color);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 234, 237), 1),
                BorderFactory.createEmptyBorder(12, 10, 12, 10)
        ));
        return label;
    }

    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(false);

        tableModelUsers = new DefaultTableModel(new String[]{"ID", "Nama", "Username", "Role"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableUsers = new JTable(tableModelUsers);
        tableUsers.setRowHeight(35);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        btnPanel.setOpaque(false);
        
        JButton btnAdd = new JButton("➕ Tambah User");
        JButton btnDelete = new JButton("🗑️ Hapus User");
        
        btnPanel.add(btnAdd);
        btnPanel.add(btnDelete);
        
        btnAdd.addActionListener(e -> showAddUserDialog());
        btnDelete.addActionListener(e -> deleteSelectedUser());

        panel.add(new JScrollPane(tableUsers), BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createNotifHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(false);

        tableModelNotifikasi = new DefaultTableModel(new String[]{"Waktu", "Jenis", "ID Antrian", "Pesan"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableNotifikasi = new JTable(tableModelNotifikasi);
        tableNotifikasi.setRowHeight(30);

        JButton btnReset = new JButton("⚠️ Reset Semua Antrian & Notif");
        btnReset.setForeground(new Color(192, 57, 43));
        btnReset.addActionListener(e -> resetSystemData());

        panel.add(new JScrollPane(tableNotifikasi), BorderLayout.CENTER);
        panel.add(btnReset, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_CARD_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        txtBatasNotif = new JTextField(20);
        txtFormatNomor = new JTextField(20);
        txtJamBuka = new JTextField(20);
        txtJamTutup = new JTextField(20);
        
        Map<String, String> s = settingsRepository.getSettings();
        txtBatasNotif.setText(s.get("batas_notifikasi"));
        txtFormatNomor.setText(s.get("format_nomor"));
        txtJamBuka.setText(s.get("jam_buka"));
        txtJamTutup.setText(s.get("jam_tutup"));

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Batas Notifikasi 'Dekat' (jumlah antrian)"), gbc);
        gbc.gridx = 1; panel.add(txtBatasNotif, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Format Nomor Antrian"), gbc);
        gbc.gridx = 1; panel.add(txtFormatNomor, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Jam Operasional Buka (HH:mm)"), gbc);
        gbc.gridx = 1; panel.add(txtJamBuka, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Jam Operasional Tutup (HH:mm)"), gbc);
        gbc.gridx = 1; panel.add(txtJamTutup, gbc);
        
        JButton btnSave = new JButton("💾 Simpan Pengaturan");
        btnSave.setBackground(COLOR_PRIMARY);
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(new Dimension(200, 40));
        btnSave.addActionListener(e -> saveSettings());
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnSave, gbc);
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(panel, BorderLayout.NORTH);
        return wrapper;
    }

    private void showAddUserDialog() {
        JTextField nameField = new JTextField();
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"PENJUAL", "ADMIN"});
        
        Object[] message = {
            "Nama Lengkap:", nameField,
            "Username:", userField,
            "Password:", passField,
            "Role:", roleCombo
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Tambah User Baru", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nama = nameField.getText().trim();
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword());
            String role = (String) roleCombo.getSelectedItem();
            
            if (nama.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                DialogUtil.showError(this, "Semua field harus diisi!");
                return;
            }
            
            User newUser = role.equals("ADMIN") ? new Admin(IdGenerator.generateId(), nama, user, pass) 
                                               : new Penjual(IdGenerator.generateId(), nama, user, pass);
            userRepository.tambahUser(newUser);
            loadDataUsers();
            DialogUtil.showInfo(this, "User berhasil ditambahkan.");
        }
    }

    private void deleteSelectedUser() {
        int row = tableUsers.getSelectedRow();
        if (row == -1) {
            DialogUtil.showError(this, "Pilih user yang ingin dihapus.");
            return;
        }
        
        String id = (String) tableModelUsers.getValueAt(row, 0);
        String username = (String) tableModelUsers.getValueAt(row, 2);
        
        if (username.equals(admin.getUsername())) {
            DialogUtil.showError(this, "Anda tidak bisa menghapus akun Anda sendiri.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Hapus user " + username + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            userRepository.deleteUser(id);
            loadDataUsers();
        }
    }

    private void saveSettings() {
        try {
            int batas = Integer.parseInt(txtBatasNotif.getText());
            settingsRepository.saveSettings(batas, txtFormatNomor.getText(), txtJamBuka.getText(), txtJamTutup.getText());
            DialogUtil.showInfo(this, "Pengaturan sistem berhasil disimpan.");
        } catch (NumberFormatException e) {
            DialogUtil.showError(this, "Batas notifikasi harus berupa angka.");
        }
    }

    private void resetSystemData() {
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus semua data transaksi? Tindakan ini tidak bisa dibatalkan.", "Peringatan", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            antrianController.hapusSemuaAntrian();
            loadDataSistem();
            DialogUtil.showInfo(this, "Sistem berhasil direset.");
        }
    }

    public void loadDataUsers() {
        tableModelUsers.setRowCount(0);
        List<User> users = userRepository.getAllUsers();
        for (User u : users) {
            tableModelUsers.addRow(new Object[]{u.getId(), u.getNama(), u.getUsername(), u.getRole()});
        }
    }

    public void loadDataSistem() {
        Map<String, Integer> ringkasan = antrianController.getRingkasanStatus();
        int menunggu = ringkasan.getOrDefault(AntrianStatus.MENUNGGU.getCode(), 0);
        int diproses = ringkasan.getOrDefault(AntrianStatus.DIPROSES.getCode(), 0);
        int siap = ringkasan.getOrDefault(AntrianStatus.SIAP.getCode(), 0);
        
        lblTotalAktif.setText("<html><center><font size='3'>Total Aktif</font><br><font size='5'><b>" + (menunggu + diproses + siap) + "</b></font></center></html>");
        lblMenunggu.setText("<html><center><font size='3'>Menunggu</font><br><font size='5'><b>" + menunggu + "</b></font></center></html>");
        lblDiproses.setText("<html><center><font size='3'>Diproses</font><br><font size='5'><b>" + diproses + "</b></font></center></html>");
        lblSiap.setText("<html><center><font size='3'>Siap</font><br><font size='5'><b>" + siap + "</b></font></center></html>");

        tableModelNotifikasi.setRowCount(0);
        List<Notifikasi> notifikasi = antrianController.getNotifikasiTerbaru(20);
        for (Notifikasi item : notifikasi) {
            tableModelNotifikasi.addRow(new Object[]{item.getWaktuKirim(), item.getJenis(), item.getAntrianId(), item.getPesan()});
        }
    }
}
