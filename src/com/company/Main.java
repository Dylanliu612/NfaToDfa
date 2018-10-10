package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Number of states: ");
        Scanner sc = new Scanner(System.in);
        int numberOfStates = Integer.parseInt(sc.nextLine());
        System.out.println("Number of transitions: ");
        int numberOfTransitions = Integer.parseInt(sc.nextLine());
        System.out.println("You have an NFA with " + numberOfStates + " states and " + numberOfTransitions + " transitions.");
        char[] states = aliasStates(numberOfStates);
        char[] transitions = aliasTransitions(numberOfTransitions);
        System.out.println("Your states are: ");
        printChars(states);
        System.out.println();
        System.out.println("Your transitions are: ");
        printChars(transitions);


    }

    public static char[] aliasStates(int numberOfStates) {
        char[] states = new char[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            states[i] = (char) ('A' + i);
        }
        return states;
    }

    public static char[] aliasTransitions(int numberOfTransitions) {
        char[] transitions = new char[numberOfTransitions];
        for (int i = 0; i < numberOfTransitions; i++) {
            transitions[i] = (char) ('a' + i);
        }
        return transitions;
    }

    public static void printChars(char[] chars) {
        for (char ch: chars) {
            System.out.print(ch + ", ");
        }
    }
}
