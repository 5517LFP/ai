package com.example.ai.IO;

public class testStringBuffertwo {
    public static void main(String[] args) {
        final StringBuffer one=new StringBuffer();
        final int i;
        i=2;

        System.out.println(i);
        one.append("one");
        one.append("two");
        System.out.println(one);
        synchronized (one){
            one.append("three");
        }
        System.out.println(one);
    }

}
