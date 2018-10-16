package com.company;

import java.util.ArrayList;
import java.util.Map;

public class DFAState {
    private String state;
    private String transitionA;
    private String transitionB;

    public DFAState(String state, String transitionA, String transitionB) {
        this.state = state;
        this.transitionA = transitionA;
        this.transitionB = transitionB;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTransitionA() {
        return transitionA;
    }

    public void setTransitionA(String transitionA) {
        this.transitionA = transitionA;
    }

    public String getTransitionB() {
        return transitionB;
    }

    public void setTransitionB(String transitionB) {
        this.transitionB = transitionB;
    }

    @Override
    public String toString() {
        return "DFAState{" +
                "state='" + state + '\'' +
                ", transitionA='" + transitionA + '\'' +
                ", transitionB='" + transitionB + '\'' +
                '}';
    }
}
