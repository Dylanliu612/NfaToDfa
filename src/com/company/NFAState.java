package com.company;

import java.util.ArrayList;
import java.util.Map;

public class NFAState {
    private Character state;
    private Map<Character, ArrayList<Character>> transitions;

    public NFAState(Character state, Map<Character, ArrayList<Character>> transitions) {
        this.state = state;
        this.transitions = transitions;
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


    @Override
    public String toString() {
        return "NFAState{" +
                "state='" + state + '\'' +
                ", transitions=" + transitions +
                '}';
    }
}
