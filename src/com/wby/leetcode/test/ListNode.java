package com.wby.leetcode.test;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/23
 * package_name: com.wby.leetcode.test
 * <p>
 * description:
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(){}

    public ListNode(int val){
        this.val = val;
    }

    public ListNode(int[] arr) {
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

    public String print(){
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
