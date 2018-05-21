package edu.tseidler;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now().plus(Period.ofDays(1));
        LocalDate firstTuesday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        LocalDate firstTuesday2 = today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println(firstTuesday);
        System.out.println(firstTuesday2);

        LocalDate nextFriday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        TemporalAdjuster NEXT_WORKDAY = w -> {
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        };

        LocalDate nextWorkDay = nextFriday.with(NEXT_WORKDAY);
        System.out.println("next workday after friday: " + nextWorkDay);
        System.out.println(nextWorkDay.getDayOfWeek());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusDay = now.plusDays(1).plusNanos(10);
        Duration interval = Duration.between(now, nowPlusDay);
        System.out.println(interval.toNanos());
        System.out.println();

        LocalDate programmersDay = LocalDate.of(2018, 1, 1).plusDays(255);
        System.out.println(programmersDay);
        System.out.println(today.until(programmersDay, ChronoUnit.DAYS));
        long mondaysUntilProgramersDay = today.datesUntil(programmersDay)
                .filter(d -> d.getDayOfWeek() == DayOfWeek.MONDAY)
                .peek(System.out::println)
                .count();
        System.out.println("Mondays until programmers day: " + mondaysUntilProgramersDay);
        System.out.println(today.until(programmersDay, ChronoUnit.DAYS) / 7);
        System.out.println(LocalDate.of(2018, Month.MAY, 21).getDayOfWeek());

        MonthDay christmass = MonthDay.of(Month.DECEMBER, 26);
        int year = 2025;
        System.out.println("Christmas in " + year + ": " + christmass.atYear(2018).getDayOfWeek());
        System.out.println();
        MonthDay someDay = MonthDay.of(Month.JULY, 28);
        for (int i = 1991; i < 2015; i++) {
            System.out.printf("year: %d, day of week: %s\n", i, someDay.atYear(i).getDayOfWeek());
        }
        System.out.println("only Thursdays:");
        IntStream.rangeClosed(1991, 2015)
                .filter(i -> someDay.atYear(i).getDayOfWeek() == DayOfWeek.THURSDAY)
                .forEach(System.out::println);
    }
}
