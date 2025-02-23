public class Utility {
    public static String getColoredChar(char c) {
        if (c == '.') return "\u001B[37m.\u001B[0m";
        int color = (31 + (c - 'A')) % 5;
        return "\u001B[" + color + "m" + c + "\u001B[0m";
    }
}