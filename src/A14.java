import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class A14 {
    static boolean[][] b = new boolean[1000][200];
    static int max = Integer.MIN_VALUE;

    public static void main(String...a) throws IOException {
        Files.lines(Paths.get("a-")).forEach(A14::draw);
        int s = 0;

        paint();

        while (fallSand()) {
            s++;
        }

        System.out.println(s);

        max += 2;
        for (int i = 0; i < b.length; i++) {
            b[i][max] = true;
        }

        while (fallSand()) {
            s++;
        }

        System.out.println(s);
    }

    private static void paint() {
        for (int y = 0; y < 12; y++) {
            for (int x = 494; x < 505; x++) {
                System.out.print(b[x][y] ? "#" : '.');
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean fallSand() {
        int x = 500;

        for (int i = 0; i < b[0].length; i++) {
            if (!b[x][i])
                continue;

            x--;
            if (!b[x][i])
                continue;

            x += 2;
            if (!b[x][i])
                continue;

            if (i > 0) {
                x--;
                i--;
                b[x][i] = true;
            }
            return false;
        }

        return false;
    }

    private static void draw(String line) {
        final List<Coord> coords = Arrays.stream(line.split(" -> ")).map(Coord::new).toList();
        for (int i = 1; i < coords.size(); i++) {
            draw(coords.get(i - 1), coords.get(i));
        }
    }

    private static void draw(Coord s, Coord e) {
        final int y;

        if (s.x == e.x) {
            final int f = Integer.max(s.y, e.y);

            for (int i = Integer.min(s.y, e.y); i <= f; i++) {
                b[s.x][i] = true;
            }

            y = f;
        } else {
            final int f = Integer.max(s.x, e.x);

            for (int i = Integer.min(s.x, e.x); i <= f; i++) {
                b[i][s.y] = true;
            }

            y = s.y;
        }

        if (y > max)
            max = y;
     }
}

class Coord {
    final int x, y;

    public Coord(String c) {
        int i = c.indexOf(',');
        this.x = Integer.parseInt(c.substring(0, i));
        this.y = Integer.parseInt(c.substring(i + 1));
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + ']';
    }
}
