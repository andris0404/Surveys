package org.example.surveys.domain;

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
}
