package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final Character[] TRANSITION_SYMBOLS = {'a', 'b'};
        final Character[] LAMBDA_TRANSITION_SYMBOLS = {'a', 'b', '0'};
        boolean isLambdaNFA = false;
        Character[] transitionSymbols = TRANSITION_SYMBOLS;
        System.out.println("Number of states: ");
        Scanner sc = new Scanner(System.in);
        int numberOfStates = Integer.parseInt(sc.nextLine());

        Character[] states = aliasStates(numberOfStates);
        System.out.println("Your states are: ");
        printChars(states);
        System.out.println("Does your NFA have lambda? (y/n)");
        if (sc.nextLine().charAt(0) == 'y') {
            transitionSymbols = LAMBDA_TRANSITION_SYMBOLS;
            isLambdaNFA = true;
        }
        ;
        System.out.println("Your transitions are: ");
        printChars(transitionSymbols);

        ArrayList<NFAState> nfaStates = generateAllNFAStateTransitions(states, transitionSymbols);

        for (NFAState nfaState : nfaStates) {
            System.out.println(nfaState);
        }

        nfaToDfa(nfaStates);

    }

    public static Character[] aliasStates(int numberOfStates) {
        Character[] states = new Character[numberOfStates];
        for (int i = 0; i < numberOfStates; i++) {
            states[i] = (char) ('0' + i);
        }
        return states;
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

    //take all states and transition symbols, and have user input state by state what transitions results from a transition symbol
    //returns an array list of NFAState objects that hold the state and the possible transitions.
    public static ArrayList<NFAState> generateAllNFAStateTransitions(Character[] states, Character[] transitions) {
        Scanner sc = new Scanner(System.in);
        ArrayList<NFAState> nfaStates = new ArrayList<>();
        for (Character state : states) {
            Map<Character, ArrayList<Character>> transitionStates = new HashMap<>();
            System.out.println("What are the possible transitions for state " + state + " based on the following symbols?");
            System.out.println("(If state transitions to multiple states, enter them seperated by commas (,).");
            System.out.println("Enter 'n' if no transition");
            //user will be asked, symbol by symbol, what the transitions from state to state are.
            for (Character transition : transitions) {
                ArrayList<Character> nfaTransitions = new ArrayList<>();
                System.out.print(transition + " -> ");
                String[] possibleTransitions = sc.nextLine().split(",");
                for (String possibleTransition : possibleTransitions) {
                    possibleTransition = possibleTransition.trim();
                    if (possibleTransition.charAt(0) != 'n') {
                        nfaTransitions.add(possibleTransition.charAt(0));
                    }
                }
                transitionStates.put(transition, nfaTransitions);
            }
            nfaStates.add(new NFAState(state, transitionStates));
        }
        return nfaStates;
    }

    public static void nfaToDfa(ArrayList<NFAState> nfaStates) {
        for (NFAState nfaState : nfaStates) {
            ArrayList<String> dfaStates = new ArrayList<>();
            Collection<ArrayList<Character>> nfaStateTransitions = nfaState.getTransitions().values();
            for (ArrayList<Character> transitionSet : nfaStateTransitions) {

                if (transitionSet.size() > 1) {
                    System.out.println("nfa specific state detected : " + nfaState);
                }
//                StringBuilder dfaState = new StringBuilder();
//                for (Character transitionState : transitionSet) {
//                    dfaState.append(transitionState);
//                }
//                dfaStates.add(dfaState.toString());
//                System.out.println(dfaState);
            }
        }
    }
}
