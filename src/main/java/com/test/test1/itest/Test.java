package com.test.test1.itest;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <= 5; i++) {
            count = i <= 3 ? count + 1 : count - 1;
            for (int j = 1; j <= 5; j++) {
                if (j >= 4 - count && j <= 2 + count){
                    System.out.print("*");
                }

                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}
