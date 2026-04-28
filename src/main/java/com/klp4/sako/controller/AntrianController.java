package com.klp4.sako.controller;

import com.klp4.sako.model.Antrian;
import com.klp4.sako.model.Notifikasi;
import com.klp4.sako.service.AntrianService;

import java.util.List;
import java.util.Map;

public class AntrianController {
    private AntrianService antrianService;

    public AntrianController() {
        this.antrianService = new AntrianService();
    }

    public Antrian ambilAntrian(String namaPelanggan, String pesanan) {
        if (namaPelanggan == null || namaPelanggan.trim().isEmpty()) {
            return null; // Validasi sederhana
        }
        return antrianService.ambilAntrianBaru(namaPelanggan, pesanan);
    }

    public List<Antrian> getDaftarAntrian() {
        return antrianService.getAntrianAktif();
    }

    public Antrian getAntrianById(String id) {
        return antrianService.getAntrianById(id);
    }

    public int getJumlahAntrianSebelum(Antrian antrian) {
        return antrianService.getJumlahAntrianSebelum(antrian);
    }

    public Map<String, Integer> getRingkasanStatus() {
        return antrianService.getRingkasanStatus();
    }

    public List<Notifikasi> getNotifikasiByAntrian(String antrianId) {
        return antrianService.getNotifikasiByAntrian(antrianId);
    }

    public List<Notifikasi> getNotifikasiTerbaru(int limit) {
        return antrianService.getNotifikasiTerbaru(limit);
    }

    public void prosesAntrian(String id) {
        antrianService.prosesAntrian(id);
    }

    public void siapkanAntrian(String id) {
        antrianService.siapkanAntrian(id);
    }

    public void selesaikanAntrian(String id) {
        antrianService.selesaikanAntrian(id);
    }

    public void hapusSemuaAntrian() {
        antrianService.hapusSemuaAntrian();
    }
}
