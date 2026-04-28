# Prompt Langsung - Sekali Kirim

```text
Buat project Java desktop bertema Sistem Antrian Kantin dengan Notifikasi Otomatis. Nama project tidak perlu final, cukup gunakan singkatan kerja SAKO dan pastikan mudah diganti nanti. Saya ingin hasilnya berupa project yang siap dikembangkan, mudah dipelajari, tidak terlalu kompleks, mudah dimainten, dan mudah diotak-atik oleh mahasiswa/pemula.

Gunakan ketentuan berikut:

1. Tech stack
- Java JDK 17
- Maven
- Java Swing sebagai GUI utama
- SQLite lokal untuk penyimpanan data
- Boleh pakai FlatLaf jika perlu, tetapi jangan jadikan wajib bila hanya menambah kompleksitas

2. Tema aplikasi
Aplikasi yang dibuat adalah Sistem Antrian Kantin dengan Notifikasi Otomatis.
Tujuannya untuk membantu mengatur antrian di kantin supaya pelanggan tidak perlu berdiri lama.
Pelanggan bisa mengambil nomor antrian lewat aplikasi, melihat status antrian, dan menerima notifikasi saat giliran sudah dekat atau saat pesanan sudah siap.

3. Aktor sistem
- Pelanggan: mengambil nomor antrian, melihat status antrian, menerima notifikasi
- Penjual/Kantin: memproses pesanan, mengupdate antrian, menandai pesanan siap
- Admin: mengatur sistem dan data antrian

4. Wajib menerapkan materi OOP
Project harus jelas menerapkan:
- Class dan Object
- Encapsulation
- Inheritance
- Polymorphism
- Abstract Class
- Package

5. Struktur OOP yang wajib ada
Gunakan abstract class `User` sebagai dasar semua aktor.

Spesifikasi minimal:
- Abstract class `User`
  - atribut: `nama`
  - method abstract: `login()`, `lihatMenu()`
- Class `Pelanggan extends User`
  - method: `ambilAntrian()`, `lihatStatus()`, `terimaNotifikasi()`
- Class `Penjual extends User`
  - method: `prosesPesanan()`, `updateAntrian()`
- Class `Admin extends User`
  - method: `kelolaData()`, `aturSistem()`

Tambahkan juga class pendukung yang masuk akal, misalnya:
- `Antrian`
- `Pesanan`
- `ItemPesanan`
- `Notifikasi`
- `UserRepository`
- `AntrianRepository`
- `AntrianService`
- controller dan view yang relevan

6. Batasan implementasi
- Jangan over-engineered
- Jangan gunakan pattern berlebihan
- Pisahkan package dengan jelas
- Jangan campur query database di class GUI
- Jangan menaruh seluruh logika pada tombol/action listener
- Tiap class harus punya tanggung jawab jelas
- Kode harus rapi, sederhana, dan mudah dibaca
- Beri komentar secukupnya hanya pada bagian penting untuk pembelajaran
- Gunakan enum untuk status antrian jika diperlukan

7. Struktur package yang diinginkan
Gunakan struktur seperti ini atau yang sangat mirip:
- `app.sako.model`
- `app.sako.view`
- `app.sako.controller`
- `app.sako.service`
- `app.sako.repository`
- `app.sako.util`

8. Fitur minimal yang harus jadi
- Pelanggan bisa input nama dan mengambil nomor antrian
- Sistem menyimpan antrian dengan status awal `MENUNGGU`
- Penjual bisa melihat daftar antrian aktif
- Penjual bisa mengubah status menjadi `DIPROSES`, `SIAP`, `SELESAI`
- Pelanggan bisa melihat status antriannya
- Sistem menampilkan notifikasi otomatis saat nomor antrian sudah dekat atau pesanan siap
- Admin bisa melihat data antrian dan pengaturan dasar
- Ada login untuk penjual dan admin

9. Desain GUI
Buat GUI Swing yang sederhana tapi rapi:
- Halaman awal / login role
- Dashboard pelanggan
- Dashboard penjual
- Dashboard admin
Gunakan komponen dasar seperti JFrame, JPanel, JTable, JButton, JLabel, JTextField, JOptionPane.
Tidak perlu desain mewah, yang penting jelas dan fungsional.

10. Database SQLite
Buat skema sederhana minimal untuk tabel:
- `users`
- `antrian`
- `pesanan`
- `notifikasi`
- `settings`

11. Output yang saya inginkan dari kamu
Tolong langsung hasilkan project lengkap dan terstruktur, bukan hanya penjelasan. Saya ingin:
- struktur folder project
- isi `pom.xml`
- source code class utama
- model, controller, service, repository, dan util penting
- GUI utama yang bisa dijalankan
- script inisialisasi database atau auto-create table saat aplikasi dijalankan
- sample data secukupnya
- README singkat cara menjalankan project

12. Gaya coding
- sederhana
- konsisten
- mudah dipelajari
- mudah dimodifikasi
- cocok untuk tugas/project kuliah
- jangan terlalu banyak abstraction tambahan di luar kebutuhan

13. Prioritas hasil
Urutkan prioritas sebagai berikut:
1) project bisa dijalankan
2) struktur OOP jelas
3) fitur inti antrian bekerja
4) kode mudah dipahami
5) GUI cukup rapi

Langsung implementasikan project ini secara lengkap dengan asumsi default yang masuk akal. Jika ada hal kecil yang belum ditentukan, ambil keputusan desain yang paling sederhana dan paling mudah dirawat.
```
