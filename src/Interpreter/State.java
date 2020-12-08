package Interpreter;

public class State {
    private int pointer;
    private long acc;

    public State() {
        this.acc = 0L;
        this.pointer = 0;
    }

    public int getPointer() {
        return pointer;
    }

    public long getAcc() {
        return acc;
    }

    public void movePointer(int i){
        pointer += i;
    }

    public void updateAcc(int i){
        acc += i;
    }
}
