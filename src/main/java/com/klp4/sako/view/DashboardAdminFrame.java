package com.klp4.sako.view;

import com.klp4.sako.model.User;
import javax.swing.*;
import java.awt.*;

public class DashboardAdminFrame extends JFrame {
    
    public DashboardAdminFrame(User admin) {
        setTitle("Dashboard Admin - SAKO");
        setSize(1080, 720);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        DashboardAdminPanel panel = new DashboardAdminPanel(admin);
        add(panel);
    }
}
