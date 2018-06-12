package com.entertainment.enigma.service;

import com.entertainment.enigma.modules.EnigmaService;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceRepository implements ServiceProvider, ServiceRegistry{

    private List<EnigmaService> services;

    public ServiceRepository() {
        this.services = new LinkedList<>();
    }

    /*
    ServiceRegistry
     */
    @Override
    public void register(EnigmaService service) {
        services.add(service);
    }

    /*
    ServiceProvider
     */
    @Override
    public List<String> listAll() {
        return services.stream().map(EnigmaService::getName).collect(Collectors.toList());
    }

    /*
    ServiceProvider
     */
    @Override
    public EnigmaService getByName(String name) {
        return (EnigmaService) services.stream().filter(a->a.getName().equals(name));
    }
}
