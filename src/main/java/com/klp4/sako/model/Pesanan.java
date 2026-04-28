package com.klp4.sako.model;

import java.util.ArrayList;
import java.util.List;

public class Pesanan {
    private String idPesanan;
    private String antrianId;
    private List<ItemPesanan> daftarItem;
    private String catatan;
    private String status;

    public Pesanan(String idPesanan, String antrianId, String catatan, String status) {
        this.idPesanan = idPesanan;
        this.antrianId = antrianId;
        this.daftarItem = new ArrayList<>();
        this.catatan = catatan;
        this.status = status;
    }

    public String getIdPesanan() { return idPesanan; }
    public void setIdPesanan(String idPesanan) { this.idPesanan = idPesanan; }

    public String getAntrianId() { return antrianId; }
    public void setAntrianId(String antrianId) { this.antrianId = antrianId; }

    public List<ItemPesanan> getDaftarItem() { return daftarItem; }

    public String getCatatan() { return catatan; }
    public void setCatatan(String catatan) { this.catatan = catatan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void tambahItem(ItemPesanan item) {
        this.daftarItem.add(item);
    }

    public double hitungTotal() {
        double total = 0;
        for (ItemPesanan item : daftarItem) {
            total += item.getSubtotal();
        }
        return total;
    }
}
