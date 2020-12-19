package Challenge19;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge19Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge19.partA(Challenge19.process("src/Challenge19/test.txt"));
        assertEquals(2,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge19.partB(Challenge19.process("src/Challenge19/test2.txt"));
        assertEquals(12,ret);
    }
}
