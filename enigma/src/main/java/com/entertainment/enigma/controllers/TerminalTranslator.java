package com.entertainment.enigma.controllers;

import com.entertainment.enigma.exceptions.WrongKeyException;
import com.entertainment.enigma.modules.EnigmaService;
import com.entertainment.enigma.service.ServiceProvider;

import java.util.Scanner;

public class TerminalTranslator implements Module{

    private String mode;
    private ServiceProvider service;
    private EnigmaService enigma;

    public TerminalTranslator() {
        this.mode = null;
        this.service = null;
        this.enigma = null;
    }

    @Override
    public void initialize(ServiceProvider serviceProvider) {
        this.service = serviceProvider;
    }

    public void setParameters(String modeType, String service) {
        this.enigma = this.service.getByName(service);
        this.mode = modeType;
    }

    @Override
    public String getName() {
        return "Terminal Translator";
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        if (enigma.isKeyRequired()) {
            System.out.println("Enter a key to this cipher: ");
            try {
                this.enigma.setKey(sc.next());
            } catch (WrongKeyException e) {
                System.out.println("Invalid key!");
            }
        }
        System.out.println("Enter text to cipher/encipher\n");
        String text = sc.nextLine();
        this.cipher(text);
    }

    private void cipher(String txt) {
        switch (this.mode) {
            case "-e":
                String ciphered = this.enigma.encipher(txt);
                System.out.println(ciphered);
                break;
            case "-d":
                String deciphered = this.enigma.decipher(txt);
                System.out.println(deciphered);
                break;
            default:
                System.out.println("Invalid mode!");
                break;
        }
    }
}
