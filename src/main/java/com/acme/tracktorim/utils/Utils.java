package com.acme.tracktorim.utils;

public class Utils {
    public static int getRandomNum(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
