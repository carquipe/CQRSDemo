package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserAddress {
    private Map<String, Set<Address>> addressByRegion = new HashMap<>();

    public Map<String, Set<Address>> getAddressByRegion() {
        return addressByRegion;
    }
}
