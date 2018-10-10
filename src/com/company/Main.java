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

        int [] states = new int[numberOfStates];
        int [] transitions = new int[numberOfTransitions];
        

    }
}
