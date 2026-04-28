package com.klp4.sako.view;

import com.klp4.sako.model.Admin;
import com.klp4.sako.model.Penjual;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainFrame extends JFrame {
    
    private DashboardPelangganPanel panelPelanggan;
    private DashboardPenjualPanel panelPenjual;
    private DashboardAdminPanel panelAdmin;
    
    public MainFrame(Admin seedAdmin, Penjual seedPenjual) {
        setTitle("SAKO - Sistem Antrian Kantin dengan Notifikasi Otomatis");
        setSize(1080, 720);
        setMinimumSize(new Dimension(900, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents(seedAdmin, seedPenjual);
    }
    
    private void initComponents(Admin seedAdmin, Penjual seedPenjual) {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        panelPelanggan = new DashboardPelangganPanel();
        panelPenjual = new DashboardPenjualPanel(seedPenjual);
        panelAdmin = new DashboardAdminPanel(seedAdmin);
        
        tabbedPane.addTab("Pelanggan", panelPelanggan);
        tabbedPane.addTab("Penjual Kantin", panelPenjual);
        tabbedPane.addTab("Admin Sistem", panelAdmin);
        
        // Tambahkan listener agar saat pindah tab, data ter-refresh otomatis
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();
                if (index == 0) {
                    panelPelanggan.refreshStatus();
                } else if (index == 1) {
                    panelPenjual.loadData();
                } else if (index == 2) {
                    panelAdmin.loadDataUsers();
                    panelAdmin.loadDataSistem();
                }
            }
        });
        
        add(tabbedPane, BorderLayout.CENTER);
    }
}
