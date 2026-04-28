package com.klp4.sako.model;

public class Pelanggan extends User {
    private String nomorAntrian;
    private String statusAntrian;

    public Pelanggan(String id, String nama, String username, String password) {
        super(id, nama, username, password, "PELANGGAN");
    }

    public String getNomorAntrian() { return nomorAntrian; }
    public void setNomorAntrian(String nomorAntrian) { this.nomorAntrian = nomorAntrian; }

    public String getStatusAntrian() { return statusAntrian; }
    public void setStatusAntrian(String statusAntrian) { this.statusAntrian = statusAntrian; }

    @Override
    public void login() {
        System.out.println("Pelanggan " + this.nama + " login.");
    }

    @Override
    public void lihatMenu() {
        System.out.println("Pelanggan melihat menu...");
    }

    public void ambilAntrian() {
        System.out.println("Mengambil antrian...");
    }

    public void lihatStatus() {
        System.out.println("Melihat status antrian...");
    }

    public void terimaNotifikasi(String pesan) {
        System.out.println("Notifikasi untuk " + this.nama + ": " + pesan);
    }
}
