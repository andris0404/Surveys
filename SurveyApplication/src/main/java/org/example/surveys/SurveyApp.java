package org.example.surveys;

import org.example.surveys.data.DataStore;

public class SurveyApp {
    private final SurveyService surveyService;
    private final DataStore dataStore;

    public SurveyApp() {
        dataStore = new DataStore();
        surveyService = new SurveyService(dataStore);
    }

    public static void main(String[] args) {
        SurveyApp app = new SurveyApp();
    }
}