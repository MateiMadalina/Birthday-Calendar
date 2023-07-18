package com.codecool.birthdaycalendar.users;

import java.time.LocalDate;

public interface UserAgeCalculator {
    UserAgeDescriptor createUserAgeDescriptor(User user, LocalDate currentDate);
}
