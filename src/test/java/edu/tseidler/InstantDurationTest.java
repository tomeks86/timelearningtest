package edu.tseidler;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InstantDurationTest {

    @Test
    public void instantTests() {
        Instant t1 = Instant.now();
        Instant t2 = Instant.now();

        Duration t1t2 = Duration.between(t1, t2);

        System.out.println("instant t1: " + t1);
        System.out.println("instant t2: " + t2);

        Assert.assertFalse(t1t2.isNegative());
    }

    @Test
    public void durations() {
        Duration week = Duration.ofDays(7);
        long secondsPerWeek = week.toSeconds();

        System.out.println("week: " + week);
        System.out.println("seconds per week: " + secondsPerWeek);

        assertEquals(secondsPerWeek, 604800);
    }

    @Test
    public void durationsBetweenInstants() {
        Instant t1 = Instant.now();
        Instant t2 = t1.plus(Duration.ofSeconds(15));

        long elaspedTimeInSeconds = Duration.between(t1, t2).toSeconds();

        assertEquals(elaspedTimeInSeconds, 15);
    }

    @Test
    public void failedAttemptToOverflowLong() {
        Duration longTime = Duration.ofDays(365).multipliedBy(292);

        System.out.println("365 days times 292 to nanos:" + longTime.toNanos());

        assertTrue(true);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void successfulAttemptToOverflowLong() {
        Duration longTime = Duration.ofDays(365).multipliedBy(293);

        System.out.println(longTime.toNanos());
    }
}
