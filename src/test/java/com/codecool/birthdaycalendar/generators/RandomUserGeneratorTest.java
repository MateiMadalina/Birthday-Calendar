package com.codecool.birthdaycalendar.generators;

import com.codecool.birthdaycalendar.users.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomUserGeneratorTest {
    private final int userCount = 20;
    private final LocalDate mockedDate = LocalDate.of(1992, 7, 21);
    private RandomUserGenerator randomUserGenerator;


    @Test
    public void generatesCorrectNumberOfUsers() {

        RandomDateGenerator mockedDateGenerator = Mockito.mock(RandomDateGenerator.class);
        Mockito.when(mockedDateGenerator.generate()).thenReturn(mockedDate);

        randomUserGenerator = new RandomUserGeneratorImpl(mockedDateGenerator, userCount);
        List<User> users = randomUserGenerator.generate();

        assertEquals(userCount, users.size());
    }

    @Test
    public void createsUsersWithCorrectBirthDate() {
        RandomDateGenerator mockedDateGenerator = Mockito.mock(RandomDateGenerator.class);
        Mockito.when(mockedDateGenerator.generate()).thenReturn(mockedDate);

        randomUserGenerator = new RandomUserGeneratorImpl(mockedDateGenerator, 1);
        List<User> users = randomUserGenerator.generate();

        assertEquals(users.get(0).birthDate(),mockedDate);
    }
}

