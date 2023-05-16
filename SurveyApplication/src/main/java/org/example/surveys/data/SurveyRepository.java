package org.example.surveys.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.surveys.domain.Survey;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SurveyRepository {
    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int EXPECTED_COMPLETES = 2;
    private static final int COMPLETED_POINTS = 3;
    private static final int FILTERED_POINTS = 4;
    private final Map<Long, Survey> surveys;

    public SurveyRepository() {
        this.surveys = new HashMap<>();
        readSurveysFromCSV();
    }

    public Map<Long, Survey> getSurveys() {
        return surveys;
    }

    private void readSurveysFromCSV() {
        try (CSVReader csvReader = new CSVReader(new FileReader("OO - 2 - Surveys.csv"))) {
            csvReader.readNext(); // skipping first line
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                createSurvey(line);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createSurvey(String[] line) {
        Survey survey = new Survey();
        survey.setId(Long.parseLong(line[ID]));
        survey.setName(line[NAME]);
        survey.setExpectedCompletes(Integer.parseInt(line[EXPECTED_COMPLETES]));
        survey.setCompletionPoints(Integer.parseInt(line[COMPLETED_POINTS]));
        survey.setFilteredPoints(Integer.parseInt(line[FILTERED_POINTS]));
        surveys.put(survey.getId(), survey);
    }
}
