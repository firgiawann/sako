package com.klp4.sako.model;

public class ItemPesanan {
    private MenuItem menu;
    private int kuantitas;

    public ItemPesanan(MenuItem menu, int kuantitas) {
        this.menu = menu;
        this.kuantitas = kuantitas;
    }

    public MenuItem getMenu() { return menu; }
    public void setMenu(MenuItem menu) { this.menu = menu; }

    public int getKuantitas() { return kuantitas; }
    public void setKuantitas(int kuantitas) { this.kuantitas = kuantitas; }

    public double getSubtotal() {
        if (menu != null) {
            return menu.getHarga() * kuantitas;
        }
        return 0;
    }
}
