package com.so.models;

import com.so.enums.PhilosopherStates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public abstract class Dinner {

    protected List<PhilosopherStates> philosopherStates;
    protected List<Semaphore> philosophers;
    protected int howManyPhilosophers;

    public Dinner(int howManyPhilosophers) {
        this.howManyPhilosophers = howManyPhilosophers;
        this.philosopherStates = new ArrayList<PhilosopherStates>(howManyPhilosophers);
    }

    protected int right (int location){
        return (location + 1) % howManyPhilosophers;
    }

    protected int left (int location){
        return (location + howManyPhilosophers - 1) % howManyPhilosophers;
    }

    /**
     * O filósofo de id fornecido só pode comer se o da esquerda e o da direita não estiverem comendo
     * @param id do filósofo
     * @return boolean
     */
    protected boolean possibleEat (int id){
        boolean rightNotEating = philosopherStates.get(right(id)) != PhilosopherStates.EATING;
        boolean leftNotEating = philosopherStates.get(left(id)) != PhilosopherStates.EATING;

        return rightNotEating && leftNotEating;
    }

    public void takeTalher(int id) {
    }

    public void lendTalher(int id) {
    }
}
