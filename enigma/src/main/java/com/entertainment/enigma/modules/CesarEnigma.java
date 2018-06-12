package com.entertainment.enigma.modules;

import com.entertainment.enigma.exceptions.WrongKeyException;

public class CesarEnigma implements EnigmaService {
    private int shift;

    public CesarEnigma() {
        this.shift = 0;
    }

    @Override
    public String getName() {
        return "CesarEnigma";
    }

    private char calculateChar(char ch) {
        if (ch > 'z') {
            int diff = ch % 122;
            ch = '/';
            ch += diff;
        } else if (ch < '0') {
            int diff = 48 % ch;
            ch = '{';
            ch -= diff;
        }
        return ch;
    }

    @Override
    public String encipher(String input) {
        String ciphered = "";
        for (char ch : input.toCharArray()) {
            ch += this.shift;
            ch = this.calculateChar(ch);
            ciphered += ch;
        }
        return ciphered;
    }

    @Override
    public String decipher(String ciphered) {
        String deciphered = "";
        for (char ch : ciphered.toCharArray()) {
            ch -= this.shift;
            ch = this.calculateChar(ch);
            deciphered += ch;
        }
        return deciphered;
    }

    @Override
    public Boolean isKeyRequired() {
        return true;
    }

    @Override
    public void setKey(String key) throws WrongKeyException {
        try {
            this.shift = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            throw new WrongKeyException("Invalid key!");
        }
    }
}
