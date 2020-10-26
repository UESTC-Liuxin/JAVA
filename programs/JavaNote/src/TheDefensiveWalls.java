import java.util.*;
import java.util.Collections;

import java.lang.*;
import java.io.*;





public class TheDefensiveWalls {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        ArrayList<Line> list1 = new ArrayList<Line>();// x方向
        ArrayList<Line> list2 = new ArrayList<Line>();// y方向
        for (int i = 0; i < num; i++) {
            int lx = in.nextInt();
            int ly = in.nextInt();
            int rx = in.nextInt();
            int ry = in.nextInt();
            if (ly == ry) {
                list1.add(new Line(lx, ly, rx, ry, 1));
            } else if(lx==rx){
                list2.add(new Line(lx, ly, rx, ry, 2));
            }
        }
        int result = 0;
        Collections.sort(list1);

        for (int i = 0; i < list1.size()-1; i++) {
            for (int j = i + 1; j < list1.size(); j++) {
                Line x1 = list1.get(i);
                Line x2 = list1.get(j);
                for (int m = 0; m < list2.size()-1; m++) {
                    for (int n = m + 1; n < list2.size(); n++) {
                        Line x3 = list1.get(m);
                        Line x4 = list1.get(n);
                        if(Is(x1,x2,x3)&&Is(x1,x2,x4)) {//遍历两队平行线是否能围城矩形
                            result+=((x2.ly-x1.ly)*(x4.lx-x3.lx));
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    // 判断边是否交于平行线
    public static boolean Is(Line x1, Line x2, Line x3) {
        if (x1.kind == 1) {
            return (x2.ly <= x3.ry) && (x1.ly >= x3.ly);
        } else if (x1.kind == 2) {
            return (x3.lx <= x1.lx) && (x3.rx >= x2.rx);
        } else {
            return false;
        }

    }

}

class Line implements Comparable<Line> {
    int lx;
    int ly;
    int rx;
    int ry;
    int kind;

    public Line(int lx, int ly, int rx, int ry, int kind) {
        super();
        this.lx = lx;
        this.ly = ly;
        this.rx = rx;
        this.ry = ry;
        this.kind = kind;//kind 为1代表平行x轴，2代表平行y轴
    }

    @Override
    public int compareTo(Line o) {
        if (kind == 1) {
            return this.ly - o.ly;
        } else if (kind == 2) {
            return this.lx - o.lx;
        }
        return 0;
    }
}