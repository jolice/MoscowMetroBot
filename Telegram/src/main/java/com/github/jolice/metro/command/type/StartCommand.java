package com.github.jolice.metro.command.type;

import com.github.jolice.metro.command.Command;
import com.github.jolice.metro.command.arguments.Arguments;
import com.github.jolice.metro.SupportMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StartCommand implements Command {

    private SupportMenu supportMenu;

    @Autowired
    public StartCommand(SupportMenu supportMenu) {
        this.supportMenu = supportMenu;
    }

    @Override
    public String execute(Arguments args) {
        return supportMenu.support();
    }

    @Override
    public List<String> aliases() {
        return Arrays.asList("start", "help");
    }
}
