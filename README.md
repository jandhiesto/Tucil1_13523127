# TucilStima1_13523127
## Deskripsi
Program ini adalah solusi untuk permainan IQ Puzzler menggunakan algoritma brute force dengan teknik backtracking. Program dibuat sepenuhnya dalam bahasa Java.

## Cara Menggunakan

### Prasyarat
Pastikan Anda memiliki Java Development Kit (JDK) terinstal pada sistem Anda.
### Instalasi
#### Clone repositori ini ke komputer Anda:
```bash
git clone https://github.com/jandhiesto/TucilStima1_13523127.git
```
#### Masuk ke direktori proyek
```bash
cd TucilStima1_13523127
```
### Menjalankan Program
#### Buka terminal atau command prompt.
```bash
cd bin
```
#### Arahkan direktori ke folder bin, yang berisi file hasil kompilasi:
```bash
java Main
```
#### Format Masukan
Masukan berupa nama sebuah file .txt yang ada pada folder test. Format masukan adalah sebagai berikut :
```bash
N M P
S
puzzle_1_shape
puzzle_2_shape
…
puzzle_P_shape
```
dengan N dan M adalah dimensi panjang dan lebar papan,
P adalah jumlah blok pada masukan,
S adalah pilihan mode puzzle (DEFAULT/CUSTOM/PYRAMID). Namun program ini hanya mendukung mode DEFAULT,
serta diikuti dengan konfigurasi P blok yang disusun dengan huruf abjad kapital (A-Z).
## Struktur Direktori
```bash
TucilStima1_13523127/
│-- bin/          # Folder berisi file hasil kompilasi
|-- doc/          # Folder berisi dokumen laporan
│-- src/          # Folder berisi source code Java
│-- test/         # Folder berisi file test berupa .txt serta hasil output gambar
|-- LICENSE       # File keterrangan lisensi
│-- README.md     # File dokumentasi
```
# Catatan
- Jika ada error saat menjalankan program, pastikan Anda berada di dalam direktori proyek yang benar.
- Pastikan semua dependensi sudah terinstal dengan benar.
- Jika terjadi masalah dengan input, periksa kembali format file input sesuai spesifikasi.
# Lisensi
Program ini dibuat untuk keperluan akademik dan bebas digunakan serta dimodifikasi sesuai kebutuhan.
