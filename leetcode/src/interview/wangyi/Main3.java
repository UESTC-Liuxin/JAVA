//package interview.wangyi;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class Main3 {
//    public static void main(String[] args) {
//
//    }
//
//    class TreeNode {
//        int val = 0;
//        TreeNode left = null;
//        TreeNode right = null;
//
//        public TreeNode(int val) {
//            this.val = val;
//        }
//    }
//
//    public int[] GetTreeCenter(TreeNode root) {
//        // write code here
//
//        //创建一个数组，利用数组来计算
//        LinkedList<Integer> arr = new LinkedList<>();
//        createList(root, arr);
//        int[] nw = new int[arr.size()];
//        int i = 0;
//        int x = (int) (Math.floor(Math.log(i) / Math.log(2)) + 1);
//
//        if (x % 2 == 1) {
//            i = 0;
//            int a = 1 << ((x - 1) / 2);
//            int b = (1 << ((x + 1) / 2)) - 1;
//            for (int j = a; j <= b; j++) {
//                if (arr.get(j) != -1)
//                    nw[i++] = arr.get(j);
//            }
//        } else {
//            int a = 1 << (x / 2) - 1;
//            int b = (1 << (x / 2)) - 1;
//            i = 0;
//            for(int j= a;j<=b;j++){
//                if(arr.get(j)!=-1)
//                    nw[i++] = arr.get(j);
//            }
//        }
//
//        int[] newArr = new int[arr.size()];
//        for(int index = 0;i<arr.size();index++)
//            newArr[i] = arr.get(index);
//
//        Arrays.sort(newArr);
//
//        return new int[]{newArr}
//
//
//    }
//
//    public void createList(TreeNode root, LinkedList<Integer> arr) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            TreeNode temp = queue.poll();
//            if (temp == null) {
//                arr.add(-1);
//                continue;
//            }
//            arr.add(temp.val);
//            if (temp.left == null && temp.right == null)
//                continue;
//            queue.add(temp.left);
//            queue.add(temp.right);
//        }
//
//    }
//
//}
