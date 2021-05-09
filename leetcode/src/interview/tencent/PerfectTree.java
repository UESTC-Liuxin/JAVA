package interview.tencent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerfectTree {
    public static void main(String[] args) {
        PerfectTree.prefectTree();
    }

    static void prefectTree() {
        Scanner scanner = new Scanner(System.in);
        String[] nodes = scanner.nextLine().split(",");
        List<String> result = new ArrayList<>();
        if(nodes.length<1){
            System.out.println("");
            return;
        }
        System.out.print(nodes[0]);
        for (int i = 1; i < nodes.length;) {
            if(nodes[i].equals("#")){
                return;
            }

            if(i+1>=nodes.length||nodes[i+1].equals("#")){
                return;
            }
            System.out.print(",");
            System.out.print(nodes[i]+",");
            System.out.print(nodes[i+1]);
            i+=2;
        }
    }
}
