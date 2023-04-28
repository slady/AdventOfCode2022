package going;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class A11b {
    public static void main(String...a) throws IOException {
        Files.readAllLines(Paths.get("a-.txt"));
        System.out.println();
    }
}
