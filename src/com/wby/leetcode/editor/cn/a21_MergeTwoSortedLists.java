//将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表

package com.wby.leetcode.editor.cn;


import com.wby.leetcode.test.LinkNodeList;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/23 10:11
 * description :
 * 本体思路：
 * 迭代：
 * 两条有序链表合并成一条有序链表，则交叉遍历两条有序链表，
 * 比较两个链表头结点数据，将数据小的结点插入新链表中。
 * 结点数据小的链表往下遍历一个结点，结点数据大的链表不遍历，再次比较。
 * 直至原链表全判空。
 * <p>
 * 递归：
 * 思路同上，也是从头结点开始比较，
 * 分别判断4种情况：
 * 1.l1链表为空，返回l2剩余结点
 * 2.l2链表为空，返回l1剩余结点
 * 3.若l1.val <= l2.val，保留l1当前结点，且l1向下遍历比较l1.next.val和l2.val，返回l1
 * 4.若l1.val > l2.val，保留l2当前结点，且l2向下遍历比较l1.val和l2.next.val，返回l2
 * <p>
 * 通过递归调用，最后从尾结点依次开始返回，并将返回值插入到上一层嵌套的结点的next结点位置
 */
public class a21_MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new a21_MergeTwoSortedLists().new Solution();

        ListNode l1 = new ListNode(new int[]{1,2,3});
        ListNode l2 = new ListNode(new int[]{1,2,4});

//        l1.add(2);
//        l1.next.add(3);
//        l2.add(2);
//        l2.next.add(4);

//        l1.add(new int[]{1,2,3});
//        System.out.println(l1.print());
//
//        l2.add(new int[]{1,2,4});
//        System.out.println(l2.print());

        ListNode listNode = solution.mergeTwoLists(l1, l2);

        while (listNode.next != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

        System.out.println(listNode.val);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            init(x);
        }

        ListNode(int[] arr) {
            init(arr[0]);
            int[] temp = new int[arr.length - 1];
            for (int i = 0; i < temp.length; i++){
                temp[i] = arr[i+1];
            }
            add(temp);
        }

        void init(int x) {
            val = x;
        }

        void add(int x){
            this.next = new ListNode(x);
        }

        void add(int[] arr){

            ListNode temp = this;
            while (temp.next != null){
                temp = temp.next;
            }

            for (int index: arr){
                temp.next= new ListNode(index);

                temp = temp.next;
            }
        }

        String print(){
            String out = "";
            ListNode temp = this;
            while (temp.next!=null){
                out += temp.val + ",";
                temp = temp.next;
            }
            out += temp.val;

            return out;
        }

    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */


    /**
     * 迭代
     */
//    class Solution {
//        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//            ListNode prehead = new ListNode(-1);
//
//            ListNode prev = prehead;
//            while (l1.next != null || l2.next != null) {
//                if (l1.val <= l2.val){
//                    prev.next = l1;
//                    l1 = l1.next;
//                } else {
//                    prev.next = l2;
//                    l2 = l2.next;
//                }
//
//                prehead = prehead.next;
//
//            }
//
//            return prehead;
//        }
//    }

    /**
     * 递归
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val <= l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }

        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}