package org.legend8883.domain.util;

import org.legend8883.model.User;
import org.legend8883.model.UserStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class GeneralUtil {
    private final UserStorage userStorage;

    public GeneralUtil(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void checkIsInputEmpty(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input must not be empty");
        }
    }

    public void checkIsUsersExist() {
        List<User> users = userStorage.getUsers();
        if (users.isEmpty()) {
            throw new NoSuchElementException("Users not found. Create new user first.");
        }
    }

    public Integer parseId(String userId) {
        try {
            return Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Input must be an integer");
        }
    }
}
