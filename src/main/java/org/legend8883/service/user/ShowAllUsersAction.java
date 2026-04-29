package org.legend8883.service.user;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllUsersAction implements CommandAction {
    private final UserStorage userStorage;

    public ShowAllUsersAction(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void execute() {
        System.out.println("All Users");
        List<User> users = userStorage.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.SHOW_ALL_USERS;
    }
}
