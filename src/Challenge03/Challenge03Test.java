package Challenge03;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge03Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge03.partA(Challenge03.process("src/Challenge03/test.txt"));
        assertEquals(7,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge03.partB(Challenge03.process("src/Challenge03/test.txt"));
        assertEquals(336,ret);
    }
}
