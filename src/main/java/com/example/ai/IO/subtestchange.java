package com.example.ai.IO;

import com.sun.org.apache.xpath.internal.axes.SubContextList;

public class subtestchange extends testchange {
    public byte getNumber(){
        return 2;
    }

    public static void main(String[] args) {
        subtestchange s=new subtestchange();
        System.out.println(s.getNumber());
    }
}

