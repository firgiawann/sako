package com.klp4.sako.util;

import javax.swing.JOptionPane;
import java.awt.Component;

public class DialogUtil {
    public static void showInfo(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
