package Challenge14;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge14Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge14.partA(Challenge14.process("src/Challenge14/test.txt"));
        assertEquals(165,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge14.partB(Challenge14.process("src/Challenge14/test2.txt"));
        assertEquals(208,ret);
    }
}
