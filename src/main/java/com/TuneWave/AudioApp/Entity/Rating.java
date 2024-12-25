package com.TuneWave.AudioApp.Entity;

public enum Rating {
    POOR(1),
    FAIR(2),
    AVERAGE(3),
    GOOD(4),
    EXCELLENT(5);

    private int value;

    Rating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
};
