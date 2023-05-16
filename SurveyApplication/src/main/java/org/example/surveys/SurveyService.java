package org.example.surveys;

import org.example.surveys.data.DataStore;
import org.example.surveys.domain.Member;
import org.example.surveys.domain.Status;
import org.example.surveys.domain.Survey;

import java.util.HashSet;
import java.util.Set;

public class SurveyService {
    private final DataStore dataStore;

    public SurveyService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Set<Member> getMembersBySurveyId(final Long surveyId) {
        Set<Member> result = new HashSet<>();
        dataStore.getParticipants().stream().
                filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.COMPLETED))
                .forEach(participation -> result.add(dataStore.getMembers().get(participation.getMemberId())));
        return result;
    }

    public Set<Survey> getCompletedSurveysByMemberId(final Long memberId) {
        Set<Survey> result = new HashSet<>();
        dataStore.getParticipants().stream()
                .filter(participation -> participation.getMemberId().equals(memberId) && participation.getStatus().equals(Status.COMPLETED))
                .forEach(participation -> result.add(dataStore.getSurveys().get(participation.getSurveyId())));
        return result;
    }

    public Set<Member> getNotInvitedActiveMembers(final Long surveyId) {
        Set<Member> result = new HashSet<>();
        dataStore.getParticipants().stream()
                .filter(participation -> participation.getSurveyId().equals(surveyId) && participation.getStatus().equals(Status.NOT_ASKED))
                .forEach(participation -> {
                    Member member = dataStore.getMembers().get(participation.getMemberId());
                    if (member.isActive()) {
                        result.add(member);
                    }
                });
        return result;
    }
}
