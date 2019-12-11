package com.wby.leetcode.problem;

import java.util.Arrays;

/**
 * author: wbyz
 * email: witzel.wang@jollycorp.com
 * date: 2019/12/10 10:46
 * Description:
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 本题思路（数学方法）：
 * 根据举例可知，当该整数为负数时，不是回文数
 * 当该整数在[0-9]，属于回文数
 * 当该整数为10的倍数，不是回文数
 * 其余整数再按照需求条件进行判断
 */
public class a09_palindrome_number {
    private static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();

        for (int i = 0; i <= (chars.length - 1) / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[(chars.length - 1) - i];
            chars[(chars.length - 1) - i] = temp;
        }

        String str2 = String.valueOf(chars);

        return str.equals(str2);
    }

    private static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        if (x % 10 == 0) return false;

        int a = x;
        int b = 0;
        while (a > 0) {
            b = b * 10 + a % 10;
            a /= 10;
        }

        return x == b;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome2(121));
    }
}
