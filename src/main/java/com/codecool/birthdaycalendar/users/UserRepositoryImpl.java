package com.codecool.birthdaycalendar.users;

import com.codecool.birthdaycalendar.generators.RandomUserGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    private final Map<Integer, User> users;

    public UserRepositoryImpl(RandomUserGenerator randomUserGenerator) {
        users = randomUserGenerator.generate().stream()
                .collect(Collectors.toMap(User::id, u -> u));
    }

    public User getById(int id) {
        return users.get(id);
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }
}
