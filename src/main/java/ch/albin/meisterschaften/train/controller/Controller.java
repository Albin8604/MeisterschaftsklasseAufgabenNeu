package ch.albin.meisterschaften.train.controller;

public interface Controller {
    default void init(){
        throw new UnsupportedOperationException();
    }
}
