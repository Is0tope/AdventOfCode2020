package Challenge20;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge20Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge20.partA(Challenge20.process("src/Challenge20/test.txt"));
        assertEquals(20899048083289L,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        Challenge20.partA(Challenge20.process("src/Challenge20/test.txt"));
        long ret = Challenge20.partB(Challenge20.process("src/Challenge20/test.txt"));
        assertEquals(273,ret);
    }
}
