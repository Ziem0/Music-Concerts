package com.entertainment.enigma.controllers;

import com.entertainment.enigma.service.ServiceProvider;

public interface Module {

    void initialize(ServiceProvider serviceProvider);
    String getName();
    void start();
}
