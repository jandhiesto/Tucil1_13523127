import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------[ Bruteforce IQ Puzzler ]---------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file test case tanpa .txt (ex : tc1): ");
        String fileName1 = scanner.nextLine();
        String fileName = ("../test/" + fileName1 +".txt");

        try {
            Solver solver = new Solver(fileName);
            long start = System.currentTimeMillis();
            boolean solved = solver.solve();
            long finish = System.currentTimeMillis();

            if (solved) {
                System.out.println("Waktu pencarian: " + (finish - start) + " ms");
                System.out.println("Jumlah iterasi: " + solver.getIterationCount() + " kali");
            
                System.out.print("Simpan dalam bentuk gambar? (Y/N): ");
                String saveImage = scanner.nextLine();
                if (saveImage.equalsIgnoreCase("Y")) {
                    SaveImage.savePuzzleImage(solver.getBoard(), "../test/" + fileName1 +".png");
                    
                }                
            }
            else {
                System.out.println("Tidak ada solusi ditemukan.");
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        }
        scanner.close();
    }
}