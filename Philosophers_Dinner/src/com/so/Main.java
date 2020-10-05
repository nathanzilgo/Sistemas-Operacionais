package com.so;

import com.so.enums.Options;
import com.so.implementations.MonitorImpl;
import com.so.implementations.SemaphoreImpl;
import com.so.models.Dinner;

import java.util.Scanner;

public class Main {

    private static int howManyPhilosophers;
    private final int eatingTime = 500;
    private final int thinkingTime = 1000;
    private static Options opt;
    static Scanner scanner = new Scanner("");

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
    }
}
