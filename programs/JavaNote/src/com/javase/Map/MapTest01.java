package com.javase.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MapTest01 {
    public static void main(String[] args) {
        Map<Integer,String> map =new HashMap<>();
        map.put(1,"Liu");
        map.put(2,"liang");


        Set<Map.Entry<Integer,String>> set = map.entrySet();
        for (Map.Entry entry : set){
            System.out.println(entry);
        }
    }

}

