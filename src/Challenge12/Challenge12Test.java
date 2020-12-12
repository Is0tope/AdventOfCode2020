package Challenge12;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge12Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge12.partA(Challenge12.process("src/Challenge12/test.txt"));
        assertEquals(25,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge12.partB(Challenge12.process("src/Challenge12/test.txt"));
        assertEquals(286,ret);
    }
}
