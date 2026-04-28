# PRD / Planning - AI Agent Ready

## Informasi Dokumen
- **Kode proyek**: **SAKO** *(singkatan kerja, bisa diganti kapan saja)*
- **Nama aplikasi**: Sistem Antrian Kantin dengan Notifikasi Otomatis
- **Jenis aplikasi**: Desktop application
- **Target implementasi**: Java OOP + GUI Java Swing
- **Status dokumen**: PRD awal yang siap dibaca AI Agent seperti Codex, Claude Code, dan Copilot
- **Tujuan dokumen**: Menjadi acuan implementasi proyek yang mudah dipelajari, tidak terlalu kompleks, mudah dirawat, dan tetap kuat dari sisi konsep OOP

---

## 1. Ringkasan Produk
SAKO adalah aplikasi desktop untuk membantu mengelola antrian kantin agar pelanggan tidak perlu berdiri lama di depan penjual. Pelanggan dapat mengambil nomor antrian dari aplikasi, melihat status antrian, dan menerima notifikasi saat giliran sudah dekat atau saat pesanan sudah siap. Penjual dapat memproses antrian dan mengubah status pesanan, sedangkan admin dapat mengatur data sistem dan konfigurasi dasar.

Aplikasi ini dirancang sebagai proyek pembelajaran OOP yang tetap realistis untuk dikembangkan. Karena itu, fitur dan struktur dibuat cukup rapi untuk menyerupai proyek nyata, namun tetap sederhana agar mudah dipahami mahasiswa/pemula.

---

## 2. Masalah yang Ingin Diselesaikan
Masalah utama pada kantin manual:
1. Pelanggan harus menunggu lama sambil berdiri.
2. Tidak ada informasi posisi antrian yang jelas.
3. Penjual kesulitan mengelola urutan pesanan saat ramai.
4. Tidak ada notifikasi ketika giliran mendekat atau pesanan selesai.
5. Data antrian sulit dipantau admin.

---

## 3. Solusi yang Ditawarkan
Aplikasi menyediakan:
- Pengambilan nomor antrian secara digital.
- Informasi status antrian secara real-time dalam konteks aplikasi lokal.
- Notifikasi otomatis ketika antrian pelanggan sudah dekat atau pesanan sudah siap.
- Panel kerja penjual untuk memproses pesanan dan memanggil antrian.
- Panel admin untuk mengelola data antrian, data pengguna, dan pengaturan sistem.

**Catatan implementasi sederhana:**
Notifikasi cukup berupa dialog, badge status, highlight, atau suara pendek di desktop app. Tidak perlu push notification ke HP agar kompleksitas tetap rendah.

---

## 4. Tujuan Produk
### Tujuan utama
- Membuat sistem antrian kantin yang lebih tertib dan informatif.
- Mengurangi waktu tunggu berdiri bagi pelanggan.
- Membantu penjual memproses pesanan secara urut.
- Menyediakan contoh proyek OOP Java GUI yang layak dipelajari dan dikembangkan.

### Tujuan pembelajaran OOP
Proyek wajib memperlihatkan penerapan materi:
- Class dan Object
- Encapsulation
- Inheritance
- Polymorphism
- Abstract Class
- Package

---

## 5. Aktor Sistem
### 5.1 Pelanggan
Peran:
- Mengambil nomor antrian
- Melihat status antrian
- Menerima notifikasi

### 5.2 Penjual / Kantin
Peran:
- Melihat daftar antrian
- Memproses pesanan
- Mengubah status antrian
- Menandai pesanan siap

### 5.3 Admin
Peran:
- Mengelola data pengguna
- Mengelola data antrian
- Mengatur sistem
- Memantau jalannya aplikasi

---

## 6. Konsep OOP yang Wajib Dipakai

### 6.1 Abstract Class
Gunakan abstract class sebagai dasar semua aktor.

```java
public abstract class User {
    protected String nama;

    public User(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public abstract void login();
    public abstract void lihatMenu();
}
```

