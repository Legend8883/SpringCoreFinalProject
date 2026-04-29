package org.legend8883.console;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConsoleListener {
    private final Map<CommandType, CommandAction> commandActions;
    private final Scanner scanner;

    public ConsoleListener(List<CommandAction> actions) {
        commandActions = new HashMap<>();
        actions.forEach(action ->
                commandActions.put(action.getCommandType(), action)
        );
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printCommands();

            String input = scanner.nextLine();

            if (input.equals(CommandType.EXIT.name())) {
                break;
            }

            try {
                CommandType commandType = getCommandType(input);
                CommandAction commandAction = commandActions.get(commandType);

                if (commandAction == null) {
                    continue;
                }

                commandAction.execute();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printCommands() {
        System.out.println("Available commands: " + Arrays.toString(CommandType.values()));
    }

    private CommandType getCommandType(String input) {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid command type: " + input);
        }
        return null;
    }

    @PostConstruct
    public void init() {
        System.out.println("MiniBank started. Type EXIT to stop.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("MiniBank stopped");
    }
}
