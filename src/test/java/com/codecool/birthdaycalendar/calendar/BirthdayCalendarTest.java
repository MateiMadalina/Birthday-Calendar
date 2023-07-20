package com.codecool.birthdaycalendar.calendar;

import com.codecool.birthdaycalendar.users.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class BirthdayCalendarTest {
    private BirthdayCalendar birthdayCalendar;
    UserAgeCalculator userAgeCalculator = new UserAgeCalculatorImpl();


    @Test
    public void NoUsersReturnsEmptyCalendar()
    {
        UserRepository mockedUser =  Mockito.mock(UserRepository.class);
        List<User> emptyList = new ArrayList<>();
        Mockito.when(mockedUser.getAll()).thenReturn(emptyList);
        birthdayCalendar = new BirthdayCalendarImpl(mockedUser,userAgeCalculator,2022);
        assertNull(birthdayCalendar.getBirthdaysForMonth(5));

    }

    @Test
    public void GetBirthdaysForMonthInvalidMonthThrowsException()
    {
        UserRepository mockedUser =  Mockito.mock(UserRepository.class);
        List<User> users = new ArrayList<>();
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        birthdayCalendar = new BirthdayCalendarImpl(mockedUser,userAgeCalculator,2022);
        assertNull(birthdayCalendar.getBirthdaysForMonth(13));
        assertNull(birthdayCalendar.getBirthdaysForMonth(0));
    }

    @Test
    public void GetBirthdaysForMonthTwoUsersBornOnTheSameDay()
    {
        UserRepository mockedUser =  Mockito.mock(UserRepository.class);
        List<User> users = new ArrayList<>();
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        birthdayCalendar = new BirthdayCalendarImpl(mockedUser,userAgeCalculator,2022);
        System.out.println(birthdayCalendar.getBirthdaysForMonth(5));
        assertEquals(2,birthdayCalendar.getBirthdaysForMonth(5).size());
    }

    @Test
    public void GetBirthdateForUserNonExistentUserIdThrowsException()
    {
        UserRepository mockedUser =  Mockito.mock(UserRepository.class);
        List<User> users = new ArrayList<>();
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        birthdayCalendar = new BirthdayCalendarImpl(mockedUser,userAgeCalculator,2022);

        assertThrows(NullPointerException.class, () -> birthdayCalendar.getBirthdateForUser(3));
    }

    @Test
    public void GetBirthDateForUserReturnsCorrectBirthdate()
    {
        UserRepository mockedUser =  Mockito.mock(UserRepository.class);
        List<User> users = new ArrayList<>();
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        Mockito.when(mockedUser.getById(1)).thenReturn(users.get(0));
        birthdayCalendar = new BirthdayCalendarImpl(mockedUser,userAgeCalculator,2022);
        LocalDate actualBirthdate = birthdayCalendar.getBirthdateForUser(1);
        LocalDate expectedBirthdate = LocalDate.parse("1999-05-20");
        System.out.println(users);
        assertEquals(expectedBirthdate, actualBirthdate);
    }


    @Test
    public void GetUserAgeAtDateNonExistentUserIdThrowsException()
    {
        UserRepository mockedUser =  Mockito.mock(UserRepository.class);
        List<User> users = new ArrayList<>();
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        Mockito.when(mockedUser.getById(3)).thenReturn(null);
        birthdayCalendar = new BirthdayCalendarImpl(mockedUser,userAgeCalculator,2022);
        assertThrows(NullPointerException.class, () -> birthdayCalendar.getUserAgeAtDate(3,LocalDate.parse("2008-05-03")));
    }

    @Test
    public void GetUserAgeAtDateReturnsCorrectAgeDescriptor()
    {
        UserRepository mockedUser =  Mockito.mock(UserRepository.class);
        List<User> users = new ArrayList<>();
        users.add(new User(1,"user1",LocalDate.parse("1999-05-20")));
        users.add(new User(2,"user2", LocalDate.parse("2008-05-03")));
        Mockito.when(mockedUser.getAll()).thenReturn(users);
        Mockito.when(mockedUser.getById(1)).thenReturn(users.get(0));
        birthdayCalendar = new BirthdayCalendarImpl(mockedUser,userAgeCalculator,2022);
        UserAgeCalculatorImpl userAgeCalculator = new UserAgeCalculatorImpl();
        UserAgeDescriptor userAgeDescriptor = userAgeCalculator.createUserAgeDescriptor(users.get(0),LocalDate.now());
        assertEquals(userAgeDescriptor,birthdayCalendar.getUserAgeAtDate(1,LocalDate.now()));
    }
}

