package Challenge25;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge25Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge25.partA(Challenge25.process("src/Challenge25/test.txt"));
        assertEquals(14897079,ret);
    }
}
