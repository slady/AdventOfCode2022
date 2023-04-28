package Java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class A20 {
    public static void main(String...a) throws IOException {
        List<Long> list;

        try (Stream<String> lines = Files.lines(Paths.get("m"))) {
            list = lines.map(Long::parseLong).toList();
        }

        List<Long> mix = new ArrayList<>(list);
        int l = mix.size() - 1;
        for (Long i : list) {
            int x = mix.indexOf(i);
            mix.remove(i);
            x += i;
            x %= l;
            while (x <= 0) {
                x += l;
            }
            mix.add(x, i);
//            mix.forEach(System.out::println);
            System.out.println(i);
//            System.out.print('#');
        }
        l = mix.size();
        int p = mix.indexOf(0L);
        Long q = mix.get((1000 + p) % l);
        Long w = mix.get((2000 + p) % l);
        Long e = mix.get((3000 + p) % l);
        System.out.println(q + w + e);
    }
}
