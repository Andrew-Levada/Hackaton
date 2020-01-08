package com.example.hackaton;

import java.util.Random;

public class Content {

    private static Random rnd = new Random();

    public static int count(int a, int b, int o) {
        switch (o) {
            case 0:
                return a + b;

            case 1:
                return a - b;

            case 2:
                return a * b;
        }
        return 0;
    }

    public static int getOperation() {
        int operation = rnd.nextInt(3);
        return operation;
    }

    public static int getA(int o) {
        int a = 0;
        switch (o) {
            case 0:
                a = rnd.nextInt(76) - 25;
                break;

            case 1:
                a = rnd.nextInt(66) - 15;
                break;

            case 2:
                a = rnd.nextInt(31) - 15;
                break;
        }
        return a;
    }

    public static int getB(int o) {
        int b = 0;
        switch (o) {
            case 0:
                b = rnd.nextInt(76) - 25;
                break;

            case 1:
                b = rnd.nextInt(50) - 9;
                break;

            case 2:
                b = rnd.nextInt(11) - 3;
                break;
        }
        return b;
    }

    public static String getOperationString(int o) {
        switch (o) {
            case 0:
                return " + ";

            case 1:
                return " - ";

            case 2:
                return " × ";
        }
        return "";
    }
}
