package com.codecool.birthdaycalendar.users;

import com.codecool.birthdaycalendar.users.User;

import java.util.List;

public interface UserRepository {
    User getById(int id);
    List<User> getAll();
}
