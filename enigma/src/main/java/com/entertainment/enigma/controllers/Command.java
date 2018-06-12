package com.entertainment.enigma.controllers;

import com.entertainment.enigma.exceptions.InvalidCmdException;
import com.entertainment.enigma.exceptions.InvalidModuleNameException;

import java.util.List;

public class Command {

    private static final String ENCIPHER_CMD = "-e";
    private static final String DECIPHER_CMD = "-d";
    private static final String LIST_CMD = "-l";

    private static final int TYPE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int KEY_INDEX = 2;

    private String[] command;
    private boolean isCmdList;
    private boolean isCmdEncipher;
    private String moduleName;
    private String key;

    public Command(String[] command) {
        this.command = command;
    }

    public void resolveInput(List<String> repo) throws InvalidCmdException, InvalidModuleNameException {
        if (isCmdList()) {
            isCmdList = true;
            isCmdEncipher = false;
        } else if (isCmdEncipher()) {
            isCmdList = false;
            isCmdEncipher = true;
            assignKey();
            assignModelName(repo);
        } else if (!isCmdEncipher() && !isCmdList()) {
            isCmdList = false;
            isCmdEncipher = false;
            assignKey();
            assignModelName(repo);
        } else {
            throw new InvalidCmdException("Invalid command! Enter -e|-d cipherName [KEY]");
        }
    }

    private void assignKey() {
        if (command.length > 2 && command[KEY_INDEX].matches("[0-9]{1,}")) {
            key = command[KEY_INDEX];
        }
    }

    private void assignModelName(List<String> repo) throws InvalidModuleNameException {
        for (String name : repo) {
            if (name.equalsIgnoreCase(command[NAME_INDEX])) {
                moduleName = name;
            }
        }
        if (moduleName == null) {
            throw new InvalidModuleNameException("There is no enigma type like that");
        }
    }


    private boolean isCmdList() {
        return this.command.length == 1 && this.command[TYPE_INDEX].equals(LIST_CMD);
    }

    private boolean isCmdEncipher() {
        return this.command.length > 1 && this.command[TYPE_INDEX].equals(ENCIPHER_CMD);
    }

}
