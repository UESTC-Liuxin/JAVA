package interview.wangyi;

import java.util.*;


public class Main1 {
    public static void main(String[] args) {
        Main1 main1 = new Main1();
        System.out.println(main1.minWater(new int[]{2,4,3,3,2,4}));
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 输入
     * @param height int整型一维数组 木桩的高度数组，数组长度不大于100000
     * @return int整型
     */
    public int minWater (int[] height) {
        // write code here
        int ans = Integer.MAX_VALUE;
        int temp = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < height.length; i++){ //i表示右柱子
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]){ //栈为空表示新的水池，然后右边柱子要比左边高
                int cur = stack.peek();
                stack.pop();
                if(stack.isEmpty()) break;
                int l = stack.peek();
                int r = i;
                int h = Math.min(height[r], height[l]) - height[cur];
                temp += (r - l -1) * h;
            }
            if(stack.isEmpty() && temp!=0) {
                ans = Math.min(ans, temp);
                temp = 0; //清空
            }
            stack.push(i);
        }
        return ans;
    }
}