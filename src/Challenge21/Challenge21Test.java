package Challenge21;

import Challenge21.Challenge21;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge21Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge21.partA(Challenge21.process("src/Challenge21/test.txt"));
        assertEquals(5,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        Challenge21.partA(Challenge21.process("src/Challenge21/test.txt"));
        String ret = Challenge21.partB(Challenge21.process("src/Challenge21/test.txt"));
        assertEquals("mxmxvkd,sqjhc,fvjkl",ret);
    }
}
