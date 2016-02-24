package me.tonyirl.generator.main;

import java.util.Scanner;

/**
 * Created by tony on 16/2/7.
 */
public class MainClass {

    public static void main(String[] args) {
        System.out.println("输入最大行数:");
        int count = new Scanner(System.in).nextInt();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count / 2 - i; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j < i * 2 - 1; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
