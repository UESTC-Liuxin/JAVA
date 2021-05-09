package interview.meituan;

import java.util.HashSet;

public class BeautifulSubStr {
    int sum = 0;

    public static void main(String[] args) {
        BeautifulSubStr beautifulSubStr = new BeautifulSubStr();
        beautifulSubStr.find("aa");
        System.out.println(beautifulSubStr.sum);

    }

    void find(String src) {
        char[] chars = src.toCharArray();
        HashSet visited = new HashSet();
        for (int i = 0; i < src.length(); i++) {
            visited.add(chars[i]);
            recur(visited,i+1,chars);
            visited.remove(chars[i]);
            sum++;
        }
        sum++; //空串

    }

    void recur(HashSet visited,int start,char[] chars) {
        for(int i=start;i<chars.length;i++){
            if (!visited.contains(chars[i])){
                visited.add(chars[i]);
                recur(visited,i+1,chars);
                visited.remove(chars[i]);
                sum++;
            }
        }

    }
}
