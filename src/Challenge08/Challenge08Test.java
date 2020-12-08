package Challenge08;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge08Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge08.partA(Challenge08.process("src/Challenge08/test.txt"));
        assertEquals(5,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge08.partB(Challenge08.process("src/Challenge08/test.txt"));
        assertEquals(8,ret);
    }
}
