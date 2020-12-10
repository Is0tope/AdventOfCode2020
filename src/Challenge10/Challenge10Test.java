package Challenge10;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge10Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge10.partA(Challenge10.process("src/Challenge10/test.txt"));
        assertEquals(220,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge10.partB(Challenge10.process("src/Challenge10/test.txt"));
        assertEquals(19208,ret);
    }

    @org.junit.jupiter.api.Test
    void partB2() {
        long ret = Challenge10.partB(Challenge10.process("src/Challenge10/test2.txt"));
        assertEquals(8,ret);
    }
}
