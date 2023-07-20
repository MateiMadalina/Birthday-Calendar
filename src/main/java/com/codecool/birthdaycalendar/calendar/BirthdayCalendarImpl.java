package com.codecool.birthdaycalendar.calendar;

import com.codecool.birthdaycalendar.calendar.BirthdayCalendar;
import com.codecool.birthdaycalendar.users.User;
import com.codecool.birthdaycalendar.users.UserAgeCalculator;
import com.codecool.birthdaycalendar.users.UserAgeDescriptor;
import com.codecool.birthdaycalendar.users.UserRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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
        List<LocalDate> days = eachDay(startDate, endDate);
        System.out.println("In our calendar exist: " + users.size() + " users");
        for (int i = 0; i < days.size(); i++) {
            LocalDate day = days.get(i);
            List<UserAgeDescriptor> usersForDay = new ArrayList<>();
            for (User user : users) {
                if (user.birthDate().getMonth().equals(day.getMonth()) &&
                        user.birthDate().getDayOfMonth() == day.getDayOfMonth()) {
                    usersForDay.add(userAgeCalculator.createUserAgeDescriptor(user, LocalDate.now()));
                }
            }
            if(usersForDay.size()>0)  dates.put(day, usersForDay);
        }
        System.out.println("The birthday calendar is: " + dates);
    }

    private static List<LocalDate> eachDay (LocalDate from, LocalDate thru) {
        List<LocalDate> d = new ArrayList<>();
        LocalDate current = from;

        while (!current.isAfter(thru)) {
            d.add(current);
            current = current.plusDays(1);
        }

        return d;
    }

    @Override
    public Map<LocalDate, List<UserAgeDescriptor>> getBirthdaysForMonth(int month) {
        Map<LocalDate, List<UserAgeDescriptor>> birthdaysForMonth = new HashMap<>();

        for (Map.Entry<LocalDate, List<UserAgeDescriptor>> entry : dates.entrySet()) {
            LocalDate date = entry.getKey();
            List<UserAgeDescriptor> usersForDay = entry.getValue();

            if (date.getMonthValue() == month) {
                birthdaysForMonth.put(date, usersForDay);
            }
        }

        return birthdaysForMonth;
    }

    @Override
    public LocalDate getBirthdateForUser(int id) {
        return userRepository.getById(id).birthDate();
    }

    @Override
    public UserAgeDescriptor getUserAgeAtDate(int id, LocalDate date) {
        return userAgeCalculator.createUserAgeDescriptor(userRepository.getById(id),date);
    }
}
