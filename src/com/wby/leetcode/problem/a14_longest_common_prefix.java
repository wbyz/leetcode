package com.wby.leetcode.problem;

/**
 * author: wbyz
 * email: witzel.wang@jollycorp.com
 * date: 2019/12/10 17:54
 * Description:
 * 编写一个函数来查找字符串数组中的最长公共前缀
 * <p>
 * 本体思路：
 * 初始化前缀 -> 取字符串数字strs[]第一个元素字符串
 * 遍历数组，若前缀和当前字符串不同
 * 比较前缀和当前元素字符串长度，取最大值和最小值，
 * 遍历最小值，将相同字符查出字符串缓存，直到不同字符跳出循环
 * 继续遍历数组直到跳出
 * 前缀赋值为字符串缓存
 * 返回前缀
 * <p>
 * 健壮分析：
 * 若字符串为空，返回""
 * 若字符串有空值，遍历数组时，返回""
 */
public class a14_longest_common_prefix {
    public static void main(String[] args) {
        Solution solution = new a14_longest_common_prefix().new Solution();
        String[] strs = new String[]{"1", null};
        String s = solution.longestCommonPrefix(strs);
        System.out.println(s);
    }

    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) return "";

            String preStr = strs[0];
            String min, max;
            for (String str : strs) {
                if (str == null) return "";
                if (!preStr.equals(str)) {
                    StringBuilder sb = new StringBuilder();
                    if (preStr.length() <= str.length()) {
                        min = preStr;
                        max = str;
                    } else {
                        min = str;
                        max = preStr;
                    }
                    for (int i = 0; i < min.length(); i++) {
                        if (min.charAt(i) == max.charAt(i)) {
                            sb.append(min.charAt(i));
                        } else break;
                    }
                    preStr = sb.toString();
                }
            }

            return preStr;
        }
    }
}
