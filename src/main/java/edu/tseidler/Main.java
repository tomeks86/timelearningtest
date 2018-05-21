package edu.tseidler;

import java.time.Year;

public class Main {
    public static void main(String[] args) {
        for (int i = -1000; i < 2018; i += 100) {
            if (Year.of(i).isLeap())
                System.out.printf("year %d is leap!\n", i);
        }
    }
}
