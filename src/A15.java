import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static java.lang.Math.abs;

public class A15 {
    public static void main(String...a) throws IOException {
        final List<Scanner> scanners = Files.lines(Paths.get("s")).map(Scanner::new).toList();
        final List<Interval> intervals = scanners.stream().map(s -> s.createInterval(2000000))
                .filter(Objects::nonNull).sorted(Comparator.comparingInt(i -> i.s)).toList();
        System.out.println(mergeIntervals(intervals).stream().mapToInt(Interval::getSize).sum() - 1);

        for (int y = 0; y <= 4000000; y++) {
            final int row = y;
            final List<Interval> intervalList = mergeIntervals(scanners.stream().map(s -> s.createInterval(row))
                    .filter(Objects::nonNull).sorted(Comparator.comparingInt(i -> i.s)).toList());
            if (intervalList.size() != 1) {
                System.out.println((intervalList.get(0).e + 1L) * 4000000 + y);
            }
        }
    }

    public static List<Interval> mergeIntervals(List<Interval> arr) {
        final Stack<Interval> stack=new Stack<>();
        stack.push(arr.get(0));

        for (int i = 1 ; i < arr.size(); i++) {
            final Interval current = arr.get(i);
            final Interval top = stack.peek();

            if (top.e < current.s)
                stack.push(current);
            else if (top.e < current.e) {
                top.e = current.e;
                stack.pop();
                stack.push(top);
            }
        }

        return stack;
    }

}

class Interval {
    int s, e;

    public Interval(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public int getSize() {
        return 1 + e - s;
    }
}

class Scanner {
    final int sx, sy, r;
    public Scanner(String s) {
        final int q = s.indexOf('=') + 1;
        final int w = s.indexOf(',', q);
        sx = Integer.parseInt(s.substring(q, w));
        final int e = s.indexOf('=', w) + 1;
        final int h = s.indexOf(':', e);
        sy = Integer.parseInt(s.substring(e, h));
        final int t = s.indexOf('=', h) + 1;
        final int y = s.indexOf(',', t);
        final int bx = Integer.parseInt(s.substring(t, y));
        final int u = s.indexOf('=', y) + 1;
        final int by = Integer.parseInt(s.substring(u));
        r = abs(sx - bx) + abs(sy - by);
    }

    public Interval createInterval(int row) {
        final int t = r - abs(sy - row);
        if (t < 0) return null;
        return new Interval(sx - t, sx + t);
    }

}
