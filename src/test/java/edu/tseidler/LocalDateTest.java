package edu.tseidler;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class LocalDateTest {

    public static final LocalDate MY_BIRTHDAY = LocalDate.of(1986, 03, 17);

    @Test
    public void comparingDates() {
        LocalDate date2 = LocalDate.now();

        Period fromBitrthday = Period.between(MY_BIRTHDAY, date2);
        System.out.printf("from my birthday: years %s, months %s, days %s\n", fromBitrthday.getYears(), fromBitrthday.getMonths(), fromBitrthday.getDays());
        System.out.println("from birthday in days: " + MY_BIRTHDAY.until(date2, ChronoUnit.DAYS));

        Assert.assertTrue(MY_BIRTHDAY.isBefore(date2));
    }

    @Test
    public void timeFromEpochToBirthday() {
        System.out.println("from epoch to birthday in days: " + LocalDate.EPOCH.until(MY_BIRTHDAY, ChronoUnit.DAYS));
        System.out.println("from epoch to birthday in months: " + LocalDate.EPOCH.until(MY_BIRTHDAY, ChronoUnit.MONTHS));
    }
}
