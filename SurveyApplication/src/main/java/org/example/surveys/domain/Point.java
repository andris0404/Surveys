package org.example.surveys.domain;

import java.util.Objects;

public class Point {
    private Long surveyId;
    private int numOfPoints;

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return getNumOfPoints() == point.getNumOfPoints() && Objects.equals(getSurveyId(), point.getSurveyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurveyId(), getNumOfPoints());
    }

    @Override
    public String toString() {
        return "Point{" +
                "surveyId=" + surveyId +
                ", numOfPoints=" + numOfPoints +
                '}';
    }
}
