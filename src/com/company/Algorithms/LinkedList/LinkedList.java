package com.company.Algorithms.LinkedList;

public class LinkedList {
  static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

  static ListNode deleteDuplicates(ListNode head) {
      if (head == null || head.next == null) return  head;
      ListNode cur = head;
      ListNode later = head.next;
      while (later != null){
          if (cur.val == later.val){
              cur.next = later.next;
              later = cur.next;
          }
           else {
               cur = later;
               later = later.next;
          }
      }
      return head;
  }

  static ListNode deleteAllDuplicates(ListNode head) {
      if (head == null || head.next == null) return  head;
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
      ListNode cur = head;
      ListNode later = head.next;
      while (later != null){
          if (cur.val == later.val){
              cur = later.next;
              fakeHead.next = cur;
              later = cur.next;

          }else {
              cur = later;
              later = later.next;
          }
      }
      return fakeHead.next;
  }

  static  ListNode removeElements(ListNode head, ListNode node) {

      if (head == null ) return  head;
      ListNode pre = head;
      ListNode cur = head;
      while(pre.next != null){
          if(pre.val == node.val){
              pre = cur.next;
              cur.next = pre.next;
          }else{
              pre = cur;
              cur = cur.next;
          }
      }
      return head;
  }

  /**Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
   * You should preserve the original relative order of the nodes in each of the two partitions.
   * Example:
   * Input: head = 1->4->3->2->5->2, x = 3
   * Output: 1->2->2->4->3->5
   */
    static  ListNode partition(ListNode head, int x) {

        ListNode p1 = null;
        ListNode headP1 = p1;
        ListNode travel = head;
        ListNode pre = head;
        while (travel != null){
            if (travel.val < x){
                ListNode temp = travel;
                if (p1 == null){
                    p1 = temp;
                    headP1 = p1;
                }
                else{
                    p1.next = temp;
                    p1 = temp;
                }
                travel = travel.next;
                temp.next = null;
                if (temp == head){
                    head = travel;
                } else {
                    pre.next = travel;
                }
            }else{
                pre = travel;
                travel = travel.next;
            }
        }

        if(p1 !=null)
            p1.next = head;
        else return head;
       return headP1;

    }


  static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
     StringBuilder num1 = new StringBuilder();
      StringBuilder num2 = new StringBuilder();
      while(l1 != null){
          num1.append(l1.val);
          l1 = l1.next;
      }
      while(l2 != null) {
          num2.append(l2.val);
          l2 = l2.next;
      }
      num1 = num1.reverse();
      num2 = num2.reverse();
      ListNode res = new ListNode( 1);
      ListNode cur = res;

      if(num2.length() > num1.length()){
          StringBuilder tmp = num1;
          num1 = num2;
          num2 = tmp;
      }
      int len1 = num1.length();
      int len2 = num2.length();
      int temp = 0;
      int i = len1-1, j = len2-1;
      for (; i >= 0 && j >=0 ; i--, j--) {
          int value1 = num1.charAt(i)-'0';
          int value2 = num2.charAt(j)-'0';
          ListNode newNode =  new ListNode((value1 + value2 + temp) % 10);
          cur.next = newNode;
          temp =  (value1 + value2 + temp) /10;
          cur = cur.next;
      }

      while(i >=0){
          int value1 = num1.charAt(i)-'0';
          ListNode newNode =  new ListNode((value1  + temp) % 10);
          cur.next = newNode;
          temp =  (value1  + temp) /10;
          cur = cur.next;
          i--;
      }
      if(temp == 1)
          cur.next = new ListNode(1);

