package Challenge02;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge02Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge02.partA(Challenge02.process("src/Challenge02/test.txt"));
        assertEquals(2,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge02.partB(Challenge02.process("src/Challenge02/test.txt"));
        assertEquals(1,ret);
    }
}
