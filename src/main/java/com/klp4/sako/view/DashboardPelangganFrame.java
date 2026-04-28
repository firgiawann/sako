package com.klp4.sako.view;

import javax.swing.*;
import java.awt.*;

public class DashboardPelangganFrame extends JFrame {
    
    public DashboardPelangganFrame(String namaPelanggan) {
        setTitle("Dashboard Pelanggan - SAKO");
        setSize(1080, 720);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Menggunakan panel yang sudah di-upgrade
        DashboardPelangganPanel panel = new DashboardPelangganPanel();
        add(panel);
    }
}
