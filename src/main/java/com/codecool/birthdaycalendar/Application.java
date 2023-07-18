package com.codecool.birthdaycalendar;

import com.codecool.birthdaycalendar.calendar.BirthdayCalendar;
import com.codecool.birthdaycalendar.calendar.BirthdayCalendarImpl;
import com.codecool.birthdaycalendar.generators.RandomDateGenerator;
import com.codecool.birthdaycalendar.generators.RandomDateGeneratorImpl;
import com.codecool.birthdaycalendar.generators.RandomUserGenerator;
import com.codecool.birthdaycalendar.generators.RandomUserGeneratorImpl;
import com.codecool.birthdaycalendar.users.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        RandomDateGenerator randomDateGenerator = new RandomDateGeneratorImpl(1970, 2010);
        RandomUserGenerator randomUserGenerator = new RandomUserGeneratorImpl(randomDateGenerator, 200);

        UserRepository userRepository = new UserRepositoryImpl(randomUserGenerator);
        UserAgeCalculator userAgeCalculator = new UserAgeCalculatorImpl();

        BirthdayCalendar birthdayCalendar = new BirthdayCalendarImpl(userRepository, userAgeCalculator, 2022);

        System.out.println("Birthdays:\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (Map.Entry<LocalDate, List<UserAgeDescriptor>> entry : birthdayCalendar.getBirthdaysForMonth(Month.MARCH.getValue()).entrySet()) {
            String usersString = entry.getValue().stream()
                    .map(u -> u.user().userName() + " [" + u.ageInYears() + " years old]")
                    .collect(Collectors.joining(", "));
            System.out.println(entry.getKey().format(formatter) + ": " + usersString);
        }

        new Scanner(System.in).nextLine();
    }
}
