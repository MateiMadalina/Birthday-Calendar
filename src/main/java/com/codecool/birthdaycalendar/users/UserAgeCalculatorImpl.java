package com.codecool.birthdaycalendar.users;

import java.time.LocalDate;

public class UserAgeCalculatorImpl implements UserAgeCalculator {
    public UserAgeDescriptor createUserAgeDescriptor(User user, LocalDate currentDate) {
        return createDescriptor(user, currentDate);
    }

    private static UserAgeDescriptor createDescriptor(User user, LocalDate currentDate) {
        return new UserAgeDescriptor(user,
                calculateAgeInYears(user.birthDate(), currentDate),
                calculateAgeInDays(user.birthDate(), currentDate));
    }

    private static int calculateAgeInYears(LocalDate birthDate, LocalDate currentDate) {
        int diffInYears = currentDate.getYear() - birthDate.getYear();
        return currentDate.getMonthValue() > birthDate.getMonthValue() ? diffInYears : diffInYears - 1;
    }

    private static int calculateAgeInDays(LocalDate birthDate, LocalDate currentDate) {
        return (int) (currentDate.toEpochDay() - birthDate.toEpochDay());
    }
}
