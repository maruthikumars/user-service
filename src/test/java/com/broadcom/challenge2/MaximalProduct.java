package com.broadcom.challenge2;

public class MaximalProduct {
    public static void main(String[] args) {

        System.out.println("45 Maximum Product is " + maxProd(45));
        System.out.println("8 Maximum Product is " + maxProd(8));
        System.out.println("3 Maximum Product is " + maxProd(3));
        System.out.println("2 Maximum Product is " + maxProd(2));
        System.out.println("1 Maximum Product is " + maxProd(1));
        System.out.println("0 Maximum Product is " + maxProd(0));
        System.out.println("-5 Maximum Product is " + maxProd(-5));
    }

    static int maxProd(int n) {
        if (n <= 1) {
            return 0;
        } else if (n == 2 || n == 3) {
            return (n - 1);
        } else {
            int res = 1;
            while (n > 4) {
                n -= 3;
                res *= 3;
            }
            return (n * res);
        }
    }
}