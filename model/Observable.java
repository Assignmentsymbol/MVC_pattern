package de.tum.in.ise.model;

import de.tum.in.ise.view.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();

    /** To simulate instant-reactions(smart changing GUI), there are two ways to achieve. One
     * is (observer)"keep observing/watching" statement by statement, it's perhaps more intuitive
     * -but it might lead to an unnecessary computational resource cost. So we rather use another
     * approach i.e. "tell observer take a glance when anything changed" then they can still act as
     * if they've been watching all time.(Then call the update to refresh the GUI rendered stuff
     * and update some data).
         * **/
    public void notifyObservers() {
        for(Observer observer:observers){
            observer.update();
        }
        //TODO Part 1: Implement notifyObservers().
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
