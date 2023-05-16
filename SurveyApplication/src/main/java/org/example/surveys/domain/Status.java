package org.example.surveys.domain;

public enum Status {
    NOT_ASKED(1, "Not asked"),
    REJECTED(2, "Rejected"),
    FILTERED(3, "Filtered"),
    COMPLETED(4, "Completed");

    private final int key;
    private final String value;

    private Status(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
