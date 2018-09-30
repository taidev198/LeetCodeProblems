package com.company.Algorithms.Stack;
import java.util.Stack;

public class StackInterview {

   static class Elem{
        int total;
        int lastValue;
        Elem next;
        public Elem(int total, int lastValue){
            this.total= total +lastValue;
            this.lastValue=lastValue;
        }
    }
    static class TheStack{
        private Elem e;
        public TheStack(int i, int i1){
        }
        boolean isEmpty(){return e == null;}

        Elem pop(){
            if(!isEmpty()){
                Elem tmp = e;
                e=e.next;
                return tmp;
            }
            return null;
        }

        void push(int i){

            if(e==null){
                e= new Elem(0, i);
            }else{
                Elem tmp = new Elem(e.total, i);
                tmp.next=e;
                e=tmp;
            }
        }

        Elem peek(){
            return e;
        }

    }

    /**Integer (one round's score): Directly represents the number of points you get in this round.
     "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
     "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
     "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be
     Input: ["5","2","C","D","+"]
     Output: 30
     Explanation:
     Round 1: You could get 5 points. The sum is: 5.
     Round 2: You could get 2 points. The sum is: 7.
     Operation 1: The round 2's data was invalid. The sum is: 5.
     Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
     Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
     */
    static int calPoints(String[] ops) {
        //int totalPoints = 0;
        TheStack theStack1 =  new TheStack(0,0);
        for (int i = 0; i <ops.length ; i++) {
            char[] ch = ops[i].toCharArray();
            if (ops[i].matches("[0-9]*") || ch.length >= 2 )
                theStack1.push(Integer.valueOf(ops[i]));
            else if (ops[i].equals("C"))
                theStack1.pop();
            else if (ops[i].equals("D")){
                Elem tmp = theStack1.peek();
                theStack1.push(tmp.lastValue + tmp.lastValue);
            }
            else {
                Elem tmp = theStack1.pop();
                Elem tmp1 = theStack1.peek();
                theStack1.push( tmp.lastValue);
                theStack1.push(tmp.lastValue + tmp1.lastValue);
            }
        }
        return theStack1.e.total;
    }

    static int scoreOfParentheses(String S) {
        int totalScore = 0;


        
        return totalScore;
    }
    
    static  boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        for (int i = 0; i <S.length() ; i++) {
            if (S.charAt(i) != '#')
                stackS.push(S.charAt(i));
            else{
                if (!stackS.isEmpty())
                    stackS.pop();
            }
        }
        for (int i = 0; i <T.length() ; i++) {
            if (T.charAt(i) != '#')
                stackT.push(T.charAt(i));
            else{
                if (!stackT.isEmpty())
                stackT.pop();
            }
        }

        while (!stackS.isEmpty() && !stackT.isEmpty()){
            if (stackS.pop() != stackT.pop())
                return false;
        }
        return stackS.isEmpty() && stackT.isEmpty();
    }

    /**Given an encoded string, return it's decoded string.
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
     * Note that k is guaranteed to be a positive integer.
     * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
     * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
     * For example, there won't be input like 3a or 2[4]
     * Examples:
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".*/
    static  String decodeString(String s) {
        char[] temp = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < temp.length; i++) {
            StringBuilder ans = new StringBuilder();
            if (temp[i] <='9' && temp[i] >='0'){
                stack.push(temp[i]);
            }else if (temp[i] == '[')
                stack.push(temp[i]);
            else if (temp[i] == ']'){
                StringBuilder tmp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '['){
                    tmp.append(stack.pop());
                }
                stack.pop();
                int number =0;
                tmp.reverse();
                int pow  =0;
                while (!stack.isEmpty() && stack.peek() <='9' && stack.peek() >='0'){
                    number =number + (stack.pop()-'0')*(int) Math.pow(10,pow);
                    pow++;
                }
                for (int j = 0; j < number; j++) {
                    ans.append(tmp);
                }
                for (int j = 0; j < ans.length(); j++) {
                    stack.push(ans.charAt(j));
                }
            }else stack.push(temp[i]);
        }

        return stack.toString().replace(" ","").replace(",","")
                .replace("[","").replace("]","");
    }

    static String inFixToPostFix(String expression){
        StringBuilder posFix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.toString(ch).matches("[0-9A-Za-z]*"))
                posFix.append(ch);
            else if (ch == '(')
                stack.push(ch);
            else if (ch == ')'){
                while (!stack.isEmpty() && stack.peek() != '(') {
                    posFix.append(stack.pop());
                }
                stack.pop();
            }else {
                switch (ch){
                    case '+':
                    case '-':
                        gotOperator(ch, 1, stack, posFix);
                        break;
                    case '*':
                    case '/':
                        gotOperator(ch, 2, stack, posFix);
                        break;
                }
            }
        }
        while (!stack.isEmpty())//popped remains elem on stack
            posFix.append(stack.pop());
        return posFix.toString();
    }

     static void gotOperator(char opThis, int preOfOpThis, Stack<Character> stack, StringBuilder postFix) {

        while (!stack.isEmpty()){
            char operatorTop = stack.pop();
            if (operatorTop == '('){
                stack.push(operatorTop);
                break;
            }else {
                int prec2;
                if (operatorTop == '+' || operatorTop == '-')
                    prec2 = 1;
                else prec2 = 2;
                if (prec2 < preOfOpThis){//if prec of new opp less
                    stack.push(opThis);//than prec of old then save newly-popped op
                    break;
                }else postFix.append(operatorTop);
            }
        }
         stack.push(opThis);
    }


    static int calPostFix(String postFix){
        Stack<Integer> stack = new Stack<>();
        if(postFix.matches("[0-9]*"))
            return Integer.valueOf(postFix);
        for (int i = 0; i < postFix.length(); i++) {
            char ch = postFix.charAt(i);
            if (ch <='9' && ch >='0')
                stack.push(ch -'0');
            else {
                int num2 = stack.pop() ;
                int num1 = stack.pop();
                switch (ch){
                    case '+':
                        stack.push( (num1 +num2));
                        break;
                    case '*':
                        stack.push( (num1 *num2));
                        break;
                    case '-':
                        stack.push( (num1 -num2));
                        break;
                    case '/':
                        stack.push( (num1 /num2));
                        break;
                }
            }
        }

        return stack.pop();
    }

