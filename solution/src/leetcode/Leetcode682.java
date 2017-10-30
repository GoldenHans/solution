package leetcode;

import java.util.Stack;

public class Leetcode682 {
    /*
     * 我的问题： 1.不知道stack拥有peek方法,可以直接观察顶端第一个元素
     * 2.对问题没有分析清楚本质,本质是每一局都有自己的得分,不需要每一次都去进行sum,简单的逻辑是将每一局的分数算出来统一加 这样会减少无用的代码
     * 3.
     * 我将"C","D","+"的逻辑都写在了异常处理中,想法有些怪,其实在自己的答案中,我已经将每一局的得分放入了,但是还是没有想到本质,所以造成了怪异的想法
     */
    public static int calPoints(String[] ops) {
        if (ops.length < 1 || ops.length > 1000) return -1;
        int sum = 0;
        Stack<String> opStack = new Stack<String>();
        for (String op : ops) {
            try {
                int opVal = Integer.valueOf(op);
                if (opVal < -30000 || opVal > 30000) return -1;
                sum += opVal;
                opStack.push(op);
            } catch (NumberFormatException e) {
                if ("C".equals(op)) {
                    int lastVal = Integer.valueOf(opStack.pop());
                    sum -= lastVal;
                } else if ("D".equals(op)) {
                    int lastVal = Integer.valueOf(opStack.peek());
                    opStack.push(String.valueOf(lastVal * 2));
                    sum += lastVal * 2;
                } else if ("+".equals(op)) {
                    if (opStack.size() < 2) return -1;
                    int lastVal1 = Integer.valueOf(opStack.peek());
                    int lastVal2 = Integer.valueOf(opStack.elementAt(opStack.size() - 2));
                    opStack.push(String.valueOf(lastVal1 + lastVal2));
                    sum += lastVal1 + lastVal2;
                } else {
                    return -1;
                }
            }
        }
        return sum;
    }
    
    public static int callPointsAnswer(String[] ops) {
        if (ops.length < 1 || ops.length > 1000) return -1;
        Stack<Integer> opStack = new Stack<Integer>();
        for (String op : ops) {
            if ("+" == op) {
                if (opStack.size() < 2) return -1;
                int lastlastVal = opStack.elementAt(opStack.size() - 2);
                opStack.push(lastlastVal + opStack.peek());
            }else if("C" == op){
                opStack.pop();
            } else if ("D" == op) {
                opStack.push(opStack.peek() * 2);
            } else {
                opStack.push(Integer.valueOf(op));
            }
        }
        int sum = 0;
        for (int val : opStack) {
            sum += val;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Leetcode682.callPointsAnswer(new String[] { "5", "2", "C", "D", "+" }));
    }
}
