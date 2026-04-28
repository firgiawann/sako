package com.klp4.sako.view;

import com.klp4.sako.controller.AntrianController;
import com.klp4.sako.model.Antrian;
import com.klp4.sako.model.AntrianStatus;
import com.klp4.sako.model.User;
import com.klp4.sako.util.DialogUtil;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

public class DashboardPenjualPanel extends JPanel {
    private final User penjual;
    private final AntrianController antrianController;
    private JTable tableAntrian;
    private DefaultTableModel tableModel;
    private JLabel lblMenunggu;
    private JLabel lblDiproses;
    private JLabel lblSiap;

    // UI Colors
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);    // Blue
    private final Color COLOR_SUCCESS = new Color(39, 174, 96);    // Green
    private final Color COLOR_WARNING = new Color(243, 156, 18);   // Orange
    private final Color COLOR_BG = new Color(240, 243, 245);
    private final Color COLOR_CARD_BG = Color.WHITE;
    private final Color COLOR_TEXT_MAIN = new Color(44, 62, 80);

    public DashboardPenjualPanel(User penjual) {
        this.penjual = penjual;
        this.antrianController = new AntrianController();

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        setBackground(COLOR_BG);

        initComponents();
        loadData();

        Timer timer = new Timer(10000, e -> loadData());
        timer.start();
    }

    private void initComponents() {
        add(createHeader(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(false);

        JLabel lblWelcome = new JLabel("🏪 Dashboard Penjual - " + (penjual != null ? penjual.getNama() : "Unknown"));
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblWelcome.setForeground(COLOR_TEXT_MAIN);
        panel.add(lblWelcome, BorderLayout.NORTH);

        JPanel stats = new JPanel(new GridLayout(1, 3, 15, 0));
        stats.setOpaque(false);
        lblMenunggu = createStatCard("Menunggu", COLOR_PRIMARY);
        lblDiproses = createStatCard("Diproses", COLOR_WARNING);
        lblSiap = createStatCard("Siap Diambil", COLOR_SUCCESS);
        stats.add(lblMenunggu);
        stats.add(lblDiproses);
        stats.add(lblSiap);
        panel.add(stats, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createStatCard(String title, Color color) {
        JLabel label = new JLabel("<html><center><font size='4'>" + title + "</font><br><font size='6'><b>0</b></font></center></html>", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(COLOR_CARD_BG);
        label.setForeground(color);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 234, 237), 1),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));
        return label;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 205, 210)), 
                "DAFTAR ANTRIAN AKTIF", 0, 0, new Font("Segoe UI", Font.BOLD, 12), COLOR_TEXT_MAIN));

        String[] columns = {"ID", "No", "Pelanggan", "Detail Pesanan", "Estimasi", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableAntrian = new JTable(tableModel);
        tableAntrian.setRowHeight(40);
        tableAntrian.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tableAntrian.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableAntrian.setSelectionBackground(new Color(235, 245, 255));
        tableAntrian.setSelectionForeground(COLOR_TEXT_MAIN);
        tableAntrian.setShowVerticalLines(false);
        tableAntrian.setIntercellSpacing(new Dimension(0, 1));

        tableAntrian.getColumnModel().getColumn(0).setMinWidth(0);
        tableAntrian.getColumnModel().getColumn(0).setMaxWidth(0);
        tableAntrian.getColumnModel().getColumn(1).setMaxWidth(50);
        tableAntrian.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableAntrian.getColumnModel().getColumn(3).setPreferredWidth(400);
        tableAntrian.getColumnModel().getColumn(5).setPreferredWidth(120);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tableAntrian.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableAntrian.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(tableAntrian);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 234, 237)));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelTombol.setOpaque(false);
        
        JButton btnRefresh = new JButton("🔄 Refresh");
        
        JButton btnProses = new JButton("⚙️ Proses Pesanan");
        btnProses.setBackground(COLOR_WARNING);
        btnProses.setForeground(Color.WHITE);
        
        JButton btnSiap = new JButton("✅ Tandai Siap");
        btnSiap.setBackground(COLOR_SUCCESS);
        btnSiap.setForeground(Color.WHITE);
        
        JButton btnSelesai = new JButton("🏁 Selesaikan");
        btnSelesai.setBackground(COLOR_PRIMARY);
        btnSelesai.setForeground(Color.WHITE);

        JButton[] buttons = {btnRefresh, btnProses, btnSiap, btnSelesai};
        for (JButton b : buttons) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setPreferredSize(new Dimension(160, 45));
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelTombol.add(b);
        }

        btnRefresh.addActionListener(e -> loadData());
        btnProses.addActionListener(e -> updateSelectedStatus(AntrianStatus.DIPROSES));
        btnSiap.addActionListener(e -> updateSelectedStatus(AntrianStatus.SIAP));
        btnSelesai.addActionListener(e -> updateSelectedStatus(AntrianStatus.SELESAI));
        
        return panelTombol;
    }

    public void loadData() {
        tableModel.setRowCount(0);
        List<Antrian> daftar = antrianController.getDaftarAntrian();
        for (Antrian a : daftar) {
            tableModel.addRow(new Object[]{
                    a.getId(),
                    a.getNomor(),
                    a.getNamaPelanggan(),
                    a.getPesanan().replace("\n", " | "),
                    a.getEstimasiDilayani(),
                    a.getStatusEnum().getLabel()
            });
        }
        updateSummary();
    }

    private void updateSummary() {
        Map<String, Integer> ringkasan = antrianController.getRingkasanStatus();
        setStatText(lblMenunggu, "Menunggu", ringkasan.getOrDefault(AntrianStatus.MENUNGGU.getCode(), 0));
        setStatText(lblDiproses, "Diproses", ringkasan.getOrDefault(AntrianStatus.DIPROSES.getCode(), 0));
        setStatText(lblSiap, "Siap Diambil", ringkasan.getOrDefault(AntrianStatus.SIAP.getCode(), 0));
    }

    private void setStatText(JLabel label, String title, int count) {
        label.setText("<html><center><font size='4'>" + title + "</font><br><font size='6'><b>" + count + "</b></font></center></html>");
    }

    private void updateSelectedStatus(AntrianStatus status) {
        String id = getSelectedId();
        if (id == null) return;

        switch (status) {
            case DIPROSES -> antrianController.prosesAntrian(id);
            case SIAP -> {
                antrianController.siapkanAntrian(id);
                DialogUtil.showInfo(this, "Notifikasi 'Pesanan Siap' telah dikirim.");
            }
            case SELESAI -> antrianController.selesaikanAntrian(id);
        }
        loadData();
    }

    private String getSelectedId() {
        int row = tableAntrian.getSelectedRow();
        if (row != -1) {
            int modelRow = tableAntrian.convertRowIndexToModel(row);
            return (String) tableModel.getValueAt(modelRow, 0);
        }
        DialogUtil.showError(this, "Pilih antrian dari tabel terlebih dahulu.");
        return null;
    }
}
