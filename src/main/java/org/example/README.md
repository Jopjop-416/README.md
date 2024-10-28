# Pemesanan Sayur

Aplikasi pemesanan sayur berbasis Java yang memungkinkan pengguna untuk memesan berbagai jenis sayuran, menghitung total harga, dan menentukan hari pengantaran sesuai dengan jenis sayur yang dipesan.


### Fitur Utama
- **Pemilihan Sayur**: Pengguna dapat memilih berbagai jenis sayuran yang tersedia.
- **Perhitungan Harga**: Menghitung total harga berdasarkan harga per unit dan jumlah pesanan.
- **Diskon**: Memberikan diskon 10% untuk pesanan lebih dari 5 unit.
- **Pengurangan Stok**: Secara otomatis mengurangi stok sayur setelah pemesanan.
- **Hari Pengantaran**: Menentukan hari pengantaran berdasarkan jenis sayur yang dipesan.


## BARIS CODE
Menjelaskan baris code yang ada pada codingan

## Deskripsi Proyek

Aplikasi Pemesanan Sayur ini bertujuan untuk mengelola pesanan sayur dengan mudah melalui tampilan teks. Berikut adalah penjelasan setiap bagian kode dalam aplikasi ini.

### Struktur Kode
Proyek terdiri dari dua kelas utama:

1. **Sayur.java**: Kelas untuk merepresentasikan objek sayur.
2. **PemesananSayur.java**: Kelas utama untuk mengelola proses pemesanan.

### Penjelasan Baris Kode

#### Kelas `Sayur`

```java
class Sayur {
    private String namaSayur;
    private double harga;
    private int stok;
}
```

````java

````

- Baris ini mendefinisikan kelas Sayur, yang digunakan untuk merepresentasikan sebuah sayuran dengan atribut:
  - **namaSayur**: Nama sayur. 
  - **harga**: Harga per unit. 
  - **stok**: Jumlah stok sayur.

````java
public Sayur(String nama, double harga, int stok){}
````
- Konstruktor ini digunakan untuk menginisialisasi objek Sayur dengan nilai nama, harga, dan stok.

````java
public String getNamaSayur() { return namaSayur; }
public double getHarga() { return harga; }
public int getStok() { return stok; }
````
- **Method getter** ini menyediakan akses ke atribut namaSayur, harga, dan stok dari luar kelas.

````java
public void kurangiStok(int jumlah) {}
````
- Method ini mengurangi stok sayur sesuai dengan jumlah yang dipesan pengguna.

### Kelas PemesananSayur
````java
class PemesananSayur {
    private static final double DISKON = 0.1;
    private static final Map<String, String> HARI_PENGANTARAN = new HashMap<>();
}
````
- Kelas PemesananSayur adalah kelas utama yang menangani proses pemesanan. Di dalamnya, terdapat dua atribut:
  - **DISKON**: Konstanta untuk menyimpan nilai diskon 10%.
  - **HARI_PENGANTARAN**: Map yang menghubungkan setiap jenis sayur dengan hari pengantaran yang spesifik.

````java
    static {
        HARI_PENGANTARAN.put("Bayam", "Senin");
        HARI_PENGANTARAN.put("Kangkung", "Selasa");
        HARI_PENGANTARAN.put("Tomat", "Rabu");
        HARI_PENGANTARAN.put("Wortel", "Kamis");
        HARI_PENGANTARAN.put("Terong", "Jumat");
        HARI_PENGANTARAN.put("Jamur", "Sabtu");
    }
````
- **Static block** ini menginisialisasi data pada **HARI_PENGANTARAN** dengan setiap jenis sayur dan hari pengantarannya.

````java
public static void main(String[] args) {
    ArrayList<Sayur> sayurList = buatDaftarSayur();
    tampilkanMenu(sayurList);
    System.out.println("Daftar Sayur: ");

    Scanner scanner = new Scanner(System.in);
    int pilihan = scanner.nextInt();
    System.out.println("Jumlah yang ingin dipesan: ");
    int jumlah = scanner.nextInt();

    prosesPemesanan(sayurList.get(pilihan - 1), jumlah);
}
````
- Method **main** adalah titik awal program. Di sini, program akan:
  - Membuat daftar sayur yang tersedia dengan memanggil `buatDaftarSayur()`.
  - Menampilkan menu sayur kepada pengguna.
  - Meminta input pengguna untuk memilih sayur dan jumlah pesanan.
  - Memproses pesanan dengan memanggil `prosesPemesanan()`.

````java
private static ArrayList<Sayur> buatDaftarSayur() {
    ArrayList<Sayur> sayurList = new ArrayList<>();
    sayurList.add(new Sayur("Bayam", 5000, 10));
    sayurList.add(new Sayur("Kangkung", 4000, 15));
    sayurList.add(new Sayur("Tomat", 7000, 20));
    sayurList.add(new Sayur("Wortel", 6000, 12));
    sayurList.add(new Sayur("Terong", 8000, 8));
    sayurList.add(new Sayur("Jamur", 9000, 10));
    return sayurList;
}
````
- **Method** `buatDaftarSayur` membuat dan mengembalikan daftar sayur yang tersedia dengan masing-masing stok dan harga.

````java
private static void tampilkanMenu(ArrayList<Sayur> sayurList) {
    System.out.println("Pilih Sayur: ");
    for (int i = 0; i < sayurList.size(); i++) {
        System.out.println(i + 1 + ". " + sayurList.get(i).getNamaSayur() + " (Stok: " + sayurList.get(i).getStok() + ")");
    }
}
````
- Method ini menampilkan menu sayuran kepada pengguna, di mana setiap item menu berisi informasi nama sayur dan stok yang tersedia.

````java
private static void prosesPemesanan(Sayur sayur, int jumlah) {
    if (jumlah <= sayur.getStok()) {
        double totalHarga = hitungTotalHarga(sayur.getHarga(), jumlah);
        System.out.println("Total Harga: Rp" + totalHarga);
        sayur.kurangiStok(jumlah);

        String hariPengantaran = tentukanHariPengantaran(sayur.getNamaSayur());
        System.out.println(sayur.getNamaSayur() + " akan diantarkan pada hari " + hariPengantaran);
    } else {
        System.out.println("Stok tidak cukup");
    }
}
````
- **Method** `prosesPemesanan` menangani seluruh proses pemesanan, yaitu:
  - Memeriksa apakah stok cukup untuk pesanan.
  - Menghitung total harga dengan memanggil `hitungTotalHarga()`.
  - Mengurangi stok sayur.
  - Menampilkan hari pengantaran berdasarkan jenis sayur.

````java
private static double hitungTotalHarga(double harga, int jumlah) {
    double total = harga * jumlah;
    if (jumlah > 5) {
        total -= total * DISKON;
    }
    return total;
}
````
- Method ini menghitung total harga berdasarkan jumlah yang dipesan. Jika jumlah lebih dari 5, maka diskon 10% diterapkan.

````java
private static String tentukanHariPengantaran(String namaSayur) {
    return HARI_PENGANTARAN.getOrDefault(namaSayur, "Hari tidak ditentukan");
}
````
- Method `tentukanHariPengantaran` menentukan hari pengantaran berdasarkan nama sayur yang dipesan. Jika sayur tidak ada di dalam map `HARI_PENGANTARAN`, maka akan menampilkan pesan bahwa hari belum ditentukan.

