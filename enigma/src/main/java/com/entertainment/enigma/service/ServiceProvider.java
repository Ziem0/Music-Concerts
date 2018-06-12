package com.entertainment.enigma.service;

import com.entertainment.enigma.modules.EnigmaService;

import java.util.List;

public interface ServiceProvider {
    List<String> listAll();
    EnigmaService getByName(String name);

}
