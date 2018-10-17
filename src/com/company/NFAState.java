package com.company;

import java.util.ArrayList;
import java.util.Map;

public class NFAState {
    private Character state;
    private ArrayList<Character> transitionA;
    private ArrayList<Character> transitionB;
    private Map<Character, ArrayList<Character>> transitions;

    public NFAState(Character state, Map<Character, ArrayList<Character>> transitions) {
        this.state = state;
        this.transitions = transitions;
    }

    public NFAState(Character state, ArrayList<Character> transitionA, ArrayList<Character> transitionB) {
        this.state = state;
        this.transitionA = transitionA;
        this.transitionB = transitionB;
    }
    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public Map<Character, ArrayList<Character>> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<Character, ArrayList<Character>> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<Character> getTransitionA() {
        return transitionA;
    }

    public void setTransitionA(ArrayList<Character> transitionA) {
        this.transitionA = transitionA;
    }

    public ArrayList<Character> getTransitionB() {
        return transitionB;
    }

    public void setTransitionB(ArrayList<Character> transitionB) {
        this.transitionB = transitionB;
    }


    @Override
    public String toString() {
        return "NFAState{" +
                "state=" + state +
                ", transitionA=" + transitionA +
                ", transitionB=" + transitionB +
                '}';
    }
}
