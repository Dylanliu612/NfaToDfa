package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Number of states: ");
        Scanner sc = new Scanner(System.in);
        int numberOfStates = Integer.parseInt(sc.nextLine());
        System.out.println("Number of transitions (including lambda, if applicable): ");
        int numberOfTransitions = Integer.parseInt(sc.nextLine());
        System.out.println("You have an NFA with " + numberOfStates + " states and " + numberOfTransitions + " transitions.");

        Character[] states = aliasStates(numberOfStates);
        Character[] transitions = aliasTransitions(numberOfTransitions);
        System.out.println("Your states are: ");
        printChars(states);
        System.out.println();
        System.out.println("Your transitions are: ");
        printChars(transitions);

        System.out.println(generateNFAStateTransitions(states[0],transitions));


    }

    public static Character[] aliasStates(int numberOfStates) {
        Character[] states = new Character[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            states[i] = (char)('0' + i);
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

//    public static NFAState generateNFAStateTransitions(Character state, Character[] transitions) {
//        Scanner sc = new Scanner(System.in);
//        ArrayList<Character> possibleStates = new ArrayList<>();
//        Map<Character, ArrayList<Character>> transitionStates = new HashMap<>();
//        for (Character transition : transitions) {
//            System.out.println("NFAState " + state + " with transition symbol " + transition + " -> (enter 'n' if no transition): " );
//            Character possibleState = sc.nextLine().charAt(0);
//            if(!possibleState.equals('n')) {
//                possibleStates.add(possibleState);
//            }
//        }
//        Collections.sort(possibleStates);
//        transitionStates.put(state, possibleStates);
//        return new NFAState(state, transitionStates);
//    }

    public static NFAState generateAllNFAStateTransitions(Character[] states, Character[] transitions) {
        Scanner sc = new Scanner(System.in);
        Map<Character, ArrayList<Character>> transitionStates = new HashMap<>();
        for (Character state : states) {
            ArrayList<Character> possibleStates = new ArrayList<>();
            for (Character transition : transitions) {
                System.out.println("NFAState " + state + " with transition symbol " + transition + " -> (enter 'n' if no transition): " );
                Character possibleState = sc.nextLine().charAt(0);
                if(!possibleState.equals('n')) {
                    possibleStates.add(possibleState);
                }
            }
            Collections.sort(possibleStates);
            transitionStates.put()
        }
    }

//    public static NFAState generateNFAStateTransitions(String state, Character[] transitions) {
//        Scanner sc = new Scanner(System.in);
//        Map<Character, String> stateTransitions = new HashMap<>();
//        ArrayList<String> transitionStates = new ArrayList<>();
//        for (char transition : transitions) {
//            System.out.println("NFAState " + state + " with transition " + transition + " goes to state (enter '0' for no state): ");
//            String transitionState = sc.nextLine();
//            if(!transitionState.equals("0")) {
//                transitionStates.add(transitionState);
//            }
//        }
//
//        return new NFAState(state, stateTransitions);
//    }

}
