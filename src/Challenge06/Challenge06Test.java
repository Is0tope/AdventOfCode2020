package Challenge06;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge06Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge06.partA(Challenge06.process("src/Challenge06/test.txt"));
        assertEquals(11,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge06.partB(Challenge06.process("src/Challenge06/test.txt"));
        assertEquals(6,ret);
    }
}
