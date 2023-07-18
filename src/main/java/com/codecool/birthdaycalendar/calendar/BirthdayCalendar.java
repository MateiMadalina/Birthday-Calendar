package com.codecool.birthdaycalendar.calendar;

import com.codecool.birthdaycalendar.users.UserAgeDescriptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BirthdayCalendar {
    Map<LocalDate, List<UserAgeDescriptor>> getBirthdaysForMonth(int month);
    LocalDate getBirthdateForUser(int id);
    UserAgeDescriptor getUserAgeAtDate(int id, LocalDate date);
}
