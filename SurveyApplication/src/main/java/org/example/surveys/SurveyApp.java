package org.example.surveys;

import org.example.surveys.data.DataStore;
import org.example.surveys.service.SurveyService;

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