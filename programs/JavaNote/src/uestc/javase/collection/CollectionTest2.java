package uestc.javase.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionTest2 {
    public static void main(String[] args) {
        Collection c = new HashSet();
        c.add(new String("aa"));
        c.add(new String("bb"));
        c.add(100);
        c.add(new Object());

        Iterator it = c.iterator();
        while(it.hasNext()){
            
        }
    }
}
