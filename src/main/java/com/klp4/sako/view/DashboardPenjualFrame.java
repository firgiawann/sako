package com.klp4.sako.view;

import com.klp4.sako.model.User;
import javax.swing.*;
import java.awt.*;

public class DashboardPenjualFrame extends JFrame {
    
    public DashboardPenjualFrame(User penjual) {
        setTitle("Dashboard Penjual - SAKO");
        setSize(1080, 720);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        DashboardPenjualPanel panel = new DashboardPenjualPanel(penjual);
        add(panel);
    }
}
