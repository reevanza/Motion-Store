# 📱 Motion Store App

## 🧾 Deskripsi Aplikasi
Motion Store adalah aplikasi e-commerce sederhana berbasis Android (Jetpack Compose) yang memungkinkan pengguna melihat produk, menambahkan ke keranjang, melakukan checkout, serta melacak pengiriman pesanan secara realtime.

Aplikasi ini dibuat untuk memenuhi tugas pengembangan aplikasi mobile dengan implementasi API, Room Database, dan arsitektur MVVM.

Link Presentasi: https://drive.google.com/file/d/170dVyvgvPtaoEtobx_s3mf5g-CVt1qD_/view?usp=sharing
---

## 🎯 Tujuan Pembuatan
- Menerapkan konsep CRUD pada aplikasi Android  
- Menggunakan API eksternal  
- Menggunakan database lokal Room  
- Menerapkan arsitektur MVVM  
- Membuat UI modern dengan Jetpack Compose  
- Simulasi aplikasi e-commerce sederhana  

---

## ⭐ Fitur Utama

### 🔐 Autentikasi
- Login & Register user  
- Data user disimpan di Room Database  
- Session login menggunakan StateFlow  

### 🛍️ Produk
- Mengambil data dari API DummyJSON  
- Menampilkan list produk  
- Detail produk  
- Pencarian produk  

### 🛒 Cart
- Tambah produk ke keranjang  
- Tambah / kurang jumlah item  
- Hapus item  
- Hitung total harga  

### 💳 Checkout
- Simulasi pembayaran  
- Membuat order setelah checkout  

### 🚚 Tracking Pengiriman
- Tracking realtime  
- Progress pengiriman  
- Multi order tracking  
- Berhenti saat pesanan tiba  

### 👤 Profile
- Edit nama  
- Edit alamat  
- Data disimpan di Room  
- Digunakan sebagai alamat pengiriman  

---

## 🖼️ Screen
### 🔐 Login Screen
<img width="241" height="533" alt="image" src="https://github.com/user-attachments/assets/de374696-6cb1-4fbe-9327-9e921e9a1ddf" />

Screen pertama yang muncul saat aplikasi dibuka jika user belum login.

Fungsi:
- User memasukkan username dan password
- Validasi data ke Room Database
- Jika valid → masuk ke halaman Explore
- Jika belum punya akun → navigasi ke Register

Komponen utama:
- TextField username
- TextField password
- Button login
- Button register

Data:
- Menggunakan `UserRepository`
- Session disimpan di `SessionManager`

---

### 📝 Register Screen
<img width="243" height="532" alt="image" src="https://github.com/user-attachments/assets/ab33ae65-dcb8-453d-8e2d-b926df422bd1" />

Digunakan untuk membuat akun baru.

Fungsi:
- Input username
- Input password
- Input nama user
- Menyimpan data ke Room Database
- Setelah register → kembali ke login

CRUD:
- Create user ke tabel `users`

---

### 🏠 Explore Screen
<img width="244" height="536" alt="image" src="https://github.com/user-attachments/assets/1c1f27f9-36bd-46c2-8793-9674e90cfc9b" />

Halaman utama aplikasi yang menampilkan daftar produk dari API.

Fungsi:
- Fetch produk dari API DummyJSON
- Menampilkan list produk dalam grid
- Search produk berdasarkan nama
- Navigasi ke detail produk
- Tambah produk ke cart

Komponen:
- Search bar
- Grid produk
- Bottom navigation

API: https://dummyjson.com/products


ViewModel:
- ProductViewModel

---

### 📄 Detail Screen
<img width="244" height="527" alt="image" src="https://github.com/user-attachments/assets/bc6acf28-b821-46f5-9b2c-fb626b13dc5d" />

Menampilkan detail lengkap produk.

Fungsi:
- Menampilkan gambar produk
- Menampilkan deskripsi
- Menampilkan harga
- Tombol tambah ke cart

Data:
- Diambil dari ViewModel
- Mapping dari ProductDto → Product

---

### 🛒 Cart Screen
<img width="244" height="542" alt="image" src="https://github.com/user-attachments/assets/2fb19926-6443-4283-ac04-745c71d42d8c" />

Menampilkan produk yang sudah ditambahkan ke keranjang.

Fungsi:
- Menampilkan semua item cart
- Tambah jumlah item
- Kurang jumlah item
- Hapus item
- Hitung total harga
- Navigasi ke checkout

CRUD:
- Read cart dari Room
- Update qty
- Delete item

Database:
- Tabel `cart`

---

### 💳 Checkout Screen
<img width="242" height="538" alt="image" src="https://github.com/user-attachments/assets/ef544d1f-7179-4a66-b018-0d4a8a757f87" />

Simulasi pembayaran.

Fungsi:
- Tombol bayar
- Setelah bayar → navigasi ke SuccessScreen

