package org.legend8883.domain.user;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.User;
import org.legend8883.model.UserStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShowAllUsersAction implements CommandAction {
    private final UserStorage userStorage;
    private final GeneralUtil generalUtil;

    public ShowAllUsersAction(UserStorage userStorage, GeneralUtil generalUtil) {
        this.userStorage = userStorage;
        this.generalUtil = generalUtil;
    }

    @Override
    public void execute() {
        generalUtil.checkIsUsersExist();

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
