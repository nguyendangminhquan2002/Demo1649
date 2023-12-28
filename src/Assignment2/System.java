package Assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

// Computer System
public class System {
    private LinkedListQueue<String> inBoxQueue;
    private LinkedListQueue<String> outBoxQueue;
    private LinkedListStack<String> processingStack;
    private String nameOfSystem;
    private System destination;
    private System oldDestination;
    boolean isConnect = false;

    public System(){}
    public System(String nameOfSystem){
        this.nameOfSystem = nameOfSystem;
        inBoxQueue = new LinkedListQueue<>();
        outBoxQueue = new LinkedListQueue<>();
        processingStack = new LinkedListStack<>();
    }

    public String getNameOfSystem() {
        return nameOfSystem;
    }

    public void setNameOfSystem(String nameOfSystem) {
        this.nameOfSystem = nameOfSystem;
    }

    public System getDestination() {
        return destination;
    }

    public void setDestination(System destination) {
        this.destination = destination;
    }

    public System getOldDestination() {
        return oldDestination;
    }

    public void setOldDestination(System oldDestination) {
        this.oldDestination = oldDestination;
    }

    public void menu(System system_1, System system_2){
        try {
            Scanner sc = new Scanner(java.lang.System.in);
            Scanner sc_1 = new Scanner(java.lang.System.in);
            Scanner sc_2 = new Scanner(java.lang.System.in);
            int option, numberOfMessage;
            do {
                java.lang.System.out.println("Menu of " + this.getNameOfSystem());
                java.lang.System.out.println("1. Connect");
                java.lang.System.out.println("2. Check connection");
                java.lang.System.out.println("3. Disconnect");
                java.lang.System.out.println("4. Send message");
                java.lang.System.out.println("5. Receive message");
                java.lang.System.out.println("6. Process message");
                java.lang.System.out.println("7. Check inbox of system");
                java.lang.System.out.println("8. Exit");
                java.lang.System.out.print("Enter your option: ");
                option = sc.nextInt();
                switch (option){
                    case 1:
                        java.lang.System.out.println("Select the system to connect");
                        java.lang.System.out.println("1. " + system_1.getNameOfSystem());
                        java.lang.System.out.println("2. " + system_2.getNameOfSystem());
                        java.lang.System.out.println("3. Exit");
                        java.lang.System.out.print("Enter your option: ");
                        int choice = sc.nextInt();
                        switch (choice){
                            case 1:
                                this.connect(system_1);
                                break;
                            case 2:
                                this.connect(system_2);
                                break;
                            case 3:
                                break;
                            default:
                                java.lang.System.out.println("ERROR !!! JUST TYPE FROM 1 --> 3. TRY AGAIN");
                                break;
                        }
                        break;
                    case 2:
                        this.checkConnect();
                        break;
                    case 3:
                        this.disconnect();
                        break;
                    case 4:
                        if (isConnect) {
                            java.lang.System.out.print("Number of messages you want to import: ");
                            numberOfMessage = sc_1.nextInt();
                            if (numberOfMessage <= 0) {
                                throw new IllegalArgumentException("Do not enter negative numbers, only positive numbers");
                            }
                            else {
                                for (int i = 1; i <= numberOfMessage; i++) {
                                    java.lang.System.out.printf("Message %d: ", i );
                                    String message = sc_2.nextLine();
                                    this.send(message);
                                }
                            }
                        }
                        else {
                            java.lang.System.out.println("This system is not currently connected to other systems for execution, " +
                                    "please connect to another system");
                        }
                        break;
                    case 5:
                        this.receive();
                        break;
                    case 6:
                        this.process();
                        break;
                    case 7:
                        this.checkMessage();
                        break;
                    case 8:
                        java.lang.System.out.println("EXIT");
                        break;
                    default:
                        java.lang.System.out.println("ERROR !!! JUST TYPE FROM 1 --> 8. TRY AGAIN");
                        break;
                }
            } while (option != 8);
        }
        catch (IllegalArgumentException e){
            java.lang.System.out.println("Error: " + e.getMessage());
        }
        catch (InputMismatchException e){
            java.lang.System.out.println("Error: Do not enter letters, only numbers");
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void connect(System system){
        long startTime = java.lang.System.nanoTime();
        try {
            if (!isConnect && !system.isConnect) {
                outBoxQueue = new LinkedListQueue<>();
                setDestination(system);
                setOldDestination(system);
                isConnect = true;
                system.setDestination(this);
                system.setOldDestination(this);
                system.isConnect = true;
                java.lang.System.out.println(getNameOfSystem() + " has successfully connected to " + system.getNameOfSystem());
            }

            else if (getDestination() == system) {
                java.lang.System.out.println(getNameOfSystem() + " is connecting to " + getDestination().getNameOfSystem());
            }

            else if (isConnect){
                java.lang.System.out.println(getNameOfSystem() + " is connecting to " + getDestination().getNameOfSystem()
                        + ", it cannot connect to " + system.getNameOfSystem());
            }

            else {
                java.lang.System.out.println(system.getNameOfSystem() + " is connecting to " + system.getDestination().getNameOfSystem()
                        + ", " + getNameOfSystem() + " cannot interfere in this process ");
            }
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = java.lang.System.nanoTime();
        long elapsed = endTime - startTime;
        java.lang.System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void checkConnect(){
        long startTime = java.lang.System.nanoTime();
        try {
            if (isConnect) {
                java.lang.System.out.println("It's connecting to " + getDestination().getNameOfSystem());
            }
            else {
                java.lang.System.out.println(getNameOfSystem() + " is not currently connected to other systems");
            }
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = java.lang.System.nanoTime();
        long elapsed = endTime - startTime;
        java.lang.System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void send(String message){
        long startTime = java.lang.System.nanoTime();
        try {
            if (message.isEmpty()) {
                throw new NullArgumentException("Message is empty and cannot execute the program");
            } else {
                if (message.length() <= 250) {
                    java.lang.System.out.println("Sending message: " + message);
                    outBoxQueue.enqueue(message);
                }
                else {
                    java.lang.System.out.println("Messages over 250 characters, start truncating the message");
                    String tempMessage = "";
                    for (int i = 0; i < message.length(); i++){
                        tempMessage = tempMessage + message.charAt(i);
                        if (tempMessage.length() == 250 || i == message.length() - 1) {
                            java.lang.System.out.println("Sending message: " + tempMessage);
                            outBoxQueue.enqueue(tempMessage);
                            tempMessage = "";
                        }
                    }
                }
            }
        }
        catch (NullArgumentException e){
            java.lang.System.out.println(e.getMessage());
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = java.lang.System.nanoTime();
        long elapsed = endTime - startTime;
        java.lang.System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void
    receive(){
        long startTime = java.lang.System.nanoTime();
        try {
            if (isConnect){
                if (!getDestination().outBoxQueue.isEmpty()) {
                    while (!getDestination().outBoxQueue.isEmpty()){
                        inBoxQueue.enqueue(getDestination().outBoxQueue.dequeue());
                    }
                    java.lang.System.out.println("Complete the process of receiving messages from "
                            + getDestination().getNameOfSystem());
                }
                else {
                    java.lang.System.out.println("outQueue in " + getDestination().getNameOfSystem()
                            + " is empty, process cannot be executed");
                }
            }
            else {
                java.lang.System.out.println("This system is not currently connected to other systems for execution, " +
                        "please connect to another system");
            }
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = java.lang.System.nanoTime();
        long elapsed = endTime - startTime;
        java.lang.System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void process(){
        long startTime = java.lang.System.nanoTime();
        try {
            if (!inBoxQueue.isEmpty()){
                while (!inBoxQueue.isEmpty()){
                    java.lang.System.out.println("Processing message: " + inBoxQueue.peek());
                    processingStack.push(inBoxQueue.dequeue());
                }
                java.lang.System.out.println("Complete the process of processing messages of "
                        + getNameOfSystem());
            }
            else {
                java.lang.System.out.println("inboxQueue is empty, process cannot be executed");
            }
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = java.lang.System.nanoTime();
        long elapsed = endTime - startTime;
        java.lang.System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void disconnect(){
        long startTime = java.lang.System.nanoTime();
        try {
            if (isConnect) {
                java.lang.System.out.println(getNameOfSystem() + " destroys the connection to "
                        + getDestination().getNameOfSystem());
                outBoxQueue = null;
                getDestination().isConnect = false;
                getDestination().setDestination(null);
                isConnect = false;
                destination = null;
            } else {
                java.lang.System.out.println(getNameOfSystem() + " has disconnected");
            }
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = java.lang.System.nanoTime();
        long elapsed = endTime - startTime;
        java.lang.System.out.println("Time spent: " + elapsed + " nanoseconds");
    }

    public void checkMessage(){
        long startTime = java.lang.System.nanoTime();
        int count = 0;
        try {
            if (!processingStack.isEmpty()){
                java.lang.System.out.println(getOldDestination().getNameOfSystem()
                        + " just sent " + getNameOfSystem() + " a message: ");
                while (!processingStack.isEmpty()) {
                    count++;
                    java.lang.System.out.printf("Part of message %d: ", count);
                    java.lang.System.out.println(processingStack.pop());

                }
            }
            else if (processingStack.isEmpty()){
                java.lang.System.out.println("The message has not been processed");
            }
        }
        catch (Exception e){
            java.lang.System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = java.lang.System.nanoTime();
        long elapsed = endTime - startTime;
        java.lang.System.out.println("Time spent: " + elapsed + " nanoseconds");
    }
}
