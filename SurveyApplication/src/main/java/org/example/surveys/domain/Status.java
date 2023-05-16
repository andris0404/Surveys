package org.example.surveys.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Status {
    NOT_ASKED(1, "Not asked"),
    REJECTED(2, "Rejected"),
    FILTERED(3, "Filtered"),
    COMPLETED(4, "Completed");

    private final int key;
    private final String value;
}
