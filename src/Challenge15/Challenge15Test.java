package Challenge15;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge15Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge15.partAB(Challenge15.process("src/Challenge15/test.txt"),2020);
        assertEquals(436,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge15.partAB(Challenge15.process("src/Challenge15/test.txt"),30000000);
        assertEquals(175594,ret);
    }
}