### 6.2 Turunan Abstract Class
#### Pelanggan extends User
Method utama:
- `ambilAntrian()`
- `lihatStatus()`
- `terimaNotifikasi()`

#### Penjual extends User
Method utama:
- `prosesPesanan()`
- `updateAntrian()`

#### Admin extends User
Method utama:
- `kelolaData()`
- `aturSistem()`

### 6.3 Encapsulation
Semua atribut penting dibuat `private` lalu diakses dengan getter/setter atau method khusus.
Contoh:
- `Antrian.nomor`
- `Antrian.status`
- `Pesanan.daftarItem`
- `Notifikasi.pesan`

### 6.4 Inheritance
Struktur pewarisan minimal:
- `User`
  - `Pelanggan`
  - `Penjual`
  - `Admin`

### 6.5 Polymorphism
Gunakan polymorphism pada method yang sama tetapi perilakunya berbeda di tiap turunan.
Contoh:
- `login()` pada `Pelanggan`, `Penjual`, dan `Admin`
- `lihatMenu()` dapat menampilkan menu berbeda sesuai peran
- `kirimNotifikasi(User user)` dapat menerima berbagai turunan `User`

### 6.6 Class dan Object
Harus ada objek nyata yang digunakan pada aplikasi, misalnya:
- objek pelanggan
- objek penjual
- objek admin
- objek antrian
- objek pesanan
- objek notifikasi

### 6.7 Package
Pisahkan kode ke package yang jelas supaya mudah dipelajari:
- `app.sako.model`
- `app.sako.view`
- `app.sako.controller`
- `app.sako.service`
- `app.sako.repository`
- `app.sako.util`

---

## 7. Ruang Lingkup MVP
Versi awal yang wajib ada:
1. Pelanggan dapat mengambil nomor antrian.
2. Sistem menampilkan daftar antrian yang sedang menunggu.
3. Penjual dapat memanggil dan memproses antrian.
4. Status antrian dapat berubah: `MENUNGGU`, `DIPROSES`, `SIAP`, `SELESAI`.
5. Pelanggan dapat melihat status antriannya.
6. Sistem memberi notifikasi saat nomor hampir dipanggil atau saat pesanan siap.
7. Admin dapat melihat dan mengelola data dasar.
8. Data disimpan secara lokal agar aplikasi tetap sederhana.

### Yang belum wajib untuk MVP
- Integrasi WhatsApp/SMS/email
- Login multi-device
- Sinkronisasi cloud
- Pembayaran online
- Laporan analitik kompleks
- Hak akses sangat detail

---

## 8. Fitur Fungsional per Aktor

### 8.1 Fitur Pelanggan
#### FR-P-01 Ambil nomor antrian
Pelanggan dapat menginput nama lalu mengambil nomor antrian.

#### FR-P-02 Lihat status antrian
Pelanggan dapat melihat nomor antrian, posisi, dan status saat ini.

#### FR-P-03 Terima notifikasi mendekati giliran
Jika selisih antrian tinggal sedikit, pelanggan menerima notifikasi pada aplikasi.

#### FR-P-04 Terima notifikasi pesanan siap
Saat penjual menandai pesanan siap, pelanggan mendapat notifikasi.

### 8.2 Fitur Penjual
#### FR-J-01 Lihat daftar antrian
Penjual dapat melihat seluruh antrian aktif.

#### FR-J-02 Proses pesanan
Penjual dapat memilih antrian lalu mengubah status ke `DIPROSES`.

#### FR-J-03 Tandai siap
Penjual dapat mengubah status ke `SIAP` saat pesanan selesai dibuat.

#### FR-J-04 Selesaikan antrian
Penjual dapat menutup antrian yang sudah diambil pelanggan.

### 8.3 Fitur Admin
#### FR-A-01 Kelola data pengguna
Admin dapat menambah, mengubah, dan menghapus data penjual/admin.

#### FR-A-02 Kelola data antrian
Admin dapat melihat histori antrian, menghapus data antrian tertentu, dan reset nomor harian.

#### FR-A-03 Atur sistem
Admin dapat mengatur batas notifikasi, format nomor antrian, dan jam operasional.

