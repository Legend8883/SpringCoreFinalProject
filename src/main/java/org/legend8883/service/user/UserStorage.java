package org.legend8883.service.user;

import org.legend8883.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserStorage {
    private final Map<Integer, User> users;

    public UserStorage() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
        } else {
            System.out.println("User with id " + id + " not found");
        }
    }
}
