package com.company.Algorithms.LinkedList;

public class LinkedListInterview {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static ListNode reverseNodesInKGroups(ListNode l, int k) {
        if(k == 1 || l == null || l.next == null) return l;



        return l;
    }

    static  ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode curr = head.next;
        head.next = null;
        while(curr != null){
            ListNode temp= curr;
            curr = curr.next;
            temp.next = null;
            head=  insert(temp, head);
        }

        return head;
    }

    static ListNode insert(ListNode node, ListNode head){
        ListNode pre = head;
        ListNode curr = head;
        while(curr != null && curr.val < node.val){
            pre = curr;
            curr = curr.next;
        }
        if (curr == head){
            node.next = head;
            head = node;
        }else{
            pre.next = node;
            node.next = curr;
        }
        return head;
    }

    public static void main(String...args){

        ListNode root = new ListNode(3);
        root.next = new ListNode(4);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(2);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(2);
        root = insertionSortList(root);
        while (root!= null){
            System.out.println(root.val);
            root = root.next;
        }

        class Base{
            private void f(){
                System.out.println("base f()");
            }
            public void show(){
                f();
            }
        }
        class Derived extends Base{
            public void f(){
                System.out.println("derived f()");
            }

        }
        Derived d = new Derived();
        Base b = d;
        b.show();
        d.show();

    }

}
