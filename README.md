# SAKO

SAKO adalah aplikasi desktop Java untuk Sistem Antrian Kantin dengan Notifikasi Otomatis. Project ini dibuat untuk konteks UAS dengan fokus pada penerapan OOP, MVC sederhana, dan penyimpanan lokal memakai SQLite.

## Ringkasan Teknis

- Bahasa: Java 17
- Build tool: Maven
- GUI: Swing
- Look and feel: FlatLaf
- Database: SQLite lokal
- Arsitektur: `model` - `view` - `controller` - `service` - `repository` - `util`

## Tujuan Project

Project ini dipakai sebagai implementasi tugas kuliah/UAS yang menunjukkan:

- Class dan object
- Encapsulation
- Inheritance
- Polymorphism
- Abstract class
- Package yang terpisah jelas
- Alur kerja aplikasi desktop yang rapi dan mudah dipelajari

## Konteks UAS

Dokumen dan konteks yang dipakai saat penyusunan project ini:

- `OOP_MVC_GUIDE.md`
- `sako-prd-ai-agent.md`
- `sako-prompt-one-shot.md`
- `sako-prompt-parted.md`

Intinya, project ini dibuat supaya bisa dijelaskan saat UAS dengan struktur yang mudah diuraikan: dari kebutuhan sistem, model data, alur controller, sampai penyimpanan ke database.

## Aktor Sistem

- Pelanggan: mengambil nomor antrian, melihat status, menerima notifikasi
- Penjual: melihat antrian aktif, memproses pesanan, mengubah status antrian
- Admin: memantau data, mengelola sistem, mengatur konfigurasi dasar

## Struktur Class Utama

### `User`
Abstract class dasar untuk semua aktor.

Turunan:

- `Admin`
- `Penjual`
- `Pelanggan`

### Model transaksi

- `Antrian`
- `Pesanan`
- `ItemPesanan`
- `MenuItem`
- `Notifikasi`
- `AntrianStatus`

### Layer aplikasi

- `controller`: jembatan dari view ke service
- `service`: logika bisnis utama
- `repository`: akses database SQLite
- `view`: tampilan Swing
- `util`: helper umum seperti tanggal, dialog, dan generator ID

## Alur Sistem

### 1. Aplikasi dijalankan

`Main` menginisialisasi tema Swing, membuka koneksi database, lalu melakukan seed data dasar seperti akun admin dan penjual bila belum ada.

### 2. User login / masuk ke dashboard

`MainFrame` menjadi hub utama. Dari sini user bisa diarahkan ke dashboard sesuai peran.

### 3. Pelanggan mengambil antrian

Flow teknis:

1. User mengisi data pada `DashboardPelangganPanel`
2. View memanggil `AntrianController`
3. Controller meneruskan ke `AntrianService`
4. Service membentuk object `Antrian`
5. Repository menyimpan data ke SQLite
6. Sistem menampilkan nomor antrian dan status awal

### 4. Penjual memproses antrian

Flow teknis:

1. `DashboardPenjualPanel` membaca daftar antrian aktif
2. Penjual memilih antrian tertentu
3. Action dari view diproses controller
4. Service mengubah status antrian
5. Repository melakukan update database
6. Notifikasi dapat dibuat ketika status berubah

### 5. Admin memantau sistem

Admin memakai `DashboardAdminPanel` untuk melihat data, memantau user, dan mengecek kondisi antrian serta konfigurasi.

## Status Antrian

Status utama yang dipakai:

- `MENUNGGU`
- `DIPROSES`
- `SIAP`
- `SELESAI`

Urutan ini penting karena dipakai untuk alur kerja penjual dan logika notifikasi.

## Alur Notifikasi

Notifikasi dibuat untuk memberi sinyal ketika:

- antrian sudah dekat
- pesanan sudah siap
- status antrian berubah

Di project ini notifikasi cukup direpresentasikan sebagai data aplikasi desktop, dialog, atau indikator UI. Tidak dipaksa ke sistem push notification eksternal supaya project tetap sederhana dan realistis untuk UAS.

## Database Lokal

File database yang dipakai: `sako.db`.

Tabel utama yang digunakan:

- `users`
- `antrian`
- `pesanan`
- `notifikasi`
- `settings`

## Flow Data Singkat

```text
View -> Controller -> Service -> Repository -> SQLite
SQLite -> Repository -> Service -> Controller -> View
```

Ini menjaga supaya logic tidak menumpuk di tombol GUI.

## Menjalankan Project

```sh
mvn clean package
mvn exec:java -Dexec.mainClass=com.klp4.sako.Main
```

Jika dijalankan dari environment GUI desktop di Android/Termux-X11:

```sh
termux-x11 :0
DISPLAY=:0 mvn exec:java -Dexec.mainClass=com.klp4.sako.Main
```

## Akun Seed

Saat aplikasi pertama kali dijalankan, data awal dibuat otomatis jika belum ada:

- Admin: `admin` / `admin123`
- Penjual: `penjual1` / `penjual123`

## Catatan Pengembangan

- Package sudah dipisah per tanggung jawab.
- Query database tidak ditaruh di class GUI.
- Logika utama diletakkan di service.
- Project ini sengaja dijaga tetap sederhana supaya mudah dijelaskan di UAS dan mudah diubah saat revisi.

