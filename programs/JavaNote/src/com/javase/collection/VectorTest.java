package com.javase.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class VectorTest {
    public static void main(String[] args) {
        List l1 =new ArrayList();

        Collections.synchronizedList(l1);
        l1.add(1);


    }
}
