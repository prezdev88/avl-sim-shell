package cl.prezdev.command;

import lombok.RequiredArgsConstructor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

@Component
@ShellComponent
@RequiredArgsConstructor
public class AvlCommands {

    @ShellMethod(key = "avl add", value = "Adds N simulated devices of the given type (teltonika, queclink, etc.)")
    public String add(
        @ShellOption(help = "Device type") String type,
        @ShellOption(help = "Number of devices") int count
    ) {
        return "[OK] Added " + count + " devices of type " + type.toUpperCase();
    }

    @ShellMethod(key = "avl list", value = "Lists all active simulated devices.")
    public String list() {
        return "list";
    }

    @ShellMethod(key = "avl stat", value = "Shows statistics of the current simulation.")
    public String stat() {
        return "stat";
    }

    @ShellMethod(key = "avl remove all", value = "Remove all devices")
    public String removeAll() {
        return "remove";
    }

    @ShellMethod(key = "avl start", value = "Starts all simulated AVL devices.")
    public String startAll() {
        return "[OK] All devices started.";
    }

    @ShellMethod(key = "avl stop", value = "Stops all simulated AVL devices.")
    public String stopAll() {
        return "[OK] All devices stopped.";
    }
}
