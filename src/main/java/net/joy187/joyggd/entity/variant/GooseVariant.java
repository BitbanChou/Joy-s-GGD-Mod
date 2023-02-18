package net.joy187.joyggd.entity.variant;


import java.util.Arrays;
import java.util.Comparator;

public enum GooseVariant {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5);

    private static final GooseVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(GooseVariant::getId)).toArray(GooseVariant[]::new);
    private final int id;

    GooseVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static GooseVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}