package com.klp4.sako.model;

public class Antrian {
    private String id;
    private int nomor;
    private String namaPelanggan;
    private String status; // MENUNGGU, DIPROSES, SIAP, SELESAI
    private String waktuAmbil;
    private String estimasiDilayani;
    private String pesanan; // Apa yang dipesan

    public Antrian(String id, int nomor, String namaPelanggan, String status, String waktuAmbil, String estimasiDilayani, String pesanan) {
        this.id = id;
        this.nomor = nomor;
        this.namaPelanggan = namaPelanggan;
        this.status = status;
        this.waktuAmbil = waktuAmbil;
        this.estimasiDilayani = estimasiDilayani;
        this.pesanan = pesanan;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getNomor() { return nomor; }
    public void setNomor(int nomor) { this.nomor = nomor; }

    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public AntrianStatus getStatusEnum() {
        return AntrianStatus.fromCode(status);
    }

    public String getWaktuAmbil() { return waktuAmbil; }
    public void setWaktuAmbil(String waktuAmbil) { this.waktuAmbil = waktuAmbil; }

    public String getEstimasiDilayani() { return estimasiDilayani; }
    public void setEstimasiDilayani(String estimasiDilayani) { this.estimasiDilayani = estimasiDilayani; }

    public String getPesanan() { return pesanan; }
    public void setPesanan(String pesanan) { this.pesanan = pesanan; }

    public void ubahStatus(String statusBaru) {
        this.status = statusBaru;
    }

    public boolean isAktif() {
        return getStatusEnum().isAktif();
    }
}
