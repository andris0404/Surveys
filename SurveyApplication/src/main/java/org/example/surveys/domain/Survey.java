package org.example.surveys.domain;

import java.util.Objects;

public class Survey {
    private Long id;
    private String name;
    private int expectedCompletes;
    private int completionPoints;
    private int filteredPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpectedCompletes() {
        return expectedCompletes;
    }

    public void setExpectedCompletes(int expectedCompletes) {
        this.expectedCompletes = expectedCompletes;
    }

    public int getCompletionPoints() {
        return completionPoints;
    }

    public void setCompletionPoints(int completionPoints) {
        this.completionPoints = completionPoints;
    }

    public int getFilteredPoints() {
        return filteredPoints;
    }

    public void setFilteredPoints(int filteredPoints) {
        this.filteredPoints = filteredPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return expectedCompletes == survey.expectedCompletes
                && completionPoints == survey.completionPoints
                && filteredPoints == survey.filteredPoints
                && Objects.equals(id, survey.id)
                && Objects.equals(name, survey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expectedCompletes, completionPoints, filteredPoints);
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expectedCompletes=" + expectedCompletes +
                ", completionPoints=" + completionPoints +
                ", filteredPoints=" + filteredPoints +
                '}';
    }
}
