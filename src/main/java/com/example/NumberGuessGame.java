package com.example;

import java.util.Scanner;

public class NumberGuessGame {

    private int secretNumber;

    public NumberGuessGame(int secretNumber) {
        if (secretNumber < 1 || secretNumber > 10) {
            throw new IllegalArgumentException("Secret number must be between 1 and 10.");
        }
        this.secretNumber = secretNumber;
    }

    public String guess(int userGuess) {
        if (userGuess < 1 || userGuess > 10) {
            throw new IllegalArgumentException("Guess must be between 1 and 10.");
        }

        if (userGuess == secretNumber) {
            return "Correct!";
        } else if (userGuess < secretNumber) {
            return "Too low!";
        } else {
            return "Too high!";
        }
    }

    // Entry point of the program
    public static void main(String[] args) {
        // Let's create a game with secret number 7 (example)
        NumberGuessGame game = new NumberGuessGame(7);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a number between 1 and 10:");

        while (true) {
            int userGuess = scanner.nextInt();

            String result = game.guess(userGuess);
            System.out.println(result);

            if (result.equals("Correct!")) {
                break;
            } else {
                System.out.println("Try again:");
            }
        }

        scanner.close();
        System.out.println("Game over!");
    }
}
