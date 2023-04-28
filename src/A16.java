import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A16 {
    public static void main(String...a) throws IOException {
        final Map<String, Valve> valveMap;

        try (Stream<String> lines = Files.lines(Paths.get("s-"))) {
            valveMap = lines.map(Valve::new).collect(Collectors.toMap(v -> v.name, v -> v));
        }
    }
}

class Valve {
    final String name;
    List<String> lead;
    final int rate;

    public Valve(String s) {
        name = s.substring(6, 8);
        rate = Integer.parseInt(s.substring(s.indexOf('=') + 1, s.indexOf(';')));
        String ways = s.substring(s.indexOf("valve") + 6);
        if (ways.charAt(0) == ' ')
            ways = ways.substring(1);
        lead = Arrays.stream(ways.split(", ")).toList();
        System.out.println(name);
        System.out.println(rate);
        System.out.println(lead);
    }
}
