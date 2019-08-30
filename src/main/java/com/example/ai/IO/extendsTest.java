package com.example.ai.IO;


public class extendsTest{


    public String aa(int a){
        return "a";

    }
    public String aa(int... a){
        return "b";
    }

    public void replatej(String text){
         String textz=text.replace("j","i");
        System.out.println(text);

    }

    public static void main(String[] args) {

        String s = null;

        assert s!=null?true:false;

        assert false;

        System.out.println("end");

      /*assert false;
        extendsTest e=new extendsTest();
        System.out.println(e.aa(1,2));
        String java=new String("java");
        e.replatej(java);
        System.out.println(java);*/
    }

}
