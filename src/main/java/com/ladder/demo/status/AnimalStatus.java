package com.ladder.demo.status;

import org.springframework.stereotype.Component;

@Component
public class AnimalStatus {

    private int animal1;

    private int animal2;

    private int animal3;

    private int animal4;

    public int getAnimal1() {
        return animal1;
    }

    public void setAnimal1(int animal1) {
        this.animal1 = animal1;
    }

    public int getAnimal2() {
        return animal2;
    }

    public void setAnimal2(int animal2) {
        this.animal2 = animal2;
    }

    public int getAnimal3() {
        return animal3;
    }

    public void setAnimal3(int animal3) {
        this.animal3 = animal3;
    }

    public int getAnimal4() {
        return animal4;
    }

    public void setAnimal4(int animal4) {
        this.animal4 = animal4;
    }
}
