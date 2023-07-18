package com.codecool.birthdaycalendar.generators;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomDateGeneratorImpl implements RandomDateGenerator {

    private record Range(int min, int max) {
    }
    private static final Random random = new Random();

    private static final Map<Integer[], Range> dayGenerators = new HashMap<>() {{
        put(new Integer[]{1, 3, 5, 7, 8, 10, 12}, new Range(1, 31));
        put(new Integer[]{4, 6, 9, 11}, new Range(1, 30));
        put(new Integer[]{2}, new Range(1, 28));
    }};

    private final int minYear;
    private final int maxYear;

    public RandomDateGeneratorImpl(int minYear, int maxYear) {
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    @Override
    public LocalDate generate() {
        int year = getRandomYear();
        int month = getRandomMonth();
        int day = getRandomDay(year, month);

        return LocalDate.of(year, month, day);
    }

    private int getRandomYear() {
        return random.nextInt(maxYear - minYear + 1) + minYear;
    }

    private static int getRandomMonth() {
        // Upper bound is exclusive
        return random.nextInt(12) + 1;
    }

    private static int getRandomDay(int year, int month) {
        Range generator = dayGenerators.entrySet().stream()
                .filter(entry -> Arrays.asList(entry.getKey()).contains(month))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
        if (generator == null) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        return random.nextInt(generator.max - generator.min + (month == 2 && isLeapYear(year) ? 2 : 1)) + generator.min;
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }
}
