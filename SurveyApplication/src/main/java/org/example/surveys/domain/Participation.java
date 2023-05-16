package org.example.surveys.domain;

import lombok.Getter;

@Getter
public class Participation {
    private Long memberId;
    private Long surveyId;
    private Status status;
    private int length;
}
