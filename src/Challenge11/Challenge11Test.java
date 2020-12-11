package Challenge11;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge11Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge11.partA(Challenge11.process("src/Challenge11/test.txt"));
        assertEquals(37,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge11.partB(Challenge11.process("src/Challenge11/test.txt"));
        assertEquals(26,ret);
    }
}
