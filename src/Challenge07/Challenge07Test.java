package Challenge07;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge07Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge07.partA(Challenge07.process("src/Challenge07/test.txt"));
        assertEquals(4,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge07.partB(Challenge07.process("src/Challenge07/test.txt"));
        assertEquals(32,ret);
    }
}
