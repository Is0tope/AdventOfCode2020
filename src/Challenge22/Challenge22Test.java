package Challenge22;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge22Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge22.partA(Challenge22.process("src/Challenge22/test.txt"));
        assertEquals(306,ret);
    }

    @org.junit.jupiter.api.Test
    void partBInfinite() {
        long ret = Challenge22.partB(Challenge22.process("src/Challenge22/test3.txt"));
        assertEquals(105,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge22.partB(Challenge22.process("src/Challenge22/test.txt"));
        assertEquals(291,ret);
    }
}
