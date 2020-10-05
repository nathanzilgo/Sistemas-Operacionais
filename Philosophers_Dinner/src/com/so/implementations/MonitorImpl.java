package com.so.implementations;

import com.so.enums.PhilosopherStates;
import com.so.models.Dinner;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class MonitorImpl extends Dinner {

    public MonitorImpl(int howManyPhilosophers) {
        super(howManyPhilosophers);

        this.philosophers = new ArrayList<>(howManyPhilosophers);

        this.fillPhilosophers();
    }

    /**
     * Preenche os estados e os semaforos nos filósofos inicialmente
     */
    private void fillPhilosophers(){

        for(int i = 0; i < this.howManyPhilosophers; i++){

            this.philosopherStates.add(PhilosopherStates.THINKING);
            this.philosophers.add(new Semaphore(0));
        }
    }

    @Override
    public void takeTalher(int id) {

        this.philosopherStates.set(id, PhilosopherStates.HUNGRY);
        System.out.println(this.philosopherStates);

        synchronized(this.philosophers.get(id)) {

            if (possibleEat(id)){
                this.philosopherStates.set(id, PhilosopherStates.EATING);
            }

            if (this.philosopherStates.get(id) != PhilosopherStates.EATING){

                try{
                    this.philosophers.get(id).wait();

                } catch (InterruptedException e){
                    e.printStackTrace();
                    e.getMessage();
                }
            }
            System.out.println("Estados atuais: " + this.philosopherStates);
        }
    }

    @Override
    public void lendTalher(int id) {

        this.philosopherStates.set(id, PhilosopherStates.THINKING);
        System.out.println(this.philosopherStates);

        if (this.philosopherStates.get(left(id)) == PhilosopherStates.HUNGRY
                && possibleEat(left(id))) {

            this.philosopherStates.set(left(id), PhilosopherStates.EATING);

            synchronized (this.philosophers.get(left(id))) {
                this.philosophers.get(left(id)).notify();
            }
        }

        if (this.philosopherStates.get(right(id)) == PhilosopherStates.HUNGRY
                && possibleEat(right(id))) {

            this.philosopherStates.set(right(id), PhilosopherStates.EATING);

            synchronized (this.philosophers.get(right(id))) {
                this.philosophers.get(right(id)).notify();
            }

        }
        System.out.println("Estados atuais: " + this.philosopherStates);
    }

}