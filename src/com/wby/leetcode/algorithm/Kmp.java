package com.wby.leetcode.algorithm;

import com.wby.leetcode.test.algorithm.SubStringDefined;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/23
 * package_name: com.wby.leetcode.algorithm
 * <p>
 * description:
 * 相似的查找算法有 KMP，BM，Horspool
 *
 * 暴力破解法{@link SubStringDefined}
 * 匹配最大子串 -- KMP算法 (未完成)
 * <p>
 * Knuth-Morris-Pratt 字符串查找算法，简称为 “KMP算法”，常用于在一个文本串S内查找一个模式串P 的出现位置，
 * 这个算法由Donald Knuth、Vaughan Pratt、James H. Morris三人于1977年联合发表，故取这3人的姓氏命名此算法。
 * <p>
 * =================== 需要一个新的数组next[]，用来保存当匹配失败后，模式串p每个索引对应的指针j可以回溯的索引位置 ===================
 * <p>
 *     下面先直接给出KMP的算法流程（如果感到一点点不适，没关系，坚持下，稍后会有具体步骤及解释，越往后看越会柳暗花明☺）：
 * <p>
 * ·假设现在文本串S匹配到 i 位置，模式串P匹配到 j 位置：
 * ·如果j = -1(表示指针从模式串p头索引开始)，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++，继续匹配下一个字符；
 * ·如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]。 此举意味着失配时，模式串P相对于文本串S向右移动了j - next [j] 位。
 * ·换言之，当匹配失败时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next 值（next 数组的求解会在下文的3.3.3节中详细阐述），
 * 即移动的实际位数为：j - next[j]，且此值大于等于1。
 * <p>
 * 很快，你也会意识到 next数组各值的含义：代表当前字符之前的字符串中，有多大长度的相同前缀后缀。
 * 例如如果next [j] = k，代表j 之前的字符串中有最大长度为k 的相同前缀后缀。
 * <p>
 * 此也意味着在某个字符失配时，该字符对应的next 值会告诉你下一步匹配中，模式串应该跳到哪个位置（跳到next [j] 的位置）。
 * 如果next [j] 等于0或-1，则跳到模式串的开头字符，若next [j] = k 且 k > 0，代表下次匹配跳到j 之前的某个字符，而不是跳到开头，且具体跳过了k 个字符。
 */
public class Kmp {
    /**
     * KMP算法主函数
     *
     * @param ts
     * @param ps
     * @return
     */
    public int kmp(String ts, String ps) {
        // 如果ts或者ps为null 直接返回-1
        if (ts == null || ps ==null){
            return -1;
        }

        // str -> char[]
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();

        int tl = t.length;
        int pl = p.length;
        // 如果ts或者ps为空字符串 直接返回-1
        if (tl == 0 || pl == 0) {
            return -1;
        }
        // 初始化ts和ps指针
        int i = 0;
        int j = 0;

        int[] next = getNext(ps);

        while (i < tl && j < pl) {
            if (j == -1 || t[i] == p[j]) {
                //①如果j = -1，或者当前字符匹配成功（即t[i] == p[j]），都令i++，j++
                i++;
                j++;
            } else {
                //②如果j != -1，且当前字符匹配失败（即t[i] != p[j]），则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }

        if (j == p.length) {
            return i - j;
        } else
            return -1;
    }

    /**
     * 子串每个位置匹配失败后，指针下一步的移动位置
     *
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

        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String ts = "hello";
        String ps = "ll";
        Kmp kmp = new Kmp();
        int i = kmp.kmp(ts, ps);

        System.out.println(i);

    }

}

