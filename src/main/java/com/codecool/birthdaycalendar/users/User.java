package com.codecool.birthdaycalendar.users;

import java.time.LocalDate;

public record User(int id, String userName, LocalDate birthDate) {
}
