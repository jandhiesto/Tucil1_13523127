public class Utility {
    private static final String[] COL = {
        "\u001B[38;2;255;0;0m",    // A - Red
        "\u001B[38;2;0;0;255m",    // B - Blue
        "\u001B[38;2;0;205;102m",  // C - Green
        "\u001B[38;2;255;165;0m",  // D - Orange
        "\u001B[38;2;128;0;128m",  // E - Purple
        "\u001B[38;2;64;224;208m", // F - Turquoise
        "\u001B[38;2;220;20;60m",  // G - Crimson
        "\u001B[38;2;34;139;34m",  // H - Forest Green
        "\u001B[38;2;255;20;147m", // I - Pink
        "\u001B[38;2;0;0;128m",    // J - Navy
        "\u001B[38;2;139;69;19m",  // K - Brown
        "\u001B[38;2;50;205;50m",  // L - Lime
        "\u001B[38;2;255;0;255m",  // M - Magenta
        "\u001B[38;2;0;128;128m",  // N - Teal
        "\u001B[38;2;255;215;0m",  // O - Gold
        "\u001B[38;2;75;0;130m",   // P - Indigo
        "\u001B[38;2;128;0;0m",    // Q - Maroon
        "\u001B[38;2;135;206;235m",// R - Sky Blue
        "\u001B[38;2;255;140;0m",  // S - Dark Orange
        "\u001B[38;2;138;43;226m", // T - Violet
        "\u001B[38;2;128;128;0m",  // U - Olive
        "\u001B[38;2;255;127;80m", // V - Coral
        "\u001B[38;2;0;191;255m",  // W - Deep Sky Blue
        "\u001B[38;2;139;0;0m",    // X - Dark Red
        "\u001B[38;2;0;255;127m",  // Y - Spring Green
        "\u001B[38;2;148;0;211m"   // Z - Dark Violet
    };

    public static String getColoredChar(char c) {
        if (c == '.') return "\u001B[37m.\u001B[0m";
        return COL[c - 'A'] + c + "\u001B[0m";
    }
}