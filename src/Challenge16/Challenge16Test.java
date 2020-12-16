package Challenge16;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge16Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge16.partA(Challenge16.process("src/Challenge16/test.txt"));
        assertEquals(71,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge16.partB(Challenge16.process("src/Challenge16/test2.txt"),"seat");
        assertEquals(13,ret);
    }
}
