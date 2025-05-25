package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberGuessGameTest {

    @Test
    public void testCorrectGuess() {
        NumberGuessGame game = new NumberGuessGame(5);
        assertEquals("Correct!", game.guess(5));
    }

    @Test
    public void testTooLowGuess() {
        NumberGuessGame game = new NumberGuessGame(7);
        assertEquals("Too low!", game.guess(5));
    }

    @Test
    public void testTooHighGuess() {
        NumberGuessGame game = new NumberGuessGame(3);
        assertEquals("Too high!", game.guess(5));
    }

    @Test
    public void testGuessBelowRange() {
        NumberGuessGame game = new NumberGuessGame(5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.guess(0);
        });
        assertEquals("Guess must be between 1 and 10.", exception.getMessage());
    }

    @Test
    public void testGuessAboveRange() {
        NumberGuessGame game = new NumberGuessGame(5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.guess(11);
        });
        assertEquals("Guess must be between 1 and 10.", exception.getMessage());
    }

    @Test
    public void testInvalidSecretNumberBelowRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new NumberGuessGame(0);
        });
        assertEquals("Secret number must be between 1 and 10.", exception.getMessage());
    }

    @Test
    public void testInvalidSecretNumberAboveRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new NumberGuessGame(11);
        });
        assertEquals("Secret number must be between 1 and 10.", exception.getMessage());
    }
}
