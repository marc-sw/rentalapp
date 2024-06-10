package de.talha.rentalapp.userinterface;

import de.talha.rentalapp.abstraction.Displayable;
import de.talha.rentalapp.abstraction.SimpleDisplayable;

import java.util.Scanner;

public class Userinterface {

    private final Scanner scanner;

    public Userinterface() {
        scanner = new Scanner(System.in);
    }

    public String input(String message) {
        info(message + ":");
        return scanner.nextLine();
    }

    public void info(String message) {
        System.out.println(message);
    }

    public void error(String error) {
        info("Fehler: " + error);
    }

    public void display(Displayable displayable) {
        info(displayable.display());
    }

    public void displaySimple(SimpleDisplayable simpleDisplayable) {
        info(simpleDisplayable.displaySimple());
    }
}
