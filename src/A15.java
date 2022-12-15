import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Math.abs;

public class A15 {
    public static void main(String...a) throws IOException {
        final List<Scanner> scanners = Files.lines(Paths.get("s-")).map(Scanner::new).toList();
        final boolean[] b = new boolean[85];
        scanners.forEach(s -> s.fill(b, 10, 5));
        int s = 0;
        for (boolean value : b) {
            if (value) s++;
        }
        System.out.println(s);
    }
}

class Scanner {
    final int sx, sy, bx, by, d;
    public Scanner(String s) {
        final int q = s.indexOf('=') + 1;
        final int w = s.indexOf(',', q);
        sx = Integer.parseInt(s.substring(q, w));
        final int e = s.indexOf('=', w) + 1;
        final int r = s.indexOf(':', e);
        sy = Integer.parseInt(s.substring(e, r));
        final int t = s.indexOf('=', r) + 1;
        final int y = s.indexOf(',', t);
        bx = Integer.parseInt(s.substring(t, y));
        final int u = s.indexOf('=', y) + 1;
        by = Integer.parseInt(s.substring(u));
        d = abs(sx - bx) + abs(sy - by);
    }

    public void fill(boolean[] b, int row, int offset) {
        System.out.println(this);
        final int t = d - abs(sy - row);
        if (t < 0) return;
        for (int i = offset + sx - t; i <= offset + sx + t; i++) {
            b[i] = true;
        }
        for (boolean value : b) {
            System.out.print(value ? '#' : '.');
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "[" + sx + "," + sy + "]:[" + bx + "," + by + "]=" + d;
    }
}
