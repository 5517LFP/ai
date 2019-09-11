package com.example.ai.IO;

public class TestShortfomateINT {
    public static void testType(Short i){
        System.out.println("short");
    }
    public static void testType(int i){
        System.out.println("int");
    }

    public static void main(String[] args) {
        short i=6;
       testType(i);
    }

}
