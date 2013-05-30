package org.hello.spark;

public final class Maths {
    private Maths() {
    }

    public static boolean isSquare(int x) {
        final int sqrt = (int) Math.sqrt(x);
        return ((int) Math.pow(sqrt, 2)) == x;
    }

    public static boolean isCube(int x) {
        final int cbrt = (int) Math.cbrt(x);
        return ((int) Math.pow(cbrt, 3)) == x;
    }

    public static boolean isPrime(int x) {
        // Any even number is not a prime.
        if (isEven(x)) {
            return false;
        }

        // We only need to check divider from 3 to sqrt(x).
        for (int divider = 3; divider < Math.sqrt(x); divider += 2) {
            if (x % divider == 0) {
                return false;
            }
        }

        return true;
    }

    public static long fibonacci0(int order) {
        return fibonacci(0, order);
    }

    public static long fibonacci1(int order) {
        return fibonacci(1, order);
    }

    public static long fibonacci(int start, int order) {
        if (order == 1) {
            return start;
        } else if (order == 2) {
            return 1;
        } else {
            return fibonacci(start, order - 2) + fibonacci(start, order - 1);
        }
    }

    public static boolean isEven(int x) {
        return x % 2 == 0;
    }

    public static boolean isOdd(int x) {
        return !isEven(x);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci0(9));
    }
}
