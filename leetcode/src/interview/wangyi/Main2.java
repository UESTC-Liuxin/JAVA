//package interview.wangyi;
//
//import java.util.HashSet;
//
//public class Main2 {
//    public static void main(String[] args) {
//        Main2 main2 = new Main2();
//        System.out.println(main2.getMostStrLength("abc%","zxab%c%%",new char[]{'%','#'}));
//    }
//
//    public int getMostStrLength(String s0, String s1, char[] cList) {
//        // write code here
//        //先删除噪声字符
//        HashSet<Character> set = new HashSet<>();
//        for (char c : cList) {
//            set.add(c);
//        }
//
//        int max = 0;
//        int N0 = s0.length();
//        int N1 = s1.length();
//        //s0不动
//        for (int i = 0; i < N0; i++) {
//            int len = Math.min(N1, N0 - i);
//            int maxLen = maxLength(s0,s1,i,0,len,set);
//            max = Math.max(max, maxLen);
//        }
//        //
//        for (int i = 0; i < N1; i++) {
//            int len = Math.min(N0, N1 - i);
//            int maxLen = maxLength(s0,s1,0,i,len,set);
//            max = Math.max(max, maxLen);
//        }
//        return max;
//
//    }
//
//
//    public int maxLength(String s0, String s1, int indexA, int indexB, int len, HashSet<Character> set) {
//        int ans = 0;
//        int k = 0;
//
//        for (int i = 0; i < len; i++) {
//            if (s0.charAt(indexA+i) == s1.charAt(indexB + i) || set.contains(s0.charAt(indexA+i))
//                    || set.contains(s1.charAt(indexB+i))) {
//                k++;
//            } else{
//                k = 0;
//            }
//
//        }
//
//    }
//
//}
