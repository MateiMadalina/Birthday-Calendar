package com.codecool.birthdaycalendar.statistics;

import com.codecool.birthdaycalendar.users.User;
import com.codecool.birthdaycalendar.users.UserAgeCalculator;
import com.codecool.birthdaycalendar.users.UserAgeCalculatorImpl;
import com.codecool.birthdaycalendar.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.*;

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
        List<User> users2 = new ArrayList<>();
        List<User> users1 = new ArrayList<>();
        users1.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users2.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        users1.add(new User(3,"user3",LocalDate.parse("1999-03-21")));
        users2.add(new User(4,"user4", LocalDate.parse("2008-01-09")));
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        users.add(new User(3,"user3",LocalDate.parse("1999-03-21")));
        users.add(new User(4,"user4", LocalDate.parse("2008-01-09")));

        Mockito.when(mockedUser.getAll()).thenReturn(users);
        statisticsService = new UserAgeStatisticsServiceImpl(mockedUser,userAgeCalculator);
        Map<Integer, List<User>> expected = new HashMap<>();
        expected.put(24,users1);
        expected.put(15,users2);
        assertEquals(expected,statisticsService.getUsersWithSameAgeInYears());

    }

    @Test
    void getUsersWithSameAgeInDays() {
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));


        Mockito.when(mockedUser.getAll()).thenReturn(users);
        statisticsService = new UserAgeStatisticsServiceImpl(mockedUser,userAgeCalculator);
        Map<Integer, List<User>> expected = new HashMap<>();
        expected.put(8828, Collections.singletonList(new User(1, "user1", LocalDate.parse("1999-05-20"))));
        expected.put(5557, Collections.singletonList(new User(2, "user2", LocalDate.parse("2008-05-03"))));
        assertEquals(expected,statisticsService.getUsersWithSameAgeInDays());
    }
}