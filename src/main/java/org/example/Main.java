package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Sayur {
    private String namaSayur;
    private double harga;
    private int stok;

    /**
     * Konstruktor untuk membuat objek Sayur.
     *
     * @param nama  Nama sayur.
     * @param harga Harga per unit sayur.
     * @param stok  Jumlah stok sayur yang tersedia.
     */
    public Sayur(String nama, double harga, int stok) {
        this.namaSayur = nama;
        this.harga = harga;
        this.stok = stok;
    }

    /**
     * Mendapatkan nama sayur.
     *
     * @return Nama sayur.
     */
    public String getNamaSayur() {
        return namaSayur;
    }

    /**
     * Mendapatkan harga sayur.
     *
     * @return Harga sayur.
     */
    public double getHarga() {
        return harga;
    }

    /**
     * Mendapatkan jumlah stok sayur.
     *
     * @return Jumlah stok sayur.
     */
    public int getStok() {
        return stok;
    }

    /**
     * Mengurangi stok sayur sesuai jumlah yang dipesan.
     *
     * @param jumlah Jumlah yang akan dikurangi dari stok.
     */
    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }
}

/**
 * Kelas utama untuk mengelola pemesanan sayur. Kelas ini menangani tampilan menu,
 * pemrosesan pemesanan, dan perhitungan total harga serta hari pengantaran.
 */
class PemesananSayur {
    private static final double DISKON = 0.1; // Diskon untuk pesanan lebih dari 5 unit

    // Map untuk menghubungkan sayuran dengan hari pengantarannya
    private static final Map<String, String> HARI_PENGANTARAN = new HashMap<>();

    static {
        HARI_PENGANTARAN.put("Bayam", "Senin");
        HARI_PENGANTARAN.put("Kangkung", "Selasa");
        HARI_PENGANTARAN.put("Tomat", "Rabu");
        HARI_PENGANTARAN.put("Wortel", "Kamis");
        HARI_PENGANTARAN.put("Terong", "Jumat");
        HARI_PENGANTARAN.put("Jamur", "Sabtu");
    }

    /**
     * Metode utama yang menjalankan program pemesanan sayur.
     *
     * @param args Argumen baris perintah.
     */
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

    /**
     * Membuat daftar sayur dengan menambahkan objek Sayur ke dalam list.
     *
     * @return Daftar sayur yang tersedia untuk pemesanan.
     */
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

    /**
     * Menampilkan menu sayur yang tersedia beserta stoknya.
     *
     * @param sayurList Daftar sayur yang akan ditampilkan.
     */
    private static void tampilkanMenu(ArrayList<Sayur> sayurList) {
        System.out.println("Pilih Sayur: ");
        for (int i = 0; i < sayurList.size(); i++) {
            System.out.println(i + 1 + ". " + sayurList.get(i).getNamaSayur() + " (Stok: " + sayurList.get(i).getStok() + ")");
        }
    }

    /**
     * Memproses pemesanan sayur dengan menghitung total harga dan menentukan hari pengantaran.
     *
     * @param sayur  Sayur yang akan dipesan.
     * @param jumlah Jumlah yang dipesan.
     */
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

    /**
     * Menghitung total harga berdasarkan jumlah yang dipesan dan memberikan diskon
     * jika jumlah lebih dari 5.
     *
     * @param harga  Harga per unit sayur.
     * @param jumlah Jumlah yang dipesan.
     * @return Total harga setelah diskon (jika berlaku).
     */
    private static double hitungTotalHarga(double harga, int jumlah) {
        double total = harga * jumlah;
        if (jumlah > 5) {
            total -= total * DISKON;
        }
        return total;
    }

    /**
     * Menentukan hari pengantaran berdasarkan nama sayur yang dipesan.
     *
     * @param namaSayur Nama sayur yang dipesan.
     * @return Hari pengantaran yang ditentukan.
     */
    private static String tentukanHariPengantaran(String namaSayur) {
        return HARI_PENGANTARAN.getOrDefault(namaSayur, "Hari tidak ditentukan");
    }
}
