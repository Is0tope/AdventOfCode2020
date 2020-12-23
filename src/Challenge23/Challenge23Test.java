package Challenge23;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge23Test {
    @org.junit.jupiter.api.Test
    void partA() {
        String ret = Challenge23.partA("389125467");
        assertEquals("67384529",ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge23.partB("389125467");
        assertEquals(149245887792L,ret);
    }
}
