package com.klp4.sako.service;

import com.klp4.sako.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private final List<MenuItem> daftarMenu;

    public MenuService() {
        this.daftarMenu = new ArrayList<>();
        seedMenu();
    }

    public List<MenuItem> getDaftarMenuTersedia() {
        List<MenuItem> tersedia = new ArrayList<>();
        for (MenuItem item : daftarMenu) {
            if (item.isTersedia()) {
                tersedia.add(item);
            }
        }
        return tersedia;
    }

    public double hitungTotal(MenuItem menu, int jumlah) {
        if (menu == null || jumlah <= 0) {
            return 0;
        }
        return menu.getHarga() * jumlah;
    }

    private void seedMenu() {
        daftarMenu.add(new MenuItem("M001", "Nasi Goreng", 12000, true));
        daftarMenu.add(new MenuItem("M002", "Mie Ayam", 10000, true));
        daftarMenu.add(new MenuItem("M003", "Bakso", 12000, true));
        daftarMenu.add(new MenuItem("M004", "Es Teh", 4000, true));
        daftarMenu.add(new MenuItem("M005", "Air Mineral", 3000, true));
        // Item spesial untuk input manual
        daftarMenu.add(new MenuItem("CUSTOM", "Lainnya... (Input Sendiri)", 0, true));
    }
}
