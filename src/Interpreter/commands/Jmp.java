package Interpreter.commands;

import Interpreter.State;

public class Jmp implements ICommand {
    private int offset;

    public Jmp(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(State state) {
        state.movePointer(offset);
    }
}
