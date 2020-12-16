package Challenge01;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge01Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge01.partA(Challenge01.process("src/Challenge01/test.txt"));
        assertEquals(514579,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge01.partB(Challenge01.process("src/Challenge01/test.txt"));
        assertEquals(241861950,ret);
    }
}
