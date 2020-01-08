//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串

package com.wby.leetcode.editor.cn;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date:
 * description :
 */
public class a28_ImplementStrstr {
    public static void main(String[] args) {
        Solution solution = new a28_ImplementStrstr().new Solution();
        String haystack = "hello";
        String needle = "ll";

        int i = solution.strStr(haystack, needle);
        System.out.println(i);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            if (haystack==null || needle == null){
                return 0;
            }

            if (needle.length() == 0) {
                return 0;
            }

            char[] h = haystack.toCharArray();
            char[] n = needle.toCharArray();
            int[] next = getNext(needle);

            int i = 0;
            int j = 0;

            while (i < h.length && j < n.length){
                if (j == -1 || h[i] == n[j]){
                    i++;
                    j++;
                } else {
                    j = next[j];
                }
            }

            if (j == n.length){
                return i-j;
            } else
                return -1;
        }

        public int[] getNext(String needle) {
            // init string->char[] and int[] next
            char[] p = needle.toCharArray();

            int[] next = new int[needle.length()];
            next[0] = -1;

            int i = 0;
            int k = -1;

            while (i < p.length - 1){
                if (k==-1 || p[i]==p[k]){
                    next[++i] = ++k;
                } else {
                    k = next[k];
                }
            }

            return next;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}