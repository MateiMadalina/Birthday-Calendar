package com.codecool.birthdaycalendar.calendar;

import com.codecool.birthdaycalendar.calendar.BirthdayCalendar;
import com.codecool.birthdaycalendar.users.User;
import com.codecool.birthdaycalendar.users.UserAgeCalculator;
import com.codecool.birthdaycalendar.users.UserAgeDescriptor;
import com.codecool.birthdaycalendar.users.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirthdayCalendarImpl implements BirthdayCalendar {
    private final UserRepository userRepository;
    private final UserAgeCalculator userAgeCalculator;
    private final Map<LocalDate, List<UserAgeDescriptor>> dates;

    public BirthdayCalendarImpl(UserRepository userRepository, UserAgeCalculator userAgeCalculator, int year) {
        this.userRepository = userRepository;
        this.userAgeCalculator = userAgeCalculator;
        this.dates = new HashMap<>();

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        List<User> users = userRepository.getAll();

        fillCalendar(users, startDate, endDate);
    }

    private void fillCalendar(List<User> users, LocalDate startDate, LocalDate endDate) {
        // TODO: Implement fillCalendar method
    }

    private static List<LocalDate> eachDay(LocalDate from, LocalDate thru) {
        // TODO: Implement eachDay method
        return null;
    }

    @Override
    public Map<LocalDate, List<UserAgeDescriptor>> getBirthdaysForMonth(int month) {
        // TODO: Implement getBirthdaysForMonth method
        return null;
    }

    @Override
    public LocalDate getBirthdateForUser(int id) {
        // TODO: Implement getBirthdateForUser method
        return null;
    }

    @Override
    public UserAgeDescriptor getUserAgeAtDate(int id, LocalDate date) {
        // TODO: Implement getUserAgeAtDate method
        return null;
    }
}
