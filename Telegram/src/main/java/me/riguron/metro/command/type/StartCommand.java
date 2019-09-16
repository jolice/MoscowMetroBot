package me.riguron.metro.command.type;

import me.riguron.metro.SupportMenu;
import me.riguron.metro.command.arguments.Arguments;
import me.riguron.metro.command.Command;
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
