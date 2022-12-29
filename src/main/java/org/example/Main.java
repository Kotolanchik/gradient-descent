package org.example;

import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) {
        double x1 = 1, x2 = 1, EPS = 0.05, ALPHA = 1, BETA = 0.5;

        double funcBuff = func(x1, x1), x1Buff = x1, x2Buff = x2;

        double FX1 = getDerivativeX1(x1, x2), FX2 = getDerivativeX1(x1, x2);

        x1 = x1Buff + ALPHA * FX1;
        x2 = x2Buff + ALPHA * FX2;

        while (!limit(x1, x2)
                || x1 < 0
                || x2 < 0
                || func(x1, x2) < funcBuff) {
            ALPHA *= BETA;
            x1 = x1Buff + ALPHA * FX1;
            x2 = x2Buff + ALPHA * FX2;
        }

        while (gradient(x1, x2) > EPS
                && ALPHA > 0.00001) {
            x1Buff = x1;
            x2Buff = x2;
            funcBuff = func(x1, x2);

            FX1 = getDerivativeX1(x1, x2);
            FX2 = getDerivativeX2(x1, x2);

            x1 = x1Buff + ALPHA * FX1;
            x2 = x2Buff + ALPHA * FX2;

            while (!limit(x1, x2)
                    || x1 < 0
                    || x2 < 0
                    || funcBuff < funcBuff) {
                ALPHA *= BETA;
                x1 = x1Buff + ALPHA * FX1;
                x2 = x2Buff + ALPHA * FX2;
            }
        }
        print(x1, x2);
    }

    public static double func(final double x1, final double x2) {
        return 5 - pow(x1 - 4, 2) - pow(x2 - 5, 2);
    }

    public static double getDerivativeX1(final double x1, final double x2) {
        return -2 * (x1 - 4);
    }

    public static double getDerivativeX2(final double x1, final double x2) {
        return -2 * (x2 - 5);
    }

    public static boolean limit(final double x1, final double x2) {
        return (pow(x1, 2) + pow(x2, 2)) <= 4;
    }

    public static double gradient(final double x1, final double x2) {
        return pow((pow(getDerivativeX1(x1, x2), 2) + pow(getDerivativeX2(x1, x2), 2)), 0.5);
    }

    public static void print(final double x1, final double x2) {
        System.out.println("X1:" + x1 + "\tX2:" + x2);
        System.out.println("Function: " + func(x1, x2));
        System.out.println(gradient(x1, x2));
    }
}