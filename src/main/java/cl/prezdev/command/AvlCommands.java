package cl.prezdev.command;

import lombok.RequiredArgsConstructor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import cl.prezdev.http.AvlClient;
import cl.prezdev.model.response.AddAvlResponse;

@Component
@ShellComponent
@RequiredArgsConstructor
public class AvlCommands {

    private final AvlClient avlClient;

    @ShellMethod(key = "avl add", value = "Adds N simulated devices of the given type (teltonika, queclink, etc.)")
    public String add(
        @ShellOption(help = "Device type") String type,
        @ShellOption(help = "Number of devices") int count
    ) {
        AddAvlResponse addAvlResponse = avlClient.addAvls(type, count);
        return "[OK] Added " + addAvlResponse.getCount() + " devices of type " + addAvlResponse.getType().toUpperCase();
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
