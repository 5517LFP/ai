package com.example.ai.IO;

public class Testenum {
    public enum weekDays{
        MON(1,"星期一"), TUE(2,"星期二"), WED(3,"星期三"), THU(4,"星期四"), FRI(5,"星期五"), SAT(6,"星期六");

        private  String chinessname;
        private  int num;
        weekDays (int num,String chinesename){
            this.num=num;
            this.chinessname=chinesename;
        }


    }

    public static void main(String[] args) {
        weekDays week=weekDays.FRI;
        System.out.println("getNum"+week.num);
        System.out.println("chineseName"+week.chinessname);
        Enum<weekDays> weekDaysEnum;
        for(weekDays we:weekDays.values()){
            System.out.println(we.chinessname);

        }
    }
}
