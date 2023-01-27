package console;

public class console {
    public static String setCursor(int row, int column) {
        return String.format("%c[%d;%df", 0x1B, row, column);
     }
}
