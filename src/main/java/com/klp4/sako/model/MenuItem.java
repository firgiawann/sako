package com.klp4.sako.model;

import java.text.NumberFormat;
import java.util.Locale;

public class MenuItem {
    private String idMenu;
    private String nama;
    private double harga;
    private boolean tersedia;

    public MenuItem(String idMenu, String nama, double harga, boolean tersedia) {
        this.idMenu = idMenu;
        this.nama = nama;
        this.harga = harga;
        this.tersedia = tersedia;
    }

    public String getIdMenu() { return idMenu; }
    public String getId() { return idMenu; }
    public void setIdMenu(String idMenu) { this.idMenu = idMenu; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public boolean isTersedia() { return tersedia; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }

    public String getHargaFormatted() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        format.setMaximumFractionDigits(0);
        return format.format(harga);
    }

    @Override
    public String toString() {
        return nama + " - " + getHargaFormatted();
    }
}
