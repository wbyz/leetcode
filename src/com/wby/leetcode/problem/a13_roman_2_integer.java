package com.wby.leetcode.problem;

import java.util.HashMap;
import java.util.Map;

/**
 * author: wbyz
 * email: witzel.wang@jollycorp.com
 * date: 2019/12/10 11:31
 * Description:
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 通常：罗马数字中小的数字在大的数字的右边
 * 特例：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * <p>
 * 本题思路：
 * 根本上，是判断字符串s相邻两个字符对应数值大小关系，
 * 若preChar > Char,为加法逻辑，总和+preChar对应数值preNum
 * 若preChar < Char,为减法逻辑，总和-preChar对应数值preNum
 * 初始化preNum -> 字符串s第一个字符对应数值，索引值为0
 * 初始化总和sum
 * 遍历字符串s,判断相邻字符对应数值大小，累加计入sum，将当前num赋值给preNum
 * 跳出遍历，sum+=preNum
 * 返回sum
 */
public class a13_roman_2_integer {
    class Solution2 {
        public int romanToInt(String s) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("I", 1);
            map.put("IV", 4);
            map.put("V", 5);
            map.put("IX", 9);
            map.put("X", 10);
            map.put("XL", 40);
            map.put("L", 50);
            map.put("XC", 90);
            map.put("C", 100);
            map.put("CD", 400);
            map.put("D", 500);
            map.put("CM", 900);
            map.put("M", 1000);

            int ans = 0;
            for (int i = 0; i < s.length(); ) {
                if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                    ans += map.get(s.substring(i, i + 2));
                    i += 2;
                } else {
                    ans += map.get(s.substring(i, i + 1));
                    i += 1;
                }
            }

            return ans;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int romanToInt(String s) {
            int sum = 0;
            int preNum = getValue(s.charAt(0));

            for (int i = 1; i < s.length(); i++) {
                int num = getValue(s.charAt(i));
                if (preNum < num) {
                    sum -= preNum;
                } else sum += preNum;

                preNum = num;
            }

            sum += preNum;
            return sum;
        }

        private int getValue(char ch) {
            switch (ch) {
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                case 'L':
                    return 50;
                case 'C':
                    return 100;
                case 'D':
                    return 500;
                case 'M':
                    return 1000;
                default:
                    return 0;
            }
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new a13_roman_2_integer().new Solution();
        int iv = solution.romanToInt("IV");
        System.out.println(iv);
    }
}
