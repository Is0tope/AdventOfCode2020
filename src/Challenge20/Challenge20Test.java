package Challenge20;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge20Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge20.partA(Challenge20.process("src/Challenge20/test.txt"));
        assertEquals(1951 * 3079 * 2971 * 1171,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge20.partB(Challenge20.process("src/Challenge20/test.txt"));
        assertEquals(0,ret);
    }
}
