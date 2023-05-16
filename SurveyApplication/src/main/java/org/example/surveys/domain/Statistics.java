package org.example.surveys.domain;

public class Statistics {
    private Long surveyId;
    private String surveyName;
    private int numOfCompletes;
    private int numOfFiltered;
    private int numOfRejected;
    private int avgLength;

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public int getNumOfCompletes() {
        return numOfCompletes;
    }

    public void setNumOfCompletes(int numOfCompletes) {
        this.numOfCompletes = numOfCompletes;
    }

    public int getNumOfFiltered() {
        return numOfFiltered;
    }

    public void setNumOfFiltered(int numOfFiltered) {
        this.numOfFiltered = numOfFiltered;
    }

    public int getNumOfRejected() {
        return numOfRejected;
    }

    public void setNumOfRejected(int numOfRejected) {
        this.numOfRejected = numOfRejected;
    }

    public int getAvgLength() {
        return avgLength;
    }

    public void setAvgLength(int avgLength) {
        this.avgLength = avgLength;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "surveyId=" + surveyId +
                ", surveyName='" + surveyName + '\'' +
                ", numOfCompletes=" + numOfCompletes +
                ", numOfFiltered=" + numOfFiltered +
                ", numOfRejected=" + numOfRejected +
                ", avgLength=" + avgLength +
                '}';
    }
}
