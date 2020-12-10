package Challenge09;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge09Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge09.partA(Challenge09.process("src/Challenge09/test.txt"),5);
        assertEquals(127,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge09.partB(Challenge09.process("src/Challenge09/test.txt"),127);
        assertEquals(62,ret);
    }
}
