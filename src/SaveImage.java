import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SaveImage {
    private static final int CELL_SIZE = 50; // Ukuran tiap sel
    private static final int PADDING = 10;   // Padding sekitar board

    public static void savePuzzleImage(char[][] board, String filename) {
        int rows = board.length;
        int cols = board[0].length;
        int width = cols * CELL_SIZE + 2 * PADDING;
        int height = rows * CELL_SIZE + 2 * PADDING;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Set warna background putih
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Gambar grid dan blok
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char symbol = board[r][c];
                g2d.setColor(getColorForSymbol(symbol));
                g2d.fillRect(PADDING + c * CELL_SIZE, PADDING + r * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                // Gambar border
                g2d.setColor(Color.BLACK);
                g2d.drawRect(PADDING + c * CELL_SIZE, PADDING + r * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                // Tulis huruf blok
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.drawString(String.valueOf(symbol), PADDING + c * CELL_SIZE + 20, PADDING + r * CELL_SIZE + 30);
            }
        }

        g2d.dispose();

        // Simpan sebagai file PNG
        try {
            ImageIO.write(image, "png", new File(filename));
            System.out.println("Gambar solusi disimpan sebagai " + filename);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan gambar: " + e.getMessage());
        }
    }

    // Fungsi untuk memilih warna blok berdasarkan huruf
    private static Color getColorForSymbol(char symbol) {
        switch (symbol) {
            case 'A': return new Color(255, 0, 0);      
            case 'B': return new Color(0, 0, 255);      
            case 'C': return new Color(0, 205, 102);    
            case 'D': return new Color(255, 165, 0);    
            case 'E': return new Color(128, 0, 128);    
            case 'F': return new Color(64, 224, 208);   
            case 'G': return new Color(220, 20, 60);    
            case 'H': return new Color(34, 139, 34);    
            case 'I': return new Color(255, 20, 147);   
            case 'J': return new Color(0, 0, 128);      
            case 'K': return new Color(139, 69, 19);    
            case 'L': return new Color(50, 205, 50);    
            case 'M': return new Color(255, 0, 255);    
            case 'N': return new Color(0, 128, 128);    
            case 'O': return new Color(255, 215, 0);    
            case 'P': return new Color(75, 0, 130);     
            case 'Q': return new Color(128, 0, 0);      
            case 'R': return new Color(135, 206, 235);  
            case 'S': return new Color(255, 140, 0);    
            case 'T': return new Color(138, 43, 226);   
            case 'U': return new Color(128, 128, 0);    
            case 'V': return new Color(255, 127, 80);   
            case 'W': return new Color(0, 191, 255);    
            case 'X': return new Color(139, 0, 0);      
            case 'Y': return new Color(0, 255, 127);    
            case 'Z': return new Color(148, 0, 211);    
            default: return Color.GRAY;
        }
    }
}