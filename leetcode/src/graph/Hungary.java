package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Hungary {
    public static void main(String[] args) {
        Hungary hungary = new Hungary();
        int all = hungary.HungaryMatch(
                new int[][]{
                        new int[]{0,103},
                        new int[]{1,103},
                        new int[]{1,104},
                        new int[]{2,104},
                        new int[]{2,105},
                        new int[]{2,106},
                        new int[]{3,103}
                }
        );
        System.out.println(all);

    }

    // 输入是多个连接线段
    public int HungaryMatch(int[][] lines) {
        int N = lines.length;
        HashMap<Integer, HashSet<Integer>> map = new HashMap();

        HashMap<Integer, Integer> girls = new HashMap<>();
        HashSet<Integer> used = new HashSet<>();

        // 构建连接表
        for (int i = 0; i < lines.length; i++) {
            if (!map.containsKey(lines[i][0])) {
                HashSet<Integer> temp = new HashSet<>();
                map.put(lines[i][0], temp);
            }
            HashSet<Integer> temp = map.get(lines[i][0]);
            temp.add(lines[i][1]);

            //构建girls表, 默认连接为-1
            girls.put(lines[i][1], -1);

        }

        int all = 0;
        //给每个男孩儿匹配
        for (Integer boy : map.keySet()) {
            used.clear();
            if (find(map, girls, used, boy))
                all++;
        }
        return all;
    }

    private boolean find(HashMap<Integer, HashSet<Integer>> map, HashMap<Integer, Integer> girls,
                         HashSet<Integer> used, int x) {
        for (Integer girl : girls.keySet()) { //查看每一个妹子
            //如果有联系但是没有标记过
            if (map.get(x).contains(girl) && !used.contains(girl)) {
                used.add(girl);
                if (girls.get(girl) == -1 || find(map, girls, used, girls.get(girl))) {
                    girls.put(girl, x);
                    return true;
                }
            }
        }
        return false;
    }
}
