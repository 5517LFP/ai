package com.example.ai.IO;

import javax.jws.soap.SOAPBinding;
import javax.sound.midi.Soundbank;
import java.io.*;

public class TestStringbuffer {


    public static void main(String[] args) {
        StringBuffer a=new StringBuffer("A");
        StringBuffer b=new StringBuffer("B");
        StringBuffer c=new StringBuffer();
        int z=4;
        int w=2;
        int q=3;

        operate2(z,w,q);
        System.out.println("z"+z);
        System.out.println("q"+q);
        c=b;
        operate(a,b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(3/2);

        String foo="blue";
        String bar=foo;
        foo="green";
        System.out.println(bar);
        System.out.println(foo);
        try {
            File file=new File("file.txt");

            OutputStream opt=new FileOutputStream(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void operate(StringBuffer x,StringBuffer y){
        x.append(y);
        y=x;
        System.out.println("y"+y);
    }

    public  static  void operate2(int x,int y,int q){
        x<<=y;
        System.out.println("opeerat2"+x);
        q++;
        System.out.println("m"+q);
    }




}
