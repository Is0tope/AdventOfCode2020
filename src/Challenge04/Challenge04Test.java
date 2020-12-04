package Challenge04;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge04Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge04.partA(Challenge04.process("src/Challenge04/test.txt"));
        assertEquals(2,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge04.partB(Challenge04.process("src/Challenge04/test2.txt"));
        assertEquals(4,ret);
    }
}
