package com.klp4.sako.service;

import com.klp4.sako.model.Antrian;
import com.klp4.sako.model.AntrianStatus;
import com.klp4.sako.model.Notifikasi;
import com.klp4.sako.repository.AntrianRepository;
import com.klp4.sako.util.IdGenerator;
import com.klp4.sako.util.DateUtil;

import java.util.List;
import java.util.Map;

public class AntrianService {
    private AntrianRepository antrianRepository;
    private NotifikasiService notifikasiService;

    public AntrianService() {
        this.antrianRepository = new AntrianRepository();
        this.notifikasiService = new NotifikasiService();
    }

    public Antrian ambilAntrianBaru(String namaPelanggan, String pesananPelanggan) {
        int nomorBaru = antrianRepository.getNomorAntrianTerakhir() + 1;
        String id = IdGenerator.generateId();
        String waktu = DateUtil.getCurrentDateTime();
        int antrianSebelum = antrianRepository.getAllAntrianAktif().size();
        String estimasi = (antrianSebelum * 5) + " menit";
        
        Antrian antrian = new Antrian(id, nomorBaru, namaPelanggan, AntrianStatus.MENUNGGU.getCode(), waktu, estimasi, pesananPelanggan);
        antrianRepository.tambahAntrian(antrian);
        if (antrianSebelum <= 2) {
            notifikasiService.kirimNotifikasiDekat(id);
        }
        return antrian;
    }

    public List<Antrian> getAntrianAktif() {
        cekNotifikasiOtomatis();
        return antrianRepository.getAllAntrianAktif();
    }

    public Antrian getAntrianById(String id) {
        return antrianRepository.getAntrianById(id);
    }

    public int getJumlahAntrianSebelum(Antrian antrian) {
        if (antrian == null) {
            return 0;
        }
        return antrianRepository.getJumlahAntrianSebelum(antrian.getNomor());
    }

    public Map<String, Integer> getRingkasanStatus() {
        return antrianRepository.getRingkasanStatus();
    }

    public List<Notifikasi> getNotifikasiByAntrian(String antrianId) {
        return notifikasiService.getNotifikasiByAntrian(antrianId);
    }

    public List<Notifikasi> getNotifikasiTerbaru(int limit) {
        return notifikasiService.getNotifikasiTerbaru(limit);
    }

    public void prosesAntrian(String id) {
        antrianRepository.updateStatusAntrian(id, AntrianStatus.DIPROSES.getCode());
        cekNotifikasiOtomatis();
    }

    public void siapkanAntrian(String id) {
        antrianRepository.updateStatusAntrian(id, AntrianStatus.SIAP.getCode());
        notifikasiService.kirimNotifikasiSiap(id);
    }

    public void selesaikanAntrian(String id) {
        antrianRepository.updateStatusAntrian(id, AntrianStatus.SELESAI.getCode());
    }
    
    public void hapusSemuaAntrian() {
        antrianRepository.deleteAll();
        notifikasiService.hapusSemuaNotifikasi();
    }

    private void cekNotifikasiOtomatis() {
        List<Antrian> daftar = antrianRepository.getAllAntrianAktif();
        for (Antrian antrian : daftar) {
            if (antrian.getStatusEnum() == AntrianStatus.MENUNGGU && antrianRepository.getJumlahAntrianSebelum(antrian.getNomor()) <= 2) {
                notifikasiService.kirimNotifikasiDekat(antrian.getId());
            }
            if (antrian.getStatusEnum() == AntrianStatus.SIAP) {
                notifikasiService.kirimNotifikasiSiap(antrian.getId());
            }
        }
    }
}
