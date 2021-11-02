package com.zookao.test;

public class Single {

    private Single(){}

    private static Single instance;

    public static Single getInstance(){
        if (instance == null) {
            instance = new Single();
        }
        return instance;
    }
}
