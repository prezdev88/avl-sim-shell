package cl.prezdev.command;

import lombok.RequiredArgsConstructor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import cl.prezdev.model.response.AddAvlResponse;
import cl.prezdev.model.response.RemoveAllResponse;
import cl.prezdev.model.response.StatResponse;
import cl.prezdev.port.AvlClientPort;

@Component
@ShellComponent
@RequiredArgsConstructor
public class AvlCommands {

    private final AvlClientPort avlClientPort;

    @ShellMethod(key = "avl add", value = "Adds N simulated devices of the given type (teltonika, queclink, etc.)")
    public String add(
        @ShellOption(help = "Device type") String type,
        @ShellOption(help = "Number of devices") int count
    ) {
        AddAvlResponse addAvlResponse = avlClientPort.addAvls(type, count);
        return "[OK] Added " + addAvlResponse.getCount() + " devices of type " + addAvlResponse.getType().toUpperCase();
    }

    @ShellMethod(key = "avl list", value = "Lists all active simulated devices.")
    public String list() {
        return "list";
    }

    @ShellMethod(key = "avl stat", value = "Shows statistics of the current simulation.")
    public String stat() {
        StatResponse statResponse = avlClientPort.getStats();
        return "Active AVL devices: " + statResponse.getAvl();
    }

    @ShellMethod(key = "avl remove all", value = "Remove all devices")
    public String removeAll() {
        RemoveAllResponse response = avlClientPort.removeAll();
        return "[OK] Removed " + response.getRemoved() + " devices.";
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
