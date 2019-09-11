package com.example.ai.IO;

import java.util.concurrent.ExecutionException;

public class ErrorTest {
    public void zz() throws Exception{

    }
    public void zzz() throws Error{
        System.out.println("onez");
    }

    public static void parse(String s){
        float f=0;
        try {
            f=Float.parseFloat(s);
        } catch (NumberFormatException e) {
            f=0;
        }finally {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        ErrorTest errorTest=new ErrorTest();
        errorTest.zzz();
        parse("invalid");
        String a="hello";
        System.out.println(a.length());
        //errorTest.one();
        //errorTest.two();
        errorTest.stringsplit();
        errorTest.equalstest();

    }

    public void one(){
        try{
            System.out.println("one");
        }finally {
            System.out.println("finally");
        }
    }
    public void two(){
        String s=null;
        s.concat("adb");
        System.out.println(s);
    }

    public void stringsplit(){
        String test="this is the test";
        String[] z=test.split("/s");
        System.out.println("z.length    "+z.length);
    }
    public void equalstest(){
        Integer i=new Integer(42);
        Double d=new Double(42.0);
        System.out.println(i.equals(d));
        System.out.println(d.equals(i));
        System.out.println(i.equals(42));
    }
}
