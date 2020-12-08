package Interpreter.commands;

import Interpreter.State;

public class Acc implements ICommand {
    private int num;

    public Acc(int num) {
        this.num = num;
    }

    @Override
    public void execute(State state) {
        state.updateAcc(num);
        state.movePointer(1);
    }
}
