package com.entertainment.enigma.matcher;

public class InputChecker {

    public static boolean checkStringToEncipher(String input) {
        return input.matches("[0-9A-Za-z]{1,}");
    }
}
