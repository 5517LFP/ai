package com.example.ai.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Testthread {
    int x=0;
    public class Runner implements Runnable{

        @Override
        public void run() {
            int current=0;
            for (int i = 0; i < 4; i++) {
                current=x;
                System.out.println(current+",");
                x=current+2;
            }
        }
    }

    public void go(){
        Runnable r1=new Runner();
        new Thread(r1).start();
        new Thread(r1).start();
        synchronized(this){
            System.out.println("one");
        }
    }

    static  double on(){
        return 1.0;
    }

    public static void main(String[] args) {
        //new Testthread().go();
        String str="null";

        if(str==null){
            System.out.println("null");
        }else if(str.length()==0){
            System.out.println("zero");

        }else{
            System.out.println("some");
        }

        try {
            OutputStream outputStream=new FileOutputStream(new File("aa.txt"),true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
