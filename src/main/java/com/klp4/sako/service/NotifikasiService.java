package com.klp4.sako.service;

import com.klp4.sako.model.Notifikasi;
import com.klp4.sako.repository.NotifikasiRepository;
import com.klp4.sako.util.IdGenerator;
import com.klp4.sako.util.DateUtil;

import java.util.List;

public class NotifikasiService {
    private NotifikasiRepository notifikasiRepository;

    public NotifikasiService() {
        this.notifikasiRepository = new NotifikasiRepository();
    }
    
    public void kirimNotifikasiDekat(String antrianId) {
        if (notifikasiRepository.existsByAntrianAndJenis(antrianId, "WARNING_DEKAT")) {
            return;
        }
        String id = IdGenerator.generateId();
        String waktu = DateUtil.getCurrentDateTime();
        Notifikasi notif = new Notifikasi(id, antrianId, "Giliran Anda sudah dekat. Silakan bersiap di area kantin.", "WARNING_DEKAT", false, waktu);
        notif.kirim();
        notifikasiRepository.tambahNotifikasi(notif);
    }

    public void kirimNotifikasiSiap(String antrianId) {
        if (notifikasiRepository.existsByAntrianAndJenis(antrianId, "READY")) {
            return;
        }
        String id = IdGenerator.generateId();
        String waktu = DateUtil.getCurrentDateTime();
        Notifikasi notif = new Notifikasi(id, antrianId, "Pesanan Anda sudah siap diambil di counter.", "READY", false, waktu);
        notif.kirim();
        notifikasiRepository.tambahNotifikasi(notif);
    }

    public List<Notifikasi> getNotifikasiByAntrian(String antrianId) {
        return notifikasiRepository.getNotifikasiByAntrian(antrianId);
    }

    public List<Notifikasi> getNotifikasiTerbaru(int limit) {
        return notifikasiRepository.getNotifikasiTerbaru(limit);
    }

    public void hapusSemuaNotifikasi() {
        notifikasiRepository.deleteAll();
    }
}
