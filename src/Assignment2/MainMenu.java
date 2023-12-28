package Assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        try {
            int option;
            Scanner sc = new Scanner(java.lang.System.in);
            System systemA = new System("System A");
            System systemB = new System("System B");
            System systemC = new System("System C");
            do {
                java.lang.System.out.println("Select the system you want to use: ");
                java.lang.System.out.println("1. " + systemA.getNameOfSystem());
                java.lang.System.out.println("2. " + systemB.getNameOfSystem());
                java.lang.System.out.println("3. " + systemC.getNameOfSystem());
                java.lang.System.out.println("4. End the program");
                java.lang.System.out.print("Enter your option: ");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        systemA.menu(systemB, systemC);
                        break;
                    case 2:
                        systemB.menu(systemA, systemC);
                        break;
                    case 3:
                        systemC.menu(systemA, systemB);
                        break;
                    case 4:
                        java.lang.System.out.println("EXIT");
                        break;
                    default:
                        java.lang.System.out.println("ERROR !!! JUST TYPE FROM 1 --> 4. TRY AGAIN");
                        break;
                }
            } while (option != 4);
        } catch (InputMismatchException e) {
            java.lang.System.out.println("Error: Only enter numbers, not letters");
        } catch (Exception e) {
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}