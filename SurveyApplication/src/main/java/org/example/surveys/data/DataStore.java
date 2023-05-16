package org.example.surveys.data;

import org.example.surveys.domain.Member;
import org.example.surveys.domain.Participation;
import org.example.surveys.domain.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
    private final Map<Long, Member> members;
    private final List<Participation> participants;
    private final Map<Long, Survey> surveys;

    public DataStore() {
        this.members = new HashMap<>();
        this.participants = new ArrayList<>();
        this.surveys = new HashMap<>();
    }

    public Map<Long, Member> getMembers() {
        return members;
    }

    public List<Participation> getParticipants() {
        return participants;
    }

    public Map<Long, Survey> getSurveys() {
        return surveys;
    }
}
