package com.company;

import java.util.Map;

public class DFAState {
    private String state;
    private Map<Character, String> transitions;

    public DFAState(String state, Map<Character, String> transitions) {
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
        return "DFAState{" +
                "state='" + state + '\'' +
                ", transitions=" + transitions +
                '}';
    }
}