---

## 9. Aturan Bisnis
1. Nomor antrian dibuat otomatis dan unik.
2. Satu antrian mewakili satu transaksi/pesanan pelanggan.
3. Status antrian bergerak berurutan:
   - `MENUNGGU`
   - `DIPROSES`
   - `SIAP`
   - `SELESAI`
4. Notifikasi “giliran dekat” muncul jika sisa antrian di depan pelanggan <= batas tertentu, misalnya 2.
5. Nomor antrian dapat direset per hari oleh admin.
6. Penjual hanya boleh memproses antrian yang statusnya masih aktif.
7. Admin memiliki hak akses penuh.

---

## 10. Non-Functional Requirements
### NFR-01 Mudah dipelajari
Kode harus menggunakan nama class, method, dan variable yang jelas.

### NFR-02 Tidak over-engineered
Hindari pattern yang terlalu banyak. Fokus pada OOP dasar + MVC/layer sederhana.

### NFR-03 Mudah dirawat
Setiap class punya satu tanggung jawab utama.

### NFR-04 GUI sederhana dan jelas
Antarmuka tidak perlu terlalu mewah. Prioritaskan keterbacaan dan alur penggunaan.

### NFR-05 Mudah dijalankan
Gunakan Maven dan dependensi yang ringan.

### NFR-06 Mudah dikembangkan
Struktur package harus memungkinkan fitur baru ditambahkan tanpa membongkar kode lama.

---

## 11. Keputusan Teknologi
### Pilihan utama
- **Bahasa**: Java
- **Versi JDK**: 17
- **GUI**: Java Swing
- **Build tool**: Maven
- **Penyimpanan data**: SQLite lokal

### Alasan memilih Swing
- Lebih ringan untuk tugas kuliah/proyek desktop sederhana.
- Setup lebih cepat dibanding JavaFX.
- Cocok untuk fokus belajar OOP, event, form, tabel, dan dialog.
- Mudah dijalankan di IDE umum.

### Dependensi minimal
- `sqlite-jdbc`
- *Opsional*: FlatLaf bila ingin tampilan lebih modern, tetapi bukan keharusan untuk MVP.

---

## 12. Arsitektur yang Direkomendasikan
Gunakan arsitektur sederhana bergaya MVC + service.

### Lapisan sistem
- **Model**: merepresentasikan data dan entitas
- **View**: form GUI Swing
- **Controller**: menangani event dari UI
- **Service**: aturan bisnis sederhana
- **Repository/DAO**: akses data SQLite
- **Util**: helper umum

### Prinsip implementasi
- Jangan campur query database di class GUI.
- Jangan letakkan logika bisnis berat di tombol/action listener.
- Gunakan controller/service untuk menjembatani view dan data.

---

## 13. Struktur Package yang Direkomendasikan
```text
sako/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── app/sako/
        │       ├── Main.java
        │       ├── model/
        │       │   ├── User.java
        │       │   ├── Pelanggan.java
        │       │   ├── Penjual.java
        │       │   ├── Admin.java
        │       │   ├── Antrian.java
        │       │   ├── Pesanan.java
        │       │   ├── ItemPesanan.java
        │       │   ├── MenuItem.java
        │       │   └── Notifikasi.java
        │       ├── view/
        │       │   ├── LoginFrame.java
        │       │   ├── DashboardPelangganFrame.java
        │       │   ├── DashboardPenjualFrame.java
        │       │   ├── DashboardAdminFrame.java
        │       │   └── components/
        │       ├── controller/
        │       │   ├── AuthController.java
        │       │   ├── AntrianController.java
        │       │   └── AdminController.java
        │       ├── service/
        │       │   ├── AntrianService.java
        │       │   ├── NotifikasiService.java
        │       │   └── UserService.java
        │       ├── repository/
        │       │   ├── DatabaseConnection.java
        │       │   ├── UserRepository.java
        │       │   ├── AntrianRepository.java
        │       │   └── PesananRepository.java
        │       └── util/
        │           ├── IdGenerator.java
        │           ├── DateUtil.java
        │           └── DialogUtil.java
        └── resources/
            └── db/
```

