package com.klp4.sako.model;

public class Notifikasi {
    private String idNotifikasi;
    private String antrianId;
    private String pesan;
    private String jenis; // WARNING_DEKAT, READY
    private boolean dibaca;
    private String waktuKirim;

    public Notifikasi(String idNotifikasi, String antrianId, String pesan, String jenis, boolean dibaca, String waktuKirim) {
        this.idNotifikasi = idNotifikasi;
        this.antrianId = antrianId;
        this.pesan = pesan;
        this.jenis = jenis;
        this.dibaca = dibaca;
        this.waktuKirim = waktuKirim;
    }

    public String getIdNotifikasi() { return idNotifikasi; }
    public void setIdNotifikasi(String idNotifikasi) { this.idNotifikasi = idNotifikasi; }

    public String getAntrianId() { return antrianId; }
    public void setAntrianId(String antrianId) { this.antrianId = antrianId; }

    public String getPesan() { return pesan; }
    public void setPesan(String pesan) { this.pesan = pesan; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public boolean isDibaca() { return dibaca; }
    public void setDibaca(boolean dibaca) { this.dibaca = dibaca; }

    public String getWaktuKirim() { return waktuKirim; }
    public void setWaktuKirim(String waktuKirim) { this.waktuKirim = waktuKirim; }

    public void tandaiDibaca() {
        this.dibaca = true;
    }

    public void kirim() {
        System.out.println("Notifikasi dikirim: [" + jenis + "] " + pesan);
    }
}
