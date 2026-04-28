# Prompt Bertahap

## Part 1 - Setup Proyek dan Arsitektur
```text
Buat fondasi project Java desktop untuk Sistem Antrian Kantin dengan Notifikasi Otomatis dengan kode proyek SAKO. Gunakan Java 17, Maven, Java Swing, dan SQLite. Jangan over-engineered. Saya ingin struktur package yang rapi dan mudah dipelajari:
- app.sako.model
- app.sako.view
- app.sako.controller
- app.sako.service
- app.sako.repository
- app.sako.util

Tolong hasilkan:
- struktur folder project
- pom.xml
- Main.java
- koneksi database SQLite
- auto create table sederhana
- enum status antrian
- class util dasar
- penjelasan singkat kenapa struktur ini dipilih

Fokus pada fondasi project yang siap dilanjutkan ke tahap berikutnya.
```

## Part 2 - Model OOP
```text
Lanjutkan project SAKO yang sedang dibuat. Sekarang fokus membuat model OOP yang jelas dan mudah dipelajari.

Wajib ada abstract class:
- User
  - atribut: nama
  - method abstract: login(), lihatMenu()

Turunannya:
- Pelanggan extends User
  - method: ambilAntrian(), lihatStatus(), terimaNotifikasi()
- Penjual extends User
  - method: prosesPesanan(), updateAntrian()
- Admin extends User
  - method: kelolaData(), aturSistem()

Tambahkan juga class yang relevan:
- Antrian
- Pesanan
- ItemPesanan
- Notifikasi

Terapkan:
- encapsulation dengan atribut private + getter/setter seperlunya
- inheritance
- polymorphism pada method yang sama dengan perilaku berbeda
- constructor yang jelas
- komentar singkat untuk bagian pembelajaran OOP

Tolong tampilkan source code lengkap untuk class-class tersebut dan pastikan cocok dengan struktur project sebelumnya.
```

## Part 3 - Repository, Service, dan Logika Bisnis
```text
Lanjutkan project SAKO. Sekarang buat layer repository dan service yang sederhana.

Saya ingin class minimal berikut:
- UserRepository
- AntrianRepository
- PesananRepository
- AntrianService
- NotifikasiService
- UserService

Fungsinya minimal:
- simpan user
- login penjual/admin
- ambil nomor antrian
- ubah status antrian
- cari antrian aktif pelanggan
- buat notifikasi otomatis saat antrian sudah dekat atau pesanan siap

Aturan penting:
- query database jangan ditaruh di GUI
- logika bisnis utama taruh di service
- tetap sederhana dan mudah dipahami

Tolong berikan source code lengkap dan sesuaikan dengan skema SQLite lokal.
```

## Part 4 - GUI Swing
```text
Lanjutkan project SAKO. Sekarang buat GUI Java Swing yang sederhana tapi rapi dan fungsional.

Saya ingin minimal ada:
- halaman awal / pilihan role
- login untuk penjual dan admin
- dashboard pelanggan
- dashboard penjual
- dashboard admin

Fitur GUI minimal:
- pelanggan input nama dan ambil antrian
- pelanggan lihat nomor dan status antrian
- penjual lihat tabel antrian aktif
- penjual ubah status: MENUNGGU -> DIPROSES -> SIAP -> SELESAI
- admin lihat data antrian dan pengaturan dasar
- notifikasi tampil lewat dialog, label, atau panel notifikasi

Gunakan komponen dasar Swing seperti JFrame, JPanel, JTable, JButton, JLabel, JTextField, JOptionPane. Hindari desain yang terlalu rumit. Yang penting alur aplikasi berjalan.

Tolong berikan source code GUI dan cara menghubungkannya ke controller/service.
```

## Part 5 - Finishing dan Rapikan Project
```text
Lanjutkan project SAKO sampai siap dipresentasikan atau dikembangkan lebih lanjut.

Tolong lakukan finishing berikut:
- rapikan navigasi antar frame
- tambahkan sample data awal
- tambahkan validasi input penting
- buat README singkat cara menjalankan project
- cek konsistensi penamaan class, package, dan method
- pastikan project mudah dipahami mahasiswa/pemula
- pastikan penerapan OOP terlihat jelas

Output akhir yang saya inginkan:
- project final yang bisa dijalankan
- struktur file lengkap
- potongan file penting jika terlalu panjang
- instruksi run dengan Maven
```
