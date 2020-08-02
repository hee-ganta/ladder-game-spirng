package com.ladder.demo.status;

import org.springframework.stereotype.Component;


public class Pair {
    private int horizontal;

    private int vertical;

    public Pair(int a, int b){
        this.horizontal = a;
        this.vertical = b;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
}
