package edu.tseidler;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
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

        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });

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

        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime zonedNow = now.atZone(ZoneId.of("UTC"));
        System.out.println(apollo11launch.until(zonedNow, ChronoUnit.HOURS));
        System.out.println();

        System.out.println("time zone change:");
        ZonedDateTime skipped = ZonedDateTime.of(
                LocalDate.of(2013, 3, 31),
                LocalTime.of(1, 58),
                ZoneId.of("Europe/Berlin"));
        System.out.println(skipped);
        for (int i = 0; i < 5; i++) {
            System.out.println("one minute later: " + skipped);
            skipped = skipped.plusMinutes(10);
        }
        System.out.println();

        ZonedDateTime czarnobyl = ZonedDateTime.of(LocalDate.of(1986, Month.APRIL, 26), LocalTime.of(1, 23, 44), ZoneId.of("Europe/Kiev"));
        ZonedDateTime myBirthday = ZonedDateTime.of(LocalDate.of(1986, Month.MARCH, 17), LocalTime.of(20, 30), ZoneId.of("Europe/Warsaw"));
        System.out.printf("explosion in Czarnobyl: %s\n", czarnobyl);
        System.out.printf("today time %s\n", zonedNow);
        System.out.printf("years after explosion: %s\n", czarnobyl.until(zonedNow, ChronoUnit.YEARS));
        System.out.printf("months after explosion: %s\n", czarnobyl.until(zonedNow, ChronoUnit.MONTHS));
        System.out.printf("seconds after explosion: %s\n", czarnobyl.until(zonedNow, ChronoUnit.SECONDS));

        System.out.println();
        System.out.printf("my birthday: %s\n", myBirthday);
        System.out.printf("today time %s\n", zonedNow);
        System.out.printf("hours from birthday to explosion: %s\n", myBirthday.until(czarnobyl, ChronoUnit.HOURS));
        System.out.printf("minutes from birthday to explosion: %s\n", myBirthday.until(czarnobyl, ChronoUnit.MINUTES));

        System.out.println();
        TemporalAdjuster NEXT_FRIDAY = TemporalAdjusters.ofDateAdjuster(d -> {
            do
                d = d.plusDays(1);
            while (d.getDayOfWeek().getValue() != 5);
            return d;
        });
        System.out.printf("today: %s\n", LocalDate.now());
        System.out.printf("next Friday: %s\n", LocalDate.now().with(NEXT_FRIDAY));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMMM-dd");
        System.out.println(formatter.format(LocalDate.now()));
        System.out.println(formatter.format(LocalDate.of(2017, 2, 28)));
        LocalDate test = LocalDate.parse("2017-lutego-31", formatter);
        System.out.println(test);
    }
}
