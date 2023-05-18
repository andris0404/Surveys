package org.example.surveys.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.surveys.domain.Participation;
import org.example.surveys.domain.Status;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParticipationRepository {
    private static final int MEMBER_ID = 0;
    private static final int SURVEY_ID = 1;
    private static final int STATUS = 2;
    private static final int LENGTH = 3;
    private static final String PATH = "src/main/resources/OO - 2 - Participation.csv";
    private final List<Participation> participants;
    private final StatusRepository statusRepository;

    public ParticipationRepository() {
        participants = new ArrayList<>();
        statusRepository = new StatusRepository();
        readParticipantsFromCSV();
    }

    public List<Participation> getParticipants() {
        return participants;
    }

    private void readParticipantsFromCSV() {
        try (CSVReader csvReader = new CSVReader(new FileReader(PATH))) {
            csvReader.readNext(); // skipping first line
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                createParticipation(line, statusRepository.getStatuses());
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createParticipation(final String[] line, final Map<Integer, Status> statuses) {
        Participation participation = new Participation();
        participation.setMemberId(Long.parseLong(line[MEMBER_ID]));
        participation.setSurveyId(Long.parseLong(line[SURVEY_ID]));
        participation.setStatus(statuses.get(Integer.parseInt(line[STATUS])));
        participation.setLength("".equals(line[LENGTH]) ? 0 : Integer.parseInt(line[LENGTH]));
        participants.add(participation);
    }
}
