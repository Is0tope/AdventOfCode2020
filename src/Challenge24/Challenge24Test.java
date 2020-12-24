package Challenge24;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge24Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge24.partA(Challenge24.process("src/Challenge24/test.txt"));
        assertEquals(10,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge24.partB(Challenge24.process("src/Challenge24/test.txt"));
        assertEquals(2208,ret);
    }
}
