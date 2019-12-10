package com.wby.leetcode.problem;

import java.util.Arrays;

/**
 * author: wbyz
 * email: witzel.wang@jollycorp.com
 * date: 2019/12/10 10:46
 * Description:
 */
public class a09_palindrome_number {
    private static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();

        for (int i = 0 ; i <= (chars.length-1)/2 ; i++){
            char temp = chars[i];
            chars[i] = chars[(chars.length-1)-i];
            chars[(chars.length-1)-i] = temp;
        }

        String str2 = String.valueOf(chars);

        return str.equals(str2);
    }

    private static boolean isPalindrome2(int x) {
        if (x < 0 ) return false;
        if (x < 10 ) return true;
        if (x%10 == 0) return false;

        int a = x;
        int b = 0;
        while(a > 0){
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
