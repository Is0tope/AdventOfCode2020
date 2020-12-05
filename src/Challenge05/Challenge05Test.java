package Challenge05;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge05Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge05.partA(Challenge05.process("src/Challenge05/test.txt"));
        assertEquals(820,ret);
    }

    // No test for part B
}
