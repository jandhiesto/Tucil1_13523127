import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
    private char[][] board; // Solve puzzle pake matriks
    private List<Block> blocks;
    private int N, M;
    private int iterationCount;

    // Baca input dari file
    private void readInput(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Baca N dan M untuk ukuran papan, dan P untuk jumlah blok
        String[] dims = br.readLine().split(" ");
        N = Integer.parseInt(dims[0]);
        M = Integer.parseInt(dims[1]);
        int P = Integer.parseInt(dims[2]);

        // Membaca mode Puzzle: DEFAULT/CUSTOM/PYRAMID
        String type = br.readLine();

        // Inisialisasi papan
        if (N <= 0 || M <= 0){
            System.out.println("Error: Ukuran papan tidak valid");
            System.exit(0);
        } else {
            board = new char[N][M];
            for (char [] row : board) {
                Arrays.fill(row, '.');
            }
        }
        

        // Menyimpan blok yang ada
        blocks = new ArrayList<>();
        Set<Character> usedSymbols = new HashSet<>(); // Cek duplikasi huruf

        // Memulai untuk membaca blok puzzle
        String currentSymbol = null;
        List<String> currentShape = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue; // Lewati baris kosong
    
            char firstChar = line.charAt(0);
    
            // Validasi: Pastikan hanya huruf kapital A - Z
            if (!Character.isUpperCase(firstChar) || firstChar < 'A' || firstChar > 'Z') {
                System.out.println("Error: Karakter blok harus berupa huruf kapital A-Z! Ditemukan: " + firstChar);
                System.exit(0);
            }
    
            // Jika menemukan blok baru (karakter berubah)
            if (currentSymbol == null || firstChar != currentSymbol.charAt(0)) {
                if (!currentShape.isEmpty()) {
                    // Validasi: Pastikan blok tidak kosong
                    if (!hasValidCharacter(currentShape)) {
                        System.out.println("Error: Blok " + currentSymbol + " tidak memiliki bentuk valid!");
                        System.exit(0);
                    }
    
                    blocks.add(new Block(currentSymbol.charAt(0), new ArrayList<>(currentShape)));
                    currentShape.clear();
                }
                currentSymbol = String.valueOf(firstChar);
    
                // Validasi: Pastikan huruf tidak duplikat
                if (usedSymbols.contains(currentSymbol.charAt(0))) {
                    System.out.println("Error: Blok " + currentSymbol + " sudah ada! Tidak boleh ada duplikasi.");
                    System.exit(0);
                }
                usedSymbols.add(currentSymbol.charAt(0));
            }
            currentShape.add(line); // Tambahkan baris ke bentuk blok
        }
    
        // Tambahkan blok terakhir yang belum tersimpan
        if (!currentShape.isEmpty()) {
            if (!hasValidCharacter(currentShape)) {
                System.out.println("Error: Blok " + currentSymbol + " tidak memiliki bentuk valid!");
                System.exit(0);
            }
            blocks.add(new Block(currentSymbol.charAt(0), new ArrayList<>(currentShape)));
        }
    
        br.close();
    
        // Validasi: Pastikan jumlah blok sesuai dengan P
        if (blocks.size() != P) {
            System.out.println("Error: Jumlah blok tidak sesuai! Diharapkan: " + P + ", tetapi ditemukan: " + blocks.size());
            System.exit(0);
        }
        
    }
    // Fungsi untuk memastikan blok memiliki minimal satu huruf yang valid
    private boolean hasValidCharacter(List<String> shape) {
        for (String row : shape) {
            for (char c : row.toCharArray()) {
                if (Character.isUpperCase(c) && c >= 'A' && c <= 'Z') {
                    return true; // Ditemukan karakter huruf kapital yang valid
                }
            }
        }
        return false; // Tidak ada huruf kapital dalam blok ini
    }

    // Konstruktor
    public Solver(String filename) throws IOException {
        readInput(filename);
    }

    // Memulai pencarian solusi
    public boolean solve() {
        System.out.println("Memulai pencarian solusi...");
        return recursiveSolve(0);
    }

    // Algoritma Bruteforce
    private boolean recursiveSolve(int index) {
        if (index == blocks.size()) return isValidBoard();
    
        Block block = blocks.get(index);
        List<Block> orientations = block.generateOrientations();
    
        for (Block orient : orientations) {  
            for (int a = 0; a < N; a++) {
                for (int c = 0; c < M; c++) {
                    if (canPlace(orient, a, c)) {
                        placeBlock(orient, a, c, block.symbol);
                        clearScreen();
                        printBoard();

                        try { Thread.sleep(0); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                        
                        if (recursiveSolve(index + 1)) return true;
                        removeBlock(orient, a, c);
                        clearScreen();
                        printBoard();
                        try { Thread.sleep(0); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                        iterationCount++;
                    }
                }
            }
        }
        return false; // backtrack
    }

    // Fungsi-fungsi pendukung
    // Mengecek apakah blok bisa ditempatkan dengan mengecek batas papan dan memastikan tidak menimpa blok lain.
    private boolean canPlace(Block block, int startX, int startY) {
        for (int[] cell : block.coordinates) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            if (x < 0 || x >= N || y < 0 || y >= M || board[x][y] != '.') {
                return false;
            }
        }
        return true;
    }

    // Menempatkan blok
    private void placeBlock(Block block, int startX, int startY, char symbol) {
        for (int[] cell : block.coordinates) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            board[x][y] = symbol;
        }
    }

    // Menghapus blok dari papan untuk backtracking
    private void removeBlock(Block block, int startX, int startY) {
        for (int[] cell : block.coordinates) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            board[x][y] = '.';
        }
    }

    // Validasi akhir papan: memastikan semua sel terisi sebelum dijadikan solusi
    private boolean isValidBoard() {
        int filledCells = 0;
        for (char[] row : board) {
            for (char cell : row) {
                if (cell != '.') filledCells++;
            }
        }
        return filledCells == (N * M);
    }
        public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(Utility.getColoredChar(cell) + " ");
            }
            System.out.println();
        }
    }

    public void saveSolution(String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (char[] row : board) {
            for (char cell : row) {
                bw.write(cell + " ");
            }
            bw.newLine();
        }
        bw.close();
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public char[][] getBoard() {
        return board;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}