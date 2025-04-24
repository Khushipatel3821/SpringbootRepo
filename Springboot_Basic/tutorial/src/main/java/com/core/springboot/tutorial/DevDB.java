package com.core.springboot.tutorial;

public class DevDB implements DB {

    @Override
    public String getData() {
        return "DEV DATA";
    }
}
