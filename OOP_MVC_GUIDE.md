# SAKO - Panduan OOP dan MVC

Project ini adalah Sistem Antrian Kantin dengan Notifikasi Otomatis untuk implementasi OOP kelompok 4.

## Struktur MVC

- `model`: class data dan konsep domain aplikasi.
- `view`: tampilan Swing yang dilihat pengguna.
- `controller`: penghubung view ke service.
- `service`: aturan bisnis aplikasi.
- `repository`: akses database SQLite.
- `util`: helper umum seperti tanggal, dialog, dan ID.

## Alur Fitur Utama

1. Pelanggan memilih menu, jumlah, dan mengambil nomor antrian.
2. `DashboardPelangganPanel` memanggil `AntrianController`.
3. `AntrianController` meneruskan ke `AntrianService`.
4. `AntrianService` membuat object `Antrian`, menghitung estimasi, lalu menyimpan via `AntrianRepository`.
5. Saat antrian dekat atau pesanan siap, `NotifikasiService` membuat object `Notifikasi`.
6. Penjual mengubah status antrian dari dashboard penjual.
7. Admin memantau user, statistik antrian, dan riwayat notifikasi.

## Konsep OOP yang Dipakai

### Class dan Object

Contoh class:
- `Antrian`
- `MenuItem`
- `Notifikasi`
- `Pesanan`

Contoh object dibuat saat pelanggan mengambil nomor:

```java
Antrian antrian = new Antrian(id, nomorBaru, namaPelanggan, status, waktu, estimasi, pesanan);
```

### Konstruktor

Konstruktor dipakai untuk mengisi nilai awal object.

```java
public MenuItem(String idMenu, String nama, double harga, boolean tersedia)
```

### Method Void dan Non Void

Void:

```java
public void tambahNotifikasi(Notifikasi notifikasi)
```

Non void:

```java
public double hitungTotal(MenuItem menu, int jumlah)
```

### Enkapsulasi

Atribut dibuat `private` atau `protected`, lalu diakses lewat getter dan setter.

```java
private String nama;
public String getNama()
```

### Inheritance

`Admin`, `Penjual`, dan `Pelanggan` mewarisi class abstrak `User`.

```java
public class Admin extends User
```

### Polymorphism

Object turunan `User` dapat diperlakukan sebagai `User`, tetapi menjalankan method sesuai class aslinya.

```java
User user = new Admin(...);
user.login();
```

### Abstract

`User` adalah class abstrak yang memaksa semua aktor punya method dasar.

```java
public abstract class User {
    public abstract void login();
    public abstract void lihatMenu();
}
```

### Package

Package memisahkan tanggung jawab supaya kode mudah dicari dan dirawat.

```java
package com.klp4.sako.model;
package com.klp4.sako.view;
package com.klp4.sako.controller;
```

## File yang Paling Sering Diubah

- Tambah menu kantin: `MenuService`
- Ubah tampilan pelanggan: `DashboardPelangganPanel`
- Ubah tampilan penjual: `DashboardPenjualPanel`
- Ubah tampilan admin: `DashboardAdminPanel`
- Ubah aturan antrian: `AntrianService`
- Ubah akses database antrian: `AntrianRepository`
- Ubah status antrian: `AntrianStatus`

## Cara Menjalankan

```sh
termux-x11 :0
DISPLAY=:0 mvn exec:java -Dexec.mainClass=com.klp4.sako.Main
```
