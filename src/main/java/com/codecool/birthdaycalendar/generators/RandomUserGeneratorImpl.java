package com.codecool.birthdaycalendar.generators;

import com.codecool.birthdaycalendar.users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RandomUserGeneratorImpl implements RandomUserGenerator {
    private final RandomDateGenerator randomDateGenerator;
    private final int count;

    public RandomUserGeneratorImpl(RandomDateGenerator randomDateGenerator, int count) {
        this.randomDateGenerator = randomDateGenerator;
        this.count = count;
    }

    public List<User> generate() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            LocalDate birthDate = randomDateGenerator.generate();
            users.add(new User(i, "user" + i, birthDate));
        }
        return users;
    }
}
