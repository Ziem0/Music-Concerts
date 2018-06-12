package com.entertainment.enigma.modules;

import com.entertainment.enigma.exceptions.WrongKeyException;

public interface EnigmaService {
    String getName();
    String encipher(String input);
    String decipher(String input);
    Boolean isKeyRequired();
    void setKey(String key) throws WrongKeyException;
}
