package leetcode;

import java.util.Stack;

public class Leetcode20 {
    /*
     * 我的问题： 1.没有stack类的概念,导致自己有数组与指针构建了stack
     * 2.思维是按照从后往前的匹配方式来思考的问题,虽然结果正常,但可以尝试正常的思维,从前向后匹配
     */
    public static boolean isVaild1(String str) {
        if (str == null || str.length() <= 0) return true;
        char[] charArr = str.toCharArray();
        int arrPointer = charArr.length - 1;
        char[] charStack = new char[str.length()];
        int stackPointer = 0;
        charStack[stackPointer] = charArr[arrPointer--];
        for (int i = arrPointer; i >= 0; i--) {
            char symbol = charArr[i];
            if (symbol == '(') {
                if (stackPointer >= 0 && charStack[stackPointer] == ')') {
                    arrPointer--;
                    stackPointer--;
                } else {
                    return false;
                }
            } else if (symbol == '[') {
                if (stackPointer >= 0 && charStack[stackPointer] == ']') {
                    arrPointer--;
                    stackPointer--;
                } else {
                    return false;
                }
            } else if (symbol == '{') {
                if (stackPointer >= 0 && charStack[stackPointer] == '}') {
                    arrPointer--;
                    stackPointer--;
                } else {
                    return false;
                }
            } else {
                charStack[++stackPointer] = symbol;
            }
        }
        return stackPointer == -1;
    }
    
    public static boolean isValidAnswer(String str) {
        if (str == null || str.length() <= 0) return true;
        Stack<Character> tempStack = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                tempStack.push(c);
            } else {
                if (tempStack.isEmpty()) {
                    return false;
                } else {
                    Character item = tempStack.pop();
                    if ((item == '(' && c == ')') || (item == '[' && c == ']') || (item == '{' && c == '}')) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        return tempStack.isEmpty();
    }
    
    public static void main(String[] args) {
        System.out.println(Leetcode20.isValidAnswer("([}]}}"));
    }
}
