package com.example.ai.IO;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.TreeSet;

public class testSynchorized {
    public static void main(String[] args) {
        final StringBuffer one=new StringBuffer();
        final StringBuffer two=new StringBuffer();

        String i="22";
        int z=3;
        String w=z+i;
        System.out.println(w);

        synchronized (one){
          one.append("one");
          two.append("one");
        }
        System.out.println(one);
        System.out.println(two);
        TreeSet set=new TreeSet();
        String[] array1={"blue","red","green","yellow","orange"};
        Arrays.sort(array1);
        for(String color:array1){
            System.out.println(color);
        }
        System.out.format("pi is %f",Math.PI);

        System.out.format("%-15s %5s %10s\n", "item","qty","price");
        System.out.format("%-15s %5s %10s\n", "----","---","-----");
        System.out.println("123456789123456789123456789123456789123456789");
        System.out.format("%-15.15s %5d %10.2f\n", "lalala",48563233,4236.25665);

    }
}
