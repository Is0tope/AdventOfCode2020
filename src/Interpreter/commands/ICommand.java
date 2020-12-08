package Interpreter.commands;


import Interpreter.State;

public interface ICommand {

    public void execute(State state);

}
