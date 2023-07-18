package com.codecool.birthdaycalendar.statistics;

import com.codecool.birthdaycalendar.users.User;

import java.util.List;
import java.util.Map;


public interface UserAgeStatisticsService {
    List<User> getOldestUsers();
    List<User> getYoungestUsers();
    Map<Integer, List<User>> getUsersWithSameAgeInYears();
    Map<Integer, List<User>> getUsersWithSameAgeInDays();
}
