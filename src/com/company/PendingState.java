package com.company;

import java.util.ArrayList;

public class PendingState {
    private String newState;
    private ArrayList<Character>transitionAs;
    private ArrayList<Character>transitionBs;
    private boolean isNFA;

    public PendingState(String newState, ArrayList<Character> transitionAs, ArrayList<Character> transitionBs) {
        this.newState = newState;
        this.transitionAs = transitionAs;
        this.transitionBs = transitionBs;
        isNFA = false;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(String newState) {
        this.newState = newState;
    }

    public ArrayList<Character> getTransitionAs() {
        return transitionAs;
    }

    public void setTransitionAs(ArrayList<Character> transitionAs) {
        this.transitionAs = transitionAs;
    }

    public ArrayList<Character> getTransitionBs() {
        return transitionBs;
    }

    public void setTransitionBs(ArrayList<Character> transitionBs) {
        this.transitionBs = transitionBs;
    }

    public boolean isNFA() {
        return isNFA;
    }

    public void setNFA(boolean NFA) {
        isNFA = NFA;
    }

    @Override
    public String toString() {
        return "PendingState{" +
                "newState='" + newState + '\'' +
                ", transitionAs=" + transitionAs +
                ", transitionBs=" + transitionBs +
                '}';
    }
}
