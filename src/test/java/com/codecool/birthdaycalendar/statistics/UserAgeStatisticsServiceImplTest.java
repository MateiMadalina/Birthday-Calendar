package com.codecool.birthdaycalendar.statistics;

import com.codecool.birthdaycalendar.users.User;
import com.codecool.birthdaycalendar.users.UserAgeCalculator;
import com.codecool.birthdaycalendar.users.UserAgeCalculatorImpl;
import com.codecool.birthdaycalendar.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserAgeStatisticsServiceImplTest {

    UserRepository mockedUser =  Mockito.mock(UserRepository.class);
    List<User> users = new ArrayList<>();
    List<User> returnedUser = new ArrayList<>();
    UserAgeCalculator userAgeCalculator = new UserAgeCalculatorImpl();
    private UserAgeStatisticsService statisticsService;


    @Test
    void getOldestUsers() {
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        statisticsService = new UserAgeStatisticsServiceImpl(mockedUser,userAgeCalculator);
        returnedUser.add(users.get(0));
        assertEquals(returnedUser,statisticsService.getOldestUsers());

    }

    @Test
    void getYoungestUsers() {
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        statisticsService = new UserAgeStatisticsServiceImpl(mockedUser,userAgeCalculator);
        returnedUser.add(users.get(1));
        assertEquals(returnedUser,statisticsService.getYoungestUsers());
    }

    @Test
    void getUsersWithSameAgeInYears() {
    }

    @Test
    void getUsersWithSameAgeInDays() {
    }
}