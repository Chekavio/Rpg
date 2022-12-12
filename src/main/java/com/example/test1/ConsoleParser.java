package com.example.test1;


import java.util.Scanner;


public class ConsoleParser implements InputParser{

    Scanner scan = new Scanner(System.in);

    public int getInt(){
        while(!scan.hasNextInt()){
            System.out.println("The input is invalid, try again");
            scan.nextLine();
        }
        int val = scan.nextInt();
        scan.nextLine();
        return val;
    }
    public int getIntInRange(int min, int max){
        while(!scan.hasNextInt()){
            System.out.println("The input is invalid, try again");
            scan.nextLine();
        }
        int val = scan.nextInt();
        scan.nextLine();
        while(val < min || val > max){
            System.out.println("Selectionnez une valeure entre "+min+" et "+max);
            scan.nextInt();
            while(!scan.hasNextInt()){
                System.out.println("The input is invalid, try again");
                scan.nextLine();
            }
        }
        scan.nextLine();
        return val;
    }

    @Override
    public void printMessage(String string) {System.out.println(string);
    }

    @Override
    public void printStringBuilder(StringBuilder stringBuilder) {
        System.out.println(stringBuilder);
    }


}


