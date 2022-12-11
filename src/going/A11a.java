package going;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Monkey {
    final List<Integer> items;
    final char operationSymbol;
    final Integer operationNumber;
    final int testDivisibleNumber, trueMonkey, falseMonkey;

    Monkey(List<Integer> items, char operationSymbol, Integer operationNumber, int testDivisibleNumber, int trueMonkey, int falseMonkey) {
        this.items = new ArrayList<>(items);
        this.operationSymbol = operationSymbol;
        this.operationNumber = operationNumber;
        this.testDivisibleNumber = testDivisibleNumber;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }
}

public class A11a {
    public static void main(String...a) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get("a-.txt"));
        final int monkeyCount = lines.size() / 7 + 1;
        final List<Monkey> monkeys = new ArrayList<>();

        for (int m = 0; m < monkeyCount; m++) {
            final int mp = m * 7;
            final String operation = lines.get(mp + 2);
            Integer operationNumber;
            try {
                operationNumber = Integer.parseInt(operation.substring(25));
            } catch (NumberFormatException e) {
                operationNumber = null;
            }

            monkeys.add(new Monkey(
                    Arrays.stream(lines.get(mp + 1).substring(18).split(", ")).mapToInt(Integer::parseInt).boxed().toList(),
                    operation.charAt(23),
                    operationNumber,
                    Integer.parseInt(lines.get(mp + 3).substring(21)),
                    Integer.parseInt(lines.get(mp + 4).substring(29)),
                    Integer.parseInt(lines.get(mp + 5).substring(30))));
        }

        for (int r = 0; r < 20; r++) {
            for (Monkey monkey : monkeys) {
                for (int item : monkey.items) {
                    ;
                }
                monkey.items.clear();
            }
        }

        System.out.println(monkeyCount);
    }
}