---

## 14. Desain Class Inti
### 14.1 User (abstract)
Atribut:
- `nama`

Method:
- `login()`
- `lihatMenu()`

### 14.2 Pelanggan
Atribut tambahan yang disarankan:
- `nomorAntrian`
- `statusAntrian`

Method:
- `ambilAntrian()`
- `lihatStatus()`
- `terimaNotifikasi()`

### 14.3 Penjual
Atribut tambahan yang disarankan:
- `idPenjual`

Method:
- `prosesPesanan()`
- `updateAntrian()`

### 14.4 Admin
Atribut tambahan yang disarankan:
- `idAdmin`

Method:
- `kelolaData()`
- `aturSistem()`

### 14.5 Antrian
Atribut:
- `id`
- `nomor`
- `namaPelanggan`
- `status`
- `waktuAmbil`
- `estimasiDilayani`

Method:
- `ubahStatus()`
- `isAktif()`

### 14.6 Pesanan
Atribut:
- `idPesanan`
- `daftarItem`
- `catatan`
- `status`

Method:
- `tambahItem()`
- `hitungTotalItem()`

### 14.7 Notifikasi
Atribut:
- `idNotifikasi`
- `pesan`
- `jenis`
- `waktuKirim`

Method:
- `kirim()`
- `tandaiDibaca()`

---

## 15. Class Diagram Sederhana (Teks)
```text
User (abstract)
├── Pelanggan
├── Penjual
└── Admin

Pelanggan ---- memiliki ----> Antrian
Pelanggan ---- membuat -----> Pesanan
Pesanan   ---- berisi ------> ItemPesanan
Penjual   ---- memproses ---> Antrian
Admin     ---- mengelola ---> User / Antrian / Pengaturan
Notifikasi ---- terkait ----> Pelanggan / Antrian
```

---

## 16. Rancangan GUI
### 16.1 Halaman/Login Awal
- Tombol masuk sebagai pelanggan
- Login penjual/admin

### 16.2 Dashboard Pelanggan
- Input nama
- Tombol ambil antrian
- Tampilan nomor antrian aktif
- Tampilan status antrian
- Panel notifikasi

### 16.3 Dashboard Penjual
- Tabel daftar antrian
- Tombol proses
- Tombol siap
- Tombol selesai
- Label antrian aktif saat ini

### 16.4 Dashboard Admin
- Tabel pengguna
- Tabel antrian/histori
- Form pengaturan sistem
- Tombol reset nomor antrian

### Pedoman UI
- Gunakan layout yang rapi dan mudah dibaca
- Gunakan `JTable`, `JPanel`, `JButton`, `JLabel`, `JTextField`, `JOptionPane`
- Hindari terlalu banyak form di satu layar
- Pisahkan dashboard per peran agar jelas

---

## 17. Alur Utama Sistem
### Alur pelanggan
1. Pelanggan membuka aplikasi.
2. Pelanggan mengisi nama.
3. Pelanggan mengambil nomor antrian.
4. Sistem menyimpan antrian dengan status `MENUNGGU`.
5. Pelanggan melihat status antrian.
6. Sistem mengirim notifikasi saat giliran dekat atau pesanan siap.

### Alur penjual
1. Penjual login.
2. Penjual melihat daftar antrian.
3. Penjual memilih antrian berikutnya.
4. Penjual mengubah status ke `DIPROSES`.
5. Saat pesanan selesai, status diubah ke `SIAP`.
6. Setelah diambil pelanggan, status diubah ke `SELESAI`.

### Alur admin
1. Admin login.
2. Admin mengelola data pengguna dan pengaturan.
3. Admin memantau data antrian.
4. Admin dapat mereset nomor antrian harian.

---

## 18. Desain Database Sederhana
### Tabel `users`
- `id` TEXT PK
- `nama` TEXT
- `username` TEXT UNIQUE
- `password` TEXT
- `role` TEXT

