package org.example.surveys.domain;

public enum Status {
    NOT_ASKED(1),
    REJECTED(2),
    FILTERED(3),
    COMPLETED(4);

    private final int key;

    private Status(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
