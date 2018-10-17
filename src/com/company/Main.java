package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final Character[] TRANSITION_SYMBOLS = {'a', 'b'};
        final Character[] LAMBDA_TRANSITION_SYMBOLS = {'a', 'b', '0'};

        ArrayList<NFAState> testStates1 = new ArrayList<>();
        ArrayList<NFAState> testStates2 = new ArrayList<>();
        ArrayList<NFAState> testStates3 = new ArrayList<>();
        ArrayList<NFAState> testStates4 = new ArrayList<>();

        Map<Character, ArrayList<Character>> transitions = new HashMap<>();
        ArrayList<Character> transitionStates = new ArrayList<>();
        transitionStates.add('0');
        transitionStates.add('1');
        transitions.put(TRANSITION_SYMBOLS[0], transitionStates);
        testStates1.add(new NFAState('0', transitions));

        transitionStates = new ArrayList<>();
        transitions = new HashMap<>();
        transitionStates.add('1');
        transitionStates.add('2');
        transitions.put(TRANSITION_SYMBOLS[1], transitionStates);
        testStates1.add(new NFAState('1', transitions));

        transitionStates = new ArrayList<>();
        transitions = new HashMap<>();
        transitionStates.add('2');
        transitions.put(TRANSITION_SYMBOLS[0], transitionStates);
        testStates1.add(new NFAState('2', transitions));

        //test 2

        transitionStates = new ArrayList<>();
        transitions = new HashMap<>();
        transitionStates.add('0');
        transitionStates.add('1');
        transitions.put(TRANSITION_SYMBOLS[0], transitionStates);
//        testStates2.add(new NFAState('0', transitions));

        transitionStates = new ArrayList<>();
//        transitions = new HashMap<>();
        transitionStates.add('1');
        transitions.put(TRANSITION_SYMBOLS[1], transitionStates);
        testStates2.add(new NFAState('0', transitions));

        transitionStates = new ArrayList<>();
        transitions = new HashMap<>();
        transitionStates.add('2');
        transitions.put(TRANSITION_SYMBOLS[0], transitionStates);
//        testStates2.add(new NFAState('1', transitions));

        transitionStates = new ArrayList<>();
//        transitions = new HashMap<>();
        transitionStates.add('2');
        transitions.put(TRANSITION_SYMBOLS[1], transitionStates);
        testStates2.add(new NFAState('1', transitions));

        transitionStates = new ArrayList<>();
        transitions = new HashMap<>();
        transitionStates.add('2');
        transitions.put(TRANSITION_SYMBOLS[1], transitionStates);
        testStates2.add(new NFAState('2', transitions));

        ArrayList<Character> transitionAs = new ArrayList<>();
        ArrayList<Character> transitionBs = new ArrayList<>();
        transitionAs.add('0');
        transitionAs.add('1');
        testStates3.add(new NFAState('0', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionBs.add('1');
        transitionBs.add('2');
        testStates3.add(new NFAState('1', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionAs.add('2');
        testStates3.add(new NFAState('2', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionAs.add('0');
        transitionAs.add('1');
        transitionBs.add('1');
        testStates4.add(new NFAState('0', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionAs.add('2');
        transitionBs.add('2');
        testStates4.add(new NFAState('1', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionBs.add('2');
        testStates4.add(new NFAState('2', transitionAs, transitionBs));





        for (NFAState nfaState : testStates3) {
            System.out.println(nfaState);
        }
        System.out.println();
        for (NFAState nfaState : testStates4) {
            System.out.println(nfaState);
        }
//        ArrayList<DFAState> dfaStates = nfaToDfa(testStates1);
//
//        for (DFAState dfaState : dfaStates) {
//            System.out.println(dfaState);
//        }


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

    public static ArrayList<DFAState> nfaToDfa(ArrayList<NFAState> nfaStates) {
        ArrayList<DFAState> dfaStates = new ArrayList<>();
        boolean hasTrapState = false;

        for (NFAState nfaState : nfaStates) {
            String transitionA = "";
            String transitionB = "";
            for (Map.Entry<Character, ArrayList<Character>> entry : nfaState.getTransitions().entrySet()) {

                StringBuilder newState = new StringBuilder();
                for (int i = 0; i < entry.getValue().size(); i++) {
                    newState.append(entry.getValue().get(i));
                }
                if (newState.length() > 1) {

                }
                if (entry.getKey() == 'a') {
                    char [] temp = newState.toString().toCharArray();
                    Arrays.sort(temp);
                    transitionA = new String(temp);
                }
                if (entry.getKey() == 'b') {
                    char [] temp = newState.toString().toCharArray();
                    Arrays.sort(temp);
                    transitionB = new String(temp);
                }
            }
            String trapState = Integer.toString(nfaStates.size());
            if (transitionA.isEmpty() || transitionB.isEmpty() && !hasTrapState) {
                DFAState dfaState = new DFAState(trapState, trapState, trapState);
                dfaStates.add(dfaState);
                hasTrapState = true;
            }
            if (transitionA.isEmpty()) {
                transitionA = trapState;
            }
            if (transitionB.isEmpty()) {
                transitionB = trapState;
            }
            DFAState dfaState = new DFAState(nfaState.getState().toString(), transitionA, transitionB);
            dfaStates.add(dfaState);

        }
        ArrayList<DFAState> newDFAStates = new ArrayList<>();
        for (DFAState dfaState : dfaStates) {
            if(dfaState.getState().equals("trap")) {
                continue;
            }

            if (dfaState.getTransitionA().length() > 1) {
                String newDFAState = dfaState.getTransitionA();
                StringBuilder transitionA = new StringBuilder();
                StringBuilder transitionB = new StringBuilder();
                for (DFAState state : dfaStates) {
                    for (int i = 0; i < newDFAState.length(); i++) {
                        StringBuilder temp = new StringBuilder();
                        temp.append(newDFAState.charAt(i));
                        if(state.getState().equals(temp.toString()) ) {
                            transitionA.append(state.getTransitionA());
                            transitionB.append(state.getTransitionB());
                        }
                    }
                }
                newDFAStates.add(new DFAState(newDFAState, transitionA.toString(), transitionB.toString()));
            }

            if (dfaState.getTransitionB().length() > 1) {
                String newDFAState = dfaState.getTransitionB();
                StringBuilder transitionA = new StringBuilder();
                StringBuilder transitionB = new StringBuilder();
                for (DFAState state : dfaStates) {
                    for (int i = 0; i < newDFAState.length(); i++) {
                        StringBuilder temp = new StringBuilder();
                        temp.append(newDFAState.charAt(i));
                        if(state.getState().equals(temp.toString()) ) {
                            transitionA.append(state.getTransitionA());
                            transitionB.append(state.getTransitionB());
                        }
                    }
                }
                newDFAStates.add(new DFAState(newDFAState, transitionA.toString(), transitionB.toString()));
            }
        }
        dfaStates.addAll(newDFAStates);

        return dfaStates;
    }
}
