package org.example.surveys.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.surveys.domain.Status;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StatusRepository {
    private static final int STATUS_ID = 0;
    private static final int NAME = 1;
    private static final String PATH = "src/main/resources/OO - 2 - Statuses.csv";
    private final Map<Integer, Status> statuses;

    public StatusRepository() {
        statuses = new HashMap<>();
        readStatusesFromCSV();
    }

    public Map<Integer, Status> getStatuses() {
        return statuses;
    }

    private void readStatusesFromCSV() {
        try (CSVReader csvReader = new CSVReader(new FileReader(PATH))) {
            csvReader.readNext(); // skipping first line
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (line[NAME].contains(" ")) {
                    statuses.put(Integer.parseInt(line[STATUS_ID]), Status.valueOf(line[NAME].toUpperCase().replace(" ", "_")));
                } else {
                    statuses.put(Integer.parseInt(line[STATUS_ID]), Status.valueOf(line[NAME].toUpperCase()));
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
