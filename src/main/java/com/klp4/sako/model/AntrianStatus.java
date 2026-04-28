package com.klp4.sako.model;

public enum AntrianStatus {
    MENUNGGU("MENUNGGU", "Menunggu", "Pelanggan sudah mengambil nomor dan menunggu giliran."),
    DIPROSES("DIPROSES", "Diproses", "Pesanan sedang dibuat oleh penjual."),
    SIAP("SIAP", "Siap Diambil", "Pesanan sudah siap diambil pelanggan."),
    SELESAI("SELESAI", "Selesai", "Antrian sudah selesai.");

    private final String code;
    private final String label;
    private final String deskripsi;

    AntrianStatus(String code, String label, String deskripsi) {
        this.code = code;
        this.label = label;
        this.deskripsi = deskripsi;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public boolean isAktif() {
        return this != SELESAI;
    }

    public static AntrianStatus fromCode(String code) {
        for (AntrianStatus status : values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        return MENUNGGU;
    }
}
