package com.example.spring.ch2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyDate {
    private int year;
    private int month;
    private int day;

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
