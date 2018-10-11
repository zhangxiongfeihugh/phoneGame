package telephoneGame;

import java.util.Scanner;

public class GamePeopleListFactory {
	private static SinglyLinkedList<Person> people = new SinglyLinkedList<Person>();

    static {
		people.insert(new Person(0.8,0.8,"Ellis"));
		people.insert(new Person(0.9,0.9,"Allison"));
		people.insert(new Person(0.8,0.9,"Andre"));
		people.insert(new Person(0.9,0.7,"Micaela"));
		people.insert(new Person(0.8,0.9,"Shanshan"));
	}

    public static void insertpeople() {
        double speaking = 0;
        double listening = 0;
        System.out.println("Please enter your player's name");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        if(name.trim().isEmpty()) {
            System.out.println("Error: No name is entered");
            return;
        }
        System.out.println("Please enter the speaking clarity of this person: a number between 0 and 1");
        String speak = s.nextLine();
        if(speak.trim().isEmpty()) {
            System.out.println("Error: No number is entered");
            return;
        }
        try {
            speaking = Double.parseDouble(speak);
            if(speaking>1 || speaking<0) {
                System.out.println("Invaild number: please enter a number between 0 and 1");
                speaking = Double.parseDouble(s.nextLine());
                if(speaking>1 || speaking<0) {
                    System.out.println("Error: Invaild number is entered");
                    return;
                }
            }
        }catch(Exception e) {
            System.out.println("Error: Invaild entering");
            return;
        }
        System.out.println("Please enter the listening ability of this person: a number between 0 and 1");
        String listen = s.nextLine();
        if(listen.trim().isEmpty()) {
            System.out.println("Error: No number is entered");
            return;
        }
        try{
            listening = Double.parseDouble(listen);
            if(listening>1 || listening<0) {
                System.out.println("Invaild number: please enter a number between 0 and 1");
                listening = Double.parseDouble(s.nextLine());
                if(listening>1 || listening<0) {
                    System.out.println("Error: Invaild number is entered");
                    return;
                }
            }
        }catch(Exception e) {
            System.out.println("Error: Invaild entering");
            return;
        }
        people.insert(new Person(speaking,listening,name));
        System.out.println("The player is inserted successfully!");
    }


    public static SinglyLinkedList<Person> getPeopleList(){
        System.out.println("We now have five players in this game. Would you like to add another player?[please answer yes/no]");
        boolean add = false;
        Scanner s = new Scanner(System.in);
        String insert = s.nextLine();
        if(insert.equalsIgnoreCase("yes") || insert.equalsIgnoreCase("y")) {
            add = true;
        } else if(insert.equalsIgnoreCase("no") || insert.equalsIgnoreCase("n")) {
            System.out.println("You don't want to set another player. Game will start soon");
            add = false;
        } else {
            System.out.println("This is an invaild answer. Game will start directly.");
            add = false;
        }
        while(add == true) {
            insertpeople();
            System.out.println("Would you like to add another player?[please answer yes/no]");
            insert = s.nextLine();
            if(insert.equalsIgnoreCase("yes") || insert.equalsIgnoreCase("y")) {
                add = true;
            } else if(insert.equalsIgnoreCase("no") || insert.equalsIgnoreCase("n")) {
                System.out.println("You don't want to set another player. Game will start soon");
                add = false;
            } else {
                System.out.println("This is an invaild answer. Game will start directly.");
                add = false;
            }
        }
        return people;
    }

}
