package Interpreter.commands;

import Interpreter.State;

public class Nop implements ICommand {

    public Nop() {
    }

    @Override
    public void execute(State state) {
        state.movePointer(1);
    }
}
