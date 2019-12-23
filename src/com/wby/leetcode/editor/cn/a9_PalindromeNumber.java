//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学

package com.wby.leetcode.editor.cn;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/10 10:46
 * Description:
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 本题思路（数学方法）：
 * 根据举例可知，当该整数为负数时，不是回文数
 * 当该整数在[0-9]，属于回文数
 * 当该整数为10的倍数，不是回文数
 * 其余整数再按照需求条件进行判断
 */
public class a9_PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new a9_PalindromeNumber().new Solution();
        boolean palindrome = solution.isPalindrome(121);
        System.out.println(palindrome);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(int x) {
            int a = x;

            if (x < 0) return false;
            if (x < 10) return true;
            if (x % 10 == 0) return false;

            int b = 0;
            while (x != 0) {
                b = b * 10 + x % 10;
                x /= 10;
            }

            return a == b;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}