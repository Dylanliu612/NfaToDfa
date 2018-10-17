package com.company;

import sun.security.krb5.internal.rcache.DflCache;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        final Character[] TRANSITION_SYMBOLS = {'a', 'b'};
        final Character[] LAMBDA_TRANSITION_SYMBOLS = {'a', 'b', '0'};

        ArrayList<NFAState> testStates1 = new ArrayList<>();
        ArrayList<NFAState> testStates2 = new ArrayList<>();

        ArrayList<Character> transitionAs = new ArrayList<>();
        ArrayList<Character> transitionBs = new ArrayList<>();
        transitionAs.add('0');
        transitionAs.add('1');
        testStates1.add(new NFAState('0', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionBs.add('1');
        transitionBs.add('2');
        testStates1.add(new NFAState('1', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionAs.add('2');
        testStates1.add(new NFAState('2', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionAs.add('0');
        transitionAs.add('1');
        transitionBs.add('1');
        testStates2.add(new NFAState('0', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionAs.add('2');
        transitionBs.add('2');
        testStates2.add(new NFAState('1', transitionAs, transitionBs));

        transitionAs = new ArrayList<>();
        transitionBs = new ArrayList<>();
        transitionBs.add('2');
        testStates2.add(new NFAState('2', transitionAs, transitionBs));


        for (NFAState nfaState : testStates1) {
            System.out.println(nfaState);
        }
        System.out.println();


        ArrayList<DFAState> dfaStates = nfaToDfa(testStates1);

        for (DFAState dfaState : dfaStates) {
            System.out.println(dfaState);
        }


    }

    public static ArrayList<DFAState> nfaToDfa(ArrayList<NFAState> nfaStates) {
        ArrayList<DFAState> dfaStates = new ArrayList<>();
        ArrayList<PendingState> pendingStates = new ArrayList<>();

        for (NFAState nfaState : nfaStates) {
            Character[] transitionsA = new Character[nfaState.getTransitionA().size()];
            Character[] transitionsB = new Character[nfaState.getTransitionB().size()];
            transitionsA = nfaState.getTransitionA().toArray(transitionsA);
            transitionsB = nfaState.getTransitionB().toArray(transitionsB);
            Arrays.sort(transitionsA);
            Arrays.sort(transitionsB);

            StringBuilder dfaTransitionA = new StringBuilder();
            StringBuilder dfaTransitionB = new StringBuilder();
            for (Character character : transitionsA) {
                dfaTransitionA.append(character);
            }
            for (Character character : transitionsB) {
                dfaTransitionB.append(character);
            }

            if (dfaTransitionA.length() > 1) {
               pendingStates = new ArrayList<>(generatePendingState(nfaStates, pendingStates, dfaTransitionA.toString()));
            }
            if (dfaTransitionB.length() > 1) {
                pendingStates = new ArrayList<>(generatePendingState(nfaStates, pendingStates, dfaTransitionB.toString()));
            }



            DFAState dfaState = new DFAState(nfaState.getState().toString(), dfaTransitionA.toString(), dfaTransitionB.toString());
            dfaStates.add(dfaState);

        }

        for (PendingState pendingState : pendingStates) {
            Character[] transitionsA = new Character[pendingState.getTransitionAs().size()];
            Character[] transitionsB = new Character[pendingState.getTransitionBs().size()];
            transitionsA = pendingState.getTransitionAs().toArray(transitionsA);
            transitionsB = pendingState.getTransitionBs().toArray(transitionsB);
            Arrays.sort(transitionsA);
            Arrays.sort(transitionsB);

            StringBuilder dfaTransitionA = new StringBuilder();
            StringBuilder dfaTransitionB = new StringBuilder();
            for (Character character : transitionsA) {
                dfaTransitionA.append(character);
            }
            for (Character character : transitionsB) {
                dfaTransitionB.append(character);
            }
            dfaStates.add(new DFAState(pendingState.getNewState(), dfaTransitionA.toString(), dfaTransitionB.toString()));
        }

        for (DFAState dfaState : dfaStates) {
            if(dfaState.getTransitionA().isEmpty() || dfaState.getTransitionB().isEmpty()) {
                dfaStates.add(new DFAState("trap", "trap", "trap"));
                break;
            }
        }

        for (DFAState dfaState : dfaStates) {
            if (dfaState.getTransitionA().isEmpty()) {
                dfaState.setTransitionA("trap");
            }
            if(dfaState.getTransitionB().isEmpty()) {
                dfaState.setTransitionB("trap");
            }
        }
        return dfaStates;
    }

    public static ArrayList<PendingState> generatePendingState(ArrayList<NFAState> nfaStates, ArrayList<PendingState> pendingStates, String dfaTransition) {
        boolean pendingStateExists = false;
        for (PendingState pendingState : pendingStates) {
            if (pendingState.getNewState().equals(dfaTransition)) {
                pendingStateExists = true;
            }
        }
        if (!pendingStateExists) {
            String newPendingState = dfaTransition;
            ArrayList<Character> pendingTransitionAs = new ArrayList<>();
            ArrayList<Character> pendingTransitionBs = new ArrayList<>();

            for (int i = 0; i < newPendingState.length(); i++) {
                for (NFAState state : nfaStates) {
                    if (state.getState() == newPendingState.charAt(i)) {
                        pendingTransitionAs.addAll(state.getTransitionA());
                        pendingTransitionBs.addAll(state.getTransitionB());
                    }
                }
            }
            Character[] pendingTransitionAsCharacters = new Character[pendingTransitionAs.size()];
            Character[] pendingTransitionBsCharacters = new Character[pendingTransitionBs.size()];
            pendingTransitionAsCharacters = pendingTransitionAs.toArray(pendingTransitionAsCharacters);
            pendingTransitionBsCharacters = pendingTransitionBs.toArray(pendingTransitionBsCharacters);
            Arrays.sort(pendingTransitionAsCharacters);
            Arrays.sort(pendingTransitionBsCharacters);

            pendingTransitionAs = new ArrayList<>(Arrays.asList(pendingTransitionAsCharacters));
            pendingTransitionBs = new ArrayList<>(Arrays.asList(pendingTransitionBsCharacters));
            pendingStates.add(new PendingState(newPendingState, pendingTransitionAs, pendingTransitionBs));
        }
        return pendingStates;
    }

    public static ArrayList<PendingState> generateRemainingPendingStates(ArrayList<NFAState> nfaStates, ArrayList<PendingState> pendingStates, boolean isComplete) {
        if (isComplete) {
            return pendingStates;
        }
        isComplete = true;

        StringBuilder transitionA = new StringBuilder();
        StringBuilder transitionB = new StringBuilder();
        for (PendingState pendingState : pendingStates) {
            if (pendingState.getTransitionAs().size() > 1) {
                for (int i = 0; i < pendingState.getTransitionAs().size(); i++) {
                    transitionA.append(pendingState.getTransitionAs().get(i));
                }
            }
            if(pendingState.getTransitionBs().size() > 1) {
                for (int i = 0; i < pendingState.getTransitionBs().size(); i++) {
                    transitionB.append(pendingState.getTransitionBs().get(i));
                }
            }
        }
        for (PendingState pendingState : pendingStates) {
            if (!pendingState.getNewState().equals(transitionA.toString())) {
                isComplete = false;
                String newPendingState = transitionA.toString();
                ArrayList<Character> pendingTransitionAs = new ArrayList<>();
                ArrayList<Character> pendingTransitionBs = new ArrayList<>();

                for (int i = 0; i < newPendingState.length(); i++) {
                    for (NFAState nfaState : nfaStates) {
                        if (nfaState.getState() == newPendingState.charAt(i)) {
                            pendingTransitionAs.addAll(nfaState.getTransitionA());
                            pendingTransitionBs.addAll(nfaState.getTransitionB());
                        }
                    }
                }

                Character[] pendingTransitionAsCharacters = new Character[pendingTransitionAs.size()];
                Character[] pendingTransitionBsCharacters = new Character[pendingTransitionBs.size()];
                pendingTransitionAsCharacters = pendingTransitionAs.toArray(pendingTransitionAsCharacters);
                pendingTransitionBsCharacters = pendingTransitionBs.toArray(pendingTransitionBsCharacters);
                Arrays.sort(pendingTransitionAsCharacters);
                Arrays.sort(pendingTransitionBsCharacters);

                pendingTransitionAs = new ArrayList<>(Arrays.asList(pendingTransitionAsCharacters));
                pendingTransitionBs = new ArrayList<>(Arrays.asList(pendingTransitionBsCharacters));

                pendingStates.add(new PendingState(newPendingState, pendingTransitionAs, pendingTransitionBs));
            }

            if (!pendingState.getNewState().equals(transitionB.toString())) {
                isComplete = false;
                String newPendingState = transitionB.toString();
                ArrayList<Character> pendingTransitionAs = new ArrayList<>();
                ArrayList<Character> pendingTransitionBs = new ArrayList<>();

                for (int i = 0; i < newPendingState.length(); i++) {
                    for (NFAState nfaState : nfaStates) {
                        if (nfaState.getState() == newPendingState.charAt(i)) {
                            pendingTransitionAs.addAll(nfaState.getTransitionA());
                            pendingTransitionBs.addAll(nfaState.getTransitionB());
                        }
                    }
                }

                Character[] pendingTransitionAsCharacters = new Character[pendingTransitionAs.size()];
                Character[] pendingTransitionBsCharacters = new Character[pendingTransitionBs.size()];
                pendingTransitionAsCharacters = pendingTransitionAs.toArray(pendingTransitionAsCharacters);
                pendingTransitionBsCharacters = pendingTransitionBs.toArray(pendingTransitionBsCharacters);
                Arrays.sort(pendingTransitionAsCharacters);
                Arrays.sort(pendingTransitionBsCharacters);

                pendingTransitionAs = new ArrayList<>(Arrays.asList(pendingTransitionAsCharacters));
                pendingTransitionBs = new ArrayList<>(Arrays.asList(pendingTransitionBsCharacters));

                pendingStates.add(new PendingState(newPendingState, pendingTransitionAs, pendingTransitionBs));
            }
        }
        return generateRemainingPendingStates(nfaStates, pendingStates, isComplete);
    }
}
