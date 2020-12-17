package Challenge17;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge17Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge17.partA(Challenge17.process("src/Challenge17/test.txt"));
        assertEquals(112,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge17.partB(Challenge17.process2("src/Challenge17/test.txt"));
        assertEquals(848,ret);
    }
}