//    static class Elem1{
//        int value;
//        Elem1 next;
//        public Elem1(int value){
//            this.value = value;
//        }
//
//    }
//    static class stackLinkedList{
//        Elem1 head;
//        Elem1 tail;
//
//        public stackLinkedList(){
//            head = tail = null;
//        }
//        public boolean isEmpty(){
//            return head == null && tail == null;
//        }
//
//        public void push(int data){
//            if (head == null){
//                head = tail =  new Elem1(data);
//            }else {
//
//            }
//
//        }
//
//    }
//
//   static class MyQueue {
//
//        /** Initialize your data structure here. */
//        public MyQueue() {
//
//        }
//
//        /** Push element x to the back of queue. */
//        public void push(int x) {
//
//        }
//
//        /** Removes the element from in front of queue and returns that element. */
//        public int pop() {
//
//        }
//
//        /** Get the front element. */
//        public int peek() {
//
//        }
//
//        /** Returns whether the queue is empty. */
//        public boolean empty() {
//
//        }
//    }


    static void generateBinaryBit(int m, int n){
        int[] ans = new int[m+n];
        for (int i = 0; i < m; i++) {
            ans[i] = 1;
        }
        for (int i = m; i <n+ m; i++) {
            ans[i] = 0;
        }
        int d=0;
        for (int start = m-1; start >=0 ; start--) {
            int i= start;
            while (true){
                print(ans);
                while (i<m+n-d && ans[i] != 0)
                    ans[i++] = 0;
                if (i <m+n-d) ans[i] = 1;
                else{
                    ans[i-1] =1;
                    break;
                }
            }
            d++;
        }
    }

    static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    /**Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * Output: 22
     * Explanation:
     * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22*/
    static int evalRPN(String[] postFix) {
        Stack<Integer> stack = new Stack<>();
        // if(postFix.matches("[0-9]*"))
        //     return Integer.valueOf(postFix);
        for (int i = 0; i < postFix.length; i++) {

            if (postFix[i].matches("[0-9]*") || (postFix[i].contains("-") && postFix[i].length() > 1))
                stack.push(Integer.valueOf(postFix[i]));
            else {
                int num2 = stack.pop() ;
                int num1 = stack.pop();
                switch (postFix[i]){
                    case "+":
                        stack.push( (num1 +num2));
                        break;
                    case "*":
                        stack.push( (num1 *num2));
                        break;
                    case "-":
                        stack.push( (num1 -num2));
                        break;
                    case "/":
                        stack.push( (num1 /num2));
                        break;
                }
            }
        }

        return stack.pop();
    }
    public static void main(String...arg){
//        System.out.println(inFixToPostFix("1+1"));
//        System.out.println(calPostFix(inFixToPostFix("4")));
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        //generateBinaryBit(3,2);

    }
}

