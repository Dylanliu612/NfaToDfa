package com.company;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Number of states: ");
        Scanner sc = new Scanner(System.in);
        int numberOfStates = Integer.parseInt(sc.nextLine());
        System.out.println("Number of transitions: ");
        int numberOfTransitions = Integer.parseInt(sc.nextLine());
        System.out.println("You have an NFA with " + numberOfStates + " states and " + numberOfTransitions + " transitions.");

        String[] states = aliasStates(numberOfStates);
        Character[] transitions = aliasTransitions(numberOfTransitions);
        System.out.println("Your states are: ");
        printStrings(states);
        System.out.println();
        System.out.println("Your transitions are: ");
        printChars(transitions);

        System.out.println(generateNFAStateTransitions(states[0],transitions));


    }

    public static String[] aliasStates(int numberOfStates) {
        String[] states = new String[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            states[i] = "q" + i;
        }
        return states;
    }

    public static Character[] aliasTransitions(int numberOfTransitions) {
        Scanner sc = new Scanner(System.in);
        Character[] transitions = new Character[numberOfTransitions];
        System.out.println("What are your possible transitions (as single characters)? '0' for lambda.");
        for (int i = 0; i < numberOfTransitions; i++) {
            transitions[i] = sc.nextLine().charAt(0);
        }
        return transitions;
    }

    public static void printChars(Character[] chars) {
        for (Character ch : chars) {
            System.out.print(ch + ", ");
        }
        System.out.println();
    }

    public static void printStrings(String[] strings) {
        for (String string : strings) {
            System.out.print(string + ", ");
        }
        System.out.println();
    }

    public static State generateNFAStateTransitions(String state, Character[] transitions) {
        Scanner sc = new Scanner(System.in);
        Map<Character, String> stateTransitions = new HashMap<>();
        for (char transition : transitions) {
            do {
                System.out.println("State " + state + " with transition " + transition + " leads to state (enter '0' when done)");
                String transitionState = sc.nextLine();
                if (transitionState.equals("0")) {
                    break;
                }
                stateTransitions.put(transition, transitionState);
            }while(true);
        }

        return new State(state, stateTransitions);
    }

//    public static State generateNfaStateTransitions(Character state, Character[] transitions) {
//        Scanner sc = new Scanner(System.in);
//        Map<Character, Character> stateTransitions = new HashMap<>();
//        for (char transition : transitions) {
//            do {
//                String nextState;
//                char hasAnotherState;
//                System.out.println(state + ", " + transition + " ->: ");
//                nextState = sc.nextLine();
//                if(!(nextState.equals("end"))) {
//
//                }
//                stateTransitions.put(transition, nextState);
//            }while ()
//
//        }
//
//        return new State(state, stateTransitions);
//    }
}
