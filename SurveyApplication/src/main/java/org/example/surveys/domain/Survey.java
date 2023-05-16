package org.example.surveys.domain;

import lombok.Getter;

@Getter
public class Survey {
    private Long id;
    private String name;
    private int expectedCompletes;
    private int completionPoints;
    private int filteredPoints;
}
