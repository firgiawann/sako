package com.klp4.sako.model;

public class Penjual extends User {
    public Penjual(String id, String nama, String username, String password) {
        super(id, nama, username, password, "PENJUAL");
    }

    @Override
    public void login() {
        System.out.println("Penjual " + this.nama + " login.");
    }

    @Override
    public void lihatMenu() {
        System.out.println("Penjual melihat menu (edit mode)...");
    }

    public void prosesPesanan(String idAntrian) {
        System.out.println("Memproses pesanan untuk antrian: " + idAntrian);
    }

    public void updateAntrian(String idAntrian, String status) {
        System.out.println("Update antrian " + idAntrian + " menjadi " + status);
    }
}
