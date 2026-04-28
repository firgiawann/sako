# SAKO Release Notes

## Isi rilis

Rilis ini berisi project desktop Java untuk Sistem Antrian Kantin dengan Notifikasi Otomatis.

Komponen utama:

- `README.md` untuk penjelasan teknis dan flow proyek
- Source code Java di `src/main/java`
- Konfigurasi Maven di `pom.xml`
- Basis data lokal `sako.db`

## Ringkasan teknis

- Java 17
- Maven
- Swing + FlatLaf
- SQLite
- Arsitektur MVC sederhana dengan layer:
  - model
  - view
  - controller
  - service
  - repository
  - util

## Alur penggunaan singkat

1. Jalankan `com.klp4.sako.Main`
2. Aplikasi inisialisasi database dan seed akun awal
3. Login atau masuk ke dashboard sesuai peran
4. Pelanggan mengambil antrian
5. Penjual memproses status antrian
6. Admin memantau data dan konfigurasi

## Akun awal

- Admin: `admin` / `admin123`
- Penjual: `penjual1` / `penjual123`

## Catatan

File database `sako.db` disertakan agar aplikasi langsung bisa dibuka tanpa setup manual tambahan.

