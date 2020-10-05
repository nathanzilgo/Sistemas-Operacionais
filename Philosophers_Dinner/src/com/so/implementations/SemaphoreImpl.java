package com.so.implementations;

import com.so.enums.PhilosopherStates;
import com.so.models.Dinner;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SemaphoreImpl extends Dinner {

    private Semaphore mutex;
    private int initialValue;

    public SemaphoreImpl(int howManyPhilosophers, int initialValue) {
        super(howManyPhilosophers);

        this.mutex = new Semaphore(initialValue);
        this.philosophers = new ArrayList<Semaphore>(this.howManyPhilosophers);

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

    /**
     * O filósofo de Id passado pega o talher
     * @param id
     */
    @Override
    public void takeTalher(int id)
    {
        try{
            mutex.acquire();
            System.out.println("Estados atuais: " + philosopherStates);

        } catch (InterruptedException e) {

            System.out.println("Interrupted Exception");
        }

        this.philosopherStates.set(id, PhilosopherStates.HUNGRY);

        if( possibleEat(id)){
            this.philosophers.get(id).release();
            this.philosopherStates.set(id, PhilosopherStates.EATING);
        }

        mutex.release();
        try{
            this.philosophers.get(id).acquire();
            System.out.println("Estados atuais: " + this.philosopherStates);

        } catch (InterruptedException e){

            System.out.println("Interrupted Exception");
        }
    }

    /**
     * O filósofo do Id passado devolve o talher
     * @param id
     */
    @Override
    public void lendTalher(int id)
    {
        try{
            mutex.acquire();
            System.out.println("Estados atuais: " + this.philosopherStates);

        } catch (InterruptedException e){

            System.out.println("Interrupted Exception");
        }

        this.philosopherStates.set(id, PhilosopherStates.HUNGRY);

        if (philosopherStates.get(right(id)) == PhilosopherStates.HUNGRY && possibleEat(right(id))) {
            this.philosopherStates.set(right(id), PhilosopherStates.EATING);
            this.philosophers.get(right(id)).release();
        }

        if (this.philosopherStates.get(left(id)) == PhilosopherStates.HUNGRY && possibleEat(left(id))) {
            this.philosopherStates.set(left(id), PhilosopherStates.EATING);
            this.philosophers.get(left(id)).release();
        }

        System.out.println("Estados atuais:" + philosopherStates);

        mutex.release();
    }
}
