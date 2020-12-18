package Challenge18;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Challenge18Test {
    @org.junit.jupiter.api.Test
    void partA() {
        long ret = Challenge18.partA(Challenge18.process("src/Challenge18/test.txt"));
        assertEquals(26+437+12240+13632,ret);
    }

    @org.junit.jupiter.api.Test
    void partB() {
        long ret = Challenge18.partB(Challenge18.process("src/Challenge18/test.txt"));
        assertEquals(46+1445+669060+23340,ret);
    }
}
