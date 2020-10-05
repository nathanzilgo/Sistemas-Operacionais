package com.so;

import com.so.enums.Options;
import com.so.implementations.MonitorImpl;
import com.so.implementations.SemaphoreImpl;
import com.so.models.Dinner;
import com.so.models.Philosopher;

import java.util.Scanner;

public class Main {

    private static int howManyPhilosophers;
    private static final int eatingTime = 500;
    private static final int thinkingTime = 1000;
    private static Options opt;
    static Scanner scanner = new Scanner(System.in);

    private static Dinner dinner;

    public static void main(String[] args) {

        System.out.println("Insira a quantidade de filósofos: ");
        howManyPhilosophers = scanner.nextInt();

        System.out.println("Resolver utilizando Semáforos(0) ou Monitores(1)? ");
        opt = Options.values()[ scanner.nextInt() ];

        if(opt.equals(Options.SEMAPHORE)){
            dinner = new SemaphoreImpl(howManyPhilosophers, 1);
        }else{
            dinner = new MonitorImpl(howManyPhilosophers);
        }

        execute();
    }

    public static void execute(){

        for(int i = 0; i < howManyPhilosophers; i++){
            new Philosopher(i, eatingTime, thinkingTime, dinner );
        }
    }
}
