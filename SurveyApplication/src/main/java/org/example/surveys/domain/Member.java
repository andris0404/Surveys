package org.example.surveys.domain;

import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private String name;
    private String email;
    private boolean isActive;
}
