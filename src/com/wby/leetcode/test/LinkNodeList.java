package com.wby.leetcode.test;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/23
 * package_name: com.wby.leetcode.test
 * <p>
 * description:
 */
public class LinkNodeList {
    ListNode head;
    ListNode current;

    public void add(int data){
        //如果头结点为空,为头结点
        if (head == null){
            head = new ListNode(data);
            current = head;
        } else {
            current.next = new ListNode(data);
            current = current.next;
        }
    }

    //打印链表
    public void print(ListNode node) {
        if(node == null) {
            return;
        }

        current = node;
        System.out.print(current.val + " ");
        // current的next结点若为空跳出循环，避免current被置空
        while(current.next != null) {
            current = current.next;
            System.out.print(current.val + " ");
        }
    }

    //初始化链表,并且返回表头
    public ListNode init() {
        for(int i=0; i<10; i++) {
            this.add(i);
        }
        return head;
    }

    //求链表长度
    public int get_length(ListNode head) {
        if (head == null) {
            return -1;
        }

        int length = 1;
        current = head;

        while (current.next != null){
            length ++;
            current = current.next;
        }

        return length;
    }

    public static void main(String[] args) {
        LinkNodeList list = new LinkNodeList();
        System.out.println(list.get_length(list.head));
        ListNode head = list.init();
        list.print(head);
        System.out.println();
        System.out.println(list.get_length(list.head));
        // 手动添加
        list.add(1);
        list.add(2);
        list.add(4);
        list.print(head);

        int length = list.get_length(head);
        System.out.println();
        System.out.println("The length of list is: " + length);
    }
}
