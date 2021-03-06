//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串

package com.wby.leetcode.editor.cn;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
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
public class a14_LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new a14_LongestCommonPrefix().new Solution();
        String[] strs = new String[]{"flower", "flow", "flight"};
        String s = solution.longestCommonPrefix(strs);
        System.out.println(s);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)

}