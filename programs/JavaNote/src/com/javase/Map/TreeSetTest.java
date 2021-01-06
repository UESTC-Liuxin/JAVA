package com.javase.Map;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        Set<String> set = new TreeSet();
        set.add("liuxin");
        set.add("liusiyu");
        set.add("zhangyijie");

        for(String s:set){
            System.out.println(s);
        }
        //        liusiyu
        //        liuxin
        //        zhangyijie
    }
}
