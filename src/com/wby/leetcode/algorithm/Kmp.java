package com.wby.leetcode.algorithm;

import com.wby.leetcode.test.algorithm.SubStringDefined;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/23
 * package_name: com.wby.leetcode.algorithm
 *
 * description:
 *      匹配最大子串 -- KMP算法
 *
 *      暴力破解法{@link SubStringDefined}
 */
public class Kmp {
    /**
     *
     * @param ts
     * @param ps
     * @return
     */
    public int Kmp(String ts, String ps){
        // str -> char[]
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();

        // 初始化ts和ps指针
        int i = 0;
        int j = 0;

        int[] next = getNext(ps);
        return 1;
    }

    /**
     * 子串每个位置匹配失败后，指针下一步的移动位置
     * @param ps 字符串
     * @return 每个位置的下一步索引数组
     */
    private int[] getNext(String ps) {
        // 初始化
        char[] p = ps.toCharArray();

        int[] next = new int[ps.length()];
        next[0] = -1;
        int j = 0;
        int k = -1;

        while (j < p.length - 1){
            if (k == -1 || p[j]==p[k]){
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }


}
