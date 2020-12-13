package Challenge13;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge13Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge13.partA(Challenge13.process("src/Challenge13/test.txt"));
        assertEquals(295,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge13.partB(Challenge13.process("src/Challenge13/test.txt"));
        assertEquals(1068781,ret);
    }
}
