package com.company;

import java.util.Map;

public class State {
    private String state;
    private Map<Character, String> transitions;

    public State(String state,  Map<Character, String> transitions) {
        this.state = state;
        this.transitions = transitions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<Character, String> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<Character, String> transitions) {
        this.transitions = transitions;
    }

    @Override
    public String toString() {
        return "State{" +
                "state='" + state + '\'' +
                ", transitions=" + transitions +
                '}';
    }
}
