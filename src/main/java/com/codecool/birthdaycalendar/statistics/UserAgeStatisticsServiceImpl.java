package com.codecool.birthdaycalendar.statistics;

import com.codecool.birthdaycalendar.users.User;
import com.codecool.birthdaycalendar.users.UserAgeCalculator;
import com.codecool.birthdaycalendar.users.UserAgeDescriptor;
import com.codecool.birthdaycalendar.users.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAgeStatisticsServiceImpl implements UserAgeStatisticsService{
    private final UserRepository userRepository;
    private final UserAgeCalculator userAgeCalculator;

    public UserAgeStatisticsServiceImpl(UserRepository userRepository, UserAgeCalculator userAgeCalculator) {
        this.userRepository = userRepository;
        this.userAgeCalculator = userAgeCalculator;
    }

    @Override
    public List<User> getOldestUsers() {
        List<User> allUsers = userRepository.getAll();
        List<UserAgeDescriptor> users = new ArrayList<>();

        for (User user : allUsers) {
            UserAgeDescriptor userAgeDescriptor = userAgeCalculator.createUserAgeDescriptor(user, LocalDate.now());
            users.add(userAgeDescriptor);
        }

        int maxAge = 0;
        for (UserAgeDescriptor userAgeDescriptor : users) {
            int age = userAgeDescriptor.ageInYears();
            if (age > maxAge) {
                maxAge = age;
            }
        }

        List<User> oldestUsers = new ArrayList<>();
        for (UserAgeDescriptor userAgeDescriptor : users) {
            if (userAgeDescriptor.ageInYears() == maxAge) {
                oldestUsers.add(userAgeDescriptor.user());
            }
        }

        return oldestUsers;
    }

    @Override
    public List<User> getYoungestUsers() {
        List<User> allUsers = userRepository.getAll();
        List<UserAgeDescriptor> users = new ArrayList<>();

        for (User user : allUsers) {
            UserAgeDescriptor userAgeDescriptor = userAgeCalculator.createUserAgeDescriptor(user, LocalDate.now());
            users.add(userAgeDescriptor);
        }

        int minAge = 100;
        for (UserAgeDescriptor userAgeDescriptor : users) {
            int age = userAgeDescriptor.ageInYears();
            if (age < minAge) {
                minAge = age;
            }
        }

        List<User> youngestUsers = new ArrayList<>();
        for (UserAgeDescriptor userAgeDescriptor : users) {
            if (userAgeDescriptor.ageInYears() == minAge) {
                youngestUsers.add(userAgeDescriptor.user());
            }
        }

        return youngestUsers;
    }

    @Override
    public Map<Integer, List<User>> getUsersWithSameAgeInYears() {
        List<User> allUsers = userRepository.getAll();
        List<UserAgeDescriptor> users = new ArrayList<>();

        for (User user : allUsers) {
            UserAgeDescriptor userAgeDescriptor = userAgeCalculator.createUserAgeDescriptor(user, LocalDate.now());
            users.add(userAgeDescriptor);
        }

        Map<Integer, List<User>> usersWithSameAgeMap = new HashMap<>();

        for (UserAgeDescriptor userAgeDescriptor : users) {
            int ageInYears = userAgeDescriptor.ageInYears();
            List<User> usersWithSameAge = usersWithSameAgeMap.getOrDefault(ageInYears, new ArrayList<>());
            usersWithSameAge.add(userAgeDescriptor.user());
            usersWithSameAgeMap.put(ageInYears, usersWithSameAge);
        }

        return usersWithSameAgeMap;
    }

    @Override
    public Map<Integer, List<User>> getUsersWithSameAgeInDays() {
        List<User> allUsers = userRepository.getAll();
        List<UserAgeDescriptor> users = new ArrayList<>();

        for (User user : allUsers) {
            UserAgeDescriptor userAgeDescriptor = userAgeCalculator.createUserAgeDescriptor(user, LocalDate.now());
            users.add(userAgeDescriptor);
        }

        Map<Integer, List<User>> usersWithSameAgeMap = new HashMap<>();

        for (UserAgeDescriptor userAgeDescriptor : users) {
            int ageInDays = userAgeDescriptor.ageInDays();
            List<User> usersWithSameAge = usersWithSameAgeMap.getOrDefault(ageInDays, new ArrayList<>());
            usersWithSameAge.add(userAgeDescriptor.user());
            usersWithSameAgeMap.put(ageInDays, usersWithSameAge);
        }

        return usersWithSameAgeMap;
    }
}
