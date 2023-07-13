package org.example.patterns.singleton.demo5;

//Lazy
public class Singleton {

    //1.Private constructor
    private Singleton(){  }

    //2.static inner class
    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    //Public visit method
    public static Singleton getInstance(){

        return SingletonHolder.INSTANCE;

    }
}