/*
 * Micah Friedman
 * Copyright (C) 2023
 */

package math;

public class MathVariable {
    private String name;
    public String getName() {return name;}

    public MathVariable(String name) {
        this.name = name;
    }

    public boolean equals(MathVariable v) {
        return this.name.equals(v.name);
    }

    public boolean is(String name) {
        return this.name.equals(name);
    }
}