      return res.next;
  }

  /**Removes all nodes have duplicate and itself; returns all distinct nodes */
  static ListNode deleteDuplicatesII(ListNode head) {
      if (head == null || head.next == null) return  head;
      ListNode fakeHead = new ListNode(0);
      fakeHead.next = head;
      ListNode pre = fakeHead;
      ListNode cur = fakeHead.next;
      while (cur != null ){
          int comparedValue = cur.val;
          cur = cur.next;
          boolean isValid = false;
          while (cur != null && cur.val == comparedValue){
              cur = cur.next;
              isValid = true;
          }
          if (isValid){
              pre.next = cur;
          }else {
              pre = pre.next;
              cur = pre.next;
          }
      }
      return fakeHead.next;
  }


  /**Input: [1,2,3,4,5]
   Output: Node 3 from this list (Serialization: [3,4,5])
   The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
   Note that we returned a ListNode object ans, such that:
   ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.*/
  static  ListNode middleNode(ListNode head) {
      if(head == null || head.next == null)
          return head;
      ListNode fastPointer = head,slowPointer= head;
      while(slowPointer != null){
          if( fastPointer == null || fastPointer.next == null )  break;
          fastPointer = fastPointer.next.next;
          slowPointer = slowPointer.next;
      }
      return slowPointer;
  }

  /**Given a linked list, swap every two adjacent nodes and return its head.
   Example:
   Given 1->2->3->4, you should return the list as 2->1->4->3.*/
  static ListNode swapPairs(ListNode head) {
      if (head == null || head.next == null)
          return  head;
      ListNode fakeHead ;
      ListNode p1 = head;
      ListNode p2 = p1.next;
      head = p2;//change head to next of node
      while (p1.next != null){
          fakeHead =p1;//save pre node to p1
          p1.next = p2.next;
          p2. next = p1;
          p1 = p1.next;
          if (p1 == null)//if has no node then break
              break;
          p2 = p1.next;
          if (p2 != null)//if p2 != null then p2 is the last node so move forward
          fakeHead.next = p2;
          else fakeHead.next = p1;//else p2 is null and p1 is the last node so restore p1 to last node
      }
      return head;
  }

  /**Input: 1->2->2->1
   Output: true
   Follow up:
   Could you do it in O(n) time and O(1) space?*/
   static boolean isPalindrome(ListNode head) {
        //find middle node
        if(head == null || head.next == null)
            return true;
        ListNode fastPointer = head,slowPointer= head;
        while(slowPointer != null){
            if( fastPointer == null || fastPointer.next == null )  break;
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        if(fastPointer != null)
            slowPointer = slowPointer.next;
        //reverse rightmost mid list
        slowPointer = reverseList(slowPointer);
        fastPointer = head;
        while(slowPointer != null){
            if(slowPointer.val != fastPointer.val)
                return false;
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }


        return true;
    }


    /**Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     * Example 1:
     * Given 1->2->3->4, reorder it to 1->4->2->3.*/
    static  void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return ;
        ListNode fastPointer = head,slowPointer= head;
        while(slowPointer != null){
            if( fastPointer == null || fastPointer.next == null )  break;
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        if(fastPointer != null)
            slowPointer = slowPointer.next;
        //reverse rightmost mid list
        slowPointer = reverseList(slowPointer);
        fastPointer = head;
        while(slowPointer != null){
            ListNode temp= slowPointer.next;
            slowPointer.next = fastPointer.next;
            fastPointer.next = slowPointer;
            fastPointer = slowPointer.next;
            slowPointer = temp;
        }
        fastPointer.next = null;
    }

    /**Input: 1->2->3->4->5->NULL
     Output: 5->4->3->2->1->NULL
     Follow up:
     A linked list can be reversed either iteratively or recursively. Could you implement both?*/
  static  ListNode reverseList(ListNode head) {
     if (head == null || head.next == null)
         return head;
     ListNode p1 = head;
     ListNode p2 = p1.next;
     while (p1.next != null){
         if (p2 == null) break;
         ListNode temp = p2.next;
         p2.next = p1;
         p1 = p2;
         p2 = temp;
     }
     head.next = null;
      return p1;
  }

  /**Input: 1->2->3->4->5->NULL, m = 2, n = 4
   Output: 1->4->3->2->5->NULL*/
  static ListNode reverseBetween(ListNode head, int m, int n) {
      if (head == null || head.next == null)
          return head;
      //if m==n then do nothing and return head
      if (n==m)
          return head;

      ListNode fakeHead = new ListNode(0);
      fakeHead.next = head;
      ListNode cur = fakeHead;
      int numberOfNodes = n-m+1;
      boolean isHead = true;//if position reverse at head node
      while (m != 1 && cur != null){//find pre node is to reverse as head of reversed list.
          cur = cur.next;
          m--;
          isHead = false;
      }

      ListNode last = cur.next;//save first and last node of reversed list .
      ListNode first = last;

      if (isHead){//reverse the first n-m +1 nodes
          last = head;
          first = last;
      }
      //reverse sublist
      ListNode p1 = first;
      ListNode p2 = p1.next;
      while (p1.next != null && numberOfNodes != 1){
          if (p2 == null) break;
          ListNode temp = p2.next;
          p2.next = p1;
          p1 = p2;
          numberOfNodes--;
          if (numberOfNodes == 1 )//if reach to last node, then connect cur.next to last node .next;
              last.next = temp;
          else p2 = temp;//else move forward
      }
      if (isHead) return p2;//if reverse at head so return list has reverse p2
      else cur.next = p1;//else disconnect last node to advoid cycle list.
      return head;
  }

  /**Given a singly linked list, group all odd nodes together followed by the even nodes.
   * Please note here we are talking about the node number and not the value in the nodes.
   You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
   Input: 1->2->3->4->5->NULL
   Output: 1->3->5->2->4->NULL*/
  static ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null)
        return  head;
    ListNode p1=  head;
    ListNode p2 = p1.next;
    ListNode connectNode = head.next;//save node to connect two list
    while (p1.next != null && p2.next != null){
        p1.next = p2.next;
        p1 = p1.next;
        p2.next = p1.next;
        p2 = p2.next;
    }
    p1.next = connectNode;//last node of list1 to connect first node of list2
    return head;//return head
  }

  /**Given a linked list, remove the n-th node from the end of list and return its head.
   * Example:
   * Given linked list: 1->2->3->4->5, and n = 2.
   * After removing the second node from the end, the linked list becomes 1->2->3->5.*/
  static  ListNode removeNthFromEnd(ListNode head, int n) {
      if(head == null )
          return head;
      int len = 0;
      ListNode fastPointer = head,slowPointer= head;
      //find middle node of list
      while(slowPointer != null){
          if( fastPointer == null || fastPointer.next == null )  break;
          fastPointer = fastPointer.next.next;
          slowPointer = slowPointer.next;
          len++;
      }
      //calculate length of list
      len *=2;
      if(fastPointer != null)
          len ++;
      ListNode fakeHead = new ListNode(0);
      fakeHead.next = head;
      ListNode pre = fakeHead, cur = head;
      while(len != n){
          pre= cur;
          cur = cur.next;
          len--;
      }
      if(cur != null && cur.next == null)
          pre.next = null;
      else  pre.next = cur.next;

      return fakeHead.next;
  }

  /**Write a program to find the node at which the intersection of two singly linked lists begins.
   For example, the following two linked lists:
   A:          a1 → a2
   ↘
   c1 → c2 → c3
   ↗
   B:     b1 → b2 → b3
   begin to intersect at node c1.*/
  static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
      if(headA == null && headB == null)
          return null;
      //find middle of two lists
      int lenA = 0, lenB=0;
      ListNode fastPointer = headA,slowPointer= headA;
      //find middle node of list
      while(slowPointer != null){
          if( fastPointer == null || fastPointer.next == null )  break;
          fastPointer = fastPointer.next.next;
          slowPointer = slowPointer.next;
          lenA++;
      }
      //calculate length of list
      lenA *=2;
      if(fastPointer != null)
          lenA ++;

      fastPointer = headB;slowPointer= headB;
      while(slowPointer != null){
          if( fastPointer == null || fastPointer.next == null )  break;
          fastPointer = fastPointer.next.next;
          slowPointer = slowPointer.next;
          lenB++;
      }
      //calculate length of list
      lenB *=2;
      if(fastPointer != null)
          lenB ++;
      //if lenB greater than listA then swap two lists and len
      if(lenB > lenA){
          lenA += lenB;
          lenB= lenA- lenB;
          lenA -= lenB;

          ListNode temp = headA;
          headA = headB;
          headB = temp;
      }
      ListNode travel = headA;
      while(lenA != lenB){
          travel = travel.next;
          lenA--;
      }

      ListNode travel1 = headB;
      while(travel != null && travel1 != null && travel.val != travel1.val){
          travel = travel.next;
          travel1 = travel1.next;
      }

      return travel;

  }


  static boolean hasCycle(ListNode head) {

      ListNode slowPointer = head, fastPointer = head;
      while (slowPointer != null && fastPointer != null && fastPointer.next != null){//avoid null error
          slowPointer = slowPointer.next;
          fastPointer = fastPointer.next.next;
          if (slowPointer == fastPointer)
              return true;
      }
      return false;
  }

  /**Input: 1->2->3->4->5->NULL, k = 2
   Output: 4->5->1->2->3->NULL
   Explanation:
   rotate 1 steps to the right: 5->1->2->3->4->NULL
   rotate 2 steps to the right: 4->5->1->2->3->NULL*/
  static ListNode rotateRight(ListNode head, int k) {
      if(head == null || head.next ==null)
          return head;

      int len =0;
      ListNode slowPointer = head, fastPointer = head;
      ListNode preFastPointer = head;
      while (slowPointer != null && fastPointer != null && fastPointer.next != null){//avoid null error
          slowPointer = slowPointer.next;
          preFastPointer = fastPointer.next;
          fastPointer = fastPointer.next.next;
          len++;
      }
      len *= 2;
      if (fastPointer != null){
          ++len;
          preFastPointer = preFastPointer.next;
      }
      k = k% len;
      if (k == 0)
          return head;
      slowPointer = head;
      while (--len != k){
          slowPointer = slowPointer.next;
      }
      ListNode preSlowPointer = slowPointer.next;
      slowPointer.next = preFastPointer.next;
      preFastPointer.next = head;
      head = preSlowPointer;

      return head;
  }

    public static void main(String...args){
        ListNode a = new ListNode(3);
        a.next = new ListNode(4);
        a.next.next = new ListNode(3);
        ListNode root = new ListNode(3);
        root.next = new ListNode(4);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(2);
       root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(2);
//        root.next.next.next.next.next.next = new ListNode(9);
//        root.next.next.next.next.next.next.next= new ListNode(9);
//        root.next.next.next.next.next.next.next.next = new ListNode(9);
//        root.next.next.next.next.next.next.next.next.next = new ListNode(9);
        root = partition(root, 3);
        while (root!= null){
            System.out.println(root.val);
            root = root.next;
        }
    }
}
