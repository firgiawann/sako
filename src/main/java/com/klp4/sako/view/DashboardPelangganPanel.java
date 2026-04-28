package com.klp4.sako.view;

import com.klp4.sako.controller.AntrianController;
import com.klp4.sako.model.Antrian;
import com.klp4.sako.model.AntrianStatus;
import com.klp4.sako.model.MenuItem;
import com.klp4.sako.model.Notifikasi;
import com.klp4.sako.service.MenuService;
import com.klp4.sako.util.DialogUtil;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

public class DashboardPelangganPanel extends JPanel {
    private final AntrianController antrianController;
    private final MenuService menuService;
    private Antrian antrianSaya;
    private boolean sudahTampilkanSiap;

    // UI Colors
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);    // Blue
    private final Color COLOR_SUCCESS = new Color(39, 174, 96);    // Green
    private final Color COLOR_WARNING = new Color(243, 156, 18);   // Orange
    private final Color COLOR_DANGER = new Color(192, 57, 43);     // Red
    private final Color COLOR_BG = new Color(240, 243, 245);
    private final Color COLOR_CARD_BG = Color.WHITE;
    private final Color COLOR_TEXT_MAIN = new Color(44, 62, 80);
    private final Color COLOR_TEXT_DIM = new Color(127, 140, 141);

    private JTextField txtNama;
    private JComboBox<MenuItem> comboMenu;
    private JTextField txtMenuCustom;
    private JLabel lblMenuCustom;
    private JSpinner spinnerJumlah;
    private JTextField txtCatatan;
    private JLabel lblNomor;
    private JLabel lblStatus;
    private JLabel lblPosisi;
    private JLabel lblEstimasi;
    private JTextArea txtNotifikasi;
    private JButton btnAmbilAntrian;
    private JButton btnRefresh;

    public DashboardPelangganPanel() {
        this.antrianController = new AntrianController();
        this.menuService = new MenuService();
        
        setLayout(new BorderLayout(24, 24));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(COLOR_BG);
        
        initComponents();

        Timer timer = new Timer(8000, e -> refreshStatus());
        timer.start();
    }

    private void initComponents() {
        add(createHeader(), BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.setOpaque(false);
        centerPanel.add(createStatusPanel(), BorderLayout.CENTER);
        centerPanel.add(createActionPanel(), BorderLayout.EAST);
        
        add(centerPanel, BorderLayout.CENTER);

        btnAmbilAntrian.addActionListener(e -> ambilAntrian());
        btnRefresh.addActionListener(e -> refreshStatus());
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        panel.setOpaque(false);

        JLabel title = new JLabel("SAKO - Antrian Kantin");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(COLOR_TEXT_MAIN);

        JLabel subtitle = new JLabel("<html><body style='width: 400px'>Ambil nomor antrian Anda secara digital. Kami akan memberitahu Anda saat pesanan sedang diproses atau sudah siap diambil.</body></html>");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(COLOR_TEXT_DIM);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(subtitle);
        
        panel.add(textPanel, BorderLayout.WEST);

        return panel;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setOpaque(false);

        // Queue Number Card
        JPanel nomorCard = createStyledCard();
        nomorCard.setLayout(new BorderLayout(15, 15));
        nomorCard.setPreferredSize(new Dimension(300, 300));
        
        JLabel labelNomor = new JLabel("NOMOR ANTRIAN ANDA", SwingConstants.CENTER);
        labelNomor.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelNomor.setForeground(COLOR_TEXT_DIM);

        lblNomor = new JLabel("-", SwingConstants.CENTER);
        lblNomor.setFont(new Font("Segoe UI", Font.BOLD, 120));
        lblNomor.setForeground(COLOR_PRIMARY);

        lblStatus = new JLabel("BELUM ADA ANTRIAN", SwingConstants.CENTER);
        lblStatus.setOpaque(true);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setBackground(COLOR_TEXT_DIM);
        lblStatus.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        nomorCard.add(labelNomor, BorderLayout.NORTH);
        nomorCard.add(lblNomor, BorderLayout.CENTER);
        nomorCard.add(lblStatus, BorderLayout.SOUTH);

        // Info and Notifications
        JPanel detailPanel = new JPanel(new BorderLayout(20, 20));
        detailPanel.setOpaque(false);

        JPanel metricsCard = createStyledCard();
        metricsCard.setLayout(new GridLayout(1, 2, 20, 0));
        
        lblPosisi = createMetricLabel("Posisi: -", "👥");
        lblEstimasi = createMetricLabel("Estimasi: -", "⏳");
        
        metricsCard.add(lblPosisi);
        metricsCard.add(lblEstimasi);

        JPanel notifCard = createStyledCard();
        notifCard.setLayout(new BorderLayout(10, 10));
        
        JLabel notifTitle = new JLabel("🔔 Notifikasi Terbaru");
        notifTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        notifTitle.setForeground(COLOR_TEXT_MAIN);

        txtNotifikasi = new JTextArea();
        txtNotifikasi.setEditable(false);
        txtNotifikasi.setLineWrap(true);
        txtNotifikasi.setWrapStyleWord(true);
        txtNotifikasi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNotifikasi.setText("Notifikasi akan muncul di sini.");
        txtNotifikasi.setBackground(COLOR_CARD_BG);

        JScrollPane notifScroll = new JScrollPane(txtNotifikasi);
        notifScroll.setBorder(null);

        notifCard.add(notifTitle, BorderLayout.NORTH);
        notifCard.add(notifScroll, BorderLayout.CENTER);

        detailPanel.add(metricsCard, BorderLayout.NORTH);
        detailPanel.add(notifCard, BorderLayout.CENTER);

        panel.add(nomorCard, BorderLayout.WEST);
        panel.add(detailPanel, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createActionPanel() {
        JPanel panel = createStyledCard();
        panel.setPreferredSize(new Dimension(340, 0));
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JLabel formTitle = new JLabel("Form Pesanan Baru");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        formTitle.setForeground(COLOR_TEXT_MAIN);

        txtNama = new JTextField();
        txtNama.setPreferredSize(new Dimension(0, 35));
        
        comboMenu = new JComboBox<>();
        comboMenu.setPreferredSize(new Dimension(0, 35));
        for (MenuItem item : menuService.getDaftarMenuTersedia()) {
            comboMenu.addItem(item);
        }
        
        spinnerJumlah = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        spinnerJumlah.setPreferredSize(new Dimension(0, 35));
        
        txtCatatan = new JTextField();
        txtCatatan.setPreferredSize(new Dimension(0, 35));
        
        // Custom menu input (hidden by default)
        txtMenuCustom = new JTextField();
        txtMenuCustom.setPreferredSize(new Dimension(0, 35));
        txtMenuCustom.setVisible(false);
        lblMenuCustom = new JLabel("Nama Menu Lainnya");
        lblMenuCustom.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMenuCustom.setVisible(false);

        comboMenu.addActionListener(e -> {
            MenuItem selected = (MenuItem) comboMenu.getSelectedItem();
            boolean isCustom = selected != null && selected.getId().equals("CUSTOM");
            lblMenuCustom.setVisible(isCustom);
            txtMenuCustom.setVisible(isCustom);
            panel.revalidate();
            panel.repaint();
        });

        btnAmbilAntrian = new JButton("Ambil Antrian Sekarang");
        btnAmbilAntrian.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAmbilAntrian.setBackground(COLOR_PRIMARY);
        btnAmbilAntrian.setForeground(Color.WHITE);
        btnAmbilAntrian.setPreferredSize(new Dimension(0, 45));
        btnAmbilAntrian.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnRefresh = new JButton("Refresh Status");
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRefresh.setPreferredSize(new Dimension(0, 40));
        btnRefresh.setEnabled(false);

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        panel.add(formTitle, gbc);
        
        gbc.gridy = row++; panel.add(Box.createVerticalStrut(10), gbc);
        
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(new JLabel("Nama Pelanggan"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; panel.add(txtNama, gbc);
        
        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0; panel.add(new JLabel("Pilih Menu"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; panel.add(comboMenu, gbc);
        
        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0; panel.add(lblMenuCustom, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; panel.add(txtMenuCustom, gbc);
        
        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0; panel.add(new JLabel("Jumlah"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; panel.add(spinnerJumlah, gbc);
        
        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0; panel.add(new JLabel("Catatan"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; panel.add(txtCatatan, gbc);
        
        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);
        
        row++;
        gbc.gridy = row; gbc.weighty = 0;
        panel.add(btnAmbilAntrian, gbc);
        
        row++;
        gbc.gridy = row;
        panel.add(btnRefresh, gbc);

        return panel;
    }

    private JPanel createStyledCard() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 234, 237), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }

    private JLabel createMetricLabel(String text, String emoji) {
        JLabel label = new JLabel(emoji + " " + text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(COLOR_TEXT_MAIN);
        return label;
    }

    private void ambilAntrian() {
        String nama = txtNama.getText().trim();
        MenuItem menu = (MenuItem) comboMenu.getSelectedItem();
        int jumlah = (Integer) spinnerJumlah.getValue();
        String catatan = txtCatatan.getText().trim();
        
        if (nama.isEmpty()) {
            DialogUtil.showError(this, "Silakan masukkan nama Anda.");
            txtNama.requestFocus();
            return;
        }
        if (menu == null) {
            DialogUtil.showError(this, "Silakan pilih menu pesanan.");
            return;
        }

        String namaMenu = menu.getNama();
        if (menu.getId().equals("CUSTOM")) {
            String custom = txtMenuCustom.getText().trim();
            if (custom.isEmpty()) {
                DialogUtil.showError(this, "Silakan isi nama menu yang Anda inginkan.");
                txtMenuCustom.requestFocus();
                return;
            }
            namaMenu = "[CUSTOM] " + custom;
        }

        String pesanan = formatPesanan(namaMenu, menu.getHarga(), jumlah, catatan);
        antrianSaya = antrianController.ambilAntrian(nama, pesanan);
        
        if (antrianSaya != null) {
            sudahTampilkanSiap = false;
            setFormEnabled(false);
            txtMenuCustom.setEnabled(false);
            btnAmbilAntrian.setEnabled(false);
            btnAmbilAntrian.setBackground(COLOR_TEXT_DIM);
            btnRefresh.setEnabled(true);
            updateStatusView();
            
            // Alert konfirmasi sukses
            String msg = "🎉 BERHASIL!\n\n" +
                         "Nomor Antrian: " + antrianSaya.getNomor() + "\n" +
                         "Nama: " + antrianSaya.getNamaPelanggan() + "\n" +
                         "Estimasi: " + antrianSaya.getEstimasiDilayani() + "\n\n" +
                         "Silakan pantau layar dashboard untuk update status pesanan Anda.";
            JOptionPane.showMessageDialog(this, msg, "Antrian Berhasil Diambil", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setFormEnabled(boolean enabled) {
        txtNama.setEnabled(enabled);
        comboMenu.setEnabled(enabled);
        spinnerJumlah.setEnabled(enabled);
        txtCatatan.setEnabled(enabled);
    }

    public void refreshStatus() {
        if (antrianSaya == null) return;

        Antrian terbaru = antrianController.getAntrianById(antrianSaya.getId());
        if (terbaru == null || terbaru.getStatusEnum() == AntrianStatus.SELESAI) {
            lblStatus.setText("ANTRIAN SELESAI");
            lblStatus.setBackground(COLOR_TEXT_DIM);
            DialogUtil.showInfo(this, "Pesanan telah selesai. Sampai jumpa kembali!");
            resetPanel();
            return;
        }

        antrianSaya = terbaru;
        updateStatusView();
        
        if (antrianSaya.getStatusEnum() == AntrianStatus.SIAP && !sudahTampilkanSiap) {
            sudahTampilkanSiap = true;
            DialogUtil.showInfo(this, "PESANAN SIAP!\nSilakan ambil pesanan Anda di counter.");
        }
    }

    private void updateStatusView() {
        lblNomor.setText(String.format("%02d", antrianSaya.getNomor()));
        lblStatus.setText(antrianSaya.getStatusEnum().getLabel().toUpperCase());
        lblStatus.setBackground(getStatusColor(antrianSaya.getStatusEnum()));

        int sebelum = antrianController.getJumlahAntrianSebelum(antrianSaya);
        lblPosisi.setText("Posisi: " + (sebelum + 1));
        lblEstimasi.setText("Estimasi: " + antrianSaya.getEstimasiDilayani());
        updateNotifikasi();
    }

    private void updateNotifikasi() {
        List<Notifikasi> notifikasi = antrianController.getNotifikasiByAntrian(antrianSaya.getId());
        if (notifikasi.isEmpty()) {
            txtNotifikasi.setText("Menunggu informasi dari penjual...");
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = notifikasi.size() - 1; i >= 0; i--) {
            Notifikasi item = notifikasi.get(i);
            builder.append("[").append(item.getWaktuKirim().substring(11, 16)).append("] ")
                   .append(item.getPesan())
                   .append("\n\n");
        }
        txtNotifikasi.setText(builder.toString());
        txtNotifikasi.setCaretPosition(0);
    }

    private String formatPesanan(String namaMenu, double harga, int jumlah, String catatan) {
        double total = harga * jumlah;
        String p = String.format("%dx %s", jumlah, namaMenu);
        if (harga > 0) p += String.format(" (Rp%,.0f)", total);
        if (!catatan.isEmpty()) p += "\nCat: " + catatan;
        return p;
    }

    private Color getStatusColor(AntrianStatus status) {
        return switch (status) {
            case MENUNGGU -> COLOR_PRIMARY;
            case DIPROSES -> COLOR_WARNING;
            case SIAP -> COLOR_SUCCESS;
            case SELESAI -> COLOR_TEXT_DIM;
        };
    }

    private void resetPanel() {
        antrianSaya = null;
        setFormEnabled(true);
        txtMenuCustom.setText("");
        txtMenuCustom.setEnabled(true);
        txtMenuCustom.setVisible(false);
        lblMenuCustom.setVisible(false);
        txtNama.setText("");
        spinnerJumlah.setValue(1);
        txtCatatan.setText("");
        btnAmbilAntrian.setEnabled(true);
        btnAmbilAntrian.setBackground(COLOR_PRIMARY);
        btnRefresh.setEnabled(false);
        lblNomor.setText("-");
        lblStatus.setText("BELUM ADA ANTRIAN");
        lblStatus.setBackground(COLOR_TEXT_DIM);
        lblPosisi.setText("Posisi: -");
        lblEstimasi.setText("Estimasi: -");
        txtNotifikasi.setText("Silakan ambil nomor antrian untuk melihat notifikasi.");
    }
}
