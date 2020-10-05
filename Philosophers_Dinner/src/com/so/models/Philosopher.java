package com.so.models;

public class Philosopher implements Runnable {

    private int id;
    private int thinkinkTime;
    private int eatingTime;
    private Dinner dinner;

    /**
     * Cria um filósofo e para cada um, uma Thread com o seu ID
     * @param id
     * @param thinkinkTime
     * @param eatingTime
     * @param dinner
     */
    public Philosopher(int id, int thinkinkTime, int eatingTime, Dinner dinner) {
        this.id = id;
        this.thinkinkTime = thinkinkTime;
        this.eatingTime = eatingTime;
        this.dinner = dinner;

        new Thread( (Runnable) this, "Philo" + id).start();
    }

    /**
     * Jornada do filósofo
     */
    @Override
    public void run() {

        while (true) {
            deepThink();
            takeTalher();
            eat();
            lendTalher();
        }
    }

    /**
     * Coloca a Thread do filósofo para "dormir" pelo tempo de pensamento do filósofo
     */
    private void deepThink () {
        try {
            Thread.sleep(this.thinkinkTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    /**
     * Pega o talher para si
     */
    private void takeTalher () {
        dinner.takeTalher(this.id);
    }

    /**
     * Devolve o talher para outro filósofo
     */
    private void lendTalher () {
        dinner.lendTalher(this.id);
    }

    /**
     * Coloca a Thread do filósofo para "dormir" pelo tempo que ele está comendo
     */
    private void eat () {
        try {
            Thread.sleep(this.eatingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