Tidak menggunakan payment gateway real (simulasi).

---

### ✅ Success Screen
<img width="252" height="542" alt="image" src="https://github.com/user-attachments/assets/afa715ea-91b3-45ff-b524-71fef3b316f5" />

Muncul setelah pembayaran berhasil.

Fungsi:
- Menampilkan pesan pembayaran berhasil
- Membuat order baru dari cart
- Navigasi ke Shipping tracking

Proses: Cart → create order → OrderManager


Order disimpan sementara di memory.

---

### 🚚 Shipping / Tracking Screen
<img width="245" height="540" alt="image" src="https://github.com/user-attachments/assets/a97af292-fd22-484c-a4e6-f82375f1795b" />

Menampilkan status pengiriman pesanan.

Fungsi:
- Menampilkan daftar order (bisa lebih dari satu)
- Menampilkan status realtime:
  - Diproses
  - Dikemas
  - Diantar
  - Tiba
- Progress bar pengiriman
- Menampilkan item yang dikirim
- Menampilkan alamat user dari profile

Tracking:
- Menggunakan coroutine delay
- Status berhenti saat pesanan tiba
- Tidak looping ulang

Komponen:
- Card per order
- Progress indicator
- Status text

---

### 👤 Profile Screen
<img width="235" height="524" alt="image" src="https://github.com/user-attachments/assets/e17ff3cc-0108-448e-81da-28fe65e6df79" />

Menampilkan data user dan alamat.

Fungsi:
- Edit nama
- Edit alamat
- Simpan ke Room
- Logout user

Data:
- Tabel `users`
- Digunakan untuk alamat pengiriman

CRUD:
- Read profile
- Update profile

---

## 🔄 Alur Navigasi Aplikasi
Login
↓
Explore
↓
Detail
↓
Cart
↓
Checkout
↓
Success
↓
Shipping


Bottom Navigation:
- Explore
- Cart
- Tracking
- Profile

- 
---

## ▶️ Cara Menjalankan Aplikasi

### 1. Clone Repository
git clone https://github.com/reevanza/Motion-Store

### 2. Buka di Android Studio
- Minimum SDK: 24  
- Compile SDK: 34  

### 3. Jalankan Emulator / Device
Klik tombol **Run ▶️**

---

## 📦 Build APK
Untuk menghasilkan APK:
Build → Build APK(s)


File APK berada di:
app/build/outputs.apk/debug/


---

## 🧭 Navigasi Antar Screen

Alur aplikasi:
Login → Explore → Detail → Cart → Checkout → Success → Shipping


Screen yang tersedia:
- LoginScreen  
- RegisterScreen  
- ExploreScreen  
- DetailScreen  
- CartScreen  
- CheckoutScreen  
- SuccessScreen  
- ShippingScreen  
- ProfileScreen  

Navigasi menggunakan **Navigation Compose**.

Bottom Navigation:
- Explore  
- Cart  
- Tracking  
- Profile  

---

## 🧱 Penerapan CRUD

### User (Room Database)
- Create → Register user  
- Read → Login & profile  
- Update → Edit profile  
- Delete → Logout  

### Cart (Room Database)
- Create → Tambah produk  
- Read → Lihat cart  
- Update → Tambah/kurang qty  
- Delete → Hapus item  

---

## 🌐 Pengelolaan Data

### API
Menggunakan:
https://dummyjson.com/products


Digunakan untuk:
- Fetch produk  
- Search produk  

Library:
- Retrofit  
- Gson Converter  

---

### Database Lokal (Room)
Digunakan untuk:
- User  
- Cart  

Entity:
- UserEntity  
- CartEntity  

DAO:
- UserDao  
- ProductDao  

---

## 🏗️ Arsitektur Aplikasi

Menggunakan arsitektur **MVVM**.
UI (Compose)
↓
ViewModel
↓
Repository
↓
API / Room


### ViewModel
- ProductViewModel  

### Repository
- ProductRepository  
- UserRepository  
- OrderManager  

---

## 📦 Library yang Digunakan
- Jetpack Compose  
- Navigation Compose  
- Room Database  
- Retrofit  
- Coil (image loading)  
- Coroutines  
- StateFlow  

---

## 🚚 Sistem Tracking
Setelah checkout:
- Order dibuat  
- Tracking realtime  
- Multi order  
- Progress bar  
- Berhenti saat sampai  

---

## 📁 Struktur Folder
data/
├ local/
├ model/
├ remote/
└ repository/

ui/
├ screen/
├ auth/
└ theme/

viewmodel/
navigation/

---

## 🎯 Kesimpulan
Aplikasi Motion Store berhasil mengimplementasikan:
- API integration  
- CRUD Room database  
- MVVM architecture  
- Navigation multi-screen  
- Realtime UI state  

Aplikasi ini merupakan simulasi e-commerce sederhana yang dapat dikembangkan lebih lanjut dengan backend server dan payment gateway.
