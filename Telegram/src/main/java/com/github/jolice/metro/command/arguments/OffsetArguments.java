package com.github.jolice.metro.command.arguments;

public class OffsetArguments implements Arguments {

    private String[] arguments;

    public OffsetArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String getArgument(int index) {
        if (index >= 0 && index < arguments.length - 1) {
            return arguments[index + 1];
        } else {
            return null;
        }
    }

    @Override
    public boolean empty() {
        return arguments.length <= 1;
    }

    @Override
    public int length() {
        return arguments.length - 1;
    }
}