### Tabel `antrian`
- `id` TEXT PK
- `nomor` INTEGER
- `nama_pelanggan` TEXT
- `status` TEXT
- `waktu_ambil` TEXT
- `estimasi` TEXT

### Tabel `pesanan`
- `id` TEXT PK
- `antrian_id` TEXT
- `catatan` TEXT
- `status` TEXT

### Tabel `notifikasi`
- `id` TEXT PK
- `antrian_id` TEXT
- `pesan` TEXT
- `jenis` TEXT
- `dibaca` INTEGER
- `waktu_kirim` TEXT

### Tabel `settings`
- `id` INTEGER PK
- `batas_notifikasi` INTEGER
- `format_nomor` TEXT
- `jam_buka` TEXT
- `jam_tutup` TEXT

---

## 19. Acceptance Criteria MVP
Aplikasi dianggap memenuhi target awal jika:
1. Bisa dijalankan dari `Main.java`.
2. Terdapat minimal 3 dashboard/halaman sesuai aktor.
3. Pelanggan bisa mengambil nomor antrian.
4. Penjual bisa mengubah status antrian.
5. Admin bisa melihat dan mengatur data dasar.
6. Notifikasi otomatis tampil saat kondisi terpenuhi.
7. Struktur package rapi dan mudah dibaca.
8. Penerapan OOP terlihat jelas pada abstract class, inheritance, encapsulation, dan polymorphism.
9. Kode tidak terlalu kompleks dan mudah dimodifikasi.

---

## 20. Batasan Implementasi untuk AI Agent
AI Agent **harus** mengikuti aturan berikut:
1. Fokus pada keterbacaan, bukan kecanggihan berlebihan.
2. Hindari framework tambahan yang tidak perlu.
3. Jangan membuat arsitektur enterprise yang terlalu besar.
4. Setiap class maksimal memegang tanggung jawab yang jelas.
5. Beri komentar singkat hanya pada bagian yang penting untuk belajar.
6. Gunakan penamaan Indonesia atau Inggris secara konsisten, jangan campur acak.
7. GUI harus fungsional walau sederhana.
8. Pisahkan model, view, controller, dan akses data.
9. Gunakan enum untuk status jika perlu.
10. Pastikan kode mudah diotak-atik oleh mahasiswa/pemula.

---

## 21. Rencana Implementasi Bertahap
### Tahap 1 - Inisialisasi proyek
- Setup Maven
- Buat package dasar
- Buat `Main.java`
- Siapkan koneksi SQLite

### Tahap 2 - Model OOP
- Buat `User` abstract class
- Buat `Pelanggan`, `Penjual`, `Admin`
- Buat `Antrian`, `Pesanan`, `Notifikasi`
- Tambahkan encapsulation dan constructor

### Tahap 3 - Data layer
- Buat tabel database
- Buat repository/DAO dasar

### Tahap 4 - GUI pelanggan
- Form ambil antrian
- Tampilan status
- Panel notifikasi

### Tahap 5 - GUI penjual
- Tabel antrian
- Tombol ubah status

### Tahap 6 - GUI admin
- Data pengguna
- Data antrian
- Pengaturan sistem

### Tahap 7 - Penyempurnaan
- Rapikan alur navigasi
- Validasi input
- Tambahkan sample data
- Rapikan komentar dan README

---

## 22. Definition of Done
Proyek dinyatakan siap dikembangkan lebih lanjut jika:
- Struktur folder sudah jelas
- Class inti sudah lengkap
- GUI utama berjalan
- Alur antrian berfungsi end-to-end
- Database lokal aktif
- PRD ini dapat langsung dijadikan panduan implementasi oleh AI Agent atau developer manusia

---

## 23. Appendix - Arah Pengembangan Lanjutan
Fitur lanjutan yang boleh ditambahkan nanti:
- Histori transaksi lebih lengkap
- Cetak struk/nomor antrian
- Statistik jumlah antrian per hari
- Multi-kantin atau multi-loket
- JavaFX migration
- Notifikasi suara yang lebih rapi
- Dukungan role tambahan

