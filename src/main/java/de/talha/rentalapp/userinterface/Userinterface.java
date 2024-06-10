package de.talha.rentalapp.userinterface;

import de.talha.rentalapp.abstraction.Displayable;

import java.util.Scanner;

public class Userinterface {

    private final Scanner scanner;

    public Userinterface() {
        scanner = new Scanner(System.in);
    }

    public String provideNextLine() {
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

    public void title(String title) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < title.length(); i++) {
            line.append('-');
        }
        System.out.println(line);
        info(title);
        System.out.println(line);
    }
}
