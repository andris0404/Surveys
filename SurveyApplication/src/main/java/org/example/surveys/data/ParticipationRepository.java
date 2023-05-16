package org.example.surveys.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.surveys.domain.Participation;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipationRepository {
    private final List<Participation> participants;

    public ParticipationRepository() {
        participants = new ArrayList<>();
    }

    public List<Participation> getParticipants() {
        return participants;
    }

    private void readParticipantsFromCSV() {
        Map<Integer, String> statuses = readStatusesFromCSV();
        try (CSVReader csvReader = new CSVReader(new FileReader("OO - 2 - Participation.csv"))) {
            csvReader.readNext(); // skipping first line
            String[] line;
            while ((line = csvReader.readNext()) != null) {
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, String> readStatusesFromCSV() {
        Map<Integer, String> result = new HashMap<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("OO - 2 - Statuses.csv"))) {
            csvReader.readNext(); // skipping first line
            String[] line;
            while ((line = csvReader.readNext()) != null) {

            }
            return result;
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